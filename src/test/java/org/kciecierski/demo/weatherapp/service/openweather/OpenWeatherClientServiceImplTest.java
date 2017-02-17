package org.kciecierski.demo.weatherapp.service.openweather;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kciecierski.demo.weatherapp.WeatherAppServer;
import org.kciecierski.demo.weatherapp.service.openweather.json.WeatherResponseJsonObject;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
@RunWith(VertxUnitRunner.class)
public class OpenWeatherClientServiceImplTest {

    private final String VALID_API_ID ="4f2219ce3e84511133b76809e0ba876b";

    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(WeatherAppServer.class.getName(),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testOpenWeatherClientServiceImplWhenCityNameIsEmpty() {

        //given
        final String cityName = "";

        //when
        OpenWeatherClientServiceImpl openWeatherClientService = new OpenWeatherClientServiceImpl(vertx, VALID_API_ID);
        Future<WeatherResponseJsonObject> weatherData = openWeatherClientService.getWeatherData(cityName);

        //then
        weatherData.setHandler(handler -> assertTrue(handler.failed()));

    }

    @Test
    public void testOpenWeatherClientServiceImplWhenCityNameIsNotValidAnd() {

        //given
        final String cityName = "Londooo";

        //when
        OpenWeatherClientServiceImpl openWeatherClientService = new OpenWeatherClientServiceImpl(vertx, VALID_API_ID);
        Future<WeatherResponseJsonObject> weatherData = openWeatherClientService.getWeatherData(cityName);

        //then
        weatherData.setHandler(handler -> assertTrue(handler.failed()));

    }

}
