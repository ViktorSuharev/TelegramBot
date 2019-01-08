package com.visu.telegram.bot.visubot;

import com.visu.telegram.bot.visubot.chuck.ChuckNorrisQuoteService;
import com.visu.telegram.bot.visubot.spring.SpringBootQuoteService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class VisuBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LogManager.getLogger(VisuBot.class);

    private static final String CHUCK_NORRIS_QUOTE_REQUEST_TAG = "chuck";
    private static final String SPRING_BOOT_QUOTE_REQUEST_TAG = "spring";

    private final String username;
    private final String token;

    public VisuBot(String username, String token, DefaultBotOptions defaultBotOptions) {
        super(defaultBotOptions);
        this.username = username;
        this.token = token;
    }
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    private synchronized void sendMsg(String chatId, String passed) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);

        String message = getMessage(passed);
        sendMessage.setText(message);
        setButtons(sendMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.log(Level.ERROR, "Exception: ", e.toString());
        }
    }

    private synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(CHUCK_NORRIS_QUOTE_REQUEST_TAG));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(SPRING_BOOT_QUOTE_REQUEST_TAG));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private String getMessage(String passed) {
        String message;
        if (CHUCK_NORRIS_QUOTE_REQUEST_TAG.equals(passed)) {
            ChuckNorrisQuoteService chuckNorrisQuoteService = new ChuckNorrisQuoteService();
            message = chuckNorrisQuoteService.getChuckNorrisQuote().getJoke();
        } else if (SPRING_BOOT_QUOTE_REQUEST_TAG.equals(passed)) {
            SpringBootQuoteService springBootQuoteService = new SpringBootQuoteService();
            message = springBootQuoteService.getSpringBootQuote().getQuote();
        } else {
            message = "Default message";
        }

        return message;
    }
}
