package com.email.emailWriter.app;
import com.email.emailWriter.app.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService
{
    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiAPIURL;
    @Value("${gemini.api.key}")
    private String geminiAPIKEY;

    //This will be injected during runtime
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest)
    {
        // Build the prompt
        String prompt=buildPrompt(emailRequest);
        // Craft a request
        Map<String, Object> requestBody=Map.of(
                "contents", new Object[]{
                        Map.of("parts",new Object[]{
                                Map.of("text",prompt)
                        })
                }
        );
        // Do request and get Response
        String response =webClient.post()
                .uri(geminiAPIURL+geminiAPIKEY)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Extract Response and Return Response
        return extractResponseContent(response);

    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper =new ObjectMapper(); //ObjectMapper is a tool from jackson library
            // help in working with JSON data(read write JSON data to java objects.)
            JsonNode rootNode = mapper.readTree(response); // Here, readTree method that turns
            //JSON response into tree like structure. Using JsonNode, We can navigate through tree.
            return rootNode.path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text").asText();
        } catch (Exception e) {
            return "Error Processing Request: "+e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt= new StringBuilder();
        prompt.append("Generate a professional reply for the following email content. Please don't generate a subject line ");
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty())
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        prompt.append("\n Original Email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
