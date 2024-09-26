package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class horizantalSlide {
     public static final int MINMIUMSLIDEPOSITION = 20;
     public static final int MAXIMUMSLIDEPOSITION = 111;
     public static final double HORIZONTIALSLIDESPEED = .2;
     public static final int MANUALSLIDEINCREMENT = 10;


    DcMotor slideMotor = null;
     public void init () {
         //hwgit
         slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
         slideMotor.setPower(0);
         slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         //slideMotor.setMode(DcMotor.ZeroPowerBehavior.FLOAT);
         slideMotor.setTargetPosition(MINMIUMSLIDEPOSITION);
         slideMotor.setPower(HORIZONTIALSLIDESPEED);


     }

    public void horizontalSlideManualOut () {
         int currentSlidePosition = 0;
         int newSlidePosition = 0;
         currentSlidePosition = slideMotor.getCurrentPosition();
         if (currentSlidePosition+MANUALSLIDEINCREMENT < MAXIMUMSLIDEPOSITION) {
         newSlidePosition = currentSlidePosition+MANUALSLIDEINCREMENT;
         slideMotor.setTargetPosition(newSlidePosition);
         slideMotor.setPower(HORIZONTIALSLIDESPEED);
         }
    }


    public void horizontalSlideManualIn () {
        int currentSlidePosition = 0;
        int newSlidePosition = 0;
        currentSlidePosition = slideMotor.getCurrentPosition();
        if (currentSlidePosition-MANUALSLIDEINCREMENT < MINMIUMSLIDEPOSITION) {
            newSlidePosition = currentSlidePosition-MANUALSLIDEINCREMENT;
            slideMotor.setTargetPosition(newSlidePosition);
            slideMotor.setPower(HORIZONTIALSLIDESPEED);
        }
    }

    public void horizontalSlideDirect(int position)
    {
       if ((position<MAXIMUMSLIDEPOSITION )&&(position>MINMIUMSLIDEPOSITION)) {

           slideMotor.setTargetPosition(position);
           slideMotor.setPower(HORIZONTIALSLIDESPEED);


       }
    }

}
