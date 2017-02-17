package org.kciecierski.demo.weatherapp.service.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kciecierski.demo.weatherapp.service.openweather.json.WeatherResponseJsonObject;
import org.kciecierski.demo.weatherapp.utils.TemperatureConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class CityWeatherData {

    protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String TWELVE_HOUR_TIME_FORMAT = "hh:mm a";

    private final String cityName;

    private final String date;

    private final String description;

    private final int fahrenheitTemperature;

    private final int celsiusTemperature;

    private final String sunriseTime;

    private final String sunsetTime;

    public CityWeatherData(final String cityName, final String date, final String description,
                           final int celsiusTemperature, final int fahrenheitTemperature,
                           final String sunriseTime, final String sunsetTime) {
        this.cityName = cityName;
        this.date = date;
        this.description = description;
        this.celsiusTemperature = celsiusTemperature;
        this.fahrenheitTemperature = fahrenheitTemperature;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getFahrenheitTemperature() {
        return fahrenheitTemperature;
    }

    public int getCelsiusTemperature() {
        return celsiusTemperature;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    /**
     * Creates object of this class from WeatherResponseJsonObject
     *
     * @param weatherResponseJsonObject
     * @return
     */
    public static CityWeatherData createFrom(final WeatherResponseJsonObject weatherResponseJsonObject) {
        final String description = weatherResponseJsonObject.getWeatherJsonObject()[0].getDescription();
        final double kelvinTemperature = weatherResponseJsonObject.getMainJsonObject().getTemperature();
        final int celsiusTemperature = TemperatureConverter.convertKelvinToCelsius(kelvinTemperature);
        final int fahrenheitTemperature = TemperatureConverter.convertKelvinToFahrenheit(kelvinTemperature);
        final String sunRiseTime = getFormattedTime(weatherResponseJsonObject.getSysJsonObject().getSunRise());
        final String sunsetTime = getFormattedTime(weatherResponseJsonObject.getSysJsonObject().getSunSet());
        return new CityWeatherData(weatherResponseJsonObject.getCityName(), getCurrentDate(), description,
                celsiusTemperature, fahrenheitTemperature, sunRiseTime, sunsetTime);
    }

    /**
     * Converts this object to map of key value <String,Object> base on its fields/getters
     *
     * @return
     */
    public Map<String, Object> toMap() {
        return MAPPER.convertValue(this, new TypeReference<Map<String, Object>>() {
        });
    }


    private static String getFormattedTime(final Integer timestamp) {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(TWELVE_HOUR_TIME_FORMAT);
        return sdf.format(date);
    }


    private static String getCurrentDate() {
        return DATE_FORMAT.format(new Date());
    }

}
