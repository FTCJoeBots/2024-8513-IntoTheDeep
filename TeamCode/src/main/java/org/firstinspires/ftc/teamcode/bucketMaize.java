
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class bucketMaize
{
    //contants
    public static final double BUCKETCLOSED = 0.2;
    public static final double BUCKETOPENED = 0.4;

    //variables
    Servo bucketServo;

    //init
    public void init (HardwareMap hwmap) {
        //get the servo from the controlhub
        bucketServo=hwmap.get(Servo.class,"bucketServo");
        bucketServo.setDirection(Servo.Direction.REVERSE);
        bucketServo.setPosition(12);

        //to set an init position
    }
    //methods
    public void openbucket () {
        bucketServo.setPosition(BUCKETOPENED);
    }
    public void closedBucket() {
        bucketServo.setPosition(BUCKETCLOSED);
    }


}
