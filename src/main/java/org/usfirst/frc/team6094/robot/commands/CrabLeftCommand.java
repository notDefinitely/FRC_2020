package org.usfirst.frc.team6094.robot.commands;

import org.usfirst.frc.team6094.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrabLeftCommand extends Command {

	public CrabLeftCommand() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.drivetrain.crabLeft();
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