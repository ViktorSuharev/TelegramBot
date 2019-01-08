package com.visu.telegram.bot.visubot;

import com.visu.telegram.bot.visubot.config.BotProperties;
import com.visu.telegram.bot.visubot.config.ProxyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@SpringBootApplication
public class VisubotApplication {

	public static void main(String[] args) {
		BotProperties botProperties = new BotProperties();
		ProxyProperties proxyProperties = new ProxyProperties();
		// set properties

		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(proxyProperties.getUser(), proxyProperties.getPassword().toCharArray());
			}
		});

		ApiContextInitializer.init();
		SpringApplication.run(VisubotApplication.class, args);

		BotLauncher launcher = new BotLauncher(botProperties, proxyProperties);
		launcher.launch();
	}
}
