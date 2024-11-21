package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HangerMaize {
    //constants
    public static final int HANGERLOWPOINT = 10;
    public static final int HANGERHANGS = 2600;
    public static final double HANGERSPEED = .7;
    public static final int HANGERHIGHPOINT = 3801;

    public static final double HANGERUP = 0.8;
    public static final double HANGERDOWN = 0;
    public static final double HANGERHANG = 0.4;

    //variables
    DcMotor hangerMotor = null;
    Servo hangerServo = null;
    ///methods


    //init
    public void init(HardwareMap hwmap) {
        hangerMotor = hwmap.get(DcMotor.class, "hangerMotor");
        hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hangerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangerMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        hangerMotor.setPower(0);
        hangerServo = hwmap.get(Servo.class, "hangerServo");
        hangerautodown();
       // hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hangerautodown();
    }

    public void handDown () {
        hangerServo.setPosition(HANGERDOWN);
    }
    public void hangUp() {
        hangerServo.setPosition(HANGERUP);

    }

    public void hangHangs() {
        hangerServo.setPosition(HANGERHANG);

    }
    //auto down
    public void hangerautodown()
    {

        hangerMotor.setTargetPosition(HANGERLOWPOINT);
        hangerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangerMotor.setPower(HANGERSPEED);

    }
    //auto up

    public void hangerautoup() {

        hangerMotor.setTargetPosition(HANGERHIGHPOINT);
        hangerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangerMotor.setPower(HANGERSPEED);
    }


    public void hangerToPos(int var) {

        hangerMotor.setTargetPosition(var);
        hangerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangerMotor.setPower(HANGERSPEED);

    }
    public void hangerGo(int pos){
        switch (pos){
            case 1:
                hangerToPos(HANGERLOWPOINT);
                break;
            case 2:
                hangerToPos(HANGERHANGS);
                break;
            case 3:
                hangerToPos(HANGERHIGHPOINT);
                break;

        }



    }
    public class UpHanger implements Action {
        public boolean loop(TelemetryPacket packet) {return false;}
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            hangerToPos(HANGERHIGHPOINT);
            return false;}
    }
    public Action HangerUpAuto() {
        return new UpHanger();
    }
}
