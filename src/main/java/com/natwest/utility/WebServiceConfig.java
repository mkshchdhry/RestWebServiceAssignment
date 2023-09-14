package com.natwest.utility;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.Playwright;
import com.natwest.service.ItemService;

import java.util.HashMap;
import java.util.Map;

public class WebServiceConfig {
    public static void setup() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        ItemService.playwright = Playwright.create();
        ItemService.request = ItemService.playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(ItemService.baseURL)
                .setExtraHTTPHeaders(headers)
                .setIgnoreHTTPSErrors(true));
    }

    public static void tearDown() {
        ItemService.request.dispose();
        ItemService.request=null;
        ItemService.playwright=null;
    }

}
