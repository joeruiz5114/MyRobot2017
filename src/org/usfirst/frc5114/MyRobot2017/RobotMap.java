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

import org.usfirst.frc5114.MyRobot2017.subsystems.ArcadeDriveTrain;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SPI;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon frontRightMotor;
    public static CANTalon rearRightMotor;
    public static CANTalon frontLeftMotor;
    public static CANTalon rearLeftMotor;
    public static CANTalon liftMotor;
    public static CANTalon intakeBallMotor;
    public static CANTalon shooterLoadMotor;
    public static CANTalon shooterLaunchMotor;
    public static AHRS ahrs;
    
    public static RobotDrive driveTrainRobotDrive4;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontRightMotor = new CANTalon(1);
        LiveWindow.addActuator("DriveTrain", "Front Right Motor", frontRightMotor);
        
        rearRightMotor = new CANTalon(2);
        LiveWindow.addActuator("DriveTrain", "Rear Right Motor", rearRightMotor);
        
        frontLeftMotor = new CANTalon(3);
        LiveWindow.addActuator("DriveTrain", "Front Left Motor", frontLeftMotor);
        
        rearLeftMotor = new CANTalon(4);
        LiveWindow.addActuator("DriveTrain", "Rear Left Motor", rearLeftMotor);
        
        liftMotor = new CANTalon(5);
        LiveWindow.addActuator("Lift Motor", "Lift Motor", liftMotor);
        
        intakeBallMotor = new CANTalon(6);
        LiveWindow.addActuator("Ball Intake", "Ball Intake Motor", intakeBallMotor);
        
        shooterLoadMotor = new CANTalon(7);
        LiveWindow.addActuator("Shooter Load", "Shooter Load Motor", shooterLoadMotor);
        
        shooterLaunchMotor = new CANTalon(8);
        LiveWindow.addActuator("Shooter Launch", "Shooter Launch", shooterLaunchMotor);
        
        try{
        	ahrs = new AHRS(SPI.Port.kMXP);
        }
        catch(RuntimeException ex){
        	DriverStation.reportError("Gyro Error", true);
        }
        
        //front left, rear left, front right, rear right
        driveTrainRobotDrive4 = new RobotDrive(frontLeftMotor, rearLeftMotor,
              frontRightMotor, rearRightMotor);//left motors = 3&4, right motors = 1&2

      
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
