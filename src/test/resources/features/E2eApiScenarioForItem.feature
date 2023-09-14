Feature: Different Operation for Item :: POST-PUT-GET-DELETE

  Scenario: Get API for a Item which Exists and any Existing Items
    Given a data for item "11" is needed
    When the request to get the item is made
    Then a 200 response code is returned
    And a item with "id" as "11" is present
    And a item with "name" as "Apple iPad Mini 5th Gen" is present
    And a item with "data.Capacity" as "254 GB" is present
    And a item with "data.'Screen size'" as "7.9" is present

  Scenario: Verify an item can be created Once Post APi is Triggered
    Given a "Apple MacBook Pro 16" item is created
    And is a "Intel Core i9" CPU model
    And has a price of "1849.99"
    When the request to add the item is made
    Then a 200 response code is returned
    And a "Apple MacBook Pro 16" is created

  Scenario: Get API for a Item which Exists and Recently created
    Given a data for item "<NewlyCreatedItem>" is needed
    When the request to get the item is made
    Then a 200 response code is returned
    And a item with "id" as "<NewlyCreatedItem>" is present
    And a item with "name" as "Apple MacBook Pro 16" is present
    And a item with "data.price" as "1849.99" is present
    And a item with "data.cpuModel" as "Intel Core i9" is present

  Scenario: Get API for Set of Items in which all Items Exist
    Given a data for item "10,11,<NewlyCreatedItem>" is needed
    When the request to get list of the item is made
    Then a 200 response code is returned
    And the count of item is 3 in returned response

  Scenario: Get API for Set of Items in which some Items Exist
    Given a data for item "10,11,99" is needed
    When the request to get list of the item is made
    Then a 200 response code is returned
    And the count of item is 2 in returned response

  Scenario: Get API for Set of Items in which none items Exist
    Given a data for item "98,99" is needed
    When the request to get list of the item is made
    Then a 200 response code is returned
    And the count of item is 0 in returned response

  Scenario: Get API for All Items
    When the request to get list of the item is made
    Then a 200 response code is returned
    And a item with "id" as "11" is present in All Items

  Scenario: Delete API for a Item which Exists
    Given a data for item "<NewlyCreatedItem>" is needed
    When the request to delete the item is made
    Then a 200 response code is returned
    And a message with text "Object with id = <NewlyCreatedItem> has been deleted." is returned

  Scenario: Delete API for a Item which does not Exist
    Given a data for item "<NewlyCreatedItem>" is needed
    When the request to delete the item is made
    Then a 404 response code is returned
    And an error with text "Object with id = <NewlyCreatedItem> doesn't exist." is returned

  Scenario: Get API for a Item which does not Exist
    Given a data for item "<NewlyCreatedItem>" is needed
    When the request to get the item is made
    Then a 404 response code is returned
    And an error with text "Oject with id=<NewlyCreatedItem> was not found." is returned