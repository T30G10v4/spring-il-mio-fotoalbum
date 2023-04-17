package me.matteogiovagnotti.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import me.matteogiovagnotti.springilmiofotoalbum.models.Message;
import me.matteogiovagnotti.springilmiofotoalbum.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/messages")
public class MessageRestController {

    @Autowired
    MessageService messageService;

    @PostMapping
    public Message create(/*@Valid*/ @RequestBody Message message) {

        return messageService.createMessage(message);
    }

}