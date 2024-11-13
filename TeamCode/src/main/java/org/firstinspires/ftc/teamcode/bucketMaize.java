
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class bucketMaize
{
    //contants
    public static final double BUCKETCLOSED = 0.07;
    public static final double BUCKETOPENED = 0.91;

    //variables
    Servo bucketServo;

    //init
    public void init (HardwareMap hwmap) {
        //get the servo from the controlhub
        bucketServo=hwmap.get(Servo.class,"bucketServo");
bucketServo.setPosition(BUCKETCLOSED);
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
