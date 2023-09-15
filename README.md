# Rest API Test Automation using Playwright - BDD

## Pre-requisite:
- Install Java JDK11
- IDEA Community Edition or Any other IDE

## Step to Execute Automation:
1. Download Automation project from Git using Direct download or using Git clone
2. Navigate to below Path -
   "src/test/java/runner"
3. Right Click on "TestRunner.Java" and Select Run.

## Scenario for API Automation with justification:
- Scenario related to POST API for Objects
```sh
1. Failure(400) in case invalid request body has been passed in POST Request.
2. Success(200) in case valid and mandatory info in request body has been passed in POST Request. 
```
- Scenario related to PUT API for Objects
```sh
1. Failure(404) in case a Item updation which doesnot Exists in PUT Request.
2. Failure(405) in case a Item updation which Exists but Reserved in PUT Request.
3. Failure(400) in case a Item updation which Exists and user-created but Invalid Request Body in PUT Request.
4. Success(200) in case a Item updation which Exists and user-created and Valid Request Body in PUT Request.
```   
- Scenario related to PATCH API for Objects
```sh
1. Failure(404) in case a Item updation which doesnot Exists in PATCH Request.
2. Failure(405) in case a Item updation which Exists but Reserved in PATCH Request.
3. Failure(400) in case a Item updation which Exists and user-created but Invalid Request Body in PATCH Request.
4. Success(200) in case a Item updation which Exists and user-created and Valid Request Body in PATCH Request.
```
- Scenario related to GET API for Objects
```sh
1. Failure(404) in case specific Item which doesnot exist/already deleted has been passed in GET Request.
2. Success(200) in case specific Item which is Reserved/User-Created/User-Updated has been passed in GET Request. 
3. Success(200) in case no specific Item has been passed (All Items is retrived) in GET Request.
3. Success(200) in case Set of Items (All items Exists/Some Items Exists/No Items Exists) has been passed in GET Request.
```   
- Scenario related to DELETE API for Objects
```sh
1. Failure(404) in case a Item deletion which doesnot Exists in DELETE Request.
2. Failure(405) in case a Item deletion which Exists but Reserved in DELETE Request.
3. Success(200) in case a Item deletion which Exists and user-created in DELETE Request. 
```   