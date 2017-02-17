package org.kciecierski.demo.weatherapp.service.openweather;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import org.apache.commons.lang3.StringUtils;
import org.kciecierski.demo.weatherapp.service.WeatherForecastClientService;
import org.kciecierski.demo.weatherapp.service.openweather.json.WeatherResponseJsonObject;
import org.kciecierski.demo.weatherapp.service.openweather.json.exception.WeatherResponseJsonObjectException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class OpenWeatherClientServiceImpl implements WeatherForecastClientService {

    private static final String CITY_QUERY_PATTERN = "/data/2.5/weather?q=%s&appid=%s";


    private static final String OPEN_WEATHER_HOST = "api.openweathermap.org";

    private static final int OPEN_WEATHER_PORT = 80;

    private static final int OK_CODE = 200;

    private final HttpClient httpClient;

    private final String openWeatherAppId;

    public OpenWeatherClientServiceImpl(final Vertx vertx, final String openWeatherAppId) {
        httpClient = vertx.createHttpClient();
        this.openWeatherAppId = openWeatherAppId;
    }

    @Override
    public Future<WeatherResponseJsonObject> getWeatherData(final String cityName) {
        final Future<WeatherResponseJsonObject> openWeatherServiceResponseFuture = Future.future();
        if (StringUtils.isBlank(cityName)) {
            openWeatherServiceResponseFuture.fail("Cannot retrieve weather: city name is missing");
        }
        try {
            final String encodedCityName = URLEncoder.encode(cityName, "utf-8");
            final String encodedAppId = URLEncoder.encode(openWeatherAppId, "utf-8");
            final String url = String.format(Locale.ROOT, CITY_QUERY_PATTERN, encodedCityName, encodedAppId);
            httpClient.getNow(OPEN_WEATHER_PORT, OPEN_WEATHER_HOST, url, httpClientResponse -> {
                handleServiceResponse(openWeatherServiceResponseFuture, httpClientResponse);
            });

        } catch (UnsupportedEncodingException e) {
            openWeatherServiceResponseFuture.fail(e);
        }
        return openWeatherServiceResponseFuture;
    }

    private void handleServiceResponse(final Future<WeatherResponseJsonObject> openWeatherServiceResponseFuture, final HttpClientResponse httpClientResponse) {
        if (httpClientResponse.statusCode() != OK_CODE) {
            openWeatherServiceResponseFuture.fail("Error returned: " + httpClientResponse.statusCode()
                    + " " + httpClientResponse.statusMessage());
        }
        httpClientResponse.bodyHandler(buffer -> {
            final String serviceResponse = buffer.getString(0, buffer.length());
            try {
                final WeatherResponseJsonObject responseJsonObject = WeatherResponseJsonObject.createFrom(serviceResponse);
                openWeatherServiceResponseFuture.complete(responseJsonObject);
            } catch (WeatherResponseJsonObjectException e) {
                openWeatherServiceResponseFuture.fail(e);
            }

        });
    }

}
