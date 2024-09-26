package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
public class Intake8513 {

    Servo leftIntakeServo = null;
    Servo rightIntakeServo = null;
    NormalizedColorSensor colorSensor;

    void init (){
        //hardwaremap
        leftIntakeServo.setPosition(0);
        rightIntakeServo.setPosition(0);
    }

    void stopintake (){

        ///stopintake

    }


    void startintake (){

        ///startintake

    }

    void reverseintake (){

        ///reverseintake

    }

void getSampleColor (){
    NormalizedRGBA colors = colorSensor.getNormalizedColors();


}



}
