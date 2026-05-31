package node;

import model.SensorData;

import java.io.File;
import java.io.FileWriter;

public class CsvStorage {

    private static final String FILE_PATH = "out/data.csv";

    public CsvStorage() {

        // tạo thư mục out nếu chưa có
        new File("out").mkdirs();

        // tạo file và ghi header nếu chưa tồn tại
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {

                fw.write(
                        "timestamp,sensorId,temp,humidity,pressure\n"
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void save(SensorData data) {

        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {

            fw.write(
                    System.currentTimeMillis() + "," +
                            data.sensorId + "," +
                            data.temp + "," +
                            data.humidity + "," +
                            data.pressure + "\n"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}