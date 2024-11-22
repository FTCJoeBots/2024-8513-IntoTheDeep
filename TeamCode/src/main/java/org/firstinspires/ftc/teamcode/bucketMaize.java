
package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class bucketMaize
{
    //contants
    public static final double BUCKETCLOSED = 0.05;
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





    public class liftBucket implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            openbucket();
            return false;}
    }
    public Action DropBlock() {
        return new liftBucket();
    }



    public class DownBucket implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            closedBucket();
            return false;}
    }
    public Action Rest() {
        return new DownBucket();
    }


}
