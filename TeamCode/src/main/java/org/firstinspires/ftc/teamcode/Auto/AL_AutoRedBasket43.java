package org.firstinspires.ftc.teamcode.Auto;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ConfigRR.AutoMecanumDrive;
import org.firstinspires.ftc.teamcode.HangerMaize;
import org.firstinspires.ftc.teamcode.Intake8513;
import org.firstinspires.ftc.teamcode.Lift;
import org.firstinspires.ftc.teamcode.bucketMaize;
import org.firstinspires.ftc.teamcode.clawMaize;
import org.firstinspires.ftc.teamcode.horizantalSlide;
@Config

@Autonomous(name = "AutoAfterLakeRedBasket", group = "Red")
public class AL_AutoRedBasket43 extends LinearOpMode {
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

        // Action Stuff
        Action Start = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                .stopAndAdd(l.Pos2())
                .strafeToConstantHeading(new Vector2d( 0, 30))
                .strafeToConstantHeading(new Vector2d( 8, 31))
                .strafeToConstantHeading(new Vector2d( 15, 38))
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
                .strafeToConstantHeading(new Vector2d( -50, 0))
                .turn(Math.toRadians(180))
                .build();

        Action Intake = drive.actionBuilder(drive.pose)
                .stopAndAdd(h.Med())
                .waitSeconds(0.9)
                .stopAndAdd(i.Down())
                .stopAndAdd(i.start())
                .waitSeconds(1)
                .stopAndAdd(h.More())
                .stopAndAdd(i.startInWithColorSensor())
                .waitSeconds(2)
                .stopAndAdd(i.stop())
                .stopAndAdd(i.Up())
                .waitSeconds(1)
                .stopAndAdd(h.Back())
                .stopAndAdd(i.reverse())
                .build();

        Action Deposit = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d( 0, 2))
                .turn(Math.toRadians(45))
                .stopAndAdd(l.Pos2())
                .strafeToConstantHeading(new Vector2d( 0, 12))
                .stopAndAdd(b.DropBlock())
                .stopAndAdd(b.Rest())
                .strafeToConstantHeading(new Vector2d( 0, -12))
                .stopAndAdd(l.Pos0())
                .turn(-45)
                .strafeToConstantHeading(new Vector2d( 0, 2))
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
                Strafe2,
                Intake,
                Deposit,
                GoToPark,
                Park

        ));
        if (isStopRequested()) return;}}
