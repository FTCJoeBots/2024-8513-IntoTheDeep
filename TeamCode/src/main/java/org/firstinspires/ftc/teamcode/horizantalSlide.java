package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class horizantalSlide {
     public static final int MINMIUMSLIDEPOSITION = 10;
     public static final int MAXIMUMSLIDEPOSITION = 2900;
     public static final double HORIZONTIALSLIDESPEED = .2;
     public static final int MANUALSLIDEINCREMENT = 10;


    DcMotor slideMotor = null;
     public void init (HardwareMap hwmap) {
         slideMotor = hwmap.get(DcMotor.class,"slidemotor");
         slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
         slideMotor.setPower(0);
         slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
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
         slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         slideMotor.setPower(HORIZONTIALSLIDESPEED);
         }
    }


    public void horizontalSlideManualIn () {
        int currentSlidePosition = 0;
        int newSlidePosition = 0;
        currentSlidePosition = slideMotor.getCurrentPosition();
        if (currentSlidePosition-MANUALSLIDEINCREMENT > MINMIUMSLIDEPOSITION) {
            newSlidePosition = currentSlidePosition-MANUALSLIDEINCREMENT;
            slideMotor.setTargetPosition(newSlidePosition);
            slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
