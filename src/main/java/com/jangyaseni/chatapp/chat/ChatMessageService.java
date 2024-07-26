package com.jangyaseni.chatapp.chat;

import com.jangyaseni.chatapp.chatroom.ChatRoomService;
import com.jangyaseni.chatapp.exception.ChatRoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repo;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(), chatMessage.getRecipientId(), true
        ).orElseThrow(() -> new ChatRoomNotFoundException("Chat room doesn't exist with this id"));
        chatMessage.setChatId(chatId);
        return repo.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repo::findByChatId).orElse(new ArrayList<>());
    }


}
