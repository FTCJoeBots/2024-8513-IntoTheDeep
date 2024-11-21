package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ConfigRR.AutoMecanumDrive;

@Config

@Autonomous(name = "AutoAfterLakeBlueBasket", group = "Blue")
public class AL_AutoBlueBasket43 extends LinearOpMode {
    @Override
    public void runOpMode() {
//Pose|Inits
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(-90));// Starting position
        AutoMecanumDrive drive = new  AutoMecanumDrive(hardwareMap, startPose);

        Intake8513 i = new Intake8513();
        Lift l = new Lift();
        horizantalSlide h= new horizantalSlide();
        HangerMaize H = new HangerMaize();
        clawMaize c= new clawMaize();
        bucketMaize b= new bucketMaize();

        l.init(hardwareMap);
        c.init(hardwareMap);
        b.init(hardwareMap);
        i.init(hardwareMap);
        h.init(hardwareMap);

        Action Start = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                //.lineToX(6)
                .stopAndAdd(l.Pos2())
                //.strafeToConstantHeading(new Vector2d( 0, 30))
                //.strafeToConstantHeading(new Vector2d( 10.5, 29))
                .strafeToConstantHeading(new Vector2d( 24.9, 37.7))
                .waitSeconds(0.5)
                .build();

      Action Block1 = drive.actionBuilder(drive.pose)
              .waitSeconds(0.2)
              .stopAndAdd(l.Pos1())
              .waitSeconds(0.1)
              .stopAndAdd(c.ClawOpen())
              .strafeToConstantHeading(new Vector2d( 0, -9))
              .stopAndAdd(l.Pos0())
              .build();

        Action Strafe = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d( -47, 0))
                .turn(Math.toRadians(-180))
                .build();

        Action Intake = drive.actionBuilder(drive.pose)
                .stopAndAdd(h.Med())
                .waitSeconds(0.9)
                .stopAndAdd(i.Down())
                .stopAndAdd(i.start())
                .waitSeconds(1)
                .stopAndAdd(h.More())
                .stopAndAdd(i.startInWithColorSensor())
                .waitSeconds(1.3)
                .stopAndAdd(i.stop())
                .stopAndAdd(i.Up())
                .waitSeconds(1)
                .stopAndAdd(h.Back())
                .waitSeconds(1)
                .stopAndAdd(i.reverse())
                .build();

        Action Deposit = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d( 20, 15.5))
                .turn(Math.toRadians(-48))
                .waitSeconds(1)
                .stopAndAdd(l.Pos5())
                .waitSeconds(1)
                .stopAndAdd(b.DropBlock())
                .waitSeconds(1)
                .stopAndAdd(b.Rest())
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d( 0, -5))
                .stopAndAdd(l.Pos0())
                .waitSeconds(1)
                .turn(Math.toRadians(1))
                .build();

        Action Strafe2 = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d( 6, 0))
                .build();

        Action GoToPark = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d(0,-30))
                .turn(Math.toRadians(90))
                .build();

        Action Park = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d(20,10))
                .stopAndAdd(H.HangerUpAuto())
                .build();

        while(!isStopRequested() && !opModeIsActive()) {}
        waitForStart();

        Actions.runBlocking(new SequentialAction(

                Start,
                Block1,
                Strafe,
                Intake,
                Deposit,
              //  Strafe2,
                Intake,
                Deposit //add comma
              //  GoToPark,
              //  Park

        ));
        if (isStopRequested()) return;}}
