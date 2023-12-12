package com.example.sbpro.config.external;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


@ConfigurationProperties("sb-common")
@lombok.Data
public class SbCommonProperties {
    @NestedConfigurationProperty
    private Jwt jwt;
}
