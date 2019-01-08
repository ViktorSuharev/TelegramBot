package com.visu.telegram.bot.visubot.chuck.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChuckQuoteResponse {
    private String type;
    @JsonProperty("value")
    private ChuckQuote value;

    public ChuckQuoteResponse() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ChuckQuote getValue() {
        return value;
    }

    public void setValue(ChuckQuote value) {
        this.value = value;
    }
}
