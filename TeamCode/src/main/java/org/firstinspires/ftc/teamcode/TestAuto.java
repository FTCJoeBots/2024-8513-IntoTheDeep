package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "AutoAfterLakeRedHP", group = "Red")
public class TestAuto extends LinearOpMode {
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
                .strafeToConstantHeading(new Vector2d( 0, 30))
                .strafeToConstantHeading(new Vector2d( -8, 31))
                .stopAndAdd(l.Pos2())
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d( -15, 38))
                .waitSeconds(0.2)
                .build();

      Action clip1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.3)
                .stopAndAdd(l.Pos1())
              .waitSeconds(0.4)
              .stopAndAdd(c.ClawOpen())
                .strafeToConstantHeading(new Vector2d( 0, -9))
                .stopAndAdd(l.Pos0())
                .build();

Action plow = drive.actionBuilder(drive.pose)
        .waitSeconds(0.2)
        .strafeToConstantHeading(new Vector2d(37, -5))
        .strafeToConstantHeading(new Vector2d(39,28))
        .turn(-1.6)
        .strafeToConstantHeading(new Vector2d(45,27))
        .strafeToConstantHeading(new Vector2d(45.1,-30))
        .strafeToConstantHeading(new Vector2d(48,-10))
        .stopAndAdd(l.Pos3())
        .stopAndAdd(c.ClawOpen())
        .waitSeconds(1.5)
        .strafeToConstantHeading(new Vector2d(48,-40))
        .strafeToConstantHeading(new Vector2d(72.,-48))
        .waitSeconds(.7)
        .stopAndAdd(c.CloseClaw())
        .stopAndAdd(l.Pos1())
        .waitSeconds(0.1)
        .strafeToConstantHeading(new Vector2d(20,-43))
        .strafeToConstantHeading(new Vector2d(24,-5))
        .turn(1.3)
        .stopAndAdd(l.Pos2())
        .strafeToConstantHeading(new Vector2d(24,-3))
        .stopAndAdd(l.Pos1())
        .waitSeconds(0.8)
        .stopAndAdd(c.ClawOpen())
        .strafeToConstantHeading(new Vector2d(24,-20))
        .build();


        while(!isStopRequested() && !opModeIsActive()) {// Init loop

        }

        waitForStart();

        Actions.runBlocking(new SequentialAction(

                start,
                clip1,
                plow
        ));

        if (isStopRequested()) return;

    }
}
//the end