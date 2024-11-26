package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.ConfigRR.TeleOpMecanum;


@TeleOp(name="TeleOp Maize | Blues ")


public class teleopMaize_Blue extends OpMode {
    boolean WHICHTEAM=true;   //0 is red,1 is blue
    double forward=0;
    double strafe=0;
    double rotate=0;

    boolean rb_state = false;
    boolean rb_prev = false;
    boolean y_state = false;
    boolean y_prev = false;
    boolean x_state = false;
    boolean x_prev = false;
    boolean lb_state=false;
    boolean lb_prev = false;
    boolean a2_prev = false;
    boolean a2_state = false;
    boolean a_prev = false;
    boolean a_state = false;
    public double MAXSPEED = .75;
    TeleOpMecanum d = new TeleOpMecanum();
    Intake8513 i = new Intake8513();
    Lift l = new Lift();
    horizantalSlide h= new horizantalSlide();
    HangerMaize H = new HangerMaize();
    clawMaize c= new clawMaize();
    bucketMaize b= new bucketMaize();




    public void init(){
        d.init(hardwareMap);
        h.init(hardwareMap);
        H.init(hardwareMap);
        i.init(hardwareMap);
        l.init(hardwareMap);
        b.init(hardwareMap);
        c.init(hardwareMap);
        telemetry.addLine("init complete");
        telemetry.update();
    }

    @Override
    public void loop() {
        // 1 - red
        // 2 - yellow
        //3 -blue
        //WHICHTEAM=false = red  true=blue
        //*****Refactor this to an enumerated variable for readability

        //Sample
        // telemetry.addData("GetSampleColor:",i.hsvValues[0]);
        if(i.getSampleColor()>0 && !rb_state){
            i.stopintake();
            //get rid of it if we have a red and we're the blue team
            if((i.getSampleColor()==1)&&(WHICHTEAM)){
                i.startintake();
                y_state = false;
                telemetry.addLine("ColorSensor: Red|BlueTeam");
                telemetry.update();

            }
            //get rid of it if we have a blue and we're the red team
            if((i.getSampleColor()==3)&&(!WHICHTEAM)){
                i.startintake();
                y_state = false;
                telemetry.addLine("ColorSensor: Blue|RedTeam");
                telemetry.update();
            }
        }







// toggle this


        //hold
        if (gamepad2.right_trigger>0.3){
            b.openbucket();
        } else {
            b.closedBucket();
        }

        forward=gamepad1.left_stick_y*-MAXSPEED;
        rotate = -gamepad1.right_stick_x*MAXSPEED;
        strafe = gamepad1.left_trigger*MAXSPEED-gamepad1.right_trigger*MAXSPEED;
        //fix...put the object in this directory
        d.driveMecanum(forward,rotate,strafe);


        if (gamepad1.b && !a_prev) {
            if(!a_state) {
                MAXSPEED = 0.3;
                a_state = true;
            } else {
                MAXSPEED = 0.85;
                a_state = false;

            }

        }

        if (gamepad1.right_bumper) {
            H.hangerautoup();
        }
        if (gamepad1.left_bumper) {
            H.hangerautodown();
        }

        if (gamepad2.left_bumper && !lb_prev) {
            if (!lb_state) {
                c.openClaw();
                lb_state = true;
            } else {
                c.closedClaw();
                lb_state = false;
            }

        }
        lb_prev=gamepad2.left_bumper;





        if (gamepad1.a) {
            H.hangUp();
        }



        if (gamepad2.right_bumper && !rb_prev) {
            if (!rb_state) {
                i.intakeUp();
                rb_state = true;
                i.stopintake();
            } else {
                i.intakeDown();
                rb_state = false;
                // i.startintake();
            }

        }
        rb_prev=gamepad2.right_bumper;

        //Toggle intake off of "Y"
        if (gamepad2.y && !y_prev) {
            if (!y_state) {
                i.startintake();
                telemetry.addLine("Intake: ON FORWARD");
                telemetry.update();
                y_state = true;
            } else {
                i.stopintake();
                y_state = false;
                telemetry.addLine("Intake: OFF");
                telemetry.update();
            }

        }
        y_prev=gamepad2.y;

        if (gamepad2.x && !x_prev) {
            if (!x_state) {
                i.reverseintake();
                x_state = true;
                telemetry.addLine("Intake: ON REVERSE");
                telemetry.update();
            } else {
                i.stopintake();
                x_state = false;
                telemetry.addLine("Intake: OFF");
                telemetry.update();
            }

        }
        x_prev=gamepad2.x;


        if (gamepad2.left_stick_button) {


            h.horizontalSlideDirect(21);
        }
//automatically puts wrist up
        if (h.returnEncoderPosition() < 150){

            i.intakeUp();
            rb_state = true;


        }




        //Operator Stuff; Lift (Vertical)

        if(gamepad2.dpad_up){
            l.liftGo(3);
            i.stopintake();
        }

        if(gamepad2.dpad_down){
            l.liftGo(1);

        }

        if(gamepad2.dpad_right){
            l.liftGo(2);
            i.stopintake();
        }
        if(gamepad2.dpad_left) {
            l.liftGo(4);
            i.stopintake();
        }



        //manual intake sliders
        if (gamepad2.left_stick_y<-.15) {
            h.horizontalSlideManualOut();
        }
        if (gamepad2.left_stick_y>.15) {
            h.horizontalSlideManualIn();
        }

        if (gamepad2.right_stick_y>.15) {
            l.liftmanualdown();
        }

        if (gamepad2.right_stick_y<-.15) {
            l.liftmanualup();
        }


        if (gamepad2.a && !x_prev) {
            if (!x_state) {
                i.reverseintake();
                x_state = true;
                telemetry.addLine("Intake: ON REVERSE");
                telemetry.update();
            } else {
                i.stopintake();
                x_state = false;
                telemetry.addLine("Intake: OFF");
                telemetry.update();
            }

        }


        // telemetry.addData("distance",i.colorSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Lift position:",l.rightLiftMotor.getCurrentPosition());
        telemetry.addData("Slide position:",h.slideMotor.getCurrentPosition());
        telemetry.addData("left stick:",gamepad2.left_stick_y);
        telemetry.addData("Hanger encoder:",H.hangerMotor.getCurrentPosition());
        telemetry.addData("lift",gamepad2.right_stick_y);

        telemetry.update();



    }







}

