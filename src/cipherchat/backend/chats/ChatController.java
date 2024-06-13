package cipherchat.backend.chats;

import cipherchat.backend.Usuario;
import java.util.HashMap;
import java.util.Map;

public class ChatController {
    private static ChatController instance;
    private Map<String, Chat> chatMap;

    private ChatController() {
        chatMap = new HashMap<>();
    }

    public static synchronized ChatController getInstance() {
        if (instance == null) {
            instance = new ChatController();
        }
        return instance;
    }

    public Chat getChat(Usuario usuario1, Usuario usuario2) {
        String key = generateKey(usuario1, usuario2);
        return chatMap.computeIfAbsent(key, k -> new Chat(usuario1, usuario2));
    }

    private String generateKey(Usuario usuario1, Usuario usuario2) {
        return usuario1.getCodeUser().compareTo(usuario2.getCodeUser()) < 0
                ? usuario1.getCodeUser() + "_" + usuario2.getCodeUser()
                : usuario2.getCodeUser() + "_" + usuario1.getCodeUser();
    }
}

