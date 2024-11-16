package org.firstinspires.ftc.teamcode;
//very good auto

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//yeah


//keep going
@Config
//Pratyush is better than Double A battery
@Autonomous(name = "RedHP23Plow", group = "Red")
public class Auto_RedHP23andPlow extends LinearOpMode {
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
//take action now
        Action start = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                .strafeToConstantHeading(new Vector2d( 0, 15))
                .strafeToConstantHeading(new Vector2d( -6, 15))
                .strafeToConstantHeading(new Vector2d( -6.1, 19.2))
                .build();

        Action clip = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                .stopAndAdd(l.Pos2())
                .waitSeconds(2)
                .strafeToConstantHeading(new Vector2d( -15, 23.5))
                .waitSeconds(.7)
                .stopAndAdd(l.Pos1())
                .waitSeconds(.4)
                .stopAndAdd(c.ClawOpen())
                .waitSeconds(.3)
                .strafeToConstantHeading(new Vector2d( -10, 19))
                .waitSeconds(.25)
                .stopAndAdd(l.Pos0())
                .build();

        Action plow = drive.actionBuilder(drive.pose)
                .stopAndAdd(l.Pos0())
                .waitSeconds(0.3)
                .strafeToConstantHeading(new Vector2d(25,-3))
                .waitSeconds(0.3)
                .strafeToConstantHeading(new Vector2d(26,14))
                .waitSeconds(0.2)
                .turn(-1.4)
                .waitSeconds(.2)
                .strafeToConstantHeading(new Vector2d(32,13))
                .waitSeconds(0.2)
                .strafeToConstantHeading(new Vector2d(32,-38))
                .strafeToConstantHeading(new Vector2d(32,13))
                .turn(-0.22)
                .strafeToConstantHeading(new Vector2d(43,13))
                .strafeToConstantHeading(new Vector2d(43.5,-38))
                .strafeToConstantHeading(new Vector2d(44,13))
                .turn(-0.09)
                .strafeToConstantHeading(new Vector2d(56,13))
                .strafeToConstantHeading(new Vector2d(57,-38))
                .build();

        while(!isStopRequested() && !opModeIsActive()) {// Init loop

        }

        waitForStart();

        Actions.runBlocking(new SequentialAction(

                start,
                clip,
                plow
        ));

        if (isStopRequested()) return;

    }
}
//the end