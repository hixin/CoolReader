package hixin.com.github.coolreader.bean;

/**
 * Created by hixin on 2017/9/5.
 */

public class Weather {
    private String city;
    private String temp;
    private String weather;

    public Weather(String city, String temp, String weather) {
        this.city = city;
        this.temp = temp;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public String getTemp() {
        return temp;
    }

    public String getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", temp='" + temp + '\'' +
                ", weather='" + weather + '\'' +
                '}';
    }
}
