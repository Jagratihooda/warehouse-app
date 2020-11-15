***Warehouse App***


***Tech Stack***

***Backend:***

- Java 1.8
- PostgreSQL
- Spring Boot
- Project Lombok
- JUnit/Mockito

***Frontend:***

- Angularjs
- Bootstrap

***Tools:***

- IntelliJ
- Maven

***How to run***

- Setup PostgreSQL with the configuration available in application.properties

- Spring Boot will create tables.

- Run with maven
  mvn test
  mvn spring-boot:run
  
- Hit below URL in browser to run the application.
localhost:8081

- All the configurations related to logging, scheduling , input files path and dataBase are available in application.properties

- A Scheduler is scheduled to call in every 86400000 milliseconds (24 hours) to Upload Input files data to the data base.

- You can view Product details on the homescreen. 

- Click on 'Check Inventory' to view the Articles details.

- Click on 'Back To Product Details' to go back to products page.

- Click on 'Sell' button to sell a product.

- Click on 'Check Inventory' again to view the updated Articles details.

***Potential improvements***

- Frontend Angular implementation

- Support for reading multiple Inventory and products files

- More?
