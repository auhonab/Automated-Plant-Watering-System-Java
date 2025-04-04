import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;
import java.util.TimerTask;

public class GraphingTask extends TimerTask {
    private final List<Integer> moistureData;

    public GraphingTask(List<Integer> moistureData) {
        this.moistureData = moistureData;
        SwingUtilities.invokeLater(this::createGraph);
    }

    @Override
    public void run() {
        createGraph();
    }

    private void createGraph() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < moistureData.size(); i++) {
            double voltage = (moistureData.get(i) / 1023.0) * 5; // Convert to voltage
            dataset.addValue(voltage, "Voltage (V)", String.valueOf(i)); // Time as x-axis
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Soil Moisture Sensor: Voltage vs Time", // Chart title
                "Time (Seconds)", // X-axis label
                "Voltage (V)", // Y-axis label
                dataset, PlotOrientation.VERTICAL, true, true, false);

        JFrame frame = new JFrame("Moisture Sensor Voltage Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new ChartPanel(lineChart));
        frame.setVisible(true);
    }
}
