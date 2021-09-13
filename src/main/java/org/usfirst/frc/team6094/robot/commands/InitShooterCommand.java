package org.usfirst.frc.team6094.robot.commands;

import org.usfirst.frc.team6094.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class InitShooterCommand extends Command {

	public InitShooterCommand() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.iscs.ShootAuton();
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