package com.visu.telegram.bot.visubot.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpringBootQuoteResponse {
    private String type;
    @JsonProperty("value")
    private SpringBootQuote value;

    public SpringBootQuoteResponse() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SpringBootQuote getValue() {
        return value;
    }

    public void setValue(SpringBootQuote value) {
        this.value = value;
    }
}