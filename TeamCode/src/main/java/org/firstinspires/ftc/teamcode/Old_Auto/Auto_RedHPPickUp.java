package org.firstinspires.ftc.teamcode.Old_Auto;
//very good auto

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Intake8513;
import org.firstinspires.ftc.teamcode.Lift;
import org.firstinspires.ftc.teamcode.ConfigRR.MecanumDrive;
import org.firstinspires.ftc.teamcode.bucketMaize;
import org.firstinspires.ftc.teamcode.clawMaize;
import org.firstinspires.ftc.teamcode.horizantalSlide;
//yeah


//keep going
@Config
@Disabled
//Pratyush is better than Double A battery
@Autonomous(name = "TEST", group = "Red")
public class Auto_RedHPPickUp extends LinearOpMode {
//ride down the street
    @Override
    public void runOpMode() {
//pose
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(-90));// Starting position
        MecanumDrive drive = new  MecanumDrive(hardwareMap, startPose);
//take some info in
        Intake8513 i = new Intake8513();
        Lift l = new Lift();
        horizantalSlide h= new horizantalSlide();
        clawMaize c= new clawMaize();
        bucketMaize b= new bucketMaize();
//l
        l.init(hardwareMap);
        c.init(hardwareMap);
        b.init(hardwareMap);
        i.init(hardwareMap);
        h.init(hardwareMap);
//take action now
        Action start = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                .stopAndAdd(i.Up())
                .strafeToConstantHeading(new Vector2d( 0, 10))
                .strafeToConstantHeading(new Vector2d( 37.7, 10.7))
                .turn(Math.toRadians(166))
                .build();
        Action intake = drive.actionBuilder(drive.pose)
                .stopAndAdd(h.Med())
                .waitSeconds(0.9)
                .stopAndAdd(i.Down())
                .stopAndAdd(i.start())
                .waitSeconds(1)
                .stopAndAdd(h.More())
                .stopAndAdd(i.startInWithColorSensor())
                .waitSeconds(3)
                .stopAndAdd(i.stop())
                .stopAndAdd(i.Up())
                .waitSeconds(1)
                .stopAndAdd(h.Back())
                .stopAndAdd(i.reverse())
                .build();



        while(!isStopRequested() && !opModeIsActive()) {// Init loop

        }

        waitForStart();

        Actions.runBlocking(new SequentialAction(

              start,
                intake



        ));

        if (isStopRequested()) return;

    }
}
//the end