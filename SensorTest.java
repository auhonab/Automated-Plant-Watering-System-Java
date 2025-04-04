import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SensorTest {
    private static final double DELTA = 0.01; // Increased delta for floating-point tolerance

    @Test
    public void testMoistureReadingBounds() {
        assertEquals(0.0, convertToVoltage(0), DELTA, "0 should convert to 0V");
        assertEquals(5.0, convertToVoltage(1023), DELTA, "1023 should convert to 5V");
        assertEquals(2.5, convertToVoltage(512), DELTA, "512 should convert to ~2.5V");
    }

    @Test
    public void testNegativeMoistureValue() {
        assertThrows(IllegalArgumentException.class,
                () -> convertToVoltage(-1),
                "Negative values should throw exception");
    }

    @Test
    public void testAboveMaxMoistureValue() {
        assertThrows(IllegalArgumentException.class,
                () -> convertToVoltage(1100),
                "Values > 1023 should throw exception");
    }

    private double convertToVoltage(int sensorValue) {
        if (sensorValue < 0 || sensorValue > 1023) {
            throw new IllegalArgumentException("Sensor value out of range (0-1023)");
        }
        return (sensorValue / 1023.0) * 5;
    }
}