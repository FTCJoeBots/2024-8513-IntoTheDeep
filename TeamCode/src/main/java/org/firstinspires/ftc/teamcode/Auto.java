package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;
//very good auto
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//yeah
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//keep going
@Config
//Pratyush is better than Double A battery
@Autonomous(name = "name", group = "hi")
public class Auto extends LinearOpMode {
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
                .strafeToConstantHeading(new Vector2d( -22, 15))
                .strafeToConstantHeading(new Vector2d( -22.1, 21))
                .build();
//TAKE ACTION
        Action clip = drive.actionBuilder(drive.pose)
                //.lineToX(6)
                .stopAndAdd(l.Pos2())
                .waitSeconds(2)
                .strafeToConstantHeading(new Vector2d( -15, 23.5))
                .waitSeconds(1)
                .stopAndAdd(l.Pos1())
                .waitSeconds(1)
                .stopAndAdd(c.ClawOpen())
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d( -10, 19))
                .waitSeconds(1)
                .stopAndAdd(l.Pos0())
                .build();
//TAKE ACTIIOOOON
                Action afterClip = drive.actionBuilder(drive.pose)
                        .stopAndAdd(l.Pos0())
                        .waitSeconds(0.5)
                        .strafeToConstantHeading(new Vector2d(-8,-12))
                        .waitSeconds(0.5)
                        .strafeToConstantHeading(new Vector2d(50,-38))
                        .build();
/*
meanwhile


 */
        while(!isStopRequested() && !opModeIsActive()) {// Init loop

        }

        waitForStart();
//happy happy happpppppppppppppppyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy

        Actions.runBlocking(new SequentialAction(

              start,
                clip,
                afterClip
        ));

        if (isStopRequested()) return;

    }
}
//the end