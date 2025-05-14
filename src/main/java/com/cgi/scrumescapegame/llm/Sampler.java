package com.cgi.scrumescapegame.llm;

@FunctionalInterface
public interface Sampler {
    int sampleToken(FloatTensor logits);

    Sampler ARGMAX = FloatTensor::argmax;
}