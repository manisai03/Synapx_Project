package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FraudConfig {

    @Value("${claims.fraud.keywords}")
    private List<String> fraudKeywords;

    public List<String> getFraudKeywords() {
        return fraudKeywords;
    }
}
