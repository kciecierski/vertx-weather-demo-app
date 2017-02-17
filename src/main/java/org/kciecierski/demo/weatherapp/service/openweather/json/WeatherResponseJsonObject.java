package org.kciecierski.demo.weatherapp.service.openweather.json;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;
import org.kciecierski.demo.weatherapp.service.openweather.json.exception.WeatherResponseJsonObjectException;

import java.util.List;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class WeatherResponseJsonObject {

    @SerializedName("name")
    private String cityName;

    @SerializedName("weather")
    private WeatherJsonObject[] weatherJsonObject;

    @SerializedName("main")
    private MainJsonObject mainJsonObject;

    @SerializedName("sys")
    private SysJsonObject sysJsonObject;

    /**
     * Creates POJO Json Object from JSON response, or throws error if it is not parsable
     *
     * @param response JSON response
     * @return POJO Json Object
     * @throws WeatherResponseJsonObjectException if JSON response is not parsable
     */
    public static WeatherResponseJsonObject createFrom(final String response) throws WeatherResponseJsonObjectException {
        try {
            if (StringUtils.isNotBlank(response)) {
                WeatherResponseJsonObject responseJsonObject = new Gson().fromJson(response, WeatherResponseJsonObject.class);
                if (responseJsonObject.isValid()) {
                    return responseJsonObject;
                } else {
                    throw new WeatherResponseJsonObjectException("Received data is not valid: " + response);
                }
            } else {
                throw new WeatherResponseJsonObjectException("Cannot receive data - empty response");
            }
        } catch (JsonSyntaxException e) {
            throw new WeatherResponseJsonObjectException("Cannot receive data - error during parsing data", e);
        }
    }

    public WeatherJsonObject[] getWeatherJsonObject() {
        return weatherJsonObject;
    }

    public MainJsonObject getMainJsonObject() {
        return mainJsonObject;
    }

    public SysJsonObject getSysJsonObject() {
        return sysJsonObject;
    }

    public String getCityName() {
        return cityName;
    }

    private boolean isValid() {
        if (StringUtils.isBlank(cityName) || weatherJsonObject == null || weatherJsonObject.length == 0
                || sysJsonObject == null || mainJsonObject == null) {
            return false;
        }
        final List<Validatable> validatables = Lists.newArrayList(weatherJsonObject);
        validatables.add(sysJsonObject);
        validatables.add(mainJsonObject);
        return validatables.stream().allMatch(Validatable::isValid);
    }

}
