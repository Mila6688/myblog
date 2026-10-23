package com.myblog.controller;

import com.myblog.model.Message;
import com.myblog.model.MessageRequest;
import com.myblog.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


/**
 * The front desk: only HTTP concerns (paths, methods, JSON, status codes).
 * NO storage code, NO business rules — everything is delegated to Service.
 */
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Create a message. POST is mandatory — browsers can only send GET.
     */
    @PostMapping("/messages")
    public Message create(@RequestBody MessageRequest request) {
        return messageService.create(request);
    }

    /**
     * List all messages, with optional keyword filtering.
     * ONE method, TWO behaviors (no Ambiguous mapping!):
     *   GET /messages                -> keyword == null -> return all
     *   GET /messages?keyword=hello  -> filtered list
     * required = false is the soul: it allows the parameter to be absent.
     */
    @GetMapping("/messages")
    public List<Message> list(@RequestParam(name = "keyword", required = false) String keyword){
        List<Message> all = messageService.list();

        // No parameter: return everything as-is
        if (keyword == null) {
            return all;
        }

        // With keyword: loop through and keep only matching messages
        List<Message> result = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            Message m = all.get(i);
            System.out.println("msg content="+m.getContent()+", keyword="+keyword);
            if (m.getContent().contains(keyword)) {
                result.add(m);
            }
        }
        return result;
    }

    /**
     * Get one message by id.
     * The path is /messages/{id} — a DIFFERENT route pattern from
     * /messages, so it coexists with the list endpoint without conflict.
     */
    @GetMapping("/messages/{id}")
    public Message getOne(@PathVariable Long id) {
        // Unbox the Optional honestly: found -> return it;
        // empty box -> 404, not a NullPointerException.
        return messageService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "message not found: id=" + id));
    }
}