package com.desk.helppanel.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.desk.helppanel.models.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MainController {


    private String pgtoken = "EAAzaJFSxzAEBO8pNEaY12gvIFZBkBR8aHcrxOv87zfnZCpLEV5DKXl9C7ay3QjJulkMy6OxNtSOwuD8EJSYIZCyQHJB3QdWZAlr5STlVO0ZBOwHzeYdAzMrWErxNiwWO1Hmv6ZChZBzpGlwBVqcvB8woAOLjYilJB6kmwLMj1HTm3eQCB66WNDSL6ZAwsZAx2IFQP";
    
    @GetMapping("/dash")
    public String homePage(){
        return "dashboard";
    }

    // @GetMapping("/")
    // public String home(){
    //     // OAuth2Authentication p = (OAuth2Authentication)principal;
    //     // Map<String,Object> user = (Map<String,Object>)p.getUserAuthentication().getDetails();
    //     // return "username="+user.get("name");
    //     return "AA";
    // }

    @GetMapping("/web-callback")
    public ResponseEntity<String> fbWebhookCallback(@RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String verifyToken,
            @RequestParam("hub.challenge") String challenge){

        return ResponseEntity.status(HttpStatus.OK).body(challenge);
    }
    
    @PostMapping("/web-callback")
    public ResponseEntity<String> fbPayloadData(@RequestBody String payload){
        
        try{
            if (payload != null) {
                // Parse the payload string into a JSON object
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonPayload = objectMapper.readTree(payload);
                System.out.println(jsonPayload);

                JsonNode entry = jsonPayload.get("entry").get(0);
                String id = entry.get("id").asText();
                String time = String.valueOf(entry.get("time").asLong());

                JsonNode messaging = entry.get("messaging").get(0);
                String sender = messaging.get("sender").get("id").asText();
                String recipient = messaging.get("recipient").get("id").asText();
                String timeStamp = String.valueOf(messaging.get("timestamp").asLong());
                String messageId = messaging.get("message").get("mid").asText();
                String messg = messaging.get("message").get("text").asText();

                Message messageObject = new Message(id, time, sender, recipient, timeStamp, messageId, messg);
                // new DataController().getMessage(messageObject);
                DataController.message = messg;

                System.out.println("web hook called");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return ResponseEntity.ok().body("ok");
        // return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PostMapping("/sendMessage")
    // @MessageMapping("/message")
    public String sendMessage(@RequestParam("recipient_id") String recipient_id,@RequestParam("message_text") String message_text) throws UnsupportedEncodingException,IOException{
        String apiUrl = "https://graph.facebook.com/v19.0/227554547113797/messages";
        String recipientId = recipient_id;
        String messageText = message_text;
        System.out.println("chala");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Define the request body as a map
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("recipient", Map.of("id", recipientId));
        requestBody.put("message", Map.of("text", messageText));
        requestBody.put("messaging_type", "RESPONSE");
        requestBody.put("access_token", getPgtoken());

        // Create the HTTP entity with headers and body
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // Check the response status
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Request successful
            String responseBody = responseEntity.getBody();
            System.out.println("Response: " + responseBody);
            return responseBody;
        } else {
            // Request failed
            System.err.println("Failed to send POST request. Response status: " + responseEntity.getStatusCode());
            return null;
        }
    }


    public String getPgtoken() {
        return pgtoken;
    }

    public void setPgtoken(String pgtoken) {
        this.pgtoken = pgtoken;
    }

   

    // public static void setMessage(String message) {
    //     this.message = message;
    // }
}