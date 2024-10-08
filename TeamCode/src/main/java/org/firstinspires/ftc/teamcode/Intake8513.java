package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
public class Intake8513 {


    public static final double INTAKESPEED = .2;
    int sample_color=-1;
    final float[] hsvValues = new float[3];
    CRServo leftIntakeServo = null;
    CRServo rightIntakeServo = null;
    NormalizedColorSensor colorSensor;

    void init (HardwareMap hwmap){
       colorSensor = hwmap.get(NormalizedColorSensor.class, "ColorSensor");
       leftIntakeServo = hwmap.get(CRServo.class,"leftintake");
       leftIntakeServo = hwmap.get(CRServo.class,"rightintake");
       stopintake();
    }

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
    if (hsvValues[0]<20) {
        return 1;
    } else if ((hsvValues[0]<60)&&(hsvValues[0]>30)) {
        return 2;
    }
    else if ((hsvValues[0]<270)&&(hsvValues[0]>210)) {
        return 3;
    }
    else {
        return -1;
    }


    //return color (as integer)
/*
Red is 0 to 20   1
Yellow is 30 to 60    2
Blue is 210 to 270    3

*/

}



}
