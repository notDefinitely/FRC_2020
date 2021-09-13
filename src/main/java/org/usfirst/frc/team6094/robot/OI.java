/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6094.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	// Game controllers
	public Joystick Joy = new Joystick(0);
	public XboxController Xbox = new XboxController(1);

	// ISCS Buttons
	// Left Bumber
	public Button intakeOut = new JoystickButton(Xbox, 5);

	// Right Bumber
	public Button intakeIn = new JoystickButton(Xbox, 6);

	// Y Button
	public Button conveyorUp = new JoystickButton(Xbox, 4);

	// A Button
	public Button conveyorDown = new JoystickButton(Xbox, 1);

	// B Button
	public Button finalButton = new JoystickButton(Xbox, 2);

	// Test button
	public Button Test = new JoystickButton(Joy, 12);

	public Joystick getJoystick() {
		return Joy;
	}

	public XboxController getXboxController() {
		return Xbox;
	}
}