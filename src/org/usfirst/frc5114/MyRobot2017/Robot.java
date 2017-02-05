// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5114.MyRobot2017;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.modes.DelayDriveStraight;
import org.usfirst.frc5114.MyRobot2017.commands.*;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    private Command autonomousCommand;
    private SendableChooser autoChooser;
    private boolean autonChooserIsNotBroken = true;
    private static double DEADBAND = 0.25;
    private static double INTAKEPOWER = 0.5;
    private static double NOPOWER = 0.0;
    private static boolean shoot = false;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static ArcadeDriveTrain driveTrain;
    public static Lift liftBot;
    public static BallIntake intakeBall;
    public static Shooter shootBall;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public void robotInit() {
    	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
    	//Initialize the Robot Hardware
    	RobotMap.init();
        
    	//Instantiate and initialize arcade drive
    	driveTrain = new ArcadeDriveTrain();
        driveTrain.init();
        
        //Instantiate Lift and Intake
        liftBot= new Lift();
        intakeBall = new BallIntake();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Delay Drive Straight", new DelayDriveStraight());
        
        SmartDashboard.putData("Auton Mode", autoChooser);
      //SmartDashboard.putNumber("Wait Time", 0.0);
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
    	
    	System.out.println();
    	System.out.println(autoChooser.getSelected() + " was selected");
    	System.out.println();
        
    	autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    
    
    public void teleopPeriodic() {
        
        Scheduler.getInstance().run();
        while (isOperatorControl() && isEnabled()) {
    		//RobotMap.driveTrainRobotDrive4.tankDrive(oi.rightJoystick,oi.leftJoystick);
        	
    		//RobotMap.driveTrainRobotDrive4.arcadeDrive(forward, turn);
        	RobotMap.driveTrainRobotDrive4.arcadeDrive(oi.driveGamePad, 1, oi.driveGamePad, 4);
        	
        	//Lift/Lower Bot if Y-axis is outside of the deadband
        	if(Math.abs(oi.controlGamePad.getY())>=DEADBAND){
        		liftBot.lift(oi.controlGamePad.getY());
        	}
        	else{
        		liftBot.lift(NOPOWER);
        	}
        	
    		if(oi.load.get()){
    			intakeBall.intake(-INTAKEPOWER);
    		}
    		else{
    			intakeBall.intake(NOPOWER);
    		}
    		
    		if(oi.launch.get()){
    			shoot^=shoot;
    		}
    		shootBall.launch(shoot?1.0:0.0);
    		    		
    		Timer.delay(0.01);

    	}
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }


    
    
    
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
