package org.usfirst.frc.team6094.robot.commands;

import org.usfirst.frc.team6094.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LoadCommand extends Command {

	public LoadCommand() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.iscs.ShootAuton();
		Robot.iscs.EverythingElse();
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