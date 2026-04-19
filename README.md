# zorrobpm-handler-example

An example Spring Boot application for handling ZorroBPM Service Tasks via RabbitMQ.

## Overview

This project demonstrates how to use the `zorrobpm-job-handler-spring-boot-starter` to implement job handlers for BPM process service tasks. The application subscribes to RabbitMQ queues and automatically receives tasks from the ZorroBPM Engine for execution.

## Project Structure

```
src/main/java/com/zorrodev/bpm/handler/examle/
├── EXAMPLE.java      # Spring Boot application entry point
└── Handler1.java     # Example handler for "job1" task
```

## Creating a Handler

Implement the `JobHandler` interface and register the class as a Spring Bean:

```java
@Slf4j
@Component
public class MyHandler implements JobHandler {

    @Override
    public String getJob() {
        return "myJobKey"; // must match the Job key defined in the BPMN model
    }

    @Override
    public List<ProcessVariable> handleJob(JobDetailModel model) {
        // business logic here
        log.info("Completing job {}", model.getJob());
        return List.of(); // process variables to write back to the process instance
    }
}
```

- `getJob()` — returns the job identifier that must match the **Job** field of the service task in the BPMN model.
- `handleJob()` — executes the business logic and returns a list of `ProcessVariable` objects that will be written back to the process instance.

## Configuration

RabbitMQ connection parameters can be set via environment variables (or `application.properties`):

| Variable            | Default      | Description            |
|---------------------|--------------|------------------------|
| `RABBITMQ_HOST`     | `localhost`  | RabbitMQ broker host   |
| `RABBITMQ_PORT`     | `5672`       | RabbitMQ port          |
| `RABBITMQ_USERNAME` | `zorrodev`   | Username               |
| `RABBITMQ_PASSWORD` | `zorrodev`   | Password               |

## Running

### Locally

```bash
mvn spring-boot:run
```

### With environment variables

```bash
RABBITMQ_HOST=my-rabbit-host \
RABBITMQ_USERNAME=user \
RABBITMQ_PASSWORD=secret \
mvn spring-boot:run
```

### Build and run JAR

```bash
mvn clean package
java -jar target/zorrobpm-handler-examle-0.0.1-SNAPSHOT.jar
```
