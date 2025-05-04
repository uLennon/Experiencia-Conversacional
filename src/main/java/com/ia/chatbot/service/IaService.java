package com.ia.chatbot.service;

import com.cohere.api.Cohere;
import com.cohere.api.resources.v2.requests.V2ChatRequest;
import com.cohere.api.types.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IaService {
    @Value("${API_IA}")
    private String API_IA;

    public String getResponseIA(String text) {
        Cohere cohere = Cohere.builder().token(API_IA).clientName("user").build();

        ChatResponse response = cohere.v2().chat(V2ChatRequest.builder()
                .model("command-a-03-2025")
                .messages(List.of(ChatMessageV2.user(UserMessage.builder().content(UserMessageContent.of(text)).build()),
                        ChatMessageV2.assistant(AssistantMessage.builder().content(AssistantMessageContent.of("Furia"+"CS")).build())))
                        .build());

        return response.getMessage().getContent().flatMap(first->first.getFirst().getText().map(TextContent::getText)).orElse("Erro na resposta");
    }
}
