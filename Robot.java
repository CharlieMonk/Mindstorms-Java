
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
    static final IMotor sideMotor = objectFactory.createMotor(EMotorPort.A);
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
    
    public static void onEdge(){
        pilot.stopMotion(START);
        pilot.setTargetSpeedToMax();
        // leftMotor.stopRotation(START);
        // rightMotor.stopRotation(START);
        pilot.moveStraight(-10, START);
    }
    public static void main(String[] args) throws InterruptedException
    {
        //Thread.sleep(5000);
        pilot.resetCounters();
        lightSensor.switchLightOn();
        pilot.moveStraight(FORWARD);
        //pilot.setTargetSpeedToMax();
        int rotations = 0;
        while(true){
            int iteration = 0;
            float ultra = ultrasonicSensor.getMeasuredValue();
            display.write("Dist val: " + ultra, 1, 2);
            float light = lightSensor.getMeasuredValue();
            display.write("Light val: " + light, 1,1);
            float side = sideMotor.getPosition();
            display.write("Side val: " + side, 1, 4);
            if(light < 40 && light != 0.0){
                onEdge();
                display.clear();
            } else if(ultra < 100){
                if(ultra  < 20){
                    pilot.moveStraight(FORWARD);
                    pilot.setTargetSpeedToMax();
                    sideMotor.rotateToPosition((float)-100, START);
                    sideMotor.setTargetSpeedToMax();
                    //sideMotor.rotateToPosition(-100, WAIT);                
                } else {
                    pilot.moveCurvature((float)-5, FORWARD);
                    display.write("Moving curved", 1, 5);
                    //sideMotor.setTargetSpeedToMax();
                    pilot.setTargetSpeedToMax();
                }
            } else {
                if(Math.abs(sideMotor.getPosition()) > 50){
                    //sideMotor.rotateTime(100, FORWARD, WAIT);
                    sideMotor.rotateToPosition((float)0, START);
                }
                //pilot.rotateByAngle(-50, EWaitingMode.START);
                leftMotor.rotate(FORWARD);
                leftMotor.setTargetSpeed(150);
                rightMotor.rotate(BACKWARD);
                rightMotor.setTargetSpeed(150);
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
        escapeButton.waitForReleaseEvent();
    }
}
