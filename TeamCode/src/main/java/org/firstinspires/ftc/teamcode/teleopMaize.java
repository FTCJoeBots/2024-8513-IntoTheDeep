package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@TeleOp(name="TeleOp Maize")


public class teleopMaize extends OpMode {
    boolean WHICHTEAM=false;   //0 is red,1 is blue
    double forward=0;
    double strafe=0;
    double rotate=0;
    boolean a_state = false;
    boolean a_prev = false;
    boolean b_state=false;
    boolean b_prev = false;
    public final double MAXSPEED = .3;
    TeleOpMecanum d = new TeleOpMecanum();
    Intake8513 i = new Intake8513();
    Lift l = new Lift();
    horizantalSlide h= new horizantalSlide();
    clawMaize c= new clawMaize();
    bucketMaize b= new bucketMaize();



    public void init(){
        d.init(hardwareMap);
        //i.init(hardwareMap);
        l.init(hardwareMap);
        h.init(hardwareMap);
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

        /*
        if(i.getSampleColor()>0){
            i.stopintake();
            //get rid of it if we have a red and we're the blue team
            if((i.getSampleColor()==1)&&(WHICHTEAM)){
                i.startintake();
            }
            //get rid of it if we have a blue and we're the red team
            if((i.getSampleColor()==3)&&(!WHICHTEAM)){
                i.startintake();
            }
        }
*/


        //bucket
        if (gamepad1.b) {
            c.openClaw();
        }


        forward=gamepad1.left_stick_y*-MAXSPEED;
        rotate = -gamepad1.right_stick_x*MAXSPEED;
        strafe = gamepad1.left_trigger*MAXSPEED-gamepad1.right_trigger*MAXSPEED;
        //fix...put the object in this directory
        d.driveMecanum(forward,rotate,strafe);


      /*  if (gamepad2.a && !a_prev) {
            if (!a_state) {
                i.startintake();
                a_state = true;
            } else {

                i.stopintake();
                a_state = false;
            }
            a_prev=true;
        }

        if (gamepad2.b && !b_prev) {
            if (!b_state) {
                c.openClaw();
                b_state = true;
            } else {
                c.closedClaw();
                b_state = false;
            }
            b_prev=true;
        }*/






        if(gamepad2.dpad_up){
            l.liftGo(3);

        }

        if(gamepad2.dpad_down){
            l.liftGo(1);
        }

        if(gamepad2.dpad_right){
            l.liftGo(2);
        }
        if(gamepad2.dpad_left) {
            l.liftGo(4);
        }

        if(gamepad2.left_stick_y>-0.15) {
            h.horizontalSlideManualIn();
        }  if(gamepad2.left_stick_y>0.15) {
            h.horizontalSlideManualOut();
        }

        telemetry.addData("Lift position:",l.rightLiftMotor.getCurrentPosition());
        telemetry.addData("Slide position:",h.slideMotor.getCurrentPosition());

        telemetry.update();
    }







}

