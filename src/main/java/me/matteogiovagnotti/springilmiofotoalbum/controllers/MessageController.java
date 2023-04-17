package me.matteogiovagnotti.springilmiofotoalbum.controllers;

import me.matteogiovagnotti.springilmiofotoalbum.models.Message;
import me.matteogiovagnotti.springilmiofotoalbum.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public String index(Model model) {

        List<Message> messages = messageService.getAllMessages();

        model.addAttribute("messages", messages);

        return "/messages/index";
    }


}
