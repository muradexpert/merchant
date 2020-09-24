package com.bestcommerce.product.util;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.util.Base64;

public class JwtTokenPayload {
    public static int getMerchantIdFromToken(HttpHeaders headers) {
        String token=headers.getFirst(HttpHeaders.AUTHORIZATION);
        String payload = token.substring(7).split("\\.")[1];
        return new JSONObject(new String(Base64.getDecoder().decode(payload))).getInt("ID");
    }
}

