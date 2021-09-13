/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6094.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6094.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6094.robot.subsystems.ELS;
import org.usfirst.frc.team6094.robot.subsystems.FML;
import org.usfirst.frc.team6094.robot.subsystems.ISCS;
import org.usfirst.frc.team6094.robot.commands.Auton;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

@SuppressWarnings("unused")
public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain drivetrain;
	public static ELS els;
	public static ISCS iscs;
	public static FML fml;

	Command Autonomous;

	Thread Camera_0;

	@Override
	public void robotInit() {
		drivetrain = new DriveTrain();
		oi = new OI();
		fml = new FML();
		els = new ELS();
		iscs = new ISCS();

		Camera_0 = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();

			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat. If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}

				// Put rectangles on the image
				Imgproc.rectangle(mat, new Point(250, 80), new Point(350, 180), new Scalar(200, 200, 200), 2);
				Imgproc.rectangle(mat, new Point(300, 250), new Point(360, 315), new Scalar(200, 200, 200), 2);

				// Da lines
				// L1 = 3.4x - 770
				Imgproc.line(mat, new Point(226.47, 0), new Point(373.76, 500.79), new Scalar(110, 50, 110), 2);
				// L2 = 13.5x - 4545
				Imgproc.line(mat, new Point(336.66, 0), new Point(373.76, 500.79), new Scalar(110, 50, 110), 2);

				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		Camera_0.setDaemon(true);
		Camera_0.start();
	}

	@Override
	public void autonomousInit() {
		Autonomous = new Auton();
		Autonomous.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void teleopInit() {
		if (Autonomous != null) {
			Autonomous.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}