/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6094.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ISCS extends Subsystem {
  VictorSPX victorSPX_I1 = new VictorSPX(5);
  // VictorSPX victorSPX_I2 = new VictorSPX(6);

  VictorSPX victorSPX_S1 = new VictorSPX(7);

  VictorSP victorSP_C1 = new VictorSP(4);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

  public void ShootAuton() {
    victorSPX_S1.set(ControlMode.PercentOutput, 1);
  }

  public void EverythingElse() {
    victorSP_C1.set(.5);
    victorSPX_I1.set(ControlMode.PercentOutput, -6);
  }

  public void IntakeUp() {
    victorSPX_I1.set(ControlMode.PercentOutput, -.6);
    // victorSPX_I2.set(ControlMode.PercentOutput, .6);
  }

  public void IntakeDown() {
    victorSPX_I1.set(ControlMode.PercentOutput, .6);
    // victorSPX_I2.set(ControlMode.PercentOutput, -.6);
  }

  public void shooterDown(XboxController Xbox) {
    victorSPX_S1.set(ControlMode.PercentOutput, Xbox.getRawAxis(3));
  }

  public void conveyorUp() {
    victorSP_C1.set(0.5);
  }

  public void conveyorDown() {
    victorSP_C1.set(-0.5);
  }

  public void conveyorStop() {
    victorSP_C1.set(0);
  }

  public void IntakeStop() {
    victorSPX_I1.set(ControlMode.PercentOutput, 0);
  }

  public void ShooterStop() {
    victorSPX_S1.set(ControlMode.PercentOutput, 0);
  }

  public void ISCSStop() {
    victorSPX_I1.set(ControlMode.PercentOutput, 0);
    // victorSPX_I2.set(ControlMode.PercentOutput, 0);
    victorSPX_S1.set(ControlMode.PercentOutput, 0);
    victorSP_C1.set(0);
  }
}