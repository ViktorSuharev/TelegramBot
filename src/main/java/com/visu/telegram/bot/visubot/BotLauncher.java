package com.visu.telegram.bot.visubot;

import com.visu.telegram.bot.visubot.config.BotProperties;
import com.visu.telegram.bot.visubot.config.ProxyProperties;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotLauncher {

    private final BotProperties botProperties;
    private final ProxyProperties proxyProperties;

    public BotLauncher(BotProperties botProperties, ProxyProperties proxyProperties) {
        this.botProperties = botProperties;
        this.proxyProperties = proxyProperties;
    }

    public void launch() {
        // Create the TelegramBotsApi object to register your bots
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register your newly created AbilityBot
        VisuBot bot = new VisuBot(botProperties.getUsername(), botProperties.getToken(), getDefaultBotOptions());

        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private DefaultBotOptions getDefaultBotOptions() {

        // Set up Http proxy
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        botOptions.setProxyHost(proxyProperties.getUrl());
        botOptions.setProxyPort(proxyProperties.getPort());
        // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        return botOptions;
    }
}
