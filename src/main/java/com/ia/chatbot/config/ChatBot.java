package com.ia.chatbot.config;

import com.ia.chatbot.service.IaService;
import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatBot extends ListenerAdapter {
    @Value("${API_BOT}")
    private String API_BOT;

    private final IaService iaService;

    public ChatBot(IaService iaService) {
        this.iaService = iaService;
    }

    @PostConstruct
    public void inicializarBot(){
        JDABuilder.createDefault(API_BOT)
                .addEventListeners(this)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw().toLowerCase();

        if (content.startsWith("!ask")) {
            String ask = content.substring(5).trim();
            String response = iaService.getResponseIA(ask);
            event.getChannel().sendMessage(response).queue();
        }

    }
}
