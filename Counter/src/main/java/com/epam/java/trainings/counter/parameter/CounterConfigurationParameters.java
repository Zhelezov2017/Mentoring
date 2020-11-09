package com.epam.java.trainings.counter.parameter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.epam.java.trainings")
public class CounterConfigurationParameters {
    public static final String DATA_SOURCE_USERNAME = "data.source.username";
    public static final String DATA_SOURCE_PASSWORD = "data.source.password";
    public static final String DATA_SOURCE_URL = "data.source.url";
    public static final String DATA_SOURCE_DRIVER = "data.source.driver";
}
