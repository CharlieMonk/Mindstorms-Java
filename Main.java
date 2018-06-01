import lejos.nxt.UltrasonicSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;

public static void main(String args[]){
    // Fill these in with ultrasonic and light ports!
    ultraPort = 0;
    lightPort = 0;

    UltrasonicSensor ultra = new UltrasonicSensor(ultraPort);
    ultra.continuous();
    LightSensor light = new LightSensor(lightPort);

    while(true){
        int dist = ultra.getDistance();
        int lightAmt = light.getLightValue();
        if(dist != 255 && lightAmt < 90){
            // don't do anything if you can't see anything or are on edge!
            // (ultra fake val is 255, >90 light is surely white)
            Motor.A.setSpeed(720);
            Motor.B.setSpeed(720);

        }

    }
}
