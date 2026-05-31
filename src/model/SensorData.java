package model;

public class SensorData {

    public int sensorId;
    public double temp;
    public double humidity;
    public double pressure;

    public SensorData(
            int sensorId,
            double temp,
            double humidity,
            double pressure
    ) {
        this.sensorId = sensorId;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}