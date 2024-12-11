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
import org.firstinspires.ftc.teamcode.Intake8513;
import org.firstinspires.ftc.teamcode.Lift;
import org.firstinspires.ftc.teamcode.bucketMaize;
import org.firstinspires.ftc.teamcode.clawMaize;
import org.firstinspires.ftc.teamcode.horizantalSlide;

@Config
@Autonomous(name = "AL_AutoHP63WithSplineThreeClips", group = "Red")
public class AL_AutoHP63WithSplineThreeClips extends LinearOpMode {
    @Override
    public void runOpMode() {
//pose
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(-90));// Starting position
        AutoMecanumDrive drive = new  AutoMecanumDrive(hardwareMap, startPose);

        Intake8513 i = new Intake8513();
        Lift l = new Lift();
        horizantalSlide h= new horizantalSlide();
        clawMaize c= new clawMaize();
        bucketMaize b= new bucketMaize();

        l.init(hardwareMap);
        c.init(hardwareMap);
        b.init(hardwareMap);
        i.init(hardwareMap);
//take action now
        Action start = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                .stopAndAdd(l.Pos2())
                //.strafeToConstantHeading(new Vector2d( 0, 30))
                //.strafeToConstantHeading(new Vector2d( -10.5, 32))
                //.strafeToConstantHeading(new Vector2d( -15, 39))
                //.waitSeconds(0.2)
                .splineToConstantHeading(new Vector2d(-13,33.5), 1.6)
                .build();

      Action clip1 = drive.actionBuilder(drive.pose)
                .stopAndAdd(l.Pos1())
              .waitSeconds(0.28)
              .stopAndAdd(c.ClawOpen())
                .splineToConstantHeading(new Vector2d( 0, -5),1.6)
                .stopAndAdd(l.Pos0())
                .build();

Action plow = drive.actionBuilder(drive.pose)
        .splineToConstantHeading(new Vector2d(33, -2),1.6)
        .splineToSplineHeading(new Pose2d(new Vector2d(30, 30), Math.toRadians(-180)),1.6)
        //start to push first block into HP
        .strafeToConstantHeading(new Vector2d(41.5, 25))
        .strafeToConstantHeading(new Vector2d(43, -20))
        //Finishing pushng first block into HP and starting second
        .splineToSplineHeading(new Pose2d(new Vector2d(45, -12), Math.toRadians(-190)),1.6)
       // .splineToConstantHeading(new Vector2d( 52, 29),1.6)
        //Finishing pushng second block into HP
        .waitSeconds(0.1)
        .turnTo(1.2)
        .build();

        Action clippickup = drive.actionBuilder(drive.pose)
        .stopAndAdd(l.Pos3())
        .stopAndAdd(c.ClawOpen())
        .waitSeconds(0.28)
                .strafeToConstantHeading(new Vector2d(3,33))
                .waitSeconds(0.1)
        .stopAndAdd(c.CloseClaw())
        .waitSeconds(0.05)
        .stopAndAdd(l.Pos1())
        .build();


Action twoclips = drive.actionBuilder(drive.pose)
        .splineTo(new Pose2d(new Vector2d(44, -20), Math.toRadians(0)).component1(),1.6)
        .stopAndAdd(l.Pos2())
        .strafeToConstantHeading(new Vector2d(36,-35))
        .stopAndAdd(l.Pos1())
        .waitSeconds(0.28)
        .stopAndAdd(c.ClawOpen())
        .stopAndAdd(l.Pos0())
        .splineToSplineHeading(new Pose2d(new Vector2d(-4, 6), Math.toRadians(265)),1.6)
        .waitSeconds(0.1)
        .stopAndAdd(l.Pos1())
        .waitSeconds(0.025)
        .stopAndAdd(c.CloseClaw())
        .build();


        Action cliptwice = drive.actionBuilder(drive.pose)
        .splineTo(new Pose2d(new Vector2d(43, -18), Math.toRadians(0)).component1(),1.6)
        .stopAndAdd(l.Pos2())
        .strafeToConstantHeading(new Vector2d(43,-35))
        .stopAndAdd(l.Pos1())
        .waitSeconds(0.28)
        .stopAndAdd(c.ClawOpen())
        .stopAndAdd(l.Pos0())
                .splineTo(new Pose2d(new Vector2d(0, 0), Math.toRadians(0)).component1(),1.6)

                .build();





Action move = drive.actionBuilder(drive.pose)
        .strafeToConstantHeading(new Vector2d(15,-62))
        .turn(1.6)
        .stopAndAdd(l.Pos2())
        .build();


        Action clip2 = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d( -8, 30))
               // .stopAndAdd(l.Pos2())
               // .waitSeconds(0.1)
                .build();

        Action clip3 = drive.actionBuilder(drive.pose)
                .stopAndAdd(l.Pos1())
                .waitSeconds(0.28)
                .stopAndAdd(c.ClawOpen())
                .stopAndAdd(l.Pos0())

                //.strafeToConstantHeading(new Vector2d( 53, -40))
               // .strafeToConstantHeading(new Vector2d( 53, -40), new TranslationalVelConstraint(750),new ProfileAccelConstraint(-10, 75))
                .build();

        while(!isStopRequested() && !opModeIsActive()) {// Init loop

        }

        waitForStart();

        Actions.runBlocking(new SequentialAction(

                start,
                clip1,
                plow,
                clippickup,
                twoclips,
                cliptwice
                //move
                //clip2,
                //clip3
        ));

        if (isStopRequested()) return;

    }
}
//the end