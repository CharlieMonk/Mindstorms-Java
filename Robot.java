

import org.jabotics.robot.en.*;

/**
 * Describe your class test here.
 * 
 * @author  (your name) 
 * @version (a version number or date)
 */
public class Robot extends XRobot
{
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
        pilot.resetCounters();
          
        lightSensor.switchLightOn();
        pilot.moveStraight(FORWARD);
        //pilot.setTargetSpeedToMax();
        int rotations = 0;
        while(true){
            float ultra = ultrasonicSensor.getMeasuredValue();
            display.write("Dist val: " + ultra, 1, 2);
            float light = lightSensor.getMeasuredValue();
            display.write("Light val: " + light, 1,1);
            if(ultra < 70){
                pilot.moveStraight(FORWARD);
                pilot.setTargetSpeedToMax();
            } else if(light < 40){
                pilot.moveStraight(BACKWARD);
                pilot.setTargetSpeedToMax();
                display.clear();
            } else {
                pilot.rotateByAngle(-25, EWaitingMode.START);
                pilot.setTargetSpeedToMax();
                rotations++;
                display.write("Rot: " + rotations, 1, 3);
                //pilot.setTargetSpeed(0);
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
