package org.kciecierski.demo.weatherapp.service.openweather.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class MainJsonObject implements Validatable {

    @SerializedName("temp")
    private Double temperature;

    /**
     *
     * @return temperature in Kelvin
     */
    public Double getTemperature() {
        return temperature;
    }

    @Override
    public boolean isValid() {
        return temperature != null;
    }
}
