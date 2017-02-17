package org.kciecierski.demo.weatherapp.service.openweather.json;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kciecierski.demo.weatherapp.service.openweather.json.exception.WeatherResponseJsonObjectException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class WeatherResponseJsonObjectTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsEmpty() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsEmptyJson() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsInvalidJson() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{aaa:aaa, {bbb}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsValidJsonButTemperatureIsMissing() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{weather: [\n" +
                "{\n" +
                "id: 300,\n" +
                "main: \"Drizzle\",\n" +
                "description: \"light intensity drizzle\",\n" +
                "icon: \"09d\"\n" +
                "}\n" +
                "]," +
                "main: {\n" +
                "pressure: 1012,\n" +
                "humidity: 81,\n" +
                "temp_min: 279.15,\n" +
                "temp_max: 281.15\n" +
                "}," +
                "sys: {\n" +
                "type: 1,\n" +
                "id: 5091,\n" +
                "message: 0.0103,\n" +
                "country: \"GB\",\n" +
                "sunrise: 1485762037,\n" +
                "sunset: 1485794875\n" +
                "}}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsValidJsonButSunriseIsMissing() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{weather: [\n" +
                "{\n" +
                "id: 300,\n" +
                "main: \"Drizzle\",\n" +
                "description: \"light intensity drizzle\",\n" +
                "icon: \"09d\"\n" +
                "}\n" +
                "]," +
                "main: {\n" +
                "temp: 280.32,\n" +
                "pressure: 1012,\n" +
                "humidity: 81,\n" +
                "temp_min: 279.15,\n" +
                "temp_max: 281.15\n" +
                "}," +
                "sys: {\n" +
                "type: 1,\n" +
                "id: 5091,\n" +
                "message: 0.0103,\n" +
                "country: \"GB\",\n" +
                "sunset: 1485794875\n" +
                "}," +
                "name: \"London\"}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsValidJsonButSunsetIsMissing() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{weather: [\n" +
                "{\n" +
                "id: 300,\n" +
                "main: \"Drizzle\",\n" +
                "description: \"light intensity drizzle\",\n" +
                "icon: \"09d\"\n" +
                "}\n" +
                "]," +
                "main: {\n" +
                "temp: 280.32,\n" +
                "pressure: 1012,\n" +
                "humidity: 81,\n" +
                "temp_min: 279.15,\n" +
                "temp_max: 281.15\n" +
                "}," +
                "sys: {\n" +
                "type: 1,\n" +
                "id: 5091,\n" +
                "message: 0.0103,\n" +
                "country: \"GB\",\n" +
                "sunrise: 1485762037,\n" +
                "}," +
                "name: \"London\"}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsValidJsonButDescriptionIsMissing() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{weather: [\n" +
                "{\n" +
                "id: 300,\n" +
                "main: \"Drizzle\",\n" +
                "icon: \"09d\"\n" +
                "}\n" +
                "]," +
                "main: {\n" +
                "temp: 280.32,\n" +
                "pressure: 1012,\n" +
                "humidity: 81,\n" +
                "temp_min: 279.15,\n" +
                "temp_max: 281.15\n" +
                "}," +
                "sys: {\n" +
                "type: 1,\n" +
                "id: 5091,\n" +
                "message: 0.0103,\n" +
                "country: \"GB\",\n" +
                "sunrise: 1485762037,\n" +
                "sunset: 1485794875\n" +
                "}," +
                "name: \"London\"}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsValidJsonButCityNameIsMissing() throws WeatherResponseJsonObjectException {
        exception.expect(WeatherResponseJsonObjectException.class);
        WeatherResponseJsonObject.createFrom("{weather: [\n" +
                "{\n" +
                "id: 300,\n" +
                "main: \"Drizzle\",\n" +
                "icon: \"09d\"\n" +
                "}\n" +
                "]," +
                "main: {\n" +
                "temp: 280.32,\n" +
                "pressure: 1012,\n" +
                "humidity: 81,\n" +
                "temp_min: 279.15,\n" +
                "temp_max: 281.15\n" +
                "}," +
                "sys: {\n" +
                "type: 1,\n" +
                "id: 5091,\n" +
                "message: 0.0103,\n" +
                "country: \"GB\",\n" +
                "sunrise: 1485762037,\n" +
                "sunset: 1485794875}}");
    }

    @Test
    public void testWeatherResponseJsonObjectCreationWhenResponseIsValidJsonAndAllDataPresent() throws WeatherResponseJsonObjectException {
        WeatherResponseJsonObject weatherResponseJsonObject = WeatherResponseJsonObject.createFrom("{weather: [\n" +
                "{\n" +
                "id: 300,\n" +
                "main: \"Drizzle\",\n" +
                "description: \"light intensity drizzle\",\n" +
                "icon: \"09d\"\n" +
                "}\n" +
                "]," +
                "main: {\n" +
                "temp: 280.32,\n" +
                "pressure: 1012,\n" +
                "humidity: 81,\n" +
                "temp_min: 279.15,\n" +
                "temp_max: 281.15\n" +
                "}," +
                "sys: {\n" +
                "type: 1,\n" +
                "id: 5091,\n" +
                "message: 0.0103,\n" +
                "country: \"GB\",\n" +
                "sunrise: 1485762037,\n" +
                "sunset: 1485794875\n" +
                "},\n" +
                "name: \"London\"}");
        assertThat(weatherResponseJsonObject.getCityName(), is("London"));
        assertTrue(weatherResponseJsonObject.getMainJsonObject().isValid());
        assertThat(weatherResponseJsonObject.getMainJsonObject().getTemperature(), is(280.32));
        assertTrue(weatherResponseJsonObject.getSysJsonObject().isValid());
        assertThat(weatherResponseJsonObject.getSysJsonObject().getSunRise(), is(1485762037));
        assertThat(weatherResponseJsonObject.getSysJsonObject().getSunSet(), is(1485794875));
        assertThat(weatherResponseJsonObject.getWeatherJsonObject().length, is(1));
        assertTrue(weatherResponseJsonObject.getWeatherJsonObject()[0].isValid());
        assertThat(weatherResponseJsonObject.getWeatherJsonObject()[0].getDescription(), is("light intensity drizzle"));
    }
}
