package org.kciecierski.demo.weatherapp.service.openweather.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class SysJsonObject implements Validatable {

    @SerializedName("sunrise")
    private Integer sunRise;

    @SerializedName("sunset")
    private Integer sunSet;

    /**
     *
     * @return returns UNIX timestamp for sun rise
     */
    public Integer getSunRise() {
        return sunRise;
    }

    /**
     *
     * @return returns UNIX timestamp for sun set
     */
    public Integer getSunSet() {
        return sunSet;
    }

    @Override
    public boolean isValid() {
        return sunRise != null && sunSet != null;
    }
}
