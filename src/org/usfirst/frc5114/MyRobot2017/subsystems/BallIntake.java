package org.usfirst.frc5114.MyRobot2017.subsystems;


import org.usfirst.frc5114.MyRobot2017.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class BallIntake extends Subsystem
{
	private final CANTalon intakeMotor = RobotMap.intakeBallMotor;

	public void initDefaultCommand() {
			
	}
	
	public void intake(double x){
		intakeMotor.set(x);
	}
}	
		
