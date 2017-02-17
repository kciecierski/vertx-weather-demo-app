package org.kciecierski.demo.weatherapp.file;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Kamil Ciecierski on 2/17/2017.
 */
public class FilePropertiesReader {

    private static final Logger LOG = LoggerFactory.getLogger(FilePropertiesReader.class);

    private final String fileName;

    public FilePropertiesReader(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns property value from file or empty string, if file or property does not exists
     * @param propertyName
     * @return
     */
    public String readProperty(final String propertyName) {
        InputStream input = null;

        try {
            input = new FileInputStream(fileName);
            Properties properties = new Properties();
            properties.load(input);
            return StringUtils.defaultString(properties.getProperty(propertyName), StringUtils.EMPTY);
        } catch (IOException e) {
            LOG.error("Error during reading file {}", fileName, e);
        } finally {
            if (input != null) {
                IOUtils.closeQuietly(input);
            }
        }

        return StringUtils.EMPTY;
    }
}

