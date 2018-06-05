import org.jabotics.robot.en.*;
public class Main extends XRobot{
    
    static final IMotor leftMotor = objectFactory.createMotor(EMotorPort.C);
    static final IMotor rightMotor = objectFactory.createMotor(EMotorPort.B);
    static final IPilot pilot = objectFactory.createPilot(EMotorPort.C, EMotorPort.B);
    static final ITouchSensor enterButton = objectFactory.createTouchSensor(EButton.ENTER);
    static final ITouchSensor escapeButton = objectFactory.createTouchSensor(EButton.ESCAPE);
    static final ITouchSensor leftButton = objectFactory.createTouchSensor(EButton.LEFT);
    static final ITouchSensor rightButton = objectFactory.createTouchSensor(EButton.RIGHT);
    static final ITouchSensor touchSensor = objectFactory.createTouchSensor(ESensorPort.S1);
    static final ISensor soundSensor = objectFactory.createSoundSensor(ESensorPort.S2);
    static final ILightSensor lightSensor = objectFactory.createLightSensor(ESensorPort.S3);
    static final ISensor ultrasonicSensor = objectFactory.createUltrasonicSensor(ESensorPort.S4);
    static final ITimer timer = objectFactory.createTimer();
    static final IDisplay display = objectFactory.createDisplay();
    static final ISpeaker speaker = objectFactory.createSpeaker();
    public static void Main(String[] args){
        timer.wait(2000); // time to get out of the way after starting the program
        pilot.resetCounters();
        pilot.setTargetSpeed(10);
        pilot.moveStraight(FORWARD);
        ultrasonicSensor.waitUntilBelowLimit(50);
        pilot.stopMotion(WAIT);
        float distance = pilot.getTachoCount(); // tacho count is being stored in new float variable "distance"
        speaker.playBeepSequence();
        display.clear();
        display.write("Distance in cm:", 0, 0);
        display.write(distance, 0, 1);
        display.write("Press", 1, 3);
        display.write("escape", 2, 4);
        display.write("button!", 3, 5);
        escapeButton.waitForReleaseEvent();
    }
    
}