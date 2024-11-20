
        package org.firstinspires.ftc.teamcode.ConfigRR;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;

public class    TeleOpMecanum {
     DcMotor motor0;
     DcMotor motor1;
     DcMotor motor2;
     DcMotor motor3;

    public static double GEAR_RATIO = 1.0; // for simulator - ours should be 0.5f;

    public static double WHEEL_RADIUS = 5.0;  // 5 cm
    public static double TICKS_PER_ROTATION = 834.645664;  // From NeveRest (for simulator)  GoBilda should be 383.6f


    public static double CM_PER_TICK = (2 * Math.PI * GEAR_RATIO * WHEEL_RADIUS) / TICKS_PER_ROTATION;

    private double maxSpeed = 0.5;

    private MatrixF conversion;
    private GeneralMatrixF encoderMatrix = new GeneralMatrixF(3, 1);

    private int frontLeftOffset;
    private int frontRightOffset;
    private int backRightOffset;
    private int backLeftOffset;


    public TeleOpMecanum() {
        float[] data = {1.0f, 1.0f, 1.0f,
                1.0f, -1.0f, -1.0f,
                1.0f, -1.0f, 1.0f};
        conversion = new GeneralMatrixF(3, 3, data);
        conversion = conversion.inverted();
    }



    public void init(HardwareMap hwMap) {
        motor0 = hwMap.get(DcMotor.class, "leftFront");
       // motor0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1 = hwMap.get(DcMotor.class, "rightFront");
    //    motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2 = hwMap.get(DcMotor.class, "leftBack");
    //    motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3 = hwMap.get(DcMotor.class, "rightBack");
   //     motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//DcMotor.ZeroPowerBehavior.FLOAT


        //Updated by gcf to test motor direction issues.
        motor0.setDirection(DcMotorSimple.Direction.FORWARD);
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.FORWARD);

        motor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    private void setSpeeds(double flSpeed, double frSpeed, double blSpeed, double brSpeed) {
        double largest = maxSpeed;
        largest = Math.max(largest, Math.abs(flSpeed));
        largest = Math.max(largest, Math.abs(frSpeed));
        largest = Math.max(largest, Math.abs(blSpeed));
        largest = Math.max(largest, Math.abs(brSpeed));

        motor0.setPower(flSpeed / largest);
        motor1.setPower(frSpeed / largest);
        motor2.setPower(blSpeed / largest);
        motor3.setPower(brSpeed / largest);
    }

    public void driveMecanum(double forward, double strafe, double rotate) {
        double frontLeftSpeed = forward + strafe + rotate;
        double frontRightSpeed = forward - strafe - rotate;
        double backLeftSpeed = forward - strafe + rotate;
        double backRightSpeed = forward + strafe - rotate;

        setSpeeds(frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed);
    }

    // Returns forward, strafe
    double[] getDistanceCm() {
        double[] distances = {0.0, 0.0};

        encoderMatrix.put(0, 0, (float) ((motor0.getCurrentPosition() - frontLeftOffset) * CM_PER_TICK));
        encoderMatrix.put(1, 0, (float) ((motor1.getCurrentPosition() - frontRightOffset) * CM_PER_TICK));
        encoderMatrix.put(2, 0, (float) ((motor2.getCurrentPosition() - backLeftOffset) * CM_PER_TICK));

        MatrixF distanceMatrix = conversion.multiplied(encoderMatrix);
        distances[0] = distanceMatrix.get(0, 0);
        distances[1] = distanceMatrix.get(1, 0);

        return distances;
    }

    void setMaxSpeed(double speed) {
        maxSpeed = Math.min(speed, 1.0);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setEncoderOffsets() {
        frontLeftOffset = motor0.getCurrentPosition();
        frontRightOffset = motor1.getCurrentPosition();
        backLeftOffset = motor2.getCurrentPosition();
        backRightOffset = motor3.getCurrentPosition();
    }
}
