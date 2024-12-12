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

@Autonomous(name = "AL_AutoBasketStartWithSample48", group = "Red")
public class AL_AutoBasketStartWithSample48 extends LinearOpMode {
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
                //.strafeToConstantHeading(new Vector2d( 0, 30))
                //.strafeToConstantHeading(new Vector2d( 10.5, 29))
                .strafeToConstantHeading(new Vector2d( 0, -9))
                .strafeToConstantHeading(new Vector2d( 30, -9))
                .build();

        Action idk = drive.actionBuilder(drive.pose)
                .waitSeconds(0.1)
                .stopAndAdd(l.Pos1())
                .waitSeconds(0.15)
                .stopAndAdd(c.ClawOpen())
                .strafeToConstantHeading(new Vector2d( 0, -9))
                .stopAndAdd(l.Pos0())
                .build();

        Action Strafe = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d(-18, -10))
                .build();


        Action Intake = drive.actionBuilder(drive.pose)
                .stopAndAdd(h.Med())
                .waitSeconds(0.7)
                .stopAndAdd(i.Down())
                .stopAndAdd(i.start())
                .waitSeconds(0.3)
                .stopAndAdd(h.Far())
                .stopAndAdd(i.startInWithColorSensor())
                .afterTime(3, i.goUp())
                .waitSeconds(0.6)
                .stopAndAdd(i.stop())
                .stopAndAdd(i.Up())
                .waitSeconds(0.4)
                .stopAndAdd(h.Back())
                .waitSeconds(1)
                .stopAndAdd(i.reverse())
                .build();

        Action Retract = drive.actionBuilder(drive.pose)
                .stopAndAdd(i.goUp())
                .build();
        Action Intake2 = drive.actionBuilder(drive.pose)
                .stopAndAdd(h.Med())
                .waitSeconds(0.7)
                .stopAndAdd(i.Down())
                .stopAndAdd(i.start())
                .waitSeconds(.3)
                .stopAndAdd(h.Far())
                .stopAndAdd(i.startInWithColorSensor())
                .waitSeconds(.6)
                .stopAndAdd(i.stop())
                .stopAndAdd(i.Up())
                .waitSeconds(0.4)
                .stopAndAdd(h.Back())
                .waitSeconds(0.9)
                .stopAndAdd(i.reverse())
                .build();

        Action Sample1 = drive.actionBuilder(drive.pose)
                //.strafeToConstantHeading(new Vector2d( 17.2, 16))
                .turn(Math.toRadians(-49))
                //.waitSeconds(0.3)
                .stopAndAdd(l.Pos5())
                .waitSeconds(1.0)
                .stopAndAdd(b.DropBlock())
                .waitSeconds(1)
                .stopAndAdd(b.Rest())
                .waitSeconds(0.3)
                .stopAndAdd(l.Pos0())
                .waitSeconds(0.7)
                .turn(Math.toRadians(49))
                .build();



        Action Deposit = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d( 17.2, 13.6))
                .turn(Math.toRadians(-49))
                //.waitSeconds(0.3)
                .stopAndAdd(l.Pos5())
                .waitSeconds(1)
                .stopAndAdd(b.DropBlock())
                .waitSeconds(1)
                .stopAndAdd(b.Rest())
                .waitSeconds(0.3)
                .stopAndAdd(l.Pos0())
                .waitSeconds(0.7)
                .build();




        Action Deposit2 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.3)
                .stopAndAdd(l.Pos5())
                //.waitSeconds(1.5)
                .strafeToConstantHeading(new Vector2d(3,7))
                .waitSeconds(0.3)
                .turn(Math.toRadians(-49))
                .waitSeconds(0.2)
                .stopAndAdd(b.DropBlock())
                .waitSeconds(1)
                .stopAndAdd(b.Rest())
                .waitSeconds(0.5)
                .stopAndAdd(l.Pos0())
                .waitSeconds(0.8)
                .build();

        Action Strafe2 = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(45))
                .strafeToConstantHeading(new Vector2d( 5, -7))
                .build();

        Action GoToPark = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d(0,-30))
                .turn(Math.toRadians(90))
                .build();

      /*  Action Park = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d(20,10))
                .stopAndAdd(H.HangerUpAuto())
                .build();*/

        while(!isStopRequested() && !opModeIsActive()) {}
        waitForStart();

        Actions.runBlocking(new SequentialAction(

                Start,
                Sample1,
                Strafe,
                Intake,
                Retract,
                Deposit,
                Strafe2,
                Intake2,
                Deposit2//,
                // GoToPark

        ));
        if (isStopRequested()) return;}}
