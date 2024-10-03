package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Lift
{
    //constants
    public static final int LIFTLOWPOINT =10;
    public static final int LIFTHIGHPOINT=200;
    public static final double LIFTSPEED=0.2;
    public static final int LIFTMANUALINC=30;

    //variables
    DcMotor liftMOTOR=null;


    ///methods


    //init
    public void init() {
      //hardware.get
    liftMOTOR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    liftMOTOR.setDirection(DcMotorSimple.Direction.FORWARD);
    liftMOTOR.setPower(LIFTSPEED);
    liftMOTOR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    liftautodown();
    }

    //manual up
    public void liftmanualup(){
        int liftCurPosition=0;
        int liftNewPosition=0;
        liftCurPosition=liftMOTOR.getCurrentPosition();
        liftNewPosition = liftCurPosition+LIFTMANUALINC;
        if(liftNewPosition<LIFTHIGHPOINT) {
            liftMOTOR.setTargetPosition(liftNewPosition);
            liftMOTOR.setPower(LIFTSPEED);
        }

    }
    //manual down
    public void liftmanualdown(){
        int liftCurPosition=0;
        int liftNewPosition=0;
        liftCurPosition=liftMOTOR.getCurrentPosition();
        liftNewPosition = liftCurPosition-LIFTMANUALINC;
        if(liftNewPosition>LIFTLOWPOINT) {
            liftMOTOR.setTargetPosition(liftNewPosition);
            liftMOTOR.setPower(LIFTSPEED);
        }

    }
    //auto down
    public void liftautodown(){

        liftMOTOR.setTargetPosition(LIFTLOWPOINT);
        liftMOTOR.setPower(LIFTSPEED);

    }
    //auto up

    public void liftautoup(){

        liftMOTOR.setTargetPosition(LIFTHIGHPOINT);
        liftMOTOR.setPower(LIFTSPEED);

    }



}
