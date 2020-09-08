package com.example.demo.configuration;

import com.example.demo.client.ArrayPersistanceHttpClient;
import com.example.demo.service.INumbersService;
import com.example.demo.service.NumbersMemoryService;
import com.example.demo.service.NumbersPersistenceService;
import com.example.demo.utils.CacheHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.naming.ConfigurationException;

@Configuration
public class DefaultConfiguration {

    @Primary
    @Bean
    public INumbersService service(@Value("${storage.type}")String storageType) throws ConfigurationException {
        if (storageType.equals("memory"))
        {
            return new NumbersMemoryService();
        }
        else if (storageType.equals("db"))
        {
            return new NumbersPersistenceService();
        }
        else
        {
            throw new ConfigurationException("Storage type must be either 'memory' or 'db'");
        }

    }

}
