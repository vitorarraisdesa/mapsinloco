package arrais.vitor.mapinloco.entity;

/**
 * Created by Vitor on 23/07/2016.
 */
public class Location {

    public Double longitude;
    public Double latitude;
    public String weatherApiId;
    private String actualCity;

    public String getActualCity() {
        return actualCity;
    }

    public void setActualCity(String actualCity) {
        this.actualCity = actualCity;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String weatherApiId() {
        return weatherApiId;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void weatherApiId(String weatherApiId) {
        this.weatherApiId = weatherApiId;
    }
}
