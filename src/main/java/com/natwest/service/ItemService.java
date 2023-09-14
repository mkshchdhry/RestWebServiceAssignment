package com.natwest.service;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import com.natwest.model.Item;
import com.natwest.utility.ReportLogger;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class ItemService {
    public static Playwright playwright;
    public static APIRequestContext request;
    public static final String baseURL = "https://api.restful-api.dev";

    public static APIResponse sendPostRequestForRest(Item item) throws Exception {
        String postUrl = "/objects";
        String jsonItem = new ObjectMapper().writeValueAsString(item);
        ReportLogger.logInfo(true,true,"postUrl :: " + postUrl);
        ReportLogger.logInfo(true,true,"postBody :: " + jsonItem);

        RequestOptions requestOptions = RequestOptions.create().setData(jsonItem);
        APIResponse response = request.post(postUrl,requestOptions);

        ReportLogger.logInfo(true,true,"ResponseStatus :: " + response.status() + " :: " + response.statusText());
        ReportLogger.logInfo(true,true,"ResponseBody :: " + response.text());
        return response;
    }

    public static APIResponse getRequestForSingleItemRest(String itemsId){
        String getUrl = "/objects/"+ itemsId;
        ReportLogger.logInfo(true,true,"getUrl :: " + getUrl);

        APIResponse response = request.get(getUrl);

        ReportLogger.logInfo(true,true,"ResponseStatus :: " + response.status() + " :: " + response.statusText());
        ReportLogger.logInfo(true,true,"ResponseBody :: " + response.text());
        return response;
    }

    public static APIResponse getRequestForMultipleItemRest(String itemsId){
        StringBuilder getUrl = new StringBuilder("/objects");
        if (Objects.nonNull(itemsId)) {
            boolean isFirstParam = true;
            for (String str : itemsId.split(",")) {
                getUrl.append((isFirstParam) ? ("?id=" + str) : ("&id=" + str));
                isFirstParam = false;
            }
        }
        ReportLogger.logInfo(true,true,"getUrl :: " + getUrl);

        APIResponse response = request.get(getUrl.toString());

        ReportLogger.logInfo(true,true,"ResponseStatus :: " + response.status() + " :: " + response.statusText());
        ReportLogger.logInfo(true,true,"ResponseBody :: " + response.text());
        return response;
    }

    public static APIResponse deleteRequestForSingleItemRest(String itemsId) {
        String getUrl = "/objects/"+ itemsId;
        ReportLogger.logInfo(true,true,"getUrl :: " + getUrl);

        APIResponse response = request.delete(getUrl);

        ReportLogger.logInfo(true,true,"ResponseStatus :: " + response.status() + " :: " + response.statusText());
        ReportLogger.logInfo(true,true,"ResponseBody :: " + response.text());
        return response;
    }

}
