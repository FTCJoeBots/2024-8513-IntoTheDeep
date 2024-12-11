package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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
@Autonomous(name = "AutoAfterLakeHP", group = "Red")
public class AL_AutoHP43 extends LinearOpMode {
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
                .strafeToConstantHeading(new Vector2d( 0, 30))
                .strafeToConstantHeading(new Vector2d( -10.5, 32))
                .strafeToConstantHeading(new Vector2d( -15, 39))
                .waitSeconds(0.2)
                .build();

      Action clip1 = drive.actionBuilder(drive.pose)
                .stopAndAdd(l.Pos1())
              .waitSeconds(0.3)
              .stopAndAdd(c.ClawOpen())
                .strafeToConstantHeading(new Vector2d( 0, -9))
                .stopAndAdd(l.Pos0())
                .build();

Action plow = drive.actionBuilder(drive.pose)
        .strafeToConstantHeading(new Vector2d(37, -5))
        .strafeToConstantHeading(new Vector2d(39,28))
        .turn(-1.6)
        .strafeToConstantHeading(new Vector2d(47,31))
        .strafeToConstantHeading(new Vector2d(45.1,-28))
        .strafeToConstantHeading(new Vector2d(48,-11))
        .stopAndAdd(l.Pos3())
        .stopAndAdd(c.ClawOpen())
        .turn(-0.16)
        .waitSeconds(1.2)
        .strafeToConstantHeading(new Vector2d(48,-40))
        .strafeToConstantHeading(new Vector2d(66,-48))
        .waitSeconds(0.3)
        .stopAndAdd(c.CloseClaw())
        .waitSeconds(0.05)
        .stopAndAdd(l.Pos1())
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
                .waitSeconds(0.3)
                .stopAndAdd(c.ClawOpen())
                .stopAndAdd(l.Pos0())
                //.strafeToConstantHeading(new Vector2d( 53, -40))
                .strafeToConstantHeading(new Vector2d( 53, -40), new TranslationalVelConstraint(750),new ProfileAccelConstraint(-10, 75))
                .build();

        while(!isStopRequested() && !opModeIsActive()) {// Init loop

        }

        waitForStart();

        Actions.runBlocking(new SequentialAction(

                start,
                clip1,
                plow,
                move,
                clip2,
                clip3
        ));

        if (isStopRequested()) return;

    }
}
//the end