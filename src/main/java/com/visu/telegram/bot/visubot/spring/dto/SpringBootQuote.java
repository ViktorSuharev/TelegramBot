package com.visu.telegram.bot.visubot.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpringBootQuote {
    private BigInteger id;
    private String quote;

    public SpringBootQuote() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "SpringBootQuote{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
