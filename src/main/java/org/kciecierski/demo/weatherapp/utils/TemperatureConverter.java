package org.kciecierski.demo.weatherapp.utils;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class TemperatureConverter {

    private TemperatureConverter() {
        //util class
    }

    /**
     * Converts temperature value in Kelvin to Celsius
     *
     * @param kelvinTemperature
     * @return int Celsius temperature
     */
    public static int convertKelvinToCelsius(final double kelvinTemperature) {
        return (int) (kelvinTemperature - 273.15);
    }

    /**
     * Converts temperature value in Kelvin to Fahrenheit
     *
     * @param kelvinTemperature
     * @return int Fahrenheit temperature
     */
    public static int convertKelvinToFahrenheit(final double kelvinTemperature) {
        return (int) (kelvinTemperature * 9 / 5. - 459.67);
    }

}