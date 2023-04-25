# SpringCloud Docker Deployment
I have basic SpringCloud project with following spring Boot modules:
  - eureka-server - Eureka Server serve `server.port=8761`
  - api-gateway - Eureka API Gateway serve `server.port=8765`
  - config-server - configuration server serve `server.port=8888` pointed to `config-server-repo` located on github
  - eureka-client - dummy microservice 1 where `server.port` assigned by `api-gateway`
  - eureka-client-2 - dummy microservice 2 where `server.port` assigned by `api-gateway`

Dummy microservices aren't use any database.

Can you layout docker-compose.yaml and dockerfile for each module?
Sure, here is an example docker-compose.yml file that can be used to deploy your Spring Cloud project:

```
version: '3.9'

services:
  eureka-server:
    build:
      context: ./eureka-server
      dockerfile: Dockerfile
    ports:
      - 8761:8761

  api-gateway:
    build:
      context: ./api-gateway
      dockerfile: Dockerfile
    ports:
      - 8765:8765
    depends_on:
      - eureka-server

  config-server:
    build:
      context: ./config-server
      dockerfile: Dockerfile
    ports:
      - 8888:8888
    depends_on:
      - eureka-server
    environment:
      - SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/{username}/{repo}.git
      - SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME={username}
      - SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD={password}

  eureka-client:
    build:
      context: ./eureka-client
      dockerfile: Dockerfile
    depends_on:
      - api-gateway

  eureka-client-2:
    build:
      context: ./eureka-client-2
      dockerfile: Dockerfile
    depends_on:
      - api-gateway
```

In this docker-compose.yml file, we define five services, one for each Spring Boot module. Each service is built using its own Dockerfile located in a subdirectory with the same name as the service (e.g. ./eureka-server/Dockerfile).

Here's an example Dockerfile for the eureka-server service:

```
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/eureka-server.jar /app

CMD ["java", "-jar", "eureka-server.jar"]
```
Here's an example Dockerfile for the api-gateway service:

```
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/api-gateway.jar /app

CMD ["java", "-jar", "api-gateway.jar"]
```
Here's an example Dockerfile for the config-server service:

```
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/config-server.jar /app

CMD ["java", "-jar", "config-server.jar"]
```
Here's an example Dockerfile for the eureka-client service:

```
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/eureka-client.jar /app

CMD ["java", "-jar", "eureka-client.jar"]
```
And finally, here's an example Dockerfile for the eureka-client-2 service:


```
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/eureka-client-2.jar /app

CMD ["java", "-jar", "eureka-client-2.jar"]
```

Note that these Dockerfile examples assume that the compiled Spring Boot JAR files are located in a target directory within each module's directory. You may need to modify the COPY commands in each Dockerfile to match your specific build configuration.

## Alexei - for config-server in docker-compose I have to add following environment vars

(currently defined as project application.properties)
spring.cloud.config.server.git.default-label=master
spring.cloud.config.server.git.clone-on-start=true
spring.cloud.config.server.git.search-paths=/{application}

Yes, you can add those environment variables to the config-server service in the docker-compose.yml file like this:

```
config-server:
    build:
      context: ./config-server
      dockerfile: Dockerfile
    ports:
      - 8888:8888
    depends_on:
      - eureka-server
    environment:
      - SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/{username}/{repo}.git
      - SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME={username}
      - SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD={password}
      - SPRING_CLOUD_CONFIG_SERVER_GIT_DEFAULT_LABEL=master
      - SPRING_CLOUD_CONFIG_SERVER_GIT_CLONE_ON_START=true
      - SPRING_CLOUD_CONFIG_SERVER_GIT_SEARCH_PATHS=/{application}
```

Here, we added the three environment variables you mentioned (SPRING_CLOUD_CONFIG_SERVER_GIT_DEFAULT_LABEL, SPRING_CLOUD_CONFIG_SERVER_GIT_CLONE_ON_START, and SPRING_CLOUD_CONFIG_SERVER_GIT_SEARCH_PATHS) to the environment section of the config-server service.

Note that you will need to replace {username} and {repo} in the SPRING_CLOUD_CONFIG_SERVER_GIT_URI environment variable with your own GitHub username and repository name, respectively. Additionally, if your repository requires authentication, you will need to replace {username} and {password} in the SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME and SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD environment variables with your own GitHub username and password, respectively.

## Alexei - how important to specify

```
    image: eclient:latest
    container_name: eclient
```

in docker-compose file?


Specifying image and container_name in the docker-compose.yml file is optional, but it is a good practice to do so.

When you specify the image property, Docker Compose will pull the image from the Docker registry and create a container based on that image. If you don't specify the image property, Docker Compose will try to build an image from the Dockerfile specified in the build property, but if the image already exists locally, it will use that image instead.

Specifying the container_name property allows you to give a specific name to the container that will be created. This can be helpful for identifying the container in the output of docker ps, logs, and other commands.

In short, while it is not strictly necessary to specify image and container_name, it can make your Docker Compose file more clear and easier to work with, especially when working with multiple containers.

## Alexei - should I tell docker-compose to build all images before run `docker-compose up -d`?  Or  it will build all images first time?

By default, docker-compose up will build any images that do not already exist locally, so you do not need to explicitly tell Docker Compose to build all images before running docker-compose up -d.

However, if you want to ensure that all images are built before running docker-compose up -d, you can use the docker-compose build command to explicitly build all the images specified in the docker-compose.yml file:

Copy code
docker-compose build
This command will build all images specified in the docker-compose.yml file, and tag them with the names specified in the image property of each service.

Once the images have been built, you can start the containers with docker-compose up -d, and Docker Compose will use the locally built images instead of trying to build them again.

## Alexei - will it detect modules with changes in source?
When you run docker-compose up, Docker Compose will detect changes to your Docker images and automatically rebuild them if necessary. This includes changes to the source code of your Spring Boot modules that are used to build your Docker images.

By default, Docker Compose uses cached layers to speed up the build process. This means that if you have not made any changes to a particular layer (for example, if you have not changed the dependencies of your Spring Boot application), Docker Compose will reuse the existing layer rather than rebuilding it from scratch. This can save a significant amount of time during the build process.

If you have made changes to your source code that require a layer to be rebuilt, Docker Compose will rebuild that layer and any subsequent layers that depend on it. This can result in longer build times, but it ensures that your Docker images are always up to date with the latest changes to your source code.

Note that if you make changes to your Dockerfile or any other files that are used during the build process (such as a requirements.txt file for a Python application), you will need to explicitly rebuild your Docker images using the docker-compose build command.





## Alexei - In all modules pom files I enable layering in config for spring-boot-maven-plugin

```
<plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <layers>
                        <enabled>true</enabled>
                    </layers>
                </configuration>
 </plugin>
```

and all submodules Docker file looks like following

```
FROM maven:3.8.6-amazoncorretto-19 AS buildOne
WORKDIR /app
COPY . /app
RUN mvn package

FROM amazoncorretto:19-al2-jdk as buildTwo
WORKDIR /app
ARG JAR_FILE=/app/target/*.jar
COPY --from=buildOne ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM amazoncorretto:19-al2-jdk
WORKDIR /app
COPY --from=buildTwo /app/dependencies/ ./
COPY --from=buildTwo /app/snapshot-dependencies/ ./
COPY --from=buildTwo /app/spring-boot-loader/ ./
COPY --from=buildTwo /app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
```

Enabling layering in the Maven plugin configuration for Spring Boot can help to improve the efficiency of the Docker build process, especially when there are frequent changes to the application's code or dependencies.

The Dockerfile you provided uses a multi-stage build to separate the build process into two stages: buildOne and buildTwo. The first stage, buildOne, builds the application's JAR file using Maven. The second stage, buildTwo, extracts the layers from the JAR file using the jarmode=layertools option, and then copies the layers into the final image.

Using the jarmode=layertools option tells Spring Boot to generate a layered JAR file, where each layer contains a different part of the application's code or dependencies. This allows Docker to cache each layer separately, so that when the application's code or dependencies change, only the affected layers need to be rebuilt.

Overall, this approach can help to reduce the size of the final Docker image, speed up the build process, and make the application more resilient to changes in its code or dependencies.
