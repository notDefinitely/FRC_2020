package org.usfirst.frc.team6094.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import org.usfirst.frc.team6094.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;

@SuppressWarnings("unused")
public class Teleop extends Command {
	/*
	 * // initializing properties private final I2C.Port i2cPort =
	 * I2C.Port.kOnboard;
	 * 
	 * private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
	 * 
	 * private final ColorMatch colorMatcher = new ColorMatch();
	 * 
	 * private final Color Blue = ColorMatch.makeColor(0.143, 0.427, 0.429); private
	 * final Color Green = ColorMatch.makeColor(0.197, 0.561, 0.240); private final
	 * Color Red = ColorMatch.makeColor(0.561, 0.232, 0.114); private final Color
	 * Yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);
	 */

	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

	public Teleop() {
		requires(Robot.drivetrain);
		requires(Robot.fml);
		requires(Robot.els);
		requires(Robot.iscs);
	}

	protected void initialize() {
		// System safety check
		Robot.fml.finalMotorStop();
		Robot.drivetrain.driveTrainStop();
		Robot.els.ELSStop();
		Robot.iscs.ISCSStop();
		Robot.drivetrain.init();
		/*
		 * colorMatcher.addColorMatch(Blue); colorMatcher.addColorMatch(Green);
		 * colorMatcher.addColorMatch(Red); colorMatcher.addColorMatch(Yellow);
		 */
	}

	@Override
	protected void execute() {
		/*
		 * // String initalizing Color detectedColor = colorSensor.getColor(); String
		 * colorString; String SBlue = "Blue"; String SGreen = "Green"; String SRed =
		 * "Red"; String SYellow = "Yellow"; String SUnknown = "Unknown";
		 * 
		 * // Testing to see if I can move this here String gameData; gameData =
		 * DriverStation.getInstance().getGameSpecificMessage(); // End of the test
		 * ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
		 * 
		 * if (match.color == Blue) { colorString = "Blue"; } else if (match.color ==
		 * Red) { colorString = "Red"; } else if (match.color == Green) { colorString =
		 * "Green"; } else if (match.color == Yellow) { colorString = "Yellow"; } else {
		 * colorString = "Unknown"; }
		 * 
		 * // colour sensor // Semi-autonomous colour scoring if
		 * (Robot.oi.colourReader.get()) { if (gameData.length() > 0) { switch
		 * (gameData.charAt(0)) { case 'B': if (colorString.equals(SRed)) {
		 * System.out.println("Done"); Robot.coloursensor.colourMotorStop(); } else {
		 * System.out.println("Turning to score"); Robot.coloursensor.colourMotorTurn();
		 * } break; case 'G': if (colorString.equals(SYellow)) {
		 * System.out.println("Done"); Robot.coloursensor.colourMotorStop(); } else {
		 * System.out.println("Turning to score"); Robot.coloursensor.colourMotorTurn();
		 * } break; case 'R': if (colorString.equals(SBlue)) {
		 * System.out.println("Done"); Robot.coloursensor.colourMotorStop(); } else {
		 * System.out.println("Turning to score"); Robot.coloursensor.colourMotorTurn();
		 * } break; case 'Y': if (colorString.equals(SGreen)) {
		 * System.out.println("Done"); Robot.coloursensor.colourMotorStop(); } else {
		 * System.out.println("Turning to score"); Robot.coloursensor.colourMotorTurn();
		 * } break; default: // This is corrupt data break; } } else { // Code for no
		 * data received yet } }
		 */

		// FML Motors
		if (Robot.oi.finalButton.get()) {
			Robot.fml.finalMotorGo();
		} else {
			Robot.fml.finalMotorStop();
		}

		// Intake Motors
		if (Robot.oi.intakeIn.get()) {
			Robot.iscs.IntakeUp();
		} else if (Robot.oi.intakeOut.get()) {
			Robot.iscs.IntakeDown();
		} else {
			Robot.iscs.IntakeStop();
		}

		// Conveyer Motors
		if (Robot.oi.conveyorUp.get()) {
			Robot.iscs.conveyorUp();
		} else if (Robot.oi.conveyorDown.get()) {
			Robot.iscs.conveyorDown();
		} else {
			Robot.iscs.conveyorStop();
		}

		Color detectedColor = colorSensor.getColor();

		// Test button
		if (Robot.oi.Test.get()) {
			System.out.println("Red " + detectedColor.red);
			System.out.println("Green " + detectedColor.green);
			System.out.println("Blue " + detectedColor.blue);
		}

		// Analog controls
		Robot.els.Elevator(Robot.oi.getXboxController());
		Robot.els.Lift(Robot.oi.getXboxController());
		Robot.iscs.shooterDown(Robot.oi.getXboxController());
		Robot.drivetrain.mecanumDrive(Robot.oi.getJoystick());

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.fml.finalMotorStop();
		Robot.drivetrain.driveTrainStop();
		Robot.els.ELSStop();
		Robot.iscs.ISCSStop();
	}
}