package com.example.demo.Util;

import java.util.UUID;

public class ClaimIdGenerator {

    public static String generateClaimId() {
        return "CLM-" + UUID.randomUUID().toString().substring(0,8);
    }
}
