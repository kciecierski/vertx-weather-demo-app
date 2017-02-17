package org.kciecierski.demo.weatherapp.service.openweather.json.exception;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class WeatherResponseJsonObjectException extends Exception {

    public WeatherResponseJsonObjectException(final String message) {
        super(message);
    }

    public WeatherResponseJsonObjectException(final String message, Throwable e) {
        super(message, e);
    }
}
