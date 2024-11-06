package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@TeleOp(name="TeleOp Maize")


public class teleopMaize extends OpMode {
    boolean WHICHTEAM=false;   //0 is red,1 is blue
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
    public final double MAXSPEED = .3;
    TeleOpMecanum d = new TeleOpMecanum();
    Intake8513 i = new Intake8513();
    Lift l = new Lift();
    horizantalSlide h= new horizantalSlide();
    clawMaize c= new clawMaize();
    bucketMaize b= new bucketMaize();



    public void init(){
        d.init(hardwareMap);
        h.init(hardwareMap);
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

        if (gamepad2.right_bumper && !rb_prev) {
            if (!rb_state) {
                i.intakeUp();
                rb_state = true;
            } else {
                i.intakeDown();
                rb_state = false;
            }

        }
        rb_prev=gamepad2.right_bumper;

        //Toggle intake off of "Y"
        if (gamepad2.y && !y_prev) {
            if (!y_state) {
                i.startintake();
                y_state = true;
            } else {
                i.stopintake();
                y_state = false;
            }

        }
        y_prev=gamepad2.y;

        if (gamepad2.x && !x_prev) {
            if (!x_state) {
                i.reverseintake();
                x_state = true;
            } else {
                i.stopintake();
                x_state = false;
            }

        }
        x_prev=gamepad2.x;








        //Operator Stuff; Lift (Vertical & Horizontal)

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

       /* if(gamepad2.left_stick_y<-0.15) {
            h.horizontalSlideJoystick(gamepad2.left_stick_y);
        }  if(gamepad2.left_stick_y>0.15) {
            h.horizontalSlideJoystick(gamepad2.left_stick_y);
        }*/
if (gamepad2.left_stick_y<-.15) {
    h.horizontalSlideManualOut();
}
        if (gamepad2.left_stick_y>.15) {
            h.horizontalSlideManualIn();
        }
       // telemetry.addData("distance",i.colorSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Lift position:",l.rightLiftMotor.getCurrentPosition());
        telemetry.addData("Slide position:",h.slideMotor.getCurrentPosition());
        telemetry.addData("left stick:",gamepad2.left_stick_y);
        telemetry.update();
    }







}

