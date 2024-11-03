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

    public static JsonNode getProfileUrls() {
        return data.get("profileUrls");
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

    public static String getFirstProductUrl() {
        return data.get("productDetails").get("firstProduct").get("url").asText();
    }

    public static String getFirstProductTitle() {
        return data.get("productDetails").get("firstProduct").get("title").asText();
    }

    public static JsonNode getProductSearchData() {
        return data.get("productSearch");
    }

    public static String getNoProductsFoundMessage(String messageKey) {
        return getProductSearchData().get("messages").get(messageKey).asText();
    }

    public static String getViewAllResultsButtonText() {
        return getProductSearchData().get("buttons").get("viewAllResults").asText();
    }

    public static String getProductName(String productKey) {
        JsonNode productSearchData = getProductSearchData();

        for (JsonNode product : productSearchData.get("productSuggestions")) {
            if (product.get("name").asText().equalsIgnoreCase(productKey)) {
                return product.get("name").asText();
            }
        }
        throw new IllegalArgumentException("Product name not found for key: " + productKey);
    }

    public static int getExpectedSuggestionCount(String productName) {
        for (JsonNode product : getProductSearchData().get("productSuggestions")) {
            if (product.get("name").asText().equalsIgnoreCase(productName)) {
                return product.get("expectedCount").asInt();
            }
        }
        throw new IllegalArgumentException("Expected count for product not found: " + productName);
    }

    private static JsonNode getShoppingCardData() {
        return data.get("shoppingCart");
    }

    public static String getProductNameFromShoppingCart(String productKey) {
        JsonNode shoppingCartData = getShoppingCardData();

        for (JsonNode product : shoppingCartData.get("products")) {
            if (product.get("name").asText().equalsIgnoreCase(productKey)) {
                return product.get("name").asText();
            }
        }

        throw new IllegalArgumentException("Product name not found for key: " + productKey);
    }

    public static int getProductQuantityFromShoppingCart(String productKey) {
        JsonNode shoppingCartData = getShoppingCardData();

        for (JsonNode product : shoppingCartData.get("products")) {
            if (product.get("name").asText().equalsIgnoreCase(productKey)) {
                return product.get("quantity").asInt();
            }
        }

        throw new IllegalArgumentException("Product quantity not found for key: " + productKey);
    }
}