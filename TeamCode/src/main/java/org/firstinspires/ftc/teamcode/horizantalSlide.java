package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class horizantalSlide {
     public static final int MINMIUMSLIDEPOSITION = 20;
    public static final int INITIALSLIDEPOSITION = 350;
     public static final int MAXIMUMSLIDEPOSITION = 2325;
     public static final double HORIZONTIALSLIDESPEED = 1;
    public static int MANUALSLIDEINCREMENT = 97;
    public static double MANUALSLIDEINCREMENT2 = 95;



    DcMotor slideMotor = null;
     public void init (HardwareMap hwmap) {
         slideMotor = hwmap.get(DcMotor.class,"slidemotor");
         slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
         slideMotor.setPower(0);
         slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
         slideMotor.setTargetPosition(0);
         slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        /* slideMotor.setTargetPosition(INITIALSLIDEPOSITION);
         slideMotor.setPower(HORIZONTIALSLIDESPEED);*/


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
        if (currentSlidePosition-MANUALSLIDEINCREMENT > MINMIUMSLIDEPOSITION) {
            newSlidePosition = currentSlidePosition-MANUALSLIDEINCREMENT;
            slideMotor.setTargetPosition(newSlidePosition);
            slideMotor.setPower(HORIZONTIALSLIDESPEED);
        }
    }

    public void horizontalSlideJoystick (double yPos) {
        int currentSlidePosition = 0;
        MANUALSLIDEINCREMENT2 = yPos * 10;
        int newSlidePosition = 0;
        currentSlidePosition = slideMotor.getCurrentPosition();
        int blank = Math.toIntExact(Math.round(currentSlidePosition-MANUALSLIDEINCREMENT2));
        if (MINMIUMSLIDEPOSITION < blank && blank < MAXIMUMSLIDEPOSITION) {
            newSlidePosition = Math.toIntExact(Math.round(currentSlidePosition-MANUALSLIDEINCREMENT2));
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

    public void horizontalSlideDirectSLOW(int position)
    {
        if ((position<MAXIMUMSLIDEPOSITION )&&(position>MINMIUMSLIDEPOSITION)) {

            slideMotor.setTargetPosition(position);
            slideMotor.setPower(0.6);


        }
    }





    public int returnEncoderPosition(){
        return slideMotor.getCurrentPosition();
    }

    public class MediumSlidePosition implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            horizontalSlideDirect(450);
            return false;}
    }
    public Action Med() {
        return new MediumSlidePosition();
    }



    public class BackIn implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            horizontalSlideDirect(21);
            return false;}
    }
    public Action Back() {
        return new BackIn();
    }

    public class MoreOUt implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            horizontalSlideDirectSLOW(1550);
            return false;}
    }
    public Action More() {
        return new MoreOUt();
    }
    public class FarOut implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            horizontalSlideDirectSLOW(2300);
            return false;}
    }
    public Action Far() {
        return new FarOut();
    }



}
