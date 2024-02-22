package com.desk.helppanel.controllers;

import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desk.helppanel.models.Message;

@RestController
public class DataController {
    static String message;
    
    // @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public Message getMessage(Message message){
        System.out.println(message.getMessage());
        System.out.println("message called");
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        return message;
    }

     @GetMapping(value = "/getMsg", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getMessage() {
        System.out.println("message called");
        String sedMessage = message;
        message = "false";
        return sedMessage;
    }

}
