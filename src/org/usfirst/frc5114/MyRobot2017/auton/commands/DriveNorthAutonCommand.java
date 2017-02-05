package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class DriveNorthAutonCommand extends AutonCmd {

	public DriveNorthAutonCommand(double percentVolt, double seconds)
    {
    	super(percentVolt, seconds, "Drive North");
    	
    	requires(Robot.driveTrain);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveTrain.driveNorth(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    	Robot.driveTrain.stop();
    }
}
