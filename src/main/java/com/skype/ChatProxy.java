package com.skype;

/**
 * Package protected is for suckers
 */
public class ChatProxy {
    public static Chat getChatInstance(String chatId) {
        return Chat.getInstance(chatId);
    }
}
