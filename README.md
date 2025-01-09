# CQRS Microservices Architecture with RabbitMQ

## Overview
This project implements the **Command Query Responsibility Segregation (CQRS)** architecture. It is divided into three microservices:

1. **ms-beautique**: Responsible for writing data to the database, handling operations such as insertion, deletion, and updates.
2. **ms-query**: Responsible for reading data from the database, performing query-only operations.
3. **ms-sync**: Handles the synchronization and communication between microservices using **RabbitMQ**.



## Databases
- **PostgreSQL** and **MongoDB** were used for querying data, ensuring an efficient and adaptable approach to the application's data needs.

## Communication
- Communication between microservices is facilitated by **RabbitMQ**, providing robust and effective messaging.

## Deployment
- Services are isolated using **Docker** and **Docker Compose**, ensuring ease of management, scalability, and portability.



## What is CQRS?
The **Command Query Responsibility Segregation (CQRS)** architecture is an architectural pattern that separates responsibilities for reading and writing operations in a system. Instead of using the same data model and logic for both operations, CQRS divides these into distinct components:

### Commands
- Responsible for changing the system state.
- Handles actions such as creating, updating, or deleting data.
- Follows the principle of not returning data, only confirming the success or failure of operations.

### Queries
- Focused on retrieving data without altering the system state.
- Can be optimized for specific query requirements, often using dedicated read models.

### Benefits of CQRS
1. **Scalability**: Enables independent scaling of read and write operations.
2. **Flexibility**: Allows for optimized complex queries with specialized read models or databases.
3. **Separation of Concerns**: Simplifies code by dividing responsibilities, making the system easier to maintain.
4. **Event Sourcing Support**: Works well with patterns like Event Sourcing, where state changes are represented as events.

### Challenges of CQRS
1. **Increased Complexity**: Adds more components, making implementation and management more challenging.
2. **Synchronization**: Maintaining consistency between read and write models, especially in distributed systems.
3. **Latency**: Propagation of changes to the read models might not be immediate.

---



## RabbitMQ in CQRS
**RabbitMQ** is used as a messaging tool to handle asynchronous communication and decoupling between components in the CQRS architecture.
---
### Broker
![Broker CQRS](https://github.com/user-attachments/assets/ad85b476-cf1d-414e-9e61-0ee714ce274a)

---
### How RabbitMQ works

![Filas RabbitMQ](https://github.com/user-attachments/assets/71abcf13-a275-4409-acf9-c3e4038c6c0f)


---
### Key Features

#### Decoupling between Commands and Queries
- **Commands (Write)**: Published to RabbitMQ queues, allowing asynchronous processing by specialized services.
- **Domain Events**: After processing a command, domain events (e.g., `OrderCreated`, `ProductUpdated`) are emitted to inform other services of state changes.

#### Synchronization between Write and Read Models
- Changes in the system state are distributed via RabbitMQ events to ensure read models stay synchronized with the write models.

### RabbitMQ Exchange Types
RabbitMQ supports several exchange types for routing messages:

1. **Direct Exchange**
   - Routes messages to queues with matching binding keys.
   - **Use Case**: Routing by severity levels (e.g., `info`, `error`).

![_Direct  Exchange](https://github.com/user-attachments/assets/a1e6efac-bd62-4e9e-8a1f-ae7ed044ace2)


2. **Topic Exchange**
   - Routes messages using patterns in routing keys:
     - `*` matches a single word.
     - `#` matches zero or more words.
   - **Use Case**: Flexible publish/subscribe systems.

![Topic  Exchange](https://github.com/user-attachments/assets/8ef43cfb-e1ed-4998-9d77-d5564bab3eaa)


3. **Fanout Exchange**
   - Broadcasts messages to all connected queues, regardless of the routing key.
   - **Use Case**: Notifications or global events.

![Fanout Exchange](https://github.com/user-attachments/assets/a2ec0de1-af90-48d1-8fd3-5775345932f3)


4. **Headers Exchange**
   - Routes messages based on headers instead of routing keys.
   - **Use Case**: Complex routing based on message attributes.
     
![Header Exchange](https://github.com/user-attachments/assets/588b46da-21f1-4fc4-ae89-62441a9fb8bc)


### Key Advantages of This Implementation
- **Scalable Architecture**: Independent scaling of read and write operations.
- **Resilient Communication**: RabbitMQ ensures reliable messaging between services.
- **Flexibility**: PostgreSQL and MongoDB provide tailored solutions for both structured and unstructured data needs.
- **Portability**: Docker simplifies deployment and portability across environments.

---




## Models and Diagrams

The model and operation of the projects, the CQRS architecture and RabbitMQ will be presented below.

### Use case diagram
![Use case diagram](https://github.com/user-attachments/assets/63302697-f07a-46c0-8719-e36eaac39941)

### Data Model

![Data Model](https://github.com/user-attachments/assets/5241396b-d2b1-4abe-beb7-49dc5b0911da)


### Model CQRS

![Model CQRS](https://github.com/user-attachments/assets/53ce50aa-1614-42b0-a0a8-132d6b1bbef4)

## How CQRS works

![Func CQRS](https://github.com/user-attachments/assets/7accee2d-1a90-42cb-b0bb-5d478c421ede)


## Deployment Instructions

### :rocket: Installation

![](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)


```
git clone https://github.com/RamonBecker/ms-auth.git
```

![](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
```
git clone https://github.com/RamonBecker/ms-auth.git
or install github https://desktop.github.com/ 
```

## 🔨 Docker

Before cloning the project, you will need to install docker on your operating system.

For windows, enter the following from the link:

```
https://docs.docker.com/desktop/windows/install/
```

For linux, follow the procedure below:
- Update your existing list of packages:

```
sudo apt update
```

- Install some prerequisite packages that let apt use packages over HTTPS:

```
sudo apt install apt-transport-https ca-certificates curl software-properties-common

```

- Add the GPG key to the official Docker repository on your system:

```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```
- Add the Docker repository to the APT sources:

```
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"

```

- Update the package database with Docker packages from the newly added repository:

```
sudo apt update
```

- Make sure you are about to install from the Docker repository instead of the default Ubuntu repository:

```
apt-cache policy docker-ce
```

- Install Docker:

```
sudo apt install docker-ce
```

- Check if it is working:

```
sudo systemctl status docker
```

- Once you have completed the docker installation, go to the infrastructure folder and run the following commands

```
docker compose up --build
docker compose up -d
```

# API Endpoints Documentation

To access the endpoints you must download the collection file and import it into your postman.

## Ms-Beautique-Query Endpoints

### Customers
1. **List All**: `GET http://localhost:8086/ms-beautique-query/customer`
2. **Find by Name**: `GET http://localhost:8086/ms-beautique-query/customer/name/testname`
3. **Find by Email**: `GET http://localhost:8086/ms-beautique-query/customer/email/testemail`

### Beauty Procedures
1. **List All**: `GET http://localhost:8086/ms-beautique-query/beauty-procedure`
2. **Find by Name**: `GET http://localhost:8086/ms-beautique-query/beauty-procedure/name/testname`
3. **Find by Description**: `GET http://localhost:8086/ms-beautique-query/beauty-procedure/description/testdescription`

### Appointments

1. **Find All**: `GET http://localhost:8086/ms-beautique-query/appointment`
2. **Find by Customer ID**: `GET http://localhost:8086/ms-beautique-query/appointment/customer/1`
3. **Find by Beauty Procedure**: `GET http://localhost:8086/ms-beautique-query/appointment/beauty-procedure/1`

---

## Ms-Command Endpoints

### Customers
1. **Create**: `POST http://localhost:8082/ms-beautique/customer`
    ```json
    {
        "name": "name",
        "phone": "1231253443",
        "email": "teste@teste.com"
    }
    ```

2. **Update**: `PUT http://localhost:8082/ms-beautique/customer`
    ```json
    {
        "id": 1,
        "name": "name",
        "phone": "1231253443",
        "email": "teste@teste.com"
    }
    ```

3. **Delete**: `DELETE http://localhost:8082/ms-beautique/customer/{id}`

### Beauty Procedures
1. **Create**: `POST http://localhost:8082/ms-beautique/beauty-procedures`
    ```json
    {
        "name": "haircut",
        "description": "men's haircut",
        "price": 45.00
    }
    ```

2. **Update**: `PATCH http://localhost:8082/ms-beautique/beauty-procedures`
    ```json
    {
        "id": 1,
        "name": "haircut",
        "description": "men's haircut",
        "price": 60.00
    }
    ```

3. **Delete**: `DELETE http://localhost:8082/ms-beautique/beauty-procedures/{id}`

### Appointments
1. **Create**: `POST http://localhost:8082/ms-beautique/appointments`
    ```json
    {
        "dateTime": "2024-12-28T09:23:00",
        "appointmentsOpen": true,
        "customer": null,
        "beautyProcedure": null
    }
    ```

2. **Update**: `PATCH http://localhost:8082/ms-beautique/appointments`
    ```json
    {
        "id": 1,
        "dateTime": "2024-12-28T21:00:00",
        "appointmentsOpen": true,
        "customer": null,
        "beautyProcedure": null
    }
    ```

3. **Set Customer**: `PUT http://localhost:8082/ms-beautique/appointments`
    ```json
    {
        "id": 1,
        "customer": 5,
        "beautyProcedure": 5
    }
    ```

4. **Delete**: `DELETE http://localhost:8082/ms-beautique/appointments/{id}`

## RabbitMQ Access Guide

## Prerequisites
Ensure you have the following requirements:
1. RabbitMQ installed and running on your server.
2. Credentials to access the RabbitMQ management interface and message broker.
3. Proper configuration and permissions to access the desired queues and exchanges.

---

## Accessing RabbitMQ Management Interface

### Default URL
The RabbitMQ Management Interface is typically accessible at:
```
http://<hostname>:15672
```
Replace `<hostname>` with the IP address or domain name of the RabbitMQ server. If running locally, use `localhost`.

### Login Credentials
Default credentials for RabbitMQ are:
- **Username**: `guest`
- **Password**: `guest`

> Note: The `guest` user can only log in from `localhost`. If you are accessing RabbitMQ remotely, create a new user with the necessary permissions.

---

## Connecting to RabbitMQ via Clients or Applications

### Connection URL
Use the following URL to connect to RabbitMQ:
```
amqp://<username>:<password>@<hostname>:5672/
```
Replace:
- `<username>` with your RabbitMQ username.
- `<password>` with your RabbitMQ password.
- `<hostname>` with the RabbitMQ server hostname or IP address.

### Libraries and Tools
- **Java**: Use libraries like [Spring AMQP](https://spring.io/projects/spring-amqp) or [RabbitMQ Java Client](https://www.rabbitmq.com/java-client.html).


## Setting Up Users and Permissions
1. Log in to the RabbitMQ Management Interface.
2. Navigate to the **Admin** tab.
3. Click on **Add a user** and fill in:
   - Username
   - Password
   - Tags (e.g., `administrator` for admin rights or `management` for limited access)
4. Assign permissions to the user:
   - Navigate to the **Permissions** section.
   - Select the user and assign permissions for vhosts, queues, and exchanges.

---

## Monitoring RabbitMQ
1. Access the Management Interface and check:
   - **Queues**: Current messages in queues.
   - **Exchanges**: Configured routing logic.
   - **Connections**: Active clients.
   - **Channels**: Message flow.
2. Use RabbitMQ CLI for monitoring:
   ```bash
   rabbitmqctl list_queues
   rabbitmqctl list_exchanges
   ```
---

For additional details, refer to the [official RabbitMQ documentation](https://www.rabbitmq.com/documentation.html).



## :zap: Technologies	

- Java
-  Spring Boot
- API REST
- PostgreSQL (Container)
- Docker
- Docker-compose
- MongoDB (Container)
- Shell
- RabbitMQ (Container)
- Spring Data JPA
- Spring AMQP 

## :memo: Developed features

- [x] CRUD Customer
- [x] CRUD BeautyProcedures
- [x] CRUD Appointments

## :technologist:	 Author

By Ramon Becker 👋🏽 Get in touch!



[<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/github.svg' alt='github' height='40'>](https://github.com/RamonBecker)  [<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/linkedin.svg' alt='linkedin' height='40'>](https://www.linkedin.com/in/ramon-becker-da-silva-96b81b141//)
![Gmail Badge](https://img.shields.io/badge/-ramonbecker68@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ramonbecker68@gmail.com)

