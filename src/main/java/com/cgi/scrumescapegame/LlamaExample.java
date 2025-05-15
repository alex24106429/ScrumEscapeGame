package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.llm.ChatFormat;
import com.cgi.scrumescapegame.llm.Llama;
import com.cgi.scrumescapegame.llm.Llama3;
import com.cgi.scrumescapegame.llm.ModelLoader;
import com.cgi.scrumescapegame.llm.Sampler;
import com.cgi.scrumescapegame.llm.Tokenizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LlamaExample {

    public static void main(String[] args) {
        String modelResourcePath = "/Llama-3.2-1B-Instruct-Q4_0.gguf"; 
        int contextLength = 2048;
        int maxTokensToGenerate = 256; 
        float temperature = 0.7f;
        float topp = 0.9f;

        try (InputStream modelStream = LlamaExample.class.getResourceAsStream(modelResourcePath);
             Scanner userInputScanner = new Scanner(System.in)) {

            if (modelStream == null) {
                System.err.println("Model resource not found: " + modelResourcePath);
                System.err.println("Please ensure the model file is in your resources directory (e.g., src/main/resources)");
                System.err.println("And the path starts with a '/' if it's at the root of resources.");
                return;
            }

            System.out.println("Loading model... This might take a moment.");
            Llama model = ModelLoader.loadModel(modelStream, contextLength, true);
            System.out.println("Model loaded.");

            Llama.State state = model.createNewState(1); 

            Tokenizer tokenizer = model.tokenizer();
            ChatFormat chatFormat = new ChatFormat(tokenizer);
            Sampler sampler = Llama3.selectSampler(model.configuration().vocabularySize, temperature, topp, System.nanoTime());
            Set<Integer> stopTokens = chatFormat.getStopTokens();

            List<ChatFormat.Message> conversationHistory = new ArrayList<>();

            System.out.println("Chat with the LLM! Type 'quit' or 'exit' to end.");

            while (true) {
                System.out.print("\nYou: ");
                String userMessageContent = userInputScanner.nextLine();

                if (userMessageContent.equalsIgnoreCase("quit") || userMessageContent.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chat.");
                    break;
                }

                conversationHistory.add(new ChatFormat.Message(ChatFormat.Role.USER, userMessageContent));

                List<Integer> promptTokens = new ArrayList<>();
                promptTokens.add(chatFormat.beginOfText); 

                for (ChatFormat.Message msg : conversationHistory) {
                    promptTokens.addAll(chatFormat.encodeMessage(msg));
                }

                promptTokens.addAll(chatFormat.encodeHeader(new ChatFormat.Message(ChatFormat.Role.ASSISTANT, "")));

                if (promptTokens.size() >= contextLength - maxTokensToGenerate) {
                    System.err.println("\n[Warning] Conversation history is getting long and might exceed context window.");

                }

                System.out.print("Assistant: ");
                StringBuilder assistantResponseBuilder = new StringBuilder();

                List<Integer> generatedResponseTokens = Llama.generateTokens(
                        model,
                        state,
                        0, 
                        promptTokens,
                        stopTokens,
                        maxTokensToGenerate,
                        sampler,
                        false, 
                        token -> {
                            String decodedToken = tokenizer.decode(List.of(token));
                            System.out.print(decodedToken);
                            System.out.flush(); 
                            assistantResponseBuilder.append(decodedToken);
                        }
                );
                System.out.println(); 

                conversationHistory.add(new ChatFormat.Message(ChatFormat.Role.ASSISTANT, assistantResponseBuilder.toString().trim()));
            }

        } catch (IOException e) {
            System.err.println("An I/O error occurred:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        } finally {

            System.out.println("Application finished.");
        }
    }
}