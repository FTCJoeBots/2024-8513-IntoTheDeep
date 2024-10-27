package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift
{
    //constants
    public static final int LIFTLOWPOINT =10;
    public static final int LIFTHIGHPOINT=1000;
    public static final double LIFTSPEED=0.2;
    public static final int LIFTMANUALINC=30;

    //variables
    DcMotor rightLiftMotor=null;

    DcMotor leftLiftMotor=null;
    ///methods


    //init
    public void init(HardwareMap hwmap) {
    rightLiftMotor = hwmap.get(DcMotor.class,"rightLiftMotor");
        leftLiftMotor = hwmap.get(DcMotor.class,"leftLiftMotor");
    rightLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      leftLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightLiftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    leftLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    rightLiftMotor.setPower(0);
        leftLiftMotor.setPower(0);
    rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //liftautodown();
    }

    //manual up
    public void liftmanualup(){
        int liftCurPosition=0;
        int liftNewPosition=0;
        liftCurPosition=rightLiftMotor.getCurrentPosition();
        liftNewPosition = liftCurPosition+LIFTMANUALINC;
        if(liftNewPosition<LIFTHIGHPOINT) {
            rightLiftMotor.setTargetPosition(liftNewPosition);
            leftLiftMotor.setTargetPosition(liftNewPosition);
            rightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLiftMotor.setPower(LIFTSPEED);
            leftLiftMotor.setPower(LIFTSPEED);
        }

    }
    //manual down
    public void liftmanualdown(){
        int liftCurPosition=0;
        int liftNewPosition=0;
        liftCurPosition=rightLiftMotor.getCurrentPosition();
        liftNewPosition = liftCurPosition-LIFTMANUALINC;
        if(liftNewPosition>LIFTLOWPOINT) {
            rightLiftMotor.setTargetPosition(liftNewPosition);
            leftLiftMotor.setTargetPosition(liftNewPosition);
            rightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLiftMotor.setPower(LIFTSPEED);
            leftLiftMotor.setPower(LIFTSPEED);
        }

    }
    //auto down
    public void liftautodown(){

        rightLiftMotor.setTargetPosition(LIFTLOWPOINT);
        leftLiftMotor.setTargetPosition(LIFTLOWPOINT);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLiftMotor.setPower(LIFTSPEED);
        leftLiftMotor.setPower(LIFTSPEED);

    }
    //auto up

    public void liftautoup(){

        rightLiftMotor.setTargetPosition(LIFTHIGHPOINT);
        leftLiftMotor.setTargetPosition(LIFTHIGHPOINT);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLiftMotor.setPower(LIFTSPEED);
        leftLiftMotor.setPower(LIFTSPEED);

    }



}
