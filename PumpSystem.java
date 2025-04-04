import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.*;

public class PumpSystem {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Initializing board
        var arduinoObject = new FirmataDevice("COM5");
        try {
            arduinoObject.start();
            System.out.println("Board started");
            arduinoObject.ensureInitializationIsDone();
        } catch (Exception ex) {
            System.out.println("Couldn't connect to board");
            return;
        }

        // Pins
        var moistureSensor = arduinoObject.getPin(15);
        moistureSensor.setMode(Pin.Mode.ANALOG);

        var waterPump = arduinoObject.getPin(7);
        waterPump.setMode(Pin.Mode.OUTPUT);

        // OLED Display
        I2CDevice i2cObject = arduinoObject.getI2CDevice((byte) 0x3C);
        SSD1306 theOledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
        theOledObject.init();

        // Collection for storing moisture data
        List<Integer> moistureData = new ArrayList<>();

        // Timer tasks
        Timer timer = new Timer();
        timer.schedule(new SensorTask(moistureSensor, waterPump, theOledObject, moistureData), 0, 5000);
        timer.schedule(new GraphingTask(moistureData), 0, 5000);
    }
}
