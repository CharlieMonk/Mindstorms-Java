

import org.jabotics.robot.en.*;

/**
 * Describe your class test here.
 * 
 * @author  (your name)
 * @version (a version number or date)
 */
public class Robot extends XRobot
{
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Definition of all objects that are necessary to control the robot in standard configuration
    ///////////////////////////////////////////////////////////////////////////////////////////////
    static final IMotor leftMotor = objectFactory.createMotor(EMotorPort.C);
    static final IMotor rightMotor = objectFactory.createMotor(EMotorPort.B);
    //static final IMotor sideMotor = objectFactory.createMotor(EMotorPort.A);
    static final IPilot pilot = objectFactory.createPilot(EMotorPort.B, EMotorPort.C);
    //static final IPilot pilot2 = objectFactory.createPilot(EMotorPort.A);
    //static final ITouchSensor enterButton = objectFactory.createTouchSensor(EButton.ENTER);
    static final ITouchSensor escapeButton = objectFactory.createTouchSensor(EButton.ESCAPE);
    //static final ISensor soundSensor = objectFactory.createSoundSensor(ESensorPort.S2);
    static final ILightSensor lightSensor = objectFactory.createLightSensor(ESensorPort.S1);
    static final ISensor ultrasonicSensor = objectFactory.createUltrasonicSensor(ESensorPort.S2);
    static final ITimer timer = objectFactory.createTimer();
    static final IDisplay display = objectFactory.createDisplay();
    static final ISpeaker speaker = objectFactory.createSpeaker();
    
     /**
     * The methode "main" (the "main program") is the starting point for the robot program.
     * In this method you will implement your own robot control.
     * Replace the sample code by your own instructions.
     */
    public static void main(String[] args)
    {
        boolean inside = true;
        pilot.resetCounters();
          
        lightSensor.switchLightOn();
        pilot.moveStraight(FORWARD);
        while(true){
            if(ultrasonicSensor.getMeasuredValue() < 200){
                pilot.setTargetSpeed(-255);
            } else {
                pilot.setTargetSpeed(0);
            }
            if(lightSensor.getMeasuredValue() < 40){
                pilot.setTargetSpeed(150);
                display.clear();
                display.write("Light value: " + lightSensor.getMeasuredValue(), 5,5);
            }
            if(escapeButton.isPressed()){
                pilot.setTargetSpeed(0);
                break;
            }
        }

    
    
        //sideMotor.setTargetSpeedToMax();
        // ultrasonicSensor.waitUntilBelowLimit(50);
        // pilot.setTargetSpeed(0);
        //display.clear();
        //display.write("Distance in cm:", 0, 0);

        escapeButton.waitForReleaseEvent();
    }
}
