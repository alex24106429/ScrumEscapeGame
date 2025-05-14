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
import java.util.Set;

public class LlamaExample {

    public static void main(String[] args) {
        String modelResourcePath = "/Llama-3.2-1B-Instruct-Q4_0.gguf"; // https://huggingface.co/mukel/Llama-3.2-1B-Instruct-GGUF/resolve/main/Llama-3.2-1B-Instruct-Q4_0.gguf
        int contextLength = 2048;
        int maxTokens = 128;
        float temperature = 0.8f; // Deterministic sampling
        float topp = 0.95f; // Wordt niet gebruikt als temperature 0 is

        try (InputStream modelStream = LlamaExample.class.getResourceAsStream(modelResourcePath)) {
            if (modelStream == null) {
                System.err.println("Model resource not found: " + modelResourcePath);
                return;
            }

            // Load the model from the InputStream
            Llama model = ModelLoader.loadModel(modelStream, contextLength, true);

            // Create a new state for the model
            Llama.State state = model.createNewState(1); // Batch size 1 for simplicity

            // Prepare the prompt
            Tokenizer tokenizer = model.tokenizer();
            ChatFormat chatFormat = new ChatFormat(tokenizer);
            List<Integer> promptTokens = new ArrayList<>();
            promptTokens.add(chatFormat.beginOfText);
            promptTokens.addAll(chatFormat.encodeMessage(new ChatFormat.Message(ChatFormat.Role.USER, "Vertel me een kort verhaal over een software engineer.")));
            promptTokens.addAll(chatFormat.encodeHeader(new ChatFormat.Message(ChatFormat.Role.ASSISTANT, "")));

            // Select a sampler
            Sampler sampler = Llama3.selectSampler(model.configuration().vocabularySize, temperature, topp, System.nanoTime());

            // Generate tokens
            Set<Integer> stopTokens = chatFormat.getStopTokens();
            List<Integer> responseTokens = Llama.generateTokens(model, state, 0, promptTokens, stopTokens, maxTokens, sampler, false, token -> {
                // Optional: Process streamed tokens here if needed
            });

            // Decode and print the response
            String responseText = tokenizer.decode(responseTokens);
            System.out.println("Generated Response:");
            System.out.println(responseText);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}