package stepdef;

import com.microsoft.playwright.*;
import com.natwest.model.*;
import com.natwest.service.*;
import com.natwest.utility.ReportLogger;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.DeserializationFeature;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    public static Item item=null;
    public static String newlyCreatedResourceId="";
    public static String itemsIdForGet=null;
    public static APIResponse response = null;

    @Given("a {string} item is created")
    public void aItemIsCreated(String name) {
        ReportLogger.logInfo(false,true,"**** aItemIsCreated *****");
        if(item == null){
          item = new Item();
        }
        item.setName(name);
    }

    @Given("is a {string} CPU model")
    public void isACPUModel(String cpuModel) {
        ReportLogger.logInfo(false,true,"**** isACPUModel *****");
        if(item == null){
            item = new Item();
        }
        if(item.getData() == null){
            item.setData(new Data());
        }
        item.getData().setCpuModel(cpuModel);
    }

    @Given("has a price of {string}")
    public void hasAPriceOf(String price) {
        ReportLogger.logInfo(false,true,"**** hasAPriceOf *****");
        if(item == null){
            item = new Item();
        }
        if(item.getData() == null){
            item.setData(new Data());
        }
        item.getData().setPrice(Double.parseDouble(price));
    }

    @When("the request to add the item is made")
    public void theRequestToAddTheItemIsMade() throws Exception {
        ReportLogger.logInfo(false,true,"**** theRequestToAddTheItemIsMade *****");
        response = ItemService.sendPostRequestForRest(item);
        if (response.ok())
            newlyCreatedResourceId = JsonPath.from(response.text()).getString("id");
        item = null;
    }

    @When("the request to get the item is made")
    public void theRequestToGetTheItemIsMade(){
        ReportLogger.logInfo(false,true,"**** theRequestToGetTheItemIsMade *****");
        List<String> lstItemId = Arrays.asList(itemsIdForGet.split(","));
        if (lstItemId.size()==1){
            response = ItemService.getRequestForSingleItemRest(lstItemId.get(0));
        }
        else {
            Assert.fail("This Operation is for Specific Items. Multiple Items Listed. " + lstItemId);
        }
        itemsIdForGet=null;
    }

    @When("the request to get list of the item is made")
    public void theRequestToGetListOfTheItemIsMade(){
        ReportLogger.logInfo(false,true,"**** theRequestToGetListOfTheItemIsMade *****");
        response = ItemService.getRequestForMultipleItemRest(itemsIdForGet);
        itemsIdForGet = null;
    }

    @When("the request to update the item using {string} is made")
    public void theRequestToUpdateTheItemUsingIsMade(String operation) throws Exception {
        ReportLogger.logInfo(false,true,"**** theRequestToUpdateTheItemUsingIsMade *****");
        response = ItemService.updateRequestForSingleItemRest(operation.equalsIgnoreCase("PUT"),itemsIdForGet,item);
        itemsIdForGet = null;
        item=null;
    }

    @When("the request to delete the item is made")
    public void theRequestToDeleteTheItemIsMade() {
        ReportLogger.logInfo(false,true,"**** theRequestToDeleteTheItemIsMade *****");
        List<String> lstItemId = Arrays.asList(itemsIdForGet.split(","));
        if (lstItemId.size()==1){
            response = ItemService.deleteRequestForSingleItemRest(lstItemId.get(0));
        }
        else {
            Assert.fail("This Operation is for Specific Items. Multiple Items Listed. " + lstItemId);
        }
        itemsIdForGet=null;
    }

    @Then("a {int} response code is returned")
    public void aResponseCodeIsReturned(Integer statusCode) {
        ReportLogger.logInfo(false,true,"**** aResponseCodeIsReturned *****");
        assertEquals("Response Code is not matching.", (int) statusCode,response.status());
    }

    @Then("a {string} is created")
    public void aIsCreated(String name) throws Exception {
        ReportLogger.logInfo(false,true,"**** aIsCreated *****");
        Item item = new ObjectMapper().readValue(response.text(), Item.class);
        assertEquals("Item name is not matching.",name, item.getName());
    }

    @Given("a data for item {string} is needed")
    public void aDataForItemIsNeeded(String items) {
        ReportLogger.logInfo(false,true,"**** aDataForItemIsNeeded *****");
        itemsIdForGet = items.replace("<NewlyCreatedItem>",newlyCreatedResourceId).trim();
    }

    @Then("a item with {string} as {string} is present")
    public void aItemWithAsIsPresent(String elePath,String data) {
        ReportLogger.logInfo(false,true,"**** aItemWithAsIsPresent *****");
        String expectedValue =data.replace("<NewlyCreatedItem>",newlyCreatedResourceId);
        JsonPath jsonData = JsonPath.from(response.text());
        String actualValue = jsonData.getString(elePath);
        if (expectedValue.equalsIgnoreCase("null"))
            Assert.assertNull("Value of Item "+elePath+" is not null",actualValue);
        else
            assertEquals("Value of Item "+elePath+" is not matching.",expectedValue, actualValue);
    }

    @Then("an error with text {string} is returned")
    public void anErrorWithTextIsReturned(String expectedError) {
        ReportLogger.logInfo(false,true,"**** aItemWithAsIsPresent *****");
        expectedError = expectedError.replace("<NewlyCreatedItem>",newlyCreatedResourceId);
        JsonPath jsonData = JsonPath.from(response.text());
        String actualError = jsonData.getString("error");
        assertEquals("error text is not matching.",expectedError, actualError);
    }

    @Then("a message with text {string} is returned")
    public void aMessageWithTextIsReturned(String expectedMessage) {
        ReportLogger.logInfo(false,true,"**** aItemWithAsIsPresent *****");
        expectedMessage = expectedMessage.replace("<NewlyCreatedItem>",newlyCreatedResourceId);
        JsonPath jsonData = JsonPath.from(response.text());
        String actualMessage = jsonData.getString("message");
        assertEquals("error text is not matching.",expectedMessage, actualMessage);
    }

    @Then("the count of item is {int} in returned response")
    public void theCountOfItemIsInReturnedResponse(Integer data) {
        ReportLogger.logInfo(false,true,"**** aItemWithAsIsPresent *****");
        int expectedCount = data;
        JsonPath jsonData = JsonPath.from(response.text());
        int actualCount = Integer.parseInt(jsonData.getString("size()"));
        assertEquals("Count of Item is not matching.", expectedCount, actualCount);
    }

    @And("a item with {string} as {string} is present in All Items")
    public void aItemWithAsIsPresentInAllItems(String elePath,String data) throws Exception {
        ReportLogger.logInfo(false,true,"**** aItemWithAsIsPresent *****");
        String expectedValue =data.replace("<NewlyCreatedItem>",newlyCreatedResourceId);

        TypeReference<List<Item>> typeReference= new TypeReference<>(){};
        List<Item> itemObjList = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(response.text(), typeReference);
        Item myItemObj = itemObjList.stream()
                .filter(item -> expectedValue.equals(item.getId()))
                .findAny()
                .orElse(null);
        Assert.assertNotNull("Item with id ::" +expectedValue + " is not Preset in Item list" ,myItemObj);
    }


}
