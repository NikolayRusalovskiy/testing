package testapi.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class City {
    private String city;

    public City() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public City(String city, String id, String name, String coord, String lat, String lon, String country) {
        this.city = city;
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coord='" + coord + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @JsonProperty("city")
    private String id;
    private String name;
    private String coord;
    private String lat;
    private String lon;
    private String country;

}
