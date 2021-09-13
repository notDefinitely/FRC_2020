/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6094.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auton extends CommandGroup {

  public Auton() {   
    addSequential(new StopCommand(), 0);
    //addSequential(new BackToLineCommand(), 200);
    addSequential(new InitShooterCommand(), 3.5);
    addSequential(new ShootCommand(), 2);
    addSequential(new LoadCommand(), 2);
    addSequential(new ShootCommand(), 2);
    addSequential(new LoadCommand(), 2);
    addSequential(new ShootCommand(), 2);
    //addSequential(new CrabRightCommand(), 3.5);
    //addSequential(new BackDriveCommand(), .4);
    addSequential(new StopCommand(), 0);
  }
}