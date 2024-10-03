package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@TeleOp(name="TeleOp Maize")


public class teleopMaize extends OpMode {
    double forward=0;
    double strafe=0;
    double rotate=0;
    boolean a_state = false;
    boolean a_prev = false;
    public final double MAXSPEED = .3;
    HardwareMap hwmap  = null;
    MecanumDrive d = new SampleMecanumDrive(hwmap);
    Intake8513 i = new Intake8513();
    Lift l = new Lift();


    public void init(){
        //d.init(hwmap);
    }

    @Override
    public void loop() {
        forward=gamepad1.left_stick_y*-MAXSPEED;
        rotate = gamepad1.right_stick_x*MAXSPEED;
        strafe = gamepad1.left_trigger*MAXSPEED-gamepad1.right_trigger*MAXSPEED;
        //fix...put the object in this directory
        //d.driveMecanum(forward,strafe,rotate);


        if (gamepad2.a && !a_prev) {
            if (a_state == false) {
                i.startintake();
                a_state = true;
            } else {

                i.stopintake();
                a_state = false;
            }

        }
        a_prev = true;

        if(gamepad2.dpad_up){
            l.liftmanualup();

        }

        if(gamepad2.dpad_down){
            l.liftmanualdown();
        }


    }
}

