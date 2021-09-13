package org.usfirst.frc.team6094.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import org.usfirst.frc.team6094.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;

public class BackToLineCommand extends Command {
	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
	private final ColorMatch colorMatcher = new ColorMatch();
	private final Color White = ColorMatch.makeColor(0.35, 0.45, 0.19);
	private final Color NotWhite = ColorMatch.makeColor(.5, .5, .0);
	Boolean Trigger = false;

	public BackToLineCommand() {
	}

	protected void initialize() {
		colorMatcher.addColorMatch(White);
		colorMatcher.addColorMatch(NotWhite);
	}

	protected void execute() {
		// String initalizing Color
		Color detectedColor = colorSensor.getColor();
		String colorString;
		String SWhite = "White";

		ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

		if (match.color == White) {
			colorString = "White";
		} else if (match.color == NotWhite) {
			colorString = "NotWhite";
		} else {
			colorString = "Unknown";
		}
		if (Trigger == false) {
			if (colorString.equals(SWhite)) {
				Robot.drivetrain.driveTrainStop();
				System.out.println("Red " + detectedColor.red);
				System.out.println("Green " + detectedColor.green);
				System.out.println("Blue " + detectedColor.blue);
				System.out.println(colorString);
				Trigger = true;
			} else {
				Robot.drivetrain.backAutonDrive();
				System.out.println(colorString);
			}
		} else {
			Robot.drivetrain.driveTrainStop();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}