package com.visu.telegram.bot.visubot.chuck;

import com.visu.telegram.bot.visubot.chuck.dto.ChuckQuote;
import com.visu.telegram.bot.visubot.chuck.dto.ChuckQuoteResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

public class ChuckNorrisQuoteService {
    private static final Logger LOGGER = LogManager.getLogger(ChuckNorrisQuoteService.class);

    private static final String SUCCESS_CHUCK_NORRIS_RESPONSE_TYPE = "success";
    private static final String RANDOM_CHUCK_NORRIS_QUOTE_URI = "http://api.icndb.com/jokes/random";

    public ChuckQuote getChuckNorrisQuote() {
        RestTemplate restTemplate = new RestTemplate();
        ChuckQuoteResponse quoteResponse = restTemplate
                .getForObject(RANDOM_CHUCK_NORRIS_QUOTE_URI, ChuckQuoteResponse.class);

        if (!SUCCESS_CHUCK_NORRIS_RESPONSE_TYPE.equals(quoteResponse.getType())) {
            LOGGER.error("Response type is {}. {} is unavailable now",
                    quoteResponse.getType(), RANDOM_CHUCK_NORRIS_QUOTE_URI);
            return null;
        }

        ChuckQuote quote = quoteResponse.getValue();
        LOGGER.debug(quote);

        return quote;
    }
}
