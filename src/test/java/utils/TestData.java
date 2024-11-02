package utils;


import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;


public class TestData {
    private static JsonNode data;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = TestData.class.getClassLoader().getResourceAsStream("data.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find data.json in resources.");
            }
            data = mapper.readTree(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load test data from data.json", e);
        }
    }

    public static JsonNode getHomePageData() {
        return data.get("homepage");
    }

    public static JsonNode getValidUser() {
        return data.get("userData").get("validUser");
    }

    public static JsonNode getInvalidUser() {
        return data.get("userData").get("invalidUser");
    }

    public static JsonNode getProduct(String productKey) {
        return data.get("productData").get(productKey);
    }

    public static JsonNode getAddressData() {
        return data.get("addressData");
    }

    public static String getAddressField(String fieldName) {
        return getAddressData().get(fieldName).asText();
    }

    public static JsonNode getMessages() {
        return data.get("messages");
    }

    public static String getMessage(String messageKey) {
        return getMessages().get(messageKey).asText();
    }

    public static String getContactInfo(String key) {
        return data.get("contactInfo").get(key).asText();
    }
}