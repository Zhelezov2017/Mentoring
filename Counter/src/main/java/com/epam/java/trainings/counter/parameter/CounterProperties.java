package com.epam.java.trainings.counter.parameter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.epam.java.trainings")
public class CounterProperties {
    public String dataSourceUsername;
    public String dataSourcePassword;
    public String dataSourceUrl;
    public String dataSourceDriver;
}
