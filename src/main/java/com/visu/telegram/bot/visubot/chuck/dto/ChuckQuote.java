package com.visu.telegram.bot.visubot.chuck.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChuckQuote {

    private BigInteger id;
    private String joke;
    private Set<String> categories;

    public ChuckQuote() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ChuckQuote{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", categories=" + categories +
                '}';
    }
}
