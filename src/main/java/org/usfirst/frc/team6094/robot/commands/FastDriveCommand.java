package org.usfirst.frc.team6094.robot.commands;

import org.usfirst.frc.team6094.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FastDriveCommand extends Command {

	public FastDriveCommand() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.drivetrain.fastAutonDrive();
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