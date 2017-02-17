package org.kciecierski.demo.weatherapp.file;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class FilePropertiesReaderTest {


    private FilePropertiesReader testedObject;


    @Test
    public void testFilePropertiesReaderWhenFileDoesNotExists() {
        //given
        testedObject = new FilePropertiesReader("test/non-existing-test.conf");

        //when
        final String propertyValue = testedObject.readProperty("test");

        //then
        assertThat(propertyValue, is(StringUtils.EMPTY));
    }

    @Test
    public void testFilePropertiesReaderWhenFileDoesExistAndThereIsNoProperty() {
        //given
        testedObject = new FilePropertiesReader("test/test1.config");

        //when
        final String propertyValue = testedObject.readProperty("test");

        //then
        assertThat(propertyValue, is(StringUtils.EMPTY));
    }

    @Test
    public void testFilePropertiesReaderWhenFileAndPropertyDoesExist() {
        //given
        testedObject = new FilePropertiesReader("test/test2.config");

        //when
        final String propertyValue = testedObject.readProperty("test");

        //then
        assertThat(propertyValue, is("apple"));
    }

    @Test
    public void testFilePropertiesReaderWhenThereAreTwoProperties() {
        //given
        testedObject = new FilePropertiesReader("test/test3.config");

        //when
        final String propertyValueOne = testedObject.readProperty("test1");
        final String propertyValueTwo = testedObject.readProperty("test2");

        //then
        assertThat(propertyValueOne, is("apple"));
        assertThat(propertyValueTwo, is("orange"));
    }


}
