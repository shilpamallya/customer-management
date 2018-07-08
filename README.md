
# Customer Management RESTful Mule Application

### Table of Contents
**[Case Study Goal](#case-study-goal)**<br>
**[Customer Management API](#customer-management-api)**<br>
**[Mulesoft API Lifecycle](#mulesoft-api-lifecycle)**<br>
**[Usecases](#usecases)**<br>
**[API Dictionary](#api-dictionary)**<br>
**[Snapshots](#snapshots)**<br>
**[Execution Steps](#execution-steps)**<br>


### Case Study Goal
Design and build a RESTful API using RAML that contains a single resource Customers. It should allow the following:
 
1. List all Customers
2.	Create a new Customer
3.	Update a Customer
4.	Delete a Customer
 
The API must be designed to support the following consumer use cases at a minimum:
1.	A consumer may periodically (every 5 minutes) consume the API to enable the consumer to maintain a copy of the provider API's Customers (the API represents the system of record).
2.	A mobile application used by customer service representatives that uses the API to retrieve and update the Customer's details.
3.	Simple extension of the API to support future resources such as Orders and Products.

### Customer Management API
This HTTPS-enabled mule application is deployed on CloudHub. 

Console URL - https://customer-management-api-v1.us-e2.cloudhub.io/customer-management/v1/console

#### Client Application Credentials
*Note: should be sent in header with every request.*

**client_id** - 6433d0b931b24820ad8698e690e98009

**client_secret** - 0148851a7Da84C1E9d66FccE2987B8fc

### Mulesoft API Lifecycle
The Customer Management mule application has gone through the below stages of the API Lifecycle.

1. Design
      *  The design and writing of the API is done using RESTful API Modeling Language (RAML).
      *  API Specification has been defined using RAML in Anypoint Design Centre.
      *  Resource types and traits features in RAML is extensively used to eliminate redundancies in resource and method definitions.
      *  Parameter functions are used wherever a parameter is used in order to transform the expanded value of the parameter when it is processed in a resource definition.
      *  Modularized the RAML using 'include' by specifying the data types and examples value in an external file.

2. Implementation
      *   A RESTful maven-based Mule application is developed.
      *   Java implementation is modularized using the Service and Data Access Object (DAO) layers. 
      *   A customer POJO is used for managing Customer resource. 
      *   A HashMap is used to store Customers.
      *   A standardized project structure with proper naming conventions are followed.
      *   A standardized logging is implemented using INFO and DEBUG log levels.
      *   Standard exception handling is implemented with uniform error message formats.
      *   Externalized configurations using global property file.
      *   In-memory Caching Strategy is used for get all customers to improve performance.
      *   MUnit test cases are defined for positive and negative scenarios for maximum code coverage.
      *   Provided comments and java documentation wherever appropriate.
      *   Proper usage of built-in aspects of HTTP (status codes, headers etc).

3. Management 
      *   SSL/HTTPS has been enabled on the application for secure communications between client and server.
      *   Client Id Enforcement policy is applied on all API endpoints to only allow authorized users from accessing the customer management application.
      *   The application is deployed on CloudHub.

### Usecases

#### Usecase 1:

*Problem Statement: A consumer may periodically (every 5 minutes) consume the API to enable the consumer to maintain a copy of the provider API's Customers (the API represents the system of record).*

   *  As it is mentioned that there will be more frequent reads from the consumer of customer management API, a Caching Strategy has been implemented on Get All Customers (GET /customers) API. This will reduce processing load and will boost performance. 

<<Thread Profiles>>

#### Usecase 2:

*Problem Statement: A mobile application used by customer service representatives that uses the API to retrieve and update the customer's details.*

   *  JSON is chosen as the message format as it is light-weight and less-verbose.
   *  Build REST API's to adhere to level three of Richardson Maturity Model i.e., HATEOAS (Hypermedia As The Engine Of Application State). HATEOAS describes a RESTful system that returns hypermedia links with each response,  providing the consumer with options for subsequent API calls instead of sending a huge blob in a single response. Hypermedia links help guide consumers by providing options for further calls based on the current application state.
   *  HTTP compression can be used to optimize compressing large responses.
   *  A Caching Strategy can be implemented to reduce processing load and for quick responses.

#### Usecase 3:

*Problem Statement: Simple extension of the API to support future resources such as orders and products.*

   *  Resource types and traits features in RAML is extensively used to eliminate redundancies in resource and method definitions.
   *  Parameter functions are used wherever a parameter is used in order to transform the expanded value of the parameter when it is processed in a resource definition.
   *  Modularized the RAML using 'include' by specifying the data types and examples value in an external file.
   *  Java implementation is modularized using the Model Class (POJO), Service and Data Access Object(DAO) layers. A Customer POJO, Customer Service and Customer DAO is used for managing a Customer resource. Similarly, this pattern can be repeated for Orders, Products and any other resources in the future.
   
### API Dictionary

##### Client Application Credentials
*Note: should be sent in header with every request.*

**client_id** - 6433d0b931b24820ad8698e690e98009

**client_secret** - 0148851a7Da84C1E9d66FccE2987B8fc

| API Operation        | HTTP Method           | URL  |   Request   |  Response |  
| :------------- |:-------------:| :-----|  :--------|   :----------|
| List All Customers     | GET | https://customer-management-api-v1.us-e2.cloudhub.io/customer-management/v1/customers |    |   ``` [ { "id": 3, "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } }, { "id": 4, "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } } ] ```   
| Create a New Customer      | POST      |   https://customer-management-api-v1.us-e2.cloudhub.io/customer-management/v1/customers | ```{ "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } }```   |   ```{ "id": 3, "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } }```
| Update a Customer | PUT      |    https://customer-management-api-v1.us-e2.cloudhub.io/customer-management/v1/customer/{id} |  ```{ "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } }```   |   ``` { "id": 3, "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } }```
| Delete a Customer  | DELETE   |   https://customer-management-api-v1.us-e2.cloudhub.io/customer-management/v1/customer/{id}  |   |   ```{ "message": "Customer 3 has been successfully deleted." }```
| Retrieve a Customer  | GET   |   https://customer-management-api-v1.us-e2.cloudhub.io/customer-management/v1/customers/{id}  |   |  ``` { "id": 3, "salutation": "Ms", "firstName": "Kathy", "lastName": "Seirra", "address": { "addressLine1": "556", "addressLine2": "Pike Street", "suburb": "Sydney", "postCode": "2000", "state": "NSW", "country": "Australia" } } ```


### Snapshots

#### HTTPS enabled Customer Management API Console
![1](https://user-images.githubusercontent.com/23097763/42412163-4b72e384-824a-11e8-895c-89dab37e96c3.png)

#### Client Id Enforcement Policy
![2](https://user-images.githubusercontent.com/23097763/42412164-4bbc00aa-824a-11e8-99a9-7e82ddb962d6.PNG)

#### List all Customers
![3](https://user-images.githubusercontent.com/23097763/42412165-4c00a322-824a-11e8-9761-1ee6c92f15b6.PNG)

#### Create a new Customer
![4](https://user-images.githubusercontent.com/23097763/42412166-4c33ef52-824a-11e8-871d-b975fa267f34.PNG)

#### Update a Customer
![5](https://user-images.githubusercontent.com/23097763/42412167-4c94b22e-824a-11e8-8f03-2e2a45ff9e08.PNG)

#### Delete a Customer
![6](https://user-images.githubusercontent.com/23097763/42412168-4d362ca8-824a-11e8-8412-27c4fdcff845.PNG)

#### Retrieve a Customer
![7](https://user-images.githubusercontent.com/23097763/42412169-4d6b3808-824a-11e8-8814-c33996b22399.PNG)

#### 404 No Customers found
![8](https://user-images.githubusercontent.com/23097763/42412170-4dc6f01c-824a-11e8-902c-59cdfb5dece9.PNG)

#### 404 Customer Id does not exist
![9](https://user-images.githubusercontent.com/23097763/42412171-4e419970-824a-11e8-8d84-f0f882a8c095.PNG)

#### 405 Method Not Allowed
![method_not_allowed](https://user-images.githubusercontent.com/23097763/42412160-4ac49400-824a-11e8-9a08-faf529f93410.PNG)

#### 406 Not Acceptable
![not_acceptable](https://user-images.githubusercontent.com/23097763/42412161-4af85fc4-824a-11e8-83a0-d5ebd070de37.PNG)

#### 415 Unsupported Media Type
![unsupported_media_type](https://user-images.githubusercontent.com/23097763/42412162-4b34e516-824a-11e8-8811-2edc812a872f.PNG)


### Execution Steps
Step 1: Download the project from GitHub.<br>
Step 2: Open command prompt and navigate to the `pom.xml` file in the project folder.<br>
Step 3: Execute `mvn clean install`.<br>
Step 4: Go to the browser and hit https://localhost:8092/customer-management/v1/console to view the API console.<br>
