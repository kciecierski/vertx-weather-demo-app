package org.kciecierski.demo.weatherapp.service.openweather.json;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class WeatherJsonObject implements Validatable {

    private String description;

    /**
     *
     * @return weather description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(description);
    }
}
