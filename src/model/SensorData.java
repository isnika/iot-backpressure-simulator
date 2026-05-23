package model;

public class SensorData {
    public int sensorId;
    public long timestamp;
    public double temperature, humidity, pressure;

    public SensorData() {}

    public SensorData(int sensorId, double temp, double hum, double press) {
        this.sensorId = sensorId;
        this.timestamp = System.currentTimeMillis();
        this.temperature = temp;
        this.humidity = hum;
        this.pressure = press;
    }

    @Override
    public String toString() {
        return sensorId + "," + timestamp + "," + temperature + "," + humidity + "," + pressure;
    }
}