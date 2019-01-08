package com.visu.telegram.bot.visubot.spring;

import com.visu.telegram.bot.visubot.spring.dto.SpringBootQuote;
import com.visu.telegram.bot.visubot.spring.dto.SpringBootQuoteResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

public class SpringBootQuoteService {
    private static final Logger LOGGER = LogManager.getLogger(SpringBootQuoteService.class);

    private static final String SUCCESS_SPRING_BOOT_RESPONSE_TYPE = "success";
    private static final String RANDOM_SPRING_BOOT_QUOTE_URI = "https://gturnquist-quoters.cfapps.io/api/random";

    public SpringBootQuote getSpringBootQuote() {
        RestTemplate restTemplate = new RestTemplate();
        SpringBootQuoteResponse quoteResponse = restTemplate
                .getForObject(RANDOM_SPRING_BOOT_QUOTE_URI, SpringBootQuoteResponse.class);

        if (!SUCCESS_SPRING_BOOT_RESPONSE_TYPE.equals(quoteResponse.getType())) {
            LOGGER.error("Response type is {}. {} is unavailable now",
                    quoteResponse.getType(), RANDOM_SPRING_BOOT_QUOTE_URI);
            return null;
        }

        SpringBootQuote quote = quoteResponse.getValue();
        LOGGER.debug(quote);

        return quote;
    }
}
