package org.usfirst.frc.team6094.robot.subsystems;

import java.lang.*;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.usfirst.frc.team6094.robot.Robot;
import org.usfirst.frc.team6094.robot.commands.Teleop;

@SuppressWarnings("unused")
public class DriveTrain extends Subsystem {

	VictorSP frontLeft = new VictorSP(0);

	VictorSP frontRight = new VictorSP(1);

	VictorSP backLeft = new VictorSP(2);

	VictorSP backRight = new VictorSP(3);

	public Encoder encoderLift = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

	public void initDefaultCommand() {
		setDefaultCommand(new Teleop());
	}

	public void init() {
		frontLeft.setSafetyEnabled(true);
		frontRight.setSafetyEnabled(false);
		backLeft.setSafetyEnabled(false);
		backRight.setSafetyEnabled(false);
	}

	// DriveTrain functions
	public void mecanumDrive(Joystick Joy) {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);

		double x = 0;
		double y = 0;
		double z = 0;

		x = Joy.getRawAxis(0);
		y = -(Joy.getRawAxis(1));
		if (java.lang.Math.abs(Joy.getRawAxis(2)) > .3) {
			z = Joy.getRawAxis(2);
		} else {
			z = 0;
		}

		if (java.lang.Math.abs(x) > 0.2 || java.lang.Math.abs(y * .3) > 0.1 || java.lang.Math.abs(z) > 0.2) {
			frontLeft.set((x + y + z) * .4);
			frontRight.set((-x + y - z) * .4);
			backLeft.set((-x + y + z) * .4);
			backRight.set((x + y - z) * .4);
		} else {
			frontLeft.set(0);
			frontRight.set(0);
			backLeft.set(0);
			backRight.set(0);
		}
	}

	public void fastAutonDrive() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);

		frontLeft.set(.5);
		frontRight.set(.5);
		backLeft.set(.5);
		backRight.set(.5);
	}

	public void slowAutonDrive() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);

		frontLeft.set(.2);
		frontRight.set(.2);
		backLeft.set(.2);
		backRight.set(.2);
	}

	public void driveTrainStop() {
		frontLeft.set(0);
		frontRight.set(0);
		backLeft.set(0);
		backRight.set(0);
	}

	public void crabRight() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);

		frontLeft.set(.4);
		frontRight.set(-.3);
		backLeft.set(-.4);
		backRight.set(.4);
	}

	public void crabLeft() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);

		frontLeft.set(-.4);
		frontRight.set(.4);
		backLeft.set(.4);
		backRight.set(-.4);
	}

	public void tCounterClockwise() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);
	}

	public void tClockwise() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);
	}

	public void backAutonDrive() {
		frontLeft.setInverted(true);
		frontRight.setInverted(true);
		backLeft.setInverted(false);
		backRight.setInverted(true);

		frontLeft.set(-.2);
		frontRight.set(-.2);
		backLeft.set(-.2);
		backRight.set(-.2);

	}

	// Encoder funtions
	public void encoderPrintOut() {
		System.out.println(encoderLift.get());
		System.out.println(encoderLift.getDistance());
		System.out.println(encoderLift.getDirection());
		System.out.println(encoderLift.getRate());
		System.out.println(encoderLift.getStopped());
	}

	public void encoderInit() {
		// Encoders
		encoderLift.setDistancePerPulse(28);
		encoderLift.setMaxPeriod(1);
		encoderLift.setMinRate(10);
		encoderLift.setReverseDirection(true);
		// encoderLift.setSamplesToAverage(7);
		int count = encoderLift.get();
		double distance = encoderLift.getDistance();
		double rate = encoderLift.getRate();
		boolean direction = encoderLift.getDirection();
		boolean stopped = encoderLift.getStopped();
		encoderLift.reset();
	}
}