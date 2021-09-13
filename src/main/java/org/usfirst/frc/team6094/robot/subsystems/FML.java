package org.usfirst.frc.team6094.robot.subsystems;

import java.lang.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;

@SuppressWarnings("unused")
public class FML extends Subsystem {

	VictorSPX finalMotor = new VictorSPX(6);

	protected void initDefaultCommand() {
	}

	public void finalMotorGo() {
		finalMotor.set(ControlMode.PercentOutput, -.8);
	}

	public void finalMotorStop() {
		finalMotor.set(ControlMode.PercentOutput, 0);
	}
}