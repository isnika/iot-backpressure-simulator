package node;

import model.SensorData;
import java.io.FileWriter;

public class CsvStorage {

    public synchronized void save(SensorData data) {
        try (FileWriter fw = new FileWriter("data.csv", true)) {
            fw.write(data.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}