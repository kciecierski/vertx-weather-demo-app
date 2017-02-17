package org.kciecierski.demo.weatherapp.utils;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class TemperatureConverterTest {


    @Test
    public void testConvertKelvinToCelsius() {
        assertThat(TemperatureConverter.convertKelvinToCelsius(100), is(-173));
        assertThat(TemperatureConverter.convertKelvinToCelsius(273.15), is(0));
        assertThat(TemperatureConverter.convertKelvinToCelsius(300), is(26));
    }


    @Test
    public void testConvertKelvinToFahrenheit() {
        assertThat(TemperatureConverter.convertKelvinToFahrenheit(0), is(-459));
        assertThat(TemperatureConverter.convertKelvinToFahrenheit(273.15), is(31));
        assertThat(TemperatureConverter.convertKelvinToFahrenheit(300), is(80));
    }


}
