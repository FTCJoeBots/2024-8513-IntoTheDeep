package org.firstinspires.ftc.teamcode;
import android.app.Notification;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Intake8513 {


    public static final double INTAKESPEED = -.3;

    public static final double INTAKEDOWN = .23;
    public static final double INTAKEUP = .98;
    int sample_color=-1;
    final float[] hsvValues = new float[3];
    CRServo leftIntakeServo = null;
    CRServo rightIntakeServo = null;
    Servo wristServo = null;
    NormalizedColorSensor colorSensor;

    public void init(HardwareMap hwmap){
       colorSensor = hwmap.get(NormalizedColorSensor.class, "ColorSensor");
       leftIntakeServo = hwmap.get(CRServo.class,"leftIntake");
       rightIntakeServo = hwmap.get(CRServo.class,"rightIntake");
        wristServo=hwmap.get(Servo.class, "wristServo");
        //colorSensor.setGain(2);
       stopintake();
        wristServo.setPosition(INTAKEUP);
    }
    public void intakeUp(){
        wristServo.setPosition(INTAKEUP);

    }

    public void intakeDown(){
        wristServo.setPosition(INTAKEDOWN);

    }
// push slide put and drop intake wrist
    void stopintake (){
        leftIntakeServo.setPower(0);
        rightIntakeServo.setPower(0);


    }


    void startintake (){
        leftIntakeServo.setPower(INTAKESPEED);
        rightIntakeServo.setPower(-INTAKESPEED);
        sample_color=getSampleColor();
        //if sample is red/blue or yellow, stop
        //otherwise spit it out

    }

    void reverseintake (){
        leftIntakeServo.setPower(-INTAKESPEED);
        rightIntakeServo.setPower(INTAKESPEED);

    }

public int getSampleColor (){
    NormalizedRGBA colors = colorSensor.getNormalizedColors();
    Color.colorToHSV(colors.toColor(), hsvValues);

    if(((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM) < 3) {
       if ((hsvValues[0] < 46) || (hsvValues[0] > 300)) {//Red
           return 1;
       } else if ((hsvValues[0] < 150) && (hsvValues[0] > 47)) {//Yellow
           return 2;
       } else if ((hsvValues[0] < 255) && (hsvValues[0] > 150)) {//Blue
           return 3;
       } else {
           return -1;
       }
   }else {
       return -1;
   }

    //return color (as integer)
/*
Red is 0 to 20   1
Yellow is 30 to 60    2
Blue is 210 to 270    3

*/

}



        public class upIn implements Action {
            public boolean loop(TelemetryPacket packet) {return false;}
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                intakeUp();
                return false;}
        }
        public Action Up() {
            return new upIn();
        }




    public class downIn implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intakeDown();
            return false;}
    }
    public Action Down() {
        return new downIn();
    }



    public class startInColorSensor implements Action {


        public void init() {}
        public boolean loop(TelemetryPacket packet) {

            return false;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            startintake();
            while(getSampleColor() < 0){
               // stopintake();
            }
            stopintake();

            return false;
        }
    }

    public class startIn implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            startintake();
            return false;}
    }
    public Action start() {
        return new startIn();
    }

    public Action startInWithColorSensor() {
        return new startInColorSensor();
    }


    public class stopIn implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            stopintake();
            return false;}
    }
    public Action stop() {
        return new stopIn();
    }


    public class reverseIn implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            reverseintake();
            return false;}
    }
    public Action reverse() {
        return new reverseIn();
    }





    }
