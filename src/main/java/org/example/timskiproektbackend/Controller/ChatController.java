package org.example.timskiproektbackend.Controller;

import org.example.timskiproektbackend.DTO.ChatRequest;
import org.example.timskiproektbackend.Service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<?> ask(@RequestBody ChatRequest request) {
        if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Prompt is empty.");
        }

        try {
            String reply = chatService.askChatGpt(request.getPrompt());
            return ResponseEntity.ok(Map.of("reply", reply));
        } catch (Exception e) {
            e.printStackTrace(); // shows in backend logs
            return ResponseEntity.status(500).body("Something went wrong while calling OpenAI.");
        }
    }

}