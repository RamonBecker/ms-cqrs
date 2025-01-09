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

2. **Topic Exchange**
   - Routes messages using patterns in routing keys:
     - `*` matches a single word.
     - `#` matches zero or more words.
   - **Use Case**: Flexible publish/subscribe systems.

3. **Fanout Exchange**
   - Broadcasts messages to all connected queues, regardless of the routing key.
   - **Use Case**: Notifications or global events.

4. **Headers Exchange**
   - Routes messages based on headers instead of routing keys.
   - **Use Case**: Complex routing based on message attributes.

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

## **APIs**





## Future Enhancements
- Implement advanced monitoring and logging for RabbitMQ and services.
- Explore additional optimizations for read models using caching solutions like Redis.
- Enhance the CI/CD pipeline for automated deployments.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request to improve this project.

---

## Contact
For questions or feedback, please contact [your-email@example.com].

