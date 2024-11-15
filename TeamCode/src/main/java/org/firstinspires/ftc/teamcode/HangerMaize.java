package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangerMaize {
    //constants
    public static final int HANGERLOWPOINT = 10;
    public static final int HANGERHANGS = 1000;
    public static final double HANGERSPEED = .5;
    public static final int HANGERHIGHPOINT = 2800;

    //variables
    DcMotor hangerMotor = null;
    ///methods


    //init
    public void init(HardwareMap hwmap) {
        hangerMotor = hwmap.get(DcMotor.class, "hangerMotor");
        hangerMotor.setTargetPosition(20);
        hangerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hangerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangerMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        hangerMotor.setPower(0);
       // hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hangerautodown();
    }

    //auto down
    public void hangerautodown() {

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

}
