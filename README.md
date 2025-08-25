# Automated Plant Watering System

## Project Overview
This project creates an automated plant watering system that combines:
- Arduino-compatible hardware (Grove board) running Firmata
- Java program using Firmata4j for communication
- Soil moisture sensing and water pump control

The system monitors soil moisture levels and automatically waters the plant when the soil becomes too dry, helping maintain optimal growing conditions.

## Key Features
- Real-time soil moisture monitoring
- Automated water pump control
- Data display on Grove OLED screen
- Java-based control system
- Event-driven architecture

## Learning Outcomes Addressed
1. **Testing and Debugging**: Unit testing and voltage verification of soil moisture sensor
2. **API Implementation**: Using Firmata4j to interface with Arduino hardware
3. **Data Collection**: Using Java Collections (ArrayList/HashMap) for sensor data
4. **Event-Driven Programming**: State machine implementation for watering logic
5. **Object-Oriented Design**: Applying OOP principles in Java implementation

## Hardware Requirements
- Grove Beginner Kit for Arduino (or equivalent)
- Soil moisture sensor
- Water pump with tubing
- Water reservoir
- Plant with pot and soil
- Multimeter (for voltage testing)

## Software Requirements
- Java Development Kit (JDK)
- Firmata firmware on Arduino
- Firmata4j library
- Optional data visualization libraries (JFreeChart, Princeton StdLib)

## Installation & Setup
1. Upload StandardFirmata to your Arduino/Grove board
2. Clone this repository
3. Import required Java libraries
4. Connect hardware components:
   - Soil moisture sensor to analog pin
   - Water pump to digital pin
   - OLED display (if used)

## Arduino Grove Board Setup 

### Set up your Arduino or Grove board
1. Download the [Arduino IDE](https://www.arduino.cc/en/Main/Software)
2. Connect the Grove board to your computer
3. Open the Arduino IDE
4. Go to `Tools > Board` and select `Arduino Uno`
5. Go to `File > Examples > Firmata > StandardFirmata`
6. Compile and upload the sketch to your device
7. Identify your Arduino port:
   - Go to `Tools > Port`
   - Note which ports are listed
   - Disconnect the USB cable from your board
   - Check `Tools > Port` again - the missing port is your Arduino
   - Reconnect the USB cable and verify the port reappears
   - This is the port you'll use in Java

### Create a Java Project in IntelliJ for Firmata
1. Create a new Java project with a main class
2. Add required libraries via Maven:

#### For Windows 10/11 & macOS Apple Silicon (M1/M2/M3):
```xml
<dependencies>
    <!-- JSSC -->
    <dependency>
        <groupId>io.github.java-native</groupId>
        <artifactId>jssc</artifactId>
        <version>2.9.4</version>
    </dependency>
    
    <!-- Firmata4J -->
    <dependency>
        <groupId>com.github.kurbatov</groupId>
        <artifactId>firmata4j</artifactId>
        <version>2.3.8</version>
    </dependency>
    
    <!-- SLF4J -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-jcl</artifactId>
        <version>1.7.3</version>
    </dependency>
</dependencies>
```

#### For Older macOS Intel (pre-2021):
```xml
<dependencies>
    <!-- Firmata4J -->
    <dependency>
        <groupId>com.github.kurbatov</groupId>
        <artifactId>firmata4j</artifactId>
        <version>2.3.8</version>
    </dependency>
    
    <!-- SLF4J -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-jcl</artifactId>
        <version>1.7.3</version>
    </dependency>
</dependencies>
```

#### Alternative: Manual JAR Import
Download [Firmata4J v2.3.9 JAR](https://www.yorku.ca/professor/drsmith/2025/02/24/version-2-3-9-of-firmata4j/) and add to your project libraries.

## Technologies Used

- **Programming Language**: Java  
- **Hardware**: Arduino (UNO or compatible), Grove Seed Board  
- **Development Tools**: IntelliJ IDEA, Arduino IDE  
- **Libraries/Frameworks**: Firmata4J, JSSC, SLF4J  

## Usage
1. Run the Java application
2. The system will:
   - Continuously monitor soil moisture
   - Display current status on OLED
   - Activate pump when soil is dry
   - Log sensor data for analysis

## Project Structure
```
/src
  /main
    /java
      PlantWateringSystem.java   # Main control class
      SensorMonitor.java         # Handles sensor data
      WaterPumpController.java   # Manages pump operations
      DataLogger.java           # Handles data collection
/test
  /java
      SystemTests.java          # Unit tests
/docs
      report.pdf                # Final project report
      video.mp4                 # Demonstration video
```

## Testing
- Unit tests for critical functions
- Voltage measurements at different soil conditions:
  - Dry soil: ~2V
  - Wet soil: ~4V
- Graph of sensor values over time

## Resources
- [Project Example](https://www.yorku.ca/professor/drsmith/2024/04/01/main-project-example-for-eecs-1021/)
- [Firmata4j Documentation](https://github.com/kurbatov/firmata4j)
- [State Machine Guide](https://en.wikipedia.org/wiki/Finite-state_machine)

## License
Copyright © 2025 Auhona Basu - For educational use at York University only. Not for distribution.

---

**Solutions and Project by Auhona Basu**

**Note**: This project is part of EECS 1021 coursework at York University. All work must be original and not generated by AI tools per course requirements. You are free to refer but do not copy. Thank You.
