package com.sistefact.electronico.AppProperties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String validaNombre;

    public  String getValidaNombre() {
        return validaNombre;
    }

    public  void setValidaNombre(String validaNombre) {
        this.validaNombre = validaNombre;
    }
}
