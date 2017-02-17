package org.kciecierski.demo.weatherapp.service.data;

import org.junit.Test;
import org.kciecierski.demo.weatherapp.service.openweather.json.WeatherResponseJsonObject;
import org.kciecierski.demo.weatherapp.service.openweather.json.exception.WeatherResponseJsonObjectException;

import java.util.Date;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.kciecierski.demo.weatherapp.service.data.CityWeatherData.DATE_FORMAT;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class CityWeatherDataTest {

    @Test
    public void testCityWeatherDataWhenCreatedFromWeatherResponseJsonObject() throws WeatherResponseJsonObjectException {
        //given
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

        //when
        CityWeatherData testedObject = CityWeatherData.createFrom(weatherResponseJsonObject);

        //then
        assertThat(testedObject.getCelsiusTemperature(), is(7));
        assertThat(testedObject.getFahrenheitTemperature(), is(44));
        assertThat(testedObject.getDescription(), is("light intensity drizzle"));
        assertThat(testedObject.getSunriseTime(), is("08:40 AM"));
        assertThat(testedObject.getSunsetTime(), is("05:47 PM"));
        assertThat(testedObject.getCityName(), is("London"));
        assertThat(testedObject.getDate(), is(DATE_FORMAT.format(new Date())));
    }

    @Test
    public void testCityWeatherDataWhenConvertedToMap() throws WeatherResponseJsonObjectException {
        //given
        CityWeatherData testedObject = new CityWeatherData("London", "12/12/2012", "drizzle", -1, 12, "09:40 AM", "04:47 PM");

        //when
        Map<String, Object> stringObjectMap = testedObject.toMap();

        //then
        assertThat(stringObjectMap.size(), is(7));
        assertThat(stringObjectMap.get("cityName"), is("London"));
        assertThat(stringObjectMap.get("date"), is("12/12/2012"));
        assertThat(stringObjectMap.get("description"), is("drizzle"));
        assertThat(stringObjectMap.get("celsiusTemperature"), is(-1));
        assertThat(stringObjectMap.get("fahrenheitTemperature"), is(12));
        assertThat(stringObjectMap.get("sunriseTime"), is("09:40 AM"));
        assertThat(stringObjectMap.get("sunsetTime"), is("04:47 PM"));
    }

}
