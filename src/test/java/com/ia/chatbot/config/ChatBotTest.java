package com.ia.chatbot.config;

import com.ia.chatbot.service.IaService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatBotTest {

    @Mock
    private IaService iaService;

    @Mock
    private MessageReceivedEvent event;

    @Mock
    private Message message;

    @Mock
    private User author;

    @Mock
    private MessageChannelUnion channel;

    @Mock
    private MessageCreateAction messageCreateAction;

    @InjectMocks
    private ChatBot chatBot;

    @Test
    void deveIgnorarMensagensDeBots() {
        User authorMock = mock(User.class);
        when(authorMock.isBot()).thenReturn(true);
        when(event.getAuthor()).thenReturn(authorMock);

        chatBot.onMessageReceived(event);

        verifyNoInteractions(iaService);
        verify(channel, never()).sendMessage(anyString());
    }

    @Test
    void deveIgnorarMensagensQueNaoComecamComComandoAsk() {
        User author = mock(User.class);
        Message message = mock(Message.class);
        MessageReceivedEvent event = mock(MessageReceivedEvent.class);

        when(event.getAuthor()).thenReturn(author);
        when(author.isBot()).thenReturn(false);
        when(message.getContentRaw()).thenReturn("hello world");
        when(event.getMessage()).thenReturn(message);

        chatBot.onMessageReceived(event);

        verifyNoInteractions(iaService);
        verify(channel, never()).sendMessage(anyString());
    }

    @Test
    void deveResponderComRespostaDaIa() {
        String userQuestion = "A FURIA participa do CS";
        String botResponse = "Sim";
        String fullMessage = "!ask " + userQuestion;

        when(event.getAuthor()).thenReturn(author);
        when(event.getMessage()).thenReturn(message);
        when(event.getChannel()).thenReturn(channel);
        when(channel.sendMessage(anyString())).thenReturn(messageCreateAction);

        when(author.isBot()).thenReturn(false);
        when(message.getContentRaw()).thenReturn(fullMessage);
        when(iaService.getResponseIA(userQuestion.toLowerCase())).thenReturn(botResponse);

        chatBot.onMessageReceived(event);

        verify(iaService).getResponseIA(userQuestion.toLowerCase());
        verify(channel).sendMessage(botResponse);
        verify(messageCreateAction).queue();
    }

}