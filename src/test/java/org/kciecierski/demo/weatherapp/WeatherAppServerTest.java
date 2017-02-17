package org.kciecierski.demo.weatherapp;

import com.google.common.collect.ImmutableMap;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.kciecierski.demo.weatherapp.WeatherAppServer.DEFAULT_PORT;

@RunWith(VertxUnitRunner.class)
public class WeatherAppServerTest {

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
    public void testIndexPageTitleProperty(TestContext context) {
        //when
        final Async async = context.async();

        vertx.createHttpClient().getNow(DEFAULT_PORT, "localhost", WeatherAppServer.INDEX_PATH,
                response -> response.handler(body -> {
                    //then
                    context.assertEquals(response.statusCode(), 200);
                    context.assertTrue(body.toString().contains("<title>Demo Weather App</title>"));
                    async.complete();
                }));


    }

    @Test
    public void testIndexPageTitlePropertyWhenDifferentFileConfigured(TestContext context) {
        //when
        final Async async = context.async();

        vertx.createHttpClient().getNow(DEFAULT_PORT, "localhost", WeatherAppServer.INDEX_PATH,
                response -> response.handler(body -> {
                    //then
                    context.assertEquals(response.statusCode(), 200);
                    context.assertTrue(body.toString().contains("<title>Demo Weather App</title>"));
                    async.complete();
                }));


    }
}