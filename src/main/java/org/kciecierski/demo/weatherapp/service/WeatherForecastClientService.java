package org.kciecierski.demo.weatherapp.service;

import io.vertx.core.Future;
import org.kciecierski.demo.weatherapp.service.openweather.json.WeatherResponseJsonObject;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public interface WeatherForecastClientService {

    Future<WeatherResponseJsonObject> getWeatherData(String cityName);
}
