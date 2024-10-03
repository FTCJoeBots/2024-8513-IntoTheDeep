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
    TeleOpMecanum d = new TeleOpMecanum();
    Intake8513 i = new Intake8513();
    Lift l = new Lift();
    horizantalSlide h = new horizantalSlide();



    public void init(){
        d.init(hardwareMap);
        i.init(hardwareMap);
        l.init(hardwareMap);
        h.init(hardwareMap);

    }

    @Override
    public void loop() {
        forward=gamepad1.left_stick_y*-MAXSPEED;
        rotate = gamepad1.right_stick_x*MAXSPEED;
        strafe = gamepad1.left_trigger*MAXSPEED-gamepad1.right_trigger*MAXSPEED;
        d.driveMecanum(forward,strafe,rotate);


        //STOP THE INTAKE IF OUR COLOR SENSOR HAS SOMETHING
        //   In this case, sample_color will return 1,2, or 3 if it has a color
        //   Otherwise, it will return -1
        //   If we get something bigger than 0, we have a color
        if (i.sample_color>0) {
            i.stopintake();
            //If it's yellow or the color we're looking for, we're good

            //If it's one we don't want, we need to yeet this backwards
        }



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

