#Feature: Different Operation supported by Objects Rest API
#
#  Scenario: Verify an item can be created
#    Given a "Apple MacBook Pro 16" item is created
#    And is a "Intel Core i9" CPU model
#    And has a price of "1849.99"
#    When the request to add the item is made
#    Then a 200 response code is returned
#    And a "Apple MacBook Pro 16" is created
#
#  Scenario: Ability to return an item
#    Given a data for item "11" is needed
#    When the request to get the item is made
#    Then a 200 response code is returned
#    And a item with "id" as "11" is present
#    And a item with "name" as "Apple iPad Mini 5th Gen" is present
#    And a item with "data.Capacity" as "254 GB" is present
#    And a item with "data.'Screen size'" as "7.9" is present
#
#  Scenario: Ability to list multiple items
#    Given a data for item "11,12,13" is needed
#    When the request to get list of the item is made
#    Then a 200 response code is returned
#
#  Scenario: Ability to full list multiple items
#    When the request to get list of the item is made
#    Then a 200 response code is returned
