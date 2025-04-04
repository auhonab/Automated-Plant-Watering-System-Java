import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

public class SensorTask extends TimerTask {
    private final Pin moistureSensor;
    private final Pin waterPump;
    private final SSD1306 theOledObject;
    private final List<Integer> moistureData;
    private boolean pumpState = false;

    public SensorTask(Pin moistureSensor, Pin waterPump, SSD1306 display, List<Integer> moistureData) {
        this.moistureSensor = moistureSensor;
        this.waterPump = waterPump;
        this.theOledObject = display;
        this.moistureData = moistureData;
    }

    @Override
    public void run() {
        try {
            int moistureValue = (int) moistureSensor.getValue();
            moistureData.add(moistureValue);

            theOledObject.getCanvas().clear();
            theOledObject.getCanvas().drawString(0, 0, "Moisture: " + moistureValue);

            // State machine logic
            if (moistureValue > 650 && !pumpState) {
                waterPump.setValue(1);
                pumpState = true;
                theOledObject.getCanvas().drawString(0, 9, "Pump is on.");
                theOledObject.getCanvas().drawString(0, 18, "SOIL IS DRY");
            } else if (moistureValue <= 650 && pumpState) {
                waterPump.setValue(0);
                pumpState = false;
                theOledObject.getCanvas().drawString(0, 9, "Pump is off.");
                theOledObject.getCanvas().drawString(0, 18, "SOIL IS WET");
            }

            theOledObject.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
