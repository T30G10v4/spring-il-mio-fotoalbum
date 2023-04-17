package me.matteogiovagnotti.springilmiofotoalbum.services;

import me.matteogiovagnotti.springilmiofotoalbum.models.Message;
import me.matteogiovagnotti.springilmiofotoalbum.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll(Sort.by("createdAt"));
    }

    public Message createMessage(Message formMessage) {

        Message messageToPersist = new Message();

        messageToPersist.setMail(formMessage.getMail());
        messageToPersist.setText(formMessage.getText());
        messageToPersist.setCreatedAt(LocalDateTime.now());

        return messageRepository.save(messageToPersist);
    }

}