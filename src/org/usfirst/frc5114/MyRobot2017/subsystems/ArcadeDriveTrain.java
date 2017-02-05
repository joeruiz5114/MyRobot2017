// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.commands.*;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import java.lang.String;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ArcadeDriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon frontRightMotor = RobotMap.frontRightMotor;
    private final CANTalon rearRightMotor = RobotMap.rearRightMotor;
    private final CANTalon frontLeftMotor = RobotMap.frontLeftMotor;
    private final CANTalon rearLeftMotor = RobotMap.rearLeftMotor;
    
    private final RobotDrive robotDrive = RobotMap.driveTrainRobotDrive4;
    
    private final AHRS gyro = RobotMap.ahrs;
        
    private double power = 0.8;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void init(){
    	
    	//Configuration of RobotDrive class
    	robotDrive.setSafetyEnabled(true);
    	robotDrive.setExpiration(0.1);
    	robotDrive.setSensitivity(0.25);
    	robotDrive.setMaxOutput(1);
    	gyro.getYaw();
    	RobotMap.ahrs.zeroYaw();
    	
    	//Configure Motor Direction for Arcade Drive
    	//Based on the direction that the motors are installed on the drive chassis, verify at each robot design
    	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
    	robotDrive.setInvertedMotor(MotorType.kRearRight, true);
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
    	
    	//Configure Encoders for driving in Arcade
    	//Encoders must be configured such that if Talon SRX Throttle (+), then the Sensor Position moves (+)
    	//Section 7 of SRX Programming Guide
    	frontLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	frontRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	frontLeftMotor.reverseSensor(true);
    	//frontRightMotor.reverseSensor(true);
    	
    	//Configure Follower mode since there is only one encoder one the left and the right
    	//Front Motors are the Masters, Rears are the slaves
    	frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
    	frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
    	rearLeftMotor.changeControlMode(TalonControlMode.Follower);
    	rearLeftMotor.set(frontLeftMotor.getDeviceID());
    	rearRightMotor.changeControlMode(TalonControlMode.Follower);
    	rearRightMotor.set(frontRightMotor.getDeviceID());
    	
      	SmartDashboard.putString("Left Motor ID:", Integer.toString(frontLeftMotor.getDeviceID()));
    	SmartDashboard.putString("Right Motor ID:",  Integer.toString(frontRightMotor.getDeviceID()));
    	
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithGamePad());
    }
    
    public void takeGamePadInput(Joystick controller) 
    {
    	robotDrive.arcadeDrive(controller);
    }
   
    public void zeroSensor()
    {
    	frontLeftMotor.setPosition(0.0);
    	
    }
    /*
    public void driveNorth(double percentVolt)
    {
    	power = percentVolt;
    	frontLeftMotor.set(power);
    	frontRightMotor.set(-power);
    }
    
    public void driveSouth(double percentVolt)
    {
    	power = -percentVolt;
    	frontLeftMotor.set(power);
    	frontRightMotor.set(-power);
    }
    */
    public void stop()
    {
    	robotDrive.drive(0, 0);
    }
}
