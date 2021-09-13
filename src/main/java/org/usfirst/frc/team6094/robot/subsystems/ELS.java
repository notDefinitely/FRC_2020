/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6094.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ELS extends Subsystem {
  VictorSPX victorSPX_E1 = new VictorSPX(11);
  VictorSPX victorSPX_E2 = new VictorSPX(12);

  VictorSPX victorSPX_L1 = new VictorSPX(9);
  VictorSPX victorSPX_L2 = new VictorSPX(10);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

  public void Elevator(XboxController Xbox) {
    if (java.lang.Math.abs(Xbox.getRawAxis(1)) > 0.2) {
      victorSPX_E1.set(ControlMode.PercentOutput, (Xbox.getRawAxis(1)) * .7);
      victorSPX_E2.set(ControlMode.PercentOutput, (Xbox.getRawAxis(1)) * .7);
    } else {
      victorSPX_E1.set(ControlMode.PercentOutput, 0);
      victorSPX_E2.set(ControlMode.PercentOutput, 0);
    }
  }

  public void Lift(XboxController Xbox) {
    if (java.lang.Math.abs(Xbox.getRawAxis(5)) > 0.2) {
      victorSPX_L1.set(ControlMode.PercentOutput, -Xbox.getRawAxis(5));
      victorSPX_L2.set(ControlMode.PercentOutput, Xbox.getRawAxis(5));
    } else {
      victorSPX_L1.set(ControlMode.PercentOutput, 0);
      victorSPX_L2.set(ControlMode.PercentOutput, 0);
    }
  }

  public void ELSStop() {
    victorSPX_L1.set(ControlMode.PercentOutput, 0);
    victorSPX_L2.set(ControlMode.PercentOutput, 0);
    victorSPX_E1.set(ControlMode.PercentOutput, 0);
    victorSPX_E2.set(ControlMode.PercentOutput, 0);
  }
}