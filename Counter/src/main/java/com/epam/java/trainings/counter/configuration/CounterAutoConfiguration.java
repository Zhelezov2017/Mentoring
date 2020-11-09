package com.epam.java.trainings.counter.configuration;

import com.epam.java.trainings.counter.Counter;
import com.epam.java.trainings.counter.parameter.CounterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_DRIVER;
import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_PASSWORD;
import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_URL;
import static com.epam.java.trainings.counter.parameter.CounterConfigurationParameters.DATA_SOURCE_USERNAME;

@Configuration
@ConditionalOnClass(Counter.class)
@EnableConfigurationProperties(CounterProperties.class)
public class CounterAutoConfiguration {

    @Autowired
    private CounterProperties counterProperties;

    @Bean
    @ConditionalOnMissingBean
    public CounterConfiguration counterConfiguration() {

        String userName = counterProperties.getDataSourceUsername() == null ? System.getProperty(DATA_SOURCE_USERNAME)
                : counterProperties.getDataSourceUsername();
        String password = counterProperties.getDataSourcePassword() == null ? System.getProperty("data.source.password")
                : counterProperties.getDataSourcePassword();
        String url = counterProperties.getDataSourceUrl() == null ? System.getProperty("data.source.url")
                : counterProperties.getDataSourceUrl();
        String driver = counterProperties.getDataSourceDriver() == null ? System.getProperty("data.source.driver")
                : counterProperties.getDataSourceDriver();

        CounterConfiguration counterConfiguration = new CounterConfiguration();
        counterConfiguration.put(DATA_SOURCE_USERNAME, userName);
        counterConfiguration.put(DATA_SOURCE_PASSWORD, password);
        counterConfiguration.put(DATA_SOURCE_URL, url);
        counterConfiguration.put(DATA_SOURCE_DRIVER, driver);

        return counterConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean
    public Counter counter(CounterConfiguration counterConfiguration) {
        return new Counter(counterConfiguration);
    }
}
