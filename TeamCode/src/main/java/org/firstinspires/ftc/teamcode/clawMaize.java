
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class clawMaize
{
    //contants
    public static final double CLAWCLOSED = 0.2;
    public static final double CLAWOPENED = 0.4;

    //variables
    Servo clawServo = null;

    //init
    public void init (HardwareMap hwmap) {
        clawServo=hwmap.get(Servo.class,"ClawServo");
    }
    //methods
    public void openClaw () {
        clawServo.setPosition(CLAWOPENED);
    }
    public void closedClaw() {
        clawServo.setPosition(CLAWCLOSED);
    }


}
