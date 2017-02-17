package org.kciecierski.demo.weatherapp;

import com.google.common.collect.ImmutableMap;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import org.kciecierski.demo.weatherapp.file.FilePropertiesReader;
import org.kciecierski.demo.weatherapp.service.WeatherForecastClientService;
import org.kciecierski.demo.weatherapp.service.data.CityWeatherData;
import org.kciecierski.demo.weatherapp.service.openweather.OpenWeatherClientServiceImpl;
import org.kciecierski.demo.weatherapp.service.openweather.json.WeatherResponseJsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.kciecierski.demo.weatherapp.utils.ParameterConstants.*;
import static org.kciecierski.demo.weatherapp.utils.TemplateConstants.*;

public class WeatherAppServer extends AbstractVerticle {

    protected static final int DEFAULT_PORT = 8081;

    protected static final String WEATHER_PATH = "/weather";

    protected static final String INDEX_PATH = "/";

    private static final Logger LOG = LoggerFactory.getLogger(WeatherAppServer.class);

    private static final HandlebarsTemplateEngine templateEngine = HandlebarsTemplateEngine.create();

    private static final FilePropertiesReader filePropertiesReader = new FilePropertiesReader("config.properties");

    private static final String HTTP_PORT = "http.port";

    @Override
    public void start(final Future<Void> fut) {
        final Router router = Router.router(vertx);

        final String title = filePropertiesReader.readProperty(TITLE_PARAMETER);

        handleIndexPage(router, title);

        final String weatherServiceApiId = filePropertiesReader.readProperty(API_ID_PARAMETER);
        final WeatherForecastClientService weatherForecastClientService = new OpenWeatherClientServiceImpl(vertx, weatherServiceApiId);

        handleWeatherResultsRequest(router, title, weatherForecastClientService);

        startServer(fut, router);
    }

    private void startServer(final Future<Void> fut, final Router router) {
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger(HTTP_PORT, DEFAULT_PORT),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }

    private void handleWeatherResultsRequest(final Router router, final String title, final WeatherForecastClientService weatherForecastClientService) {
        router.route().handler(BodyHandler.create());
        router.post(WEATHER_PATH).handler(routingContext -> {
            final String cityName = routingContext.request().getParam(CITY_NAME);
            final Future<WeatherResponseJsonObject> weatherData = weatherForecastClientService.getWeatherData(cityName);

            weatherData.setHandler(handler -> {
                if (handler.failed()) {
                    final Throwable cause = weatherData.cause();
                    LOG.error("Error during fetching weather data:", cause);
                    renderTemplate(ERROR_PAGE_TEMPLATE_PATH, ImmutableMap.of(MESSAGE_PARAMETER, cause), routingContext);
                } else if (handler.succeeded()) {
                    final CityWeatherData cityWeatherData = CityWeatherData.createFrom(handler.result());
                    final Map<String, Object> resultData = cityWeatherData.toMap();
                    resultData.put(TITLE_PARAMETER, title);
                    renderTemplate(RESULT_PAGE_TEMPLATE_PATH, resultData, routingContext);
                }
            });
        });
    }

    private void handleIndexPage(final Router router, final String title) {
        router.route(INDEX_PATH).handler(routingContext ->
                renderTemplate(INDEX_PAGE_TEMPLATE_PATH, ImmutableMap.of(TITLE_PARAMETER, title), routingContext));
    }

    private void renderTemplate(final String templatePath, final Map<String, Object> templateData, final RoutingContext routingContext) {
        templateData.forEach(routingContext::put);
        templateEngine.render(routingContext, templatePath, res -> {
            if (res.succeeded()) {
                routingContext.response().end(res.result());
            } else {
                routingContext.fail(res.cause());
            }
        });
    }
}