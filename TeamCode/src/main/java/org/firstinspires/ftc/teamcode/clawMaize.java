
package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class clawMaize
{
    //contants
    public static final double CLAWCLOSED = 0.7;
    public static final double CLAWOPENED = 0.6;

    //variables
    Servo clawServo = null;

    //init
    public void init (HardwareMap hwmap) {
        //get the servo from the controlhub
        //rightLiftMotor = hwmap.get(DcMotor.class,"rightLiftMotor");

        clawServo=hwmap.get(Servo.class, "clawServo");
        closedClaw();
    }

    //methods
    public void openClaw () {
        clawServo.setPosition(CLAWOPENED);
    }
    public void closedClaw() {
        clawServo.setPosition(CLAWCLOSED);
    }

    public class OpenClaw implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            openClaw();
            return false;}
    }
    public Action ClawOpen() {
        return new OpenClaw();
    }
}
