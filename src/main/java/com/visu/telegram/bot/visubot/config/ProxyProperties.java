package com.visu.telegram.bot.visubot.config;

import lombok.Getter;
import lombok.Setter;

public class ProxyProperties {
    @Getter @Setter
    private String url;
    @Getter @Setter
    private int port;
    @Getter @Setter
    private String user;
    @Getter @Setter
    private String password;
}
