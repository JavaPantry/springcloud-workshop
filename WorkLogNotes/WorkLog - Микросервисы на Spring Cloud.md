# March 29, 2023
## Микросервисы на Spring Cloud
- [Playlist](https://www.youtube.com/playlist?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
- `javabegin` Youtube Channel -  Микросервисы, Spring Cloud
20 videos 58,797 views Last updated on Jan 17, 2022

Введение в микросервисы для новичков. Создание проекта с микросервисами на Spring Cloud, API  …

- монолитные приложения
- что такое микросервисы
- монолит или микросервисы
- CI/CD Devops
- [что такое Spring Cloud](https://youtu.be/IOTw3Ul5OJc?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - spring.io/cloud
  - spring.io/microservices
- [что такое Service Discovery](https://youtu.be/3tMHszIzOu8?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - Eureka Netflix spring.io/projects/spring-cloud-netflix
- [схема работы тестового проекта](https://youtu.be/WDgIEq2vP2o?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - ![](assets/SpringCloudProjectStructure.png)
- [создание модульного проекта в IDEA](https://youtu.be/50IzgIDVar8?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - empty root project maven or gradle
  - select maven ![](assets/NewMavenProject.png)
- [добавление модуля Eureka Server](https://youtu.be/K4cremnFFsg?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - add eureka-server module - use spring initializr @ start.spring.io to create spring boot project with Eureka netflix dependency
  - module `eureka-server`
  - Group `com.springcloud.sbsuite`
  - add `Eureka Discovery Server`
    - select Spring Cloud Discovery > Eureka Server
    - [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)
    - [2. Service Discovery: Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)
- [разбор проекта Eureka Server](https://youtu.be/Xi3pfJIvzzo?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
- [доработка модуля Eureka Server](https://youtu.be/R3HbsekN55Q?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - add `@EnableEurekaServer`
  - `application.properties`
- [доработка модуля Eureka Server](https://youtu.be/h-Or-4RpTwI?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - select Spring Cloud Discovery > Eureka Discovery Client
  - Spring Web
  - `application.properties` `server.port=0`
  - add `@EnableEurekaClent` or `@EnableDiscoveryClent`
  - add application properties (copy from Miscellanies below)
    ```
    server.port=8081

    # unique id for this eureka server
    spring.application.name=eserver

    # turn off client-side registration with eureka server
    eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false

    # point microservi to the eureka server
    eureka.client.service-url.defaultZone=http://localhost:8081/eureka

    # turn off logging for eureka and discovery
    logging.level.com.netflix.eureka=OFF
    logging.level.com.netflix.discovery=OFF

    # logging pattern
    #logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
    logging.pattern.console=%C{1.} [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} - %msg%n
    ```
  - open services tab [Alt+8](https://youtu.be/R3HbsekN55Q?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f&t=440)
    - add SpringBoot will detect `EurekaServerApplication`
  - run `EurekaServerApplication` app
    ```
    org.springframework.boot.web.embedded.tomcat.TomcatWebServer [INFO ] - Tomcat started on port(s): 8081 (http) with context path ''
    org.springframework.cloud.netflix.eureka.serviceregistry.EurekaAutoServiceRegistration [INFO ] - Updating port to 8081
    org.springframework.boot.StartupInfoLogger [INFO ] - Started EurekaServerApplication in 8.196 seconds (process running for 10.308)
    ```
  - open http://localhost:8081/
    - ![](assets/EurekaServerConsole.png)
  - **Stopped here @ April 4, 2023**
- [добавление модуля клиента Eureka (2022)](https://youtu.be/h-Or-4RpTwI?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - module `eureka-client`
  - Group `com.springcloud.sbsuite`
  - add `Eureka Discovery Client`
  - add `Spring Web`
  - add module in root pom file
  - in `eureka-client/src/main/resources/application.properties`
    ```
    server.port=0

    # unique id for this eureka client
    spring.application.name=eclient

    # turn ON client-side registration with eureka server (default is True)
    #eureka.client.register-with-eureka=true
    #eureka.client.fetch-registry=true

    # point microservice to the eureka server
    eureka.client.service-url.defaultZone=http://localhost:8081/eureka

    # turn On logging for eureka and discovery (default is ON)
    #logging.level.com.netflix.eureka=OFF
    #logging.level.com.netflix.discovery=OFF

    # logging pattern
    #logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
    logging.pattern.console=%C{1.} [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} - %msg%n
    ```
  - in `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/EurekaClientApplication.java`  
    - adding `@EnableEurekaClient` **NOT RECOGNIZED**
    - [EnableEurekaClient import doesn't exist](https://stackoverflow.com/questions/68285299/enableeurekaclient-import-doesnt-exist)
      - @EnableEurekaClient is deprecated no need to annotate at springboot main applicationit is fine if we add the `spring-cloud-starter-netflix-eureka-client` dependency in `pom` and if we have the `spring.application.name=eclient` in yml or properties file it will be registered to Eureka Server
    - Note that `@EnableDiscoveryClient` is used when cloud server isn't Eureka
      - [What's the difference between EnableEurekaClient and EnableDiscoveryClient?](https://stackoverflow.com/questions/31976236/whats-the-difference-between-enableeurekaclient-and-enablediscoveryclient?noredirect=1&lq=1)
        - There are multiple implementations of "Discovery Service" (eureka, consul, zookeeper). @EnableDiscoveryClient lives in spring-cloud-commons and picks the implementation on the classpath. @EnableEurekaClient lives in spring-cloud-netflix and only works for eureka. If eureka is on your classpath, they are effectively the same.
  - add rest Controller `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/api/TestController.java`
    ```
    @RestController
    public class TestController {
        @GetMapping("/test")
        public String test() {
            return "test";
        }
    }
    ```
  - run eureka-client  
    ```
    com.netflix.discovery.DiscoveryClient [INFO ] - Discovery Client initialized at timestamp 1680707311878 with initial instances count: 0
    org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry [INFO ] - Registering application ECLIENT with eureka with status UP
    com.netflix.discovery.DiscoveryClient$3 [INFO ] - Saw local status change event StatusChangeEvent [timestamp=1680707311883, current=UP, previous=STARTING]
    com.netflix.discovery.DiscoveryClient [INFO ] - DiscoveryClient_ECLIENT/host.docker.internal:eclient:0: registering service...
    org.springframework.boot.StartupInfoLogger [INFO ] - Started EurekaClientApplication in 5.721 seconds (process running for 6.907)
    ```
    - ![](assets/EurekaServerConsole-confirmNewClient.png)
  - [local test - вызов микросервиса по порту](https://youtu.be/h-Or-4RpTwI?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f&t=508)
    - Neither `IntelliJ Service tab` nor `Eureka server console` doesn't show port for service
  - in project setting set default encoding to `UTF-8` [@](https://youtu.be/h-Or-4RpTwI?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f&t=634)
- [прямой вызов микросервиса по порту](https://youtu.be/Ot-Vs5Xpn84?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - just a review process of calling microservices (without API Gateway)
- [что такое API Gateway](https://youtu.be/rOW9S4fIHpY?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - old Netflix Zuul Server
  - [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
  - ![](assets/SpringCloudGateway.png)
- [добавление API Gateway](https://youtu.be/R9ZjiKKKI8g?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - module `api-gateway`
  - Group `com.springcloud.sbsuite`
  - add `Spring Cloud Routing > Gateway` dependency
  - add `Web > Spring Reactive Web` dependency
  - add `Spring Cloud Discovery > Eureka Discovery Client` dependency
  - add `<module>api-gateway</module>` in root pom
  - in project setting set default encoding to `UTF-8`
  - in `api-gateway/src/main/resources/application.properties`
    ```
    server.port=8082

    # unique id for this eureka server
    spring.application.name=api-gateway

    # turn ON client-side registration with eureka server (default is True)
    #eureka.client.register-with-eureka=false
    #eureka.client.fetch-registry=false

    # point microservice to the eureka server
    eureka.client.service-url.defaultZone=http://localhost:8081/eureka

    # turn On logging for eureka and discovery (default is ON)
    #logging.level.com.netflix.eureka=OFF
    #logging.level.com.netflix.discovery=OFF

    # logging pattern
    #logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
    logging.pattern.console=%C{1.} [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} - %msg%n

    # api-gateway config
    spring.cloud.gateway.discovery.locator.enabled=true
    spring.cloud.gateway.discovery.locator.lower-case-service-id=true
    ```
  - restart all modules in order:
    - eureka-server
    - eureka-client
    - api-gateway - Netty started on port 8082 Started ApiGatewayApplication in 5.736 seconds (process running for 6.749)
  - ![](assets/EurekaServerConsole-confirmApiGateway.png)
  - test `API Gateway` in browser on port `8082` + `microservice name` + `api endpoint` [@](http://localhost:8082/eclient/test)
  - after change in eclient
    - change TestController.java to return `Test eureka-client 1 > Hello`
    - and restart server, client and api-gateway I see only white page [@](http://localhost:8082/eclient/test)
  - full rebuild and start [@](http://localhost:8082/eclient/test) render response `Test eureka-client 1 > Hello`
    - [direct service url](http://localhost:51517/test) also render response `Test eureka-client 1 > Hello`
- [перенаправления в API Gateway](https://youtu.be/yPta5jHdUzs?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - add custom route in `api-gateway/src/main/resources/application.properties`
    ```
    # custom reroute for eureka client
    spring.cloud.gateway.routes[0].id=test
    spring.cloud.gateway.routes[0].uri=lb://eclient
    spring.cloud.gateway.routes[0].predicates[0]=Path=/main/test
    spring.cloud.gateway.routes[0].predicates[1]=Method=GET
    ```
  - change API mappings in `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/api/TestController.java`
    ```
    @RestController
    @RequestMapping("/main")
    public class TestController {
        @GetMapping("/test")
        public String test() {
            return "Test eureka-client 1 with custom reroute > Hello";
        }
    }
    ```
  - rebuild and run
    - http://localhost:8082/eclient/main/test -> Test eureka-client 1 with custom reroute > Hello
    - http://localhost:8082/main/test -> Test eureka-client 1 with custom reroute > Hello
- [добавление нового микросервиса](https://youtu.be/QGA4wTNR6hI?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - module `eureka-client-2`
  - Group `com.springcloud.sbsuite`
  - add `Eureka Discovery Client`
  - add `Spring Web`
  - add module in root pom file
  - in `eureka-client/src/main/resources/application.properties`
    ```
    server.port=0

    # unique id for this eureka client
    spring.application.name=eclient2

    # turn ON client-side registration with eureka server (default is True)
    #eureka.client.register-with-eureka=true
    #eureka.client.fetch-registry=true

    # point microservice to the eureka server
    eureka.client.service-url.defaultZone=http://localhost:8081/eureka

    # turn On logging for eureka and discovery (default is ON)
    #logging.level.com.netflix.eureka=OFF
    #logging.level.com.netflix.discovery=OFF

    # logging pattern
    #logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
    logging.pattern.console=%C{1.} [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} - %msg%n
    ```
  - add `eureka-client-2/src/main/java/com/springcloud/sbsuite/eurekaclient2/api/TestController.java`
    ```
    @RestController
    @RequestMapping("/new")
    public class TestController {
        @GetMapping("/name")
        public String test() {
                return "Test eureka-client 2  > Hello";
            }
    }
    ```
  - run all and confirm in eureks console localhost:8081
  - ![](assets/EurekaServerConsole-confirmNewClient2.png)
  - test [localhost:8082/eclient2/new/name](http://localhost:8082/eclient2/new/name) -> Test eureka-client 2 > Hello
  - add custom route for eclient2 in `api-gateway/src/main/resources/application.properties`
    ```
    # custom reroute for eureka client2
    spring.cloud.gateway.routes[1].id=name
    spring.cloud.gateway.routes[1].uri=lb://eclient2
    spring.cloud.gateway.routes[1].predicates[0]=Path=/new/name
    spring.cloud.gateway.routes[1].predicates[1]=Method=GET
    ```
  - restart `api-gateway`
    - test [localhost:8082/eclient2/new/name](http://localhost:8082/eclient2/new/name) -> Test eureka-client 2 > Hello
    - test [localhost:8082/new/name](http://localhost:8082/new/name) -> Test eureka-client 2 > Hello
- [что такое Load Balancer](https://youtu.be/35MFOs06s8I?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - old Netflix/ribbon
  - [Client-Side Load-Balancing with Spring Cloud LoadBalancer](https://spring.io/guides/gs/spring-cloud-loadbalancer/)
- [Load balancer в API Gateway](https://youtu.be/2ZK80tRDmc8?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - let's start 2nd instance of eclient (eureka-client)
    - select `EurekaClientApplication` in IntelliJ Run toolbar
    - select edit configuration
    - rename `EurekaClientApplication` to `EurekaClientApplication-inst1`
    - keep it open and press "Copy" button in dialog toolbar
    - rename new `EurekaClientApplication-inst1 (1)` to `EurekaClientApplication-inst2`
    - repeat new `EurekaClientApplication-inst3`
    - ![](assets/EurekaClientApplication-instances.png)
  - in `eureka-client/src/main/resources/application.properties` add
    ```
    # add unique id to instances
    eureka.instance.instance-id=${spring.application.name}:${random.uuid}}
    ```
  - in `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/api/TestController.java` add instanceId to output
    ```
    @RestController
    @RequestMapping("/main")
    public class TestController {

        @Value("${eureka.instance.instance-id}")
        private String instanceId;
        @GetMapping("/test")
        public String test() {
            // return text with instance id
            return String.format("Test eureka-client (instance %s) > Hello", instanceId);
        }
    }
    ```
  - lets start server, all instances and api-gateway
  - ![](assets/EurekaServerConsole-confirm-3-ClientInstances.png)
  - refresh test [localhost:8082/main/test](http://localhost:8082/main/test)
    - Test eureka-client (instance eclient:14372dce-cec0-49ab-a153-271ce1d926a9} ) > Hello
    - Test eureka-client (instance eclient:bd7fb44b-76d0-43aa-8323-01f66e479849} ) > Hello
    - Test eureka-client (instance eclient:69e9ae38-3194-4255-92ea-cab9e64df839} ) > Hello
- [стандартные порты приложений](https://youtu.be/B9f4l500lg8?list=PL8X2nqRlWfaZcyrJrsrWmQ17vtagWKv3f)
  - api-gateway standard port `server.port=8765`
  - clients usually have port unassigned `server.port=0` but you can assign manually 8080,8081,8082, etc
  - eureka-server standard port `server.port=8761`
  - change ports in eureka-Server to `8761` and api-gateway to `8765`
  - change ports in all application.properties `eureka.client.service-url.defaultZone=http://localhost:8081/eureka` to `eureka.client.service-url.defaultZone=http://localhost:8761/eureka`
  - restart all in order (server, apps, gateway)
  - open standard [server console](http://localhost:8761/)
  - ![](assets/EurekaServerConsole-confirm-allPorts.png)
  - open api-gateway for [1st client](http://localhost:8765/main/test) -> Test eureka-client (instance eclient:71389d78-815e-47ce-a640-7c13b6b185ba} ) > Hello
  - open api-gateway for [2nd client](http://localhost:8765/eclient2/new/name) -> Test eureka-client 2 > Hello
  - open api-gateway for [2nd client](http://localhost:8765/new/name) -> Test eureka-client 2 > Hello

## Move to Bitbucket
Get your local Git repository on Bitbucket
Step 1: Switch to your repository's directory
- cd /path/to/your/repo
Step 2: Connect your existing repository to Bitbucket
- git remote add origin git@bitbucket.org:JavaPantry/spring-cloud-eureka-base.git
- git push -u origin master

## Miscellanies
- github search: `@EurekaServerApplication`
  - [AtBeginHeart/EurekaserverApplication](https://github.com/AtBeginHeart/EurekaserverApplication/blob/master/src/main/resources/application.yml)
- github search: `spring-micro-demo`
  - [final-demos/springboot-microservices-demo](https://github.com/final-demos/springboot-microservices-demo/blob/main/eureka-server/src/main/resources/application.properties)

## Reading
- google search [spring microservices with Api gateway and spring authorization server](https://duckduckgo.com/?q=spring+microservices+with+Api+gateway+and+spring+authorization+server&atb=v314-1&ia=web)
  - [Spring Boot Authorization: Creating an Authorization Server for your Microservices](DayToDayNotes\2023\April\Микросервисы на Spring Cloud\Spring Boot Authorization_ Creating an Authorization Server for your Microservices.pdf)
  - [Using Spring Cloud Gateway with OAuth 2.0 Patterns](https://www.baeldung.com/spring-cloud-gateway-oauth2)
- Spring Authorization Server
  - [Spring Authorization Server](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html)
  - [Spring Boot Authorization Server](https://blog.devgenius.io/spring-boot-authorization-server-825230ae0ed2)

# April 7, 2023 - Add Python service to Spring Cloud Eureka
- follow article - [Spring Boot and Flask Microservice Discovery with Netflix Eureka](https://stackabuse.com/spring-boot-and-flask-microservices-eureka-client/)
  - [PDF](DayToDayNotes\2023\April\Микросервисы на Spring Cloud\Spring Boot and Flask Microservice Discovery with Netflix Eureka.pdf)
  - create Quart app project in C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite-quart
  - add eureka registration in app.py

    ```python
    import py_eureka_client.eureka_client as eureka_client

    rest_port = 5000
    eureka_client.init(eureka_server="http://localhost:8761/eureka",
                               app_name="data-aggregation-service",
                               instance_port=rest_port)
    app = Quart(__name__)

    @app.route("/")
    def home():
        return "Hello, Quart!"
    ```
  - start eureka-server
  - then all microservices
  - start Quart app springcloud-sbsuite-quart
  - start ApiGatewayApplication
  - confirm all services in eureka server [console](http://localhost:8761/)
    - ![](assets/EurekaServerConsole-confirm-4-QuartInstance.png)
  - confirm services access via
    - open api-gateway for [1st client](http://localhost:8765/main/test) -> Test eureka-client (instance eclient:71389d78-815e-47ce-a640-7c13b6b185ba} ) > Hello
    - open api-gateway for [2nd client](http://localhost:8765/eclient2/new/name) -> Test eureka-client 2 > Hello
    - open api-gateway for [2nd client](http://localhost:8765/new/name) -> Test eureka-client 2 > Hello
    - http://localhost:5000/hello/Alex
  - [test URL request to localhost:8765/data-aggregation-service/ failed](http://localhost:8765/data-aggregation-service/)
    - render `Whitelabel Error Page`
  - change Quart app host to `0.0.0.0`
    ```
    if __name__ == "__main__":
      app.run(debug=True, host="0.0.0.0", port=rest_port)
      # app.run(debug=True, host="127.0.0.1", port=rest_port)
    ```
  - Restart all apps in order
    - ![](assets/Restart-all-apps-in-order.png)
  - Test succeed [test URL request to localhost:8765/data-aggregation-service/](http://localhost:8765/data-aggregation-service/readFolder?folder=MyRequestFolder) > GET request path "/readFolder" folder: MyRequestFolder

## April 13, 2023 - create branch to save dev stage
- create branch `very-basic-cloud-base` for vanilla spring cloud base
  - only gateway, eureka-server, 2 springboot clients and one python client
- switch back to master

## April 15, 2023 - Create Spring Cloud config-server project

### Remove Gradle dependencies from root pom project
commit - remove intelliJ `.idea` folder and reopen project

### Create Spring Cloud config-server project
commit - create Spring Cloud config-server project

- [Quick Intro to Spring Cloud Configuration](https://www.baeldung.com/spring-cloud-configuration)
  - Last modified: February 16, 2023
- [Spring Cloud Config Server: Step by Step](https://www.springcloud.io/post/2022-03/spring-cloud-config-server-step-by-step)
  - 2022-03-17  tutorials
- follow Udemy [course](https://www.udemy.com/course/spring-boot-microservices-with-spring-cloud-beginner-to-guru/learn/lecture/18588296#overview)
- Add `@EnableConfigServer` to `ConfigServerApplication` class
  - cannot find symbol EnableConfigServer
  - directly import `import org.springframework.cloud.config.server.EnableConfigServer;`
- in `config-server/src/main/resources/application.properties`
  - `spring.cloud.config.server.git.uri=https://bitbucket.org/JavaPantry/spring-cloud-eureka-base/src/master/config-server`
- Error: org.springframework.cloud.config.server.environment.JGitEnvironmentRepository [WARN ] 2023-04-15 12:29:21.102 - Error occured cloning to base directory.
  org.eclipse.jgit.api.errors.TransportException: https://bitbucket.org/JavaPantry/spring-cloud-eureka-base/src/master/config-server: Authentication is required but no CredentialsProvider has been registered
- to fix [see](C:\0-Bitbucket\d2d-notesbb\DayToDayNotes\2023\April\GPTChat-Configure-SpringCloudConfigServer.md)
  ```properties
  spring.cloud.config.server.git.uri=https://bitbucket.org/JavaPantry/spring-cloud-eureka-base/src/master/config-server
  spring.cloud.config.server.git.username=JavaPantry
  spring.cloud.config.server.git.password=API-key
  spring.cloud.config.server.git.default-label=master
  ```
commit - run Spring Cloud config-server project (and confirm that it registered in Eureka server)

### Create config-server-repo folder
- this isn't a project, just a folder with config-server config files to which config-server will connect
  - create `springcloud-sbsuite/config-server-repo` folder
- create config files for each microservice application
  - create folders for each microservice application (e.g. name from `spring.application.name` in `application.properties` file. `spring.application.name=eclient )
    - create `springcloud-sbsuite/config-server-repo/eclient` folder
    - create `springcloud-sbsuite/config-server-repo/eclient2` folder
    - create `springcloud-sbsuite/config-server-repo/data-aggregation-service` folder
      - data-aggregation-service (python app has this name defined when it registers in Eureka server in app.py)
      - `eureka_client.init(eureka_server="http://localhost:8761/eureka", app_name="data-aggregation-service", instance_port=rest_port)`
  - currently my microservices don't have any database connection, so I don't need to create any config files for them
    - but I will create config files for `eclient` and `eclient2` just to test config-server

commit - Create config-server-repo folder

### Point config-server to config-server-repo folder
- point `config.server.git.uri` to this repo - `spring.cloud.config.server.git.uri=https://bitbucket.org/JavaPantry/spring-cloud-eureka-base/src/master/config-server-repo/`

commit - Point config-server to config-server-repo folder

## Python client registered but can not be connected via API Gateway

- WARNING: Error when getting host by   ip
  - Traceback (most recent call last): File "c:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite\springcloud-sbsuite-quart\.venv\lib\site-packages\py_eureka_client\netint_utils.py", line 35,
    - in get_host_by_ip return socket.gethostbyaddr(ip)[0] socket.herror: [Errno 11004] host not found
      - https://github.com/keijack/python-eureka-client/issues/75
        - That means that the host cannot find by ip automatically, you should specify the `instance_host` in init method.
        ```python
          eureka_client.init(eureka_server="http://localhost:8761/eureka",
                              app_name="data-aggregation-service",
                              instance_host="localhost",
                              instance_port=rest_port)
        ```
        - error is gone
        - in eureka console
        - instead `DATA-AGGREGATION-SERVICE	n/a (1)	(1)	UP (1) - 10.0.7.97:data-aggregation-service:5000`
          - shows `DATA-AGGREGATION-SERVICE	n/a (2)	(2)	UP (2) - 127.0.0.1:data-aggregation-service:5000 , 10.0.7.97:data-aggregation-service:5000`
            - restart all apps in order
              - EurekaServerApplication
              - ConfigServerApplication
              - EurekaClientApplication
              - EurekaClient2Application
              - DataAggregationServiceApplication
              - ApiGatewayApplication
              - console shows
                ```console
                Instances currently registered with Eureka
                Application	AMIs	Availability Zones	Status
                API-GATEWAY	n/a (1)	(1)	UP (1) - host.docker.internal:api-gateway:8765
                CONFIG-SERVER	n/a (1)	(1)	UP (1) - config-server:88fc4f4b-b859-4680-81cb-aae005e5591e}
                DATA-AGGREGATION-SERVICE	n/a (1)	(1)	UP (1) - 127.0.0.1:data-aggregation-service:5000
                ECLIENT	n/a (1)	(1)	UP (1) - eclient:c32942aa-d3be-4e43-add9-f05574e78b35}
                ECLIENT2	n/a (1)	(1)	UP (1) - host.docker.internal:eclient2:0
                ```
              - python microservice responsive again
                - http://localhost:8765/data-aggregation-service/readFolder?folder=MyRequestFolder  -> GET request path "/readFolder" folder: MyRequestFolder
                - http://localhost:8765/data-aggregation-service/ -> Hello, Quart!

- commit - Fix error: Python client registered but can not be connected via API Gateway

## April 16, 2023 - Create config property files for data-aggregation-service, eclient and eclient2
### Add config property files for data-aggregation-service, eclient and eclient2
- config-server-repo/data-aggregation-service/application.properties
  - `mycloud.config.test.var=data-aggregation-service-app`
- config-server-repo/eclient/application.properties
  - `mycloud.config.test.var=eclient-app`
- config-server-repo/eclient2/application.properties
  - `mycloud.config.test.var=eclient2-app`
- commit - Add config property files for data-aggregation-service, eclient and eclient2

### Test config-server
- http://localhost:8888/data-aggregation-service/local
  - return `{"name":"data-aggregation-service","profiles":["local"],"label":null,"version":"83278e7f7240336288c205ed8042bae8afb05661","state":null,"propertySources":[]}`
- http://localhost:8888/eclient/local
  - return `{"name":"eclient","profiles":["local"],"label":null,"version":"83278e7f7240336288c205ed8042bae8afb05661","state":null,"propertySources":[]}`
### add application-local.properties file to config-server-repo
- config-server-repo/eclient/application-local.properties
  - `mycloud.config.test.var=eclient-app-local`
- commit - add application-local.properties file to config-server-repo
- http://localhost:8888/eclient/local
  - return `{"name":"eclient","profiles":["local"],"label":null,"version":"0b4debd0da7dbfb8b43994ccc6a8bc56b9c3617c","state":null,"propertySources":[]}`
- http://localhost:8888/data-aggregation-service/default return `{"name":"data-aggregation-service","profiles":["default"],"label":null,"version":"0b4debd0da7dbfb8b43994ccc6a8bc56b9c3617c","state":null,"propertySources":[]}`
- http://localhost:8888/eclient/default return `{"name":"eclient","profiles":["default"],"label":null,"version":"0b4debd0da7dbfb8b43994ccc6a8bc56b9c3617c","state":null,"propertySources":[]}`

### Why I can't see the config property files in config-server?
- follow - [Spring Cloud Config Server: Step by Step](https://www.springcloud.io/post/2022-03/spring-cloud-config-server-step-by-step)
  - in `config-server/src/main/resources/application.properties`
    - comment github location and point to local file system
      - `spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/springcloud-sbsuite/springcloud-sbsuite-quart/config-server-repo`
        - restrat config-server
          - Error: org.springframework.cloud.config.server.environment.JGitEnvironmentRepository [INFO ] 2023-04-16 14:01:54.897 - Could not refresh default label main
          - java.lang.IllegalStateException: Cannot load environment
          - Caused by: java.lang.IllegalStateException: No .git at file:///c:/IntelliJ_WS_SpringBootWorkshop/springcloud-sbsuite/config-server-repo
          - to **Fix** copy submodule `C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite\config-server-repo` to `C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo`
            - run git init in `C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo`
            - add and commit your changes. The spring boot app will not read it until you commit it. Otherwise, it will not be able to find the file.
              - git add .
              - git commit -m "initial commit"
            - repoint `spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo`

            ```properties
            # point to local file system
            # replace `\` with `/` for windows path
            #spring.cloud.config.server.git.uri=file:///c:/IntelliJ_WS_SpringBootWorkshop/springcloud-sbsuite/config-server-repo
            spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo
            spring.cloud.config.server.git.search-paths=/{application}
            ```
          - restart config-server
          - http://localhost:8888/eclient/default -> `{"name":"eclient","profiles":["default"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,"propertySources":[{"name":"file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo/eclient/application.properties","source":{"mycloud.config.test.var":"eclient-app"}}]}`
            ```properties
            {"name":"eclient",
             "profiles":["default"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,
              "propertySources":[
                 {"name":"file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo/eclient/application.properties",
                  "source":{"mycloud.config.test.var":"eclient-app"}
                 }
              ]
            }
            ```
          - http://localhost:8888/eclient/local   ->
            ```properties
            {"name":"eclient",
             "profiles":["local"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f",
             "state":null,"propertySources":[
                {"name":"file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo/eclient/application-local.properties",
                 "source":{"mycloud.config.test.var":"eclient-app-local"}
            }]}
            ```
          - http://localhost:8888/data-aggregation-service/default -> `{"name":"data-aggregation-service","profiles":["default"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,"propertySources":[{"name":"file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo/data-aggregation-service/application.properties","source":{"mycloud.config.test.var":"data-aggregation-service-app"}}]}`
      - NOTE that for each service `mycloud.config.test.var` variable has unique value
        - `data-aggregation-service` has `data-aggregation-service-app`
        - `eclient` has `eclient-app`
        - `eclient` has `eclient-app-local` for `local` profile
        - `eclient2` has `eclient2-app`

- So to properly configure server-config property repository it should be located in separate folder with initialised (and committed) git

- commit - Configure config-server to read local config-server-repo in file system

### Create config-server-repo in bitbucket repository
- create new repository `config-server-repo` in `MicroservicesWorkshop` project
  - [Projects/MicroservicesWorkshop](https://bitbucket.org/JavaPantry/workspace/projects/MIC)
    - ![](assets/config-server-repo.png)
  - Commit
    - cd C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo
      - git remote add origin git@bitbucket.org:JavaPantry/config-server-repo.git
      - git push -u origin master
- in `config-server/src/main/resources/application.properties` point uri to bitbucket repository - https://bitbucket.org/JavaPantry/config-server-repo

  ```properties
  server.port=8888

  # unique id for this eureka client
  spring.application.name=config-server

  spring.cloud.config.server.git.uri=https://bitbucket.org/JavaPantry/config-server-repo
  spring.cloud.config.server.git.username=JavaPantry
  spring.cloud.config.server.git.password=C98LcSyS9wbmFuevstdz
  spring.cloud.config.server.git.default-label=master
  spring.cloud.config.server.git.clone-on-start=true
  spring.cloud.config.server.git.search-paths=/{application}

  # point to local file system
  # replace `\` with `/` for windows path
  # ERROR should be outside current project folder. This is error location: spring.cloud.config.server.git.uri=file:///c:/IntelliJ_WS_SpringBootWorkshop/springcloud-sbsuite/config-server-repo
  #spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo
  #spring.cloud.config.server.git.search-paths=/{application}

  logging.level.org.springframework.cloud=DEBUG
  logging.level.org.springframework.web=DEBUG
  ```

- restart config-server app
  - Test config-server
    - http://localhost:8888/eclient2/local
      ```properties
         {"name":"eclient2",
           "profiles":["local"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,
           "propertySources":[
               {"name":"https://bitbucket.org/JavaPantry/config-server-repo/eclient2/application.properties",
                "source":{"mycloud.config.test.var":"eclient2-app"}
         }]}
      ```
    - http://localhost:8888/eclient2/default
      ```properties
        {"name":"eclient2",
         "profiles":["default"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,
         "propertySources":[
              {"name":"https://bitbucket.org/JavaPantry/config-server-repo/eclient2/application.properties",
               "source":{"mycloud.config.test.var":"eclient2-app"}
      }]}
      ```
    - http://localhost:8888/eclient/default
      ```
        {"name":"eclient",
         "profiles":["default"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,
          "propertySources":[{"name":"https://bitbucket.org/JavaPantry/config-server-repo/eclient/application.properties",
                              "source":{"mycloud.config.test.var":"eclient-app"}
                                }
                            ]
            }
  ```
    - http://localhost:8888/eclient/local
        ```
          {"name":"eclient",
          "profiles":["local"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,
          "propertySources":[
                             {"name":"https://bitbucket.org/JavaPantry/config-server-repo/eclient/application-local.properties",
                              "source":{"mycloud.config.test.var":"eclient-app-local"}
                              },
                              {"name":"https://bitbucket.org/JavaPantry/config-server-repo/eclient/application.properties",
                              "source":{"mycloud.config.test.var":"eclient-app"}
                              }
                            ]}
        ```
    - http://localhost:8888/data-aggregation-service/default
      - `{"name":"data-aggregation-service","profiles":["default"],"label":null,"version":"9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state":null,"propertySources":[{"name":"https://bitbucket.org/JavaPantry/config-server-repo/data-aggregation-service/application.properties","source":{"mycloud.config.test.var":"data-aggregation-service-app"}}]}`

- commit - Configure config-server to read config-server-repo from bitbucket repository

## April 16-17, 2023 - Securing Config Server
- follow udemy course [Securing Config Server](https://www.udemy.com/course/spring-boot-microservices-with-spring-cloud-beginner-to-guru/learn/lecture/19751322#overview)
  - John uses postman
  - I'll use [IntelliJ Http Client](https://www.youtube.com/results?search_query=intellij+http+client)
    - [Start using Intellij Idea Http Client](https://youtu.be/jRZi3f3YmSs)
    - [HttpClient doc](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html)

### Use IntelliJ HttpClient to test encrypt/decrypt endpoints in ConfigServerApplication
- follow Udemy microservices [@](C:\IntelliJ_WS-SpringGuru\Spring Boot Microservices with Spring Cloud and Docker Course\SpringCloud - Securing Spring Cloud-25.md)
- more convinent than use browser and postman (not to mention that http test files will be saved in git)
  - this section copied [to](LearningNotes\IntelliJ\IntelliJ HttpClient to test endpoints\IntelliJ-HttpClient.md)
- create `project-root\menu New\menu HttpRequest-> TestServerConfigApp.http`
- add
  ```
   ### get default profiles
   GET http://localhost:8888/eclient/default

   ### get local profiles
   GET http://localhost:8888/eclient/local
  ```
- run methods in `TestServerConfigApp.http` file by click green arrow on left side of method
- ![](assets/HttpClient-TestServerConfigApp.png)
- responses

  ```console
    GET http://localhost:8888/eclient/default

      {
      "name": "eclient","profiles": ["default"],"label": null,
      "version": "9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state": null,
      "propertySources": [
      {
        "name": "https://bitbucket.org/JavaPantry/config-server-repo/eclient/application.properties",
        "source": {"mycloud.config.test.var": "eclient-app"}
      }]}

    GET http://localhost:8888/eclient/local

      {
      "name": "eclient","profiles": ["local"],"label": null,
      "version": "9ae8fc961f57afbb8ddc14a1c5f7a8b9ea61201f","state": null,
      "propertySources": [
      {
        "name": "https://bitbucket.org/JavaPantry/config-server-repo/eclient/application-local.properties",
        "source": {"mycloud.config.test.var": "eclient-app-local"}
      },
      {
        "name": "https://bitbucket.org/JavaPantry/config-server-repo/eclient/application.properties",
        "source": {"mycloud.config.test.var": "eclient-app"}
      }]}
  ```

- add HttpClient environment
  - ![](assets/add-HttpClient-environment.png)
- create http-client.env.json
  - ![](assets/http-client-env-json.png)
  ```
  {
    "local": {
  	"host": "http://localhost:8888"
    }
  }
  ```
- activate environment
  - ![](assets/activate-environment.png)
- and replace `http://localhost:8888` with `{{host}}`
  ```
  ### get default profiles
  GET {{host}}/eclient/default

  ### get local profiles
  GET {{host}}/eclient/local
  ```
- try to POST `POST {{host}}/encrypt`
  ```
  ### Encrypt the data
  POST {{host}}/encrypt

  mypassword
  ```
  - ERROR because I haven't set encript key
- in `config-server/src/main/resources/application.properties` switch to local file
  - comment bitbucket connection
  - and uncomment `spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo`
- **WRONG** `encrypt.key=MySuperSecretKey` to `config-server/src/main/resources/bootstrap.properties`
  - !!! Later we'll put it in environment variable
- Restart `ConfigServerApplication`
  - try `POST {{host}}/encrypt` -> same Error `"status": 400, "error": "Bad Request",`
- put `encrypt.key=MySuperSecretKey` to `config-server/src/main/resources/application.properties`
  - correct format in HTTP Client to POST **RAW** data
  ```
  ### Encrypt the data
  POST {{host}}/encrypt

  mypassword
  ```
  - response
  ```
  POST http://localhost:8888/encrypt
  Content-Type: text/plain;charset=UTF-8
  64de50e93acf5b39889af96e2cd3facfea66c22361bf4a69d7c99c8495588242
  ```
  - decrypt
  ```
  ### Decript the data
  POST {{host}}/decrypt

  64de50e93acf5b39889af96e2cd3facfea66c22361bf4a69d7c99c8495588242
  ```
  - response
  ```
  POST http://localhost:8888/decrypt
  Content-Type: text/plain;charset=UTF-8
  mypassword
  ```

- in `config-server/src/main/resources/application.properties` switch to local file
  - uncomment bitbucket connection
  - and comment `spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo`
  - restart ConfigServerApplication
  - test encrypt/decrypt again - tests run **OK**

### Encrypt some properties in ConfigServerApplication/eclient
- in `config-server/src/main/resources/application.properties` switch to local file
  - comment bitbucket connection
  - and uncomment `spring.cloud.config.server.git.uri=file:///C:/IntelliJ_WS_SpringBootWorkshop/config-server-repo`
- create `c:\IntelliJ_WS_SpringBootWorkshop\config-server-repo\eclient\application-local-secure.properties` file in config-server-repo repository
  - add variable `mycloud.config.test.var=eclient-app-local-secured`
  - run encrypt
    ```
    ### Encrypt the data
    POST {{host}}/encrypt

    eclient-app-local-secured
    ```
  - Response > 11f093e57adcf38c91ef3af5762bea3edc8b4acf313e37d651266d440e5342a7fe3dfa5cd958fb7fc0bca1d1d07a8740
  - rest decrypt
    ```
    ### Decript the data (send raw data)
    POST {{host}}/decrypt

    11f093e57adcf38c91ef3af5762bea3edc8b4acf313e37d651266d440e5342a7fe3dfa5cd958fb7fc0bca1d1d07a8740
    ```
  - Response > eclient-app-local-secured
  - put encrypted `eclient-app-local-secured` in `application-local-secure.properties`
    - `mycloud.config.test.var={cipher}11f093e57adcf38c91ef3af5762bea3edc8b4acf313e37d651266d440e5342a7fe3dfa5cd958fb7fc0bca1d1d07a8740`
  - commit and push to git
    ```
    C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>git add .
    C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>git commit -m "add secured property"
    C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>git push
    ```
  - restart ConfigServerApplication (looks like not required. profile picked up)
    ```
    ### get local-secure profiles
    GET {{host}}/eclient/local-secure
    ```
  - Response - NOTE that `mycloud.config.test.var` is decripted  `"mycloud.config.test.var": "eclient-app-local-secured"`
    ```
    GET http://localhost:8888/eclient/local-secure

    {
      "name": "eclient",
      "profiles": [
    	"local-secure"
      ],
      "label": null,
      "version": "113de9e3cd5b1991a20aa6be3e8494a88ccdd555",
      "state": null,
      "propertySources": [
    	{
    	  "name": "https://bitbucket.org/JavaPantry/config-server-repo/eclient/application-local-secure.properties",
    	  "source": {
    		"mycloud.config.test.var": "eclient-app-local-secured"
    	  }
    	},
    	{
    	  "name": "https://bitbucket.org/JavaPantry/config-server-repo/eclient/application.properties",
    	  "source": {
    		"mycloud.config.test.var": "eclient-app"
    	  }
    	}
      ]
    }
    ```

- excersizes above could be executed from command line `curl`
  - see in [spring-cloud-config/docs/3.0.5](https://docs.spring.io/spring-cloud-config/docs/3.0.5/reference/html/#_security)
  ```
  $ curl localhost:8888/encrypt -s -d mysecret
  682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda

  $ curl localhost:8888/decrypt -s -d 682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda
  mysecret
  ```

## April 18, 2023 - Securing Config Server
### 287. Secure Spring Cloud Config Server 9min
- add security to `config-server/pom.xml`
  ```
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```
- will add basic authentication with randomized password for user
  - to define user and password add following properties in `config-server/src/main/resources/application.properties`
  ```
  spring.security.user.name=avp
  spring.security.user.password=password
  ```
- start EurekaServerApplication
- start ConfigServerApplication
- now if you try access ConfigServerApplication you'll get basic authentication form
  - http://localhost:8888/data-aggregation-service/default
  - and cold login with `avp/password` and see response
  - open http://localhost:8888/data-aggregation-service/default as already logged in user
  - open http://localhost:8888/eclient/default as already logged in user
- try to start eclient - http://localhost:50358/main/test (note 50358 is a port assigned at startup)
  - add `spring.cloud.config.fail-fast=true` `in eureka-client/src/main/resources/application.properties`
    - suppose to `fail fast` if config server is not available, but in udemy course John meant that it will fail for unauthenticated access
  - it should fail on start (but started without errors)
    - means that it is not reading ConfigServerApplication security
  - started without errors even if I shut down ConfigServerApplication
- try to import properties from ConfigServerApplication
  - [Spring Boot Config Data Import](https://docs.spring.io/spring-cloud-config/docs/3.0.5/reference/html/#config-data-import)
    - note: -	A bootstrap file (properties or yaml) is not needed for the Spring Boot Config Data method of import via spring.config.import.
  - In `eureka-client/src/main/resources/application.properties` add
    ```
    # note last slash `/` in the path is important.
    spring.config.import=optional:configserver:http://localhost:8888/
    ```
  - In `eureka-client/src/main/resources/application.properties` add
    ```
    # suppose to specify config server url and fail fast if not available
    spring.cloud.config.uri=http://localhost:8888
    ```

## April 19, 2023 - Securing Config Server

### Secure Eureka Server
288. Use Spring Security to Secure Eureka Server 10min
- add security to `eureka-server/pom.xml`
  ```
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```
- will add basic authentication with randomized password for user
  - to define user and password add following properties in `eureka-client/src/main/resources/application.properties`
  ```
  spring.security.user.name=avp
  spring.security.user.password=password
  ```
- course suggests to create `class SecurityConfig extends WebSecurityConfigurerAdapter`
  - `WebSecurityConfigurerAdapter` is deprecated in 3.x
  - [How to fix error of WebSecurityConfigurerAdapter when upgrade to Spring Boot 3.0.0?](https://stackoverflow.com/questions/74666596/how-to-fix-error-of-websecurityconfigureradapter-when-upgrade-to-spring-boot-3-0)
    - WebSecurityConfigurerAdapter is deprecated and should use component-based security configuration. You'll have to create a SecurityFilterChain bean for HTTPSecurity and shouldn't extend WebSecurityConfigurerAdapter as other answer suggested. Please refer [Spring Security without the WebSecurityConfigurerAdapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter) for more details.
    - Instead `@Override protected void configure(HttpSecurity http) throws Exception {...}`
    - create `@Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {...}`
- start EurekaServerApplication
  - open eureka server console @ http://localhost:8761/
  - now it shows basic authentication popup to login. Enter `avp/password` to login
-

#### Configure ConfigServerApplication app

- go to `config-server/src/main/resources/application.properties`
- add `eureka.client.service-url.defaultZone=http://netflix:eureka@localhost:8761/eureka`
  - course suggests put `#eureka.client.service-url.defaultZone=http://netflix:eureka@localhost:8761/eureka`
- but beacause we define `avp/password` to login add `eureka.client.service-url.defaultZone=http://avp:password@localhost:8761/eureka`
- now start ConfigServerApplication (Started - Ok)
- Refresh eureka server console @ http://localhost:8761/
  - confirm - Instances currently registered with Eureka: `CONFIG-SERVER`	n/a (1)	(1)	UP (1) - config-server:a9e6807b-bb21-4ae8-a096-2f032567bb4b}
-

## April 20, 2023 - Secure Eclient Microservice

### 289. Secure Inventory Service with Spring Security 4min
- [Secure Inventory Service](https://www.udemy.com/course/spring-boot-microservices-with-spring-cloud-beginner-to-guru/learn/lecture/19910046#overview)
- add Security to `eureka-client/pom.xml`
  ```
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```
- add `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/config/WebSecurityConfig.java`
- course suggests to create `class WebSecurityConfig extends WebSecurityConfigurerAdapter`
  - `WebSecurityConfigurerAdapter` is deprecated in 3.x
  - [How to fix error of WebSecurityConfigurerAdapter when upgrade to Spring Boot 3.0.0?](https://stackoverflow.com/questions/74666596/how-to-fix-error-of-websecurityconfigureradapter-when-upgrade-to-spring-boot-3-0)
    - WebSecurityConfigurerAdapter is deprecated and should use component-based security configuration. You'll have to create a SecurityFilterChain bean for HTTPSecurity and shouldn't extend WebSecurityConfigurerAdapter as other answer suggested. Please refer [Spring Security without the WebSecurityConfigurerAdapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter) for more details.
      - Instead `@Override protected void configure(HttpSecurity http) throws Exception {...}`
      - create `@Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {...}`
- will add basic authentication with randomized password for user
- to define user and password add following properties in `eureka-client/src/main/resources/application.properties`
  ```
  spring.security.user.name=avp
  spring.security.user.password=password
  ```
- start ECLIENT
- open http://localhost:59338/main/test
  - and confirm basic authentication popup
  - enter `avp/password`
  - on success login confirm render `Test eureka-client (instance eclient:xxx-...-xxx} ) > Hello`

- commit - Secure Eclient Microservice

### Configure eclient2 security
- here I need to make eclient microservice communicate with eclient2 (I skip this in very basic project layout)
  - make same changes to eclient2 as I did in eclient (see `289. Secure Inventory Service with Spring Security 4min`)
    - add security dependency in `eureka-client-2/pom.xml`
      ```
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
      </dependency>
      ```
    - copy and add `eureka-client-2/src/main/java/com/springcloud/sbsuite/eurekaclient/config/WebSecurityConfig.java`
    - add user/password to `eureka-client-2/src/main/resources/application.properties`
      ```
      spring.security.user.name=avp
      spring.security.user.password=password
      ```
    - open http://localhost:61137/new/name
      - and confirm basic authentication popup
      - enter `avp/password`
      - on success login confirm render `Test eureka-client 2 > Hello`
- commit - Secure Eclient2 Microservice

### Create Dummy Eclien2Service within Eclient microservice
- create `interface Eclient2Service` within `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/eclient2`
  - with dummy implementation in `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/eclient2/Eclient2ServiceRestTemplateImpl.java`
    - dummy - no actual contact to eclient2 service
- wire `Eclient2Service` in `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/api/TestController.java`
  - `private final Eclient2Service inventoryRestService;` with constructor
- test http://localhost:eclientport/main/eclient2/name
  - twice asking for basic authentication -> enter `avp/password`
  - Response: `Test eureka-client (instance eclient:2ada3e91-79b8-4949-9ee1-f8e9c69f804c} ) eclient2 response > Test eureka-client 2 > Hello`
- commit - Create Dummy Eclien2Service within Eclient microservice

### Connect Eclient microservice to Eclient2 with Eclien2Service
- Add `private final RestTemplate restTemplate;` to
  - build rest template `this.restTemplate = restTemplateBuilder.basicAuthentication("avp", "password").build();`
  - make a call to `String eclient2url = "http://localhost:8765/new/name";`
    - `String response = restTemplate.exchange(eclient2url, HttpMethod.GET, null, String.class).getBody();`
- Errors: Eureka server doesn't list all services nor API Gateway
  - **When did it happen?!**
- commit - Connect Eclient microservice to Eclient2 with Eclien2Service
  - Cloud not properly loaded

??? ### 290. Configure RESTTemplate for HTTP Basic Authentication 5min
-
??? 291. Configure Feign Client for HTTP Basic Authentication 6min
-
??? 292. Security Retrospective 6min

## April 21, 2023 - Rollback security in springcloud-sbsuite
### Rollback security in `very-basic-cloud-base-no-secure`
- make branch `very-basic-cloud-base-no-secure`
  - comment `spring-boot-starter-security` in all modules
  - refresh mavens in project
  - confirm all applications are UP in [EutekaServerApplication](http://localhost:8761/)
    - API-GATEWAY	UP (1) - host.docker.internal:api-gateway:8765
    - CONFIG-SERVER	UP (1) - config-server:91f07997-88cc-4e87-a743-eab38cfc01de}
    - DATA-AGGREGATION-SERVICE UP (1) - 127.0.0.1:data-aggregation-service:5000
    - ECLIENT	UP (1) - eclient:a2bb2bee-6abc-44ec-9903-16231ceb68d6}
    - ECLIENT2	UP (1) - host.docker.internal:eclient2:0
  - add Python service profiles in `config-server-repo/data-aggregation-service`
    - change `mycloud.config.test.var=data-aggregation-service-app-default` application.properties
    - add `mycloud.config.test.var=data-aggregation-service-app-local` in application-local.properties
  - test loading cloud in Browsers
    - ECLIENT http://localhost:8765/main/test
    - ECLIENT http://localhost:8765/eclient/main/test
    - ECLIENT2 via ECLIENT http://localhost:8765/eclient/main/eclient2/name
    - ECLIENT2 http://localhost:8765/new/name
    - ECLIENT2 http://localhost:8765/eclient2/new/name
    - Python http://localhost:8765/data-aggregation-service/
    - Python http://localhost:8765/data-aggregation-service/hello/Alexei Ptitchkin
    - Python http://localhost:8765/data-aggregation-service/readFolder?folder=MyRequestFolder
    - Python http://localhost:8765/data-aggregation-service//folder/FolderAsPath
  - test in HttpClient file TestApiGateway.http - Ok
  - test in HttpClient file TestServerConfigApp.http - Ok
- commit - Turn off security and add HttpClient tests

### Make a branch with basic security in `very-basic-cloud-base-with-failed-security`
- from master branch make new branch `very-basic-cloud-base-with-failed-security`
  - to store stage where security applied but whole cloud not discoverable
- after that will merge `very-basic-cloud-base-no-secure` to `master`
- commit - Make a branch with basic security to store stage where security applied but whole cloud not discoverable

### Merge `very-basic-cloud-base-no-secure` into `master` branch to continue with Docker
- test loading cloud in Browsers and HttpClient
- commit - Merge very-basic-cloud-base-no-secure into master branch to continue with Docker

### Read properties from ConfigServerApplication repo
- Previous issue
  - I can test ConfigServerApplication asking for application properties but can't read them from client
- Solution
  - read how to read properties from remote config repo [Spring Cloud Config](https://docs.spring.io/spring-cloud-config/docs/3.0.5/reference/html/#_client_side_usage)
  - make sure that you have `spring.config.import` in project `eureka-client/src/main/resources/application.properties`
    - `spring.config.import=optional:configserver:http://localhost:8888/`
  - add dependency `eureka-client/pom.xml`
    ```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    ```
  - add mapped method to `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/api/TestController.java`
    ```
    @Value("${mycloud.config.test.var}")
    private String configVar;
    ...
    @GetMapping("/config-var")
    public String testConfigVar() {
        return String.format("Test eureka-client config var > %s", configVar);
    }
    ```
  - **Not required** add `@ConfigurationPropertiesScan` annotation in `eureka-client/src/main/java/com/springcloud/sbsuite/eurekaclient/EurekaClientApplication.java` **Not required**
  - rename `config-server-repo/eclient/application-local-secure.properties` to `config-server-repo/eclient/application-localsecure.properties`
    - after any change in repo you need to submit repository
      ```
      C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>git add .
      C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>git commit -m "add localsecure eclient properties"
      C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>git push
      ```
  - build and start `eureka-client` module
  - Testing
    - no active profile (as initial build)
    ```
    GET http://localhost:8765/eclient/main/config-var
    > Test eureka-client config var > eclient-app
    ```
    - Change active profile to `local` in Intellij project Run/Debug dialog
    ```
    GET http://localhost:8765/eclient/main/config-var
    > Test eureka-client config var > eclient-app-local
    ```
    - Change active profile to `localsecure` in Intellij project Run/Debug dialog
      - will show `mycloud.config.test.var={cipher}xxxx....xxx`
      - note that response shows decrypted variable
    ```
    GET http://localhost:8765/eclient/main/config-var
    Test eureka-client config var > eclient-app-local-secured
    ```

- commit - Fix Reading properties from ConfigServerApplication repo


## April 24, 2023 - Dockerize springcloud-sbsuite

### Make a branch for Docker `very-basic-cloud-base-docker`

## April 25, 2023 - Dockerize springcloud-sbsuite
### Enable docker layering in all submodules pom files

- add <layers><enabled>true</enabled></layers> in all submodules pom files

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

- commit - Enable docker layering in all submodules pom files

### Add Dockerfile for each module and docker-compose.yml
- see GPTChat-SpringCloud-Docker-Deployment.md
- try to run docker-compose up -d

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.springcloud.sbsuite.inventory.EurekaClientApplicationTests

[main] DEBUG org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Neither @ContextConfiguration nor @ContextHierarchy found for test class [EurekaClientApplicationTests]: using SpringBootContextLoader
[main] DEBUG org.springframework.test.context.support.AbstractContextLoader -- Could not detect default resource locations for test class [com.springcloud.sbsuite.inventory.EurekaClientApplicationTests]: no resource found for suffixes {-context.xml, C
ontext.groovy}.

[main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.springcloud.sbsuite.inventory.EurekaClientApplicationTests]: EurekaClientApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.

DEBUG org.springframework.test.context.support.AbstractDirtiesContextTestExecutionListener -- Before test class: class [EurekaClientApplicationTests], class annotated with @DirtiesContext [false] with mode [null]
ERROR org.springframework.boot.SpringApplication -- Application run failed
org.springframework.cloud.config.client.ConfigClientFailFastException: Could not locate PropertySource and the fail fast property is set, failing

[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 5.374 s <<< FAILURE! - in com.springcloud.sbsuite.inventory.EurekaClientApplicationTests

[ERROR] contextLoads  Time elapsed: 0.062 s  <<< ERROR!
java.lang.IllegalStateException: Failed to load ApplicationContext for [WebMergedContextConfiguration@303a5119  ```
```
- comment out test classes
- try to run `docker-compose up -d`
  - all sevices and eureka cloud started but not responsive
  - eclient constantly restarting
- commit - initial dockerizing springcloud-sbsuite

## May 3, 2023 - Dockerize test springcloud-sbsuite-docker
### Create dummy **springcloud-sbsuite-docker\eureka-client** clone
- Dummy microservice project cloned from `C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite\eureka-client`
  - to `C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite-docker\eureka-client`
  - init Git `C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite-docker\eureka-client>git init`
- see [Docker CheetSeet](LearningNotes\Docker-Kubernetes\Docker-CheetSeet.md)
- build image `C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite-docker\eureka-client>docker build -t eclient-test .`

```
[+] Building 69.1s (18/18) FINISHED
 => [internal] load build definition from Dockerfile                                                                                                  0.2s
 => => naming to docker.io/library/eclient-test
```

- Run `docker run -p8080:8080 eclient-test`
  - add `-d` for detach
  - fix errors

```
in pom.xml
<!--May 3:
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>-->

in application.properties
server.port=0 -> server.port=8080
# May 3: eureka.client.service-url.defaultZone=http://localhost:8761/eureka
# May 3: spring.config.import=optional:configserver:http://localhost:8888/
# May 3: spring.cloud.config.fail-fast=true
# May 3: spring.cloud.config.uri=http://localhost:8888

in TestController.java
//May 3: @ Value("${mycloud.config.test.var}")
```

- Run `docker run -p8080:8080 eclient-test`
- Test in IntelliJ HttpClient

```
### get eclient via gateway via gateway- OK
GET http://localhost:8080/main/test

### get direct eclient - OK
GET http://localhost:8080/main/config-var
```

- confirm unpacked app loaded

```
app
  BOOT-INF
    classes
      application.properties
      com
        springcloud
          sbsuite
            eurekaclient
              api\TestController.class
              eclient2
              EurekaClientApplication.class
```

## May 10 - Resume on Dockerizing springcloud-sbsuite
- read IntelliJ Idea [Integrated tools/Docker](https://www.jetbrains.com/help/idea/docker.html)
- all following actions done in in IntelliJ Idea
  - open `C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite\docker-compose.yml`
  - click on double green arrow next to `services`
  - all services started but eclient immediately failed (and continue restarting)
    - 2023-05-10 16:16:41 Caused by: org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8888/eclient/default": Connection refused
    - 2023-05-10 16:16:41 20:16:41.243 [main] ERROR org.springframework.boot.SpringApplication -- Application run failed
    - 2023-05-10 16:16:41 org.springframework.cloud.config.client.ConfigClientFailFastException: Could not locate PropertySource and the fail fast property is set, failing
    - see `spring.cloud.config.fail-fast=true` in `eureka-client/src/main/resources/application.properties`

- committed - May 10, 2023 - sync eclient & eclient2 properties
  - run docker compose from within IntelliJ
  - all sevices and eureka cloud started but not responsive
  - eclient constantly restarting
  - kill docker containers and run whole cloud from intelliJ one-by-one - Ok

## May 11 - Finalize Dockerizing springcloud-sbsuite

### Rearrange service dependencies in docker-compose.yaml
  config-server:
    depends_on:
      - eureka-server
  api-gateway:
    depends_on:
      - config-server  instead eureka-server

### Fix Issue that microservices within dockerized spring cloud are not discoverable
- **Fix** replace in all properties files `localhost` with `host.docker.internal`
  - `eureka.client.service-url.defaultZone=http://localhost:8761/eureka`
  - `eureka.client.service-url.defaultZone=http://host.docker.internal:8761/eureka`
  - within `Eclient2ServiceRestTemplateImpl.java` replace  `localhost` with `host.docker.internal`
    - String eclient2url = "http://localhost:8765/new/name";
    - String eclient2url = "http://host.docker.internal:8765/new/name";

## May 12 - Finalize Dockerizing springcloud-sbsuite
### Merge `very-basic-cloud-base-docker` branch to `master` branch
- run test requests from TestApiGateway.http - Ok
- python app still not dockerized (not discoverable (but visible in eureka server console http://localhost:8761/), but runs as standalone server at port 5000)
- commit - Merge `very-basic-cloud-base-docker` branch to `master` branch

### Dockerize python springcloud-sbsuite-quart service
- create simplified `Dockerfile`
- add service `sbsuite-quart` to docker-compose.yml
  - `DATA-AGGREGATION-SERVICE` visible in eureka server console (port 8761)
  - HTTP Error code 500 on request via ApiGateway:8765
- commit - Dockerize python springcloud-sbsuite-quart service

## May 13 - springcloud-sbsuite - Dead end branch before make services more specific
- create `dead-end-very-basic-cloud-base-docker` branch
- will rename and create new services for each microservice
  - ordering service
  - inventory service
  - payment service
  - shipping service
  - etc.
- commit - Dead end branch before make services more specific

### Rename `eureka-client` to `inventory-service`
- rename module `eureka-client` to `inventory-service` in root pom.xml
- in separate config-server-repo project
  - in `C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>` rename `eureka-client` to `inventory-service`
    - git add .
    - git commit -m "rename eureka-client to inventory-service"
    - git push
    - copy it in project `config-server-repo`
- Rename EurekaClientApplication.java to InventoryServiceApplication.java

## May 14, 2023 - springcloud-sbsuite refactor services
### Rename `eureka-client` service to `inventory-service`
- rename module name in file system and in root pom
- replace `host.docker.internal` with `localhost` in all property files
- replace `host.docker.internal` with `localhost` in Eclient2ServiceRestTemplateImpl.java
- strat whole cloud from IntelliJ - Ok
- test requests to `inventory-service` from TestApiGateway.http - Ok
- commit - Rename `eclient` service to `inventory-service`

### Rename `eureka-client-2` service to `store-service`
- rename module name in file system and in root pom
- rename module `eureka-client-2` to `store-service` in root pom.xml
- in separate config-server-repo project
  - in `C:\IntelliJ_WS_SpringBootWorkshop\config-server-repo>` rename `eclient2` to `store-service`
    - git add .
    - git commit -m "rename eclient2 to store-service"
    - git push
    - copy it in project `config-server-repo`
- Rename EurekaClient2Application.java to StoreServiceApplication.java
- rename load-balancer urls in api-gateway/src/main/resources/application.properties
  - spring.cloud.gateway.routes[0].uri=lb://inventory-service
  - spring.cloud.gateway.routes[1].uri=lb://store-service
- strat whole cloud from IntelliJ - Ok
  - API-GATEWAY	- UP
  - CONFIG-SERVER	- UP
  - INVENTORY-SERVICE - UP
  - STORE-SERVICE	- UP
- test requests to `store-service` from TestApiGateway.http - Ok
- commit - Rename `eclient2` service to `store-service`
   
### Merge `rename-services` branch to `master` branch
- commit - Merge `rename-services` branch to `master` branch

### Introduce profile for local development
- profile `localdev` to distinguish between local development and dockerized cloud
- see [chat](WorkLogNotes/GPTChat-introduce-local-docker-profile.md)
- test running whole cloud on local-dev profile - Ok
- commit - Introduce profile for local development

### run whole cloud on docker 
- Fix docker-compose.yml to run whole cloud on default profile
- rename services inside docker-compose.yml to match new submodule names
- test running whole cloud on local-dev profile - Ok except one
  - GET http://localhost:8765/inventory-service/main/eclient2/name - "status": 500, "error": "Internal Server Error"
    - [ERROR] 2023-05-14 21:30:49.959 - Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8765/new/name": Connect to http://localhost:8765 [localhost/127.0.0.1] failed: Connection refused] with root cause
      2023-05-14 17:30:49 org.apache.hc.client5.http.HttpHostConnectException: Connect to http://localhost:8765 [localhost/127.0.0.1] failed: Connection refused
- commit - run whole cloud on docker

### Fix inventory-service/main/eclient2/name Error 500
- in Eclient2ServiceRestTemplateImpl.java read `hostnameurl` property and form proper url 

```java
class Eclient2ServiceRestTemplateImpl
	@Value("${hostnameurl}")
	private String hostnameurl;

	@Override
	public String testName(){
		String eclient2url=String.format("http://%s:8765/new/name",hostnameurl);
		// ...
		}
}
```
- commit - Fix inventory-service/main/eclient2/name Error 500

## May 15, 2023 - springcloud-sbsuite refactor services
### Rename service modules in pom files
- eureka-client -> inventory-service
- eureka-client-2 -> store-service
- commit - Rename service modules in pom files

### Rename endpoints in inventory-service and store-service
- in store-service
  - rename package `com.springcloud.sbsuite.eurekaclient2.api` to `com.springcloud.sbsuite.orders.api` 
  - in StoreController.java - @GetMapping("/new/name") -> @GetMapping("store/inventory")
- in inventory-service
  - rename package `com.springcloud.sbsuite.inventory.api` to `com.springcloud.sbsuite.inventory.api`
  - rename TestController.java to InventoryController.java
    - @GetMapping("/main/test") -> @GetMapping("inventory/test")
    - @GetMapping("/main/eclient2/name") -> @GetMapping("inventory/store/inventory")
- in api-gateway/src/main/resources/application.properties
  - rename load-balancer routers ids and predicates
- fix tests in TestApiGateway.http
- test in local-dev profile - Ok
- test in docker - Ok
- commit - Rename endpoints in inventory-service and store-service

### Rename package `com.springcloud.sbsuite.eurekaclient2` to `com.springcloud.sbsuite.inventory`  
- commit - Rename package `eurekaclient2` to `inventory` in inventory-service module

### Move "/storeinventory" from inventory-service to store-service
Originally "/storeinventory" mapping was in dummy "eclient" service  which became "inventory-service" (i.e. have no sense in inventory-service ask store-service for inventory). So move it to store-service.
And vice versa move "/inventory" from store-service to inventory-service.
- test in local-dev profile - Ok
- test in docker - Ok
- commit - Move "/storeinventory" from inventory-service to store-service

## May 16, 2023 - 
### Remove spring-cloud-starter-config dependency from inventory-service pom.xml
- turn out I need this dependency if I want to refer `@Value("${mycloud.config.test.var}")` in InventoryController.java
- roll back

### Add Lombok to all services
- add lombok dependency to inventory-service and store-service
  - lombok plugin bundled by default in IntelliJ 
  - need to enable annotation processing in IntelliJ
    - Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing SET

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
- commit - Add Lombok to inventory and store services

## May 17, 2023 - devtools, add product-service and order-service 

### Add spring-boot-devtools dependency to inventory-service and store-service

  ```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
  </dependency>
  ```

- open Run/Edit configuration
  - in `build and run` section click `Modify options`
    - select menu item `On 'Update' action` - then select `Update classes and resources`
    - select menu item `On Frame deactivation` - then select `Update classes and resources`

- commit - Add spring-boot-devtools dependency to inventory-service and store-service

### Clone store-service to product-service
- copy whole store-service folder as product-service and rename all occurrences of `store` to `product`
  - Rename application name `spring.application.name=product-service` in application.properties
- add product-service to config-server-repo/product-service
  - copy to `c:\IntelliJ_WS_SpringBootWorkshop\config-server-repo\product-service`
  - push repo to bitbucket
    - git add .
    - git commit -m "add product-service"
    - git push
- add `product-service` section to docker-compose.yml
- add `local-dev` as active profile to ProductServiceApplication Run/Debug configuration
- TODO: property defined in config-server-repo/product-service/product-service.properties is not visible in product-service
  - `@Value("${mycloud.config.test.var}")` is not resolved in ProductController.java
  - same property is resolved in InventoryController.java
- test in local-dev profile - Ok
- test in docker - Ok
- submit - Clone store-service as product-service

### Clone product-service as order-service
- copy whole product-service folder as order-service and rename all occurrences of `product` to `order`
  - Rename application name `spring.application.name=order-service` in application.properties
- add product-service to config-server-repo/product-service
  - copy to `c:\IntelliJ_WS_SpringBootWorkshop\config-server-repo\product-service`
  - push repo to bitbucket
    - git add .
    - git commit -m "add order-service"
    - git push
- add `order-service` section to docker-compose.yml
- add `local-dev` as active profile to ProductServiceApplication Run/Debug configuration
- TODO: property defined in config-server-repo/product-service/product-service.properties is not visible in product-service
  - `@Value("${mycloud.config.test.var}")` is not resolved in ProductController.java
  - same property is resolved in InventoryController.java
- test in local-dev profile - Ok
- test in docker - Ok
- submit - Clone product-service as order-service

## May 18, 2023 - add shared data module for base entity and dto classes
### Add shared data module for base entity and dto classes
- create new module `shared-data` in IntelliJ
  - add jpa and lombok dependencies to pom.xml 
  - BaseEntity.java for all entities in service modules
  - *Dto.java for all dto in service modules
  - add lombok dependency to shared-data pom.xml
  - add shared-data dependency to product-service pom.xml
  - turn off jpa autoconfigure in product-service pom.xml
    - [Disable Spring Data Auto Configuration](https://www.baeldung.com/spring-data-disable-auto-config) 
    - `spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration`
- test in local-dev profile - Ok
- test in docker - skipped
- commit - add shared data module for base entity and dto classes

### Build docker image for product-service with shared-data dependency
- see [Build-docker-image-with-dependency-from-another-module](Build-docker-image-with-dependency-from-another-module.md)
- Posted question for stackoverflow - [How to build docker image with dependency from another module](https://stackoverflow.com/questions/76285229/how-to-build-docker-image-with-dependency-from-another-module)

How to build docker image with dependency from another module
 
- projectRoot
  - docker-compose.yml
  - product-service (module folder)
    - src\
    - Dockerfile
    - pom.xml
  - shared-data (module folder for jar library)
    - src\ 
    - pom.xml

docker-compose.yml
```
version: '3.9'

services:
  product-service:
    build:
      context: ./product-service
      dockerfile: Dockerfile
    image: product-service:latest
    container_name: product-service
    restart: unless-stopped
```

how I can build docker image for product-service with dependency from shared-data module?
my approach was to build shared-data module first and then build product-service module with dependency to shared-data module. 

Dockerfile
```
FROM maven:3.8.6-amazoncorretto-19 AS buildOne

# Set the working directory to /shared-data
WORKDIR /shared-data

# Copy the shared-data module folder to /shared-data in the container
COPY ../shared-data /shared-data

# Build the shared-data module
RUN mvn package

# Set the working directory to /app
WORKDIR /app

# Copy the project files to /app in the container
COPY . /app

# Build the product-service module
RUN mvn package

FROM amazoncorretto:19-al2-jdk as buildTwo

# Set the working directory to /app
WORKDIR /app

# Set the JAR_FILE argument to the location of the product-service JAR file
ARG JAR_FILE=/app/target/*.jar

# Copy the built JAR file from buildOne stage to application.jar
COPY --from=buildOne ${JAR_FILE} application.jar

# Extract the layers from the application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM amazoncorretto:19-al2-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the dependencies from buildTwo stage
COPY --from=buildTwo /app/dependencies/ ./
COPY --from=buildTwo /app/snapshot-dependencies/ ./
COPY --from=buildTwo /app/spring-boot-loader/ ./
COPY --from=buildTwo /app/application/ ./

# Set the entry point for the Docker container
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
```

The problem is that docker can't go outside his context folder, thus line `COPY ../shared-data /shared-data` is not working.

can you suggest how to solve this problem?

- Posted on stackoverflow - [How to build docker image with dependency from another module](https://stackoverflow.com/questions/76285229/how-to-build-docker-image-with-dependency-from-another-module)


## May 19, 2023 - add shared data module for base entity and dto classes
- see chat with [ChatGpt](GPTChat-DTO-Library-for-Dockerized-SpringCloud-project.md)
- commit - add GPTChat-DTO-Library-for-Dockerized-SpringCloud-project.md

### Switch to branch `dockerize with shared-data_dependency`
- follow [ChatGpt](GPTChat-DTO-Library-for-Dockerized-SpringCloud-project.md)
- can't build product-service docker image with shared-data dependency
  - failed to solve: executor failed running [/bin/sh -c mvn -f product-service/pom.xml clean package -DskipTests]: exit code: 1
  - `docker-compose` process finished with exit code 17
- commit - can't dockerize modules with shared-data dependency
- switch back to master branch

### Remove shared-data module from master pom file and copy shared-data dto classes to all service modules
- remove shared-data dependency from product-service pom.xml
- copy shared-data dto classes to product-service
- build and run all cloud services
- test in local-dev profile - Ok
- test on docker - Failed
  - because product-service can't find  dependency on spring-boot-starter-data-jpa (in local build shared-data indirectly add dependency on spring-boot-starter-data-jpa)
  - add spring-boot-starter-data-jpa dependency to product-service pom.xml
- test on docker - Ok
- commit - remove shared-data module from master pom file and copy shared-data dto classes to all service modules

## May 20, 2023 - remove shared data module
### Remove shared data module completely
- test in local-dev profile - Ok
- test on docker - Ok
- commit - remove shared data module completely

### Duplicate business entities in all service modules
- add BaseEntity.java and *Dto.java to all service modules
- add spring-boot-starter-data-jpa dependency to all service modules
- turn off jpa autoconfigure in application.properties for all service modules
  - `spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration`
- test in local-dev profile - Ok
- test on docker - Ok
- commit - duplicate business entities in all service modules

### Modify BaseEntity.java and add specific entities to all service modules
- modify BaseEntity.java
- remove Product.java from inventory-service, order-service and store-service
- add specific entities to all inventory-service and order-service
- **TODO** - One time reproduced: inventory service can't map `mycloud.config.test.var` from config server
- commit - modify BaseEntity.java and add specific entities to all service modules 

# May 22, 2023 - dummy work cleaning code and add business logic to services

## modify all dockerfiles to skip tests
- add `clean package -DskipTests` to build command in all dockerfiles
- commit - modify all dockerfiles to skip tests
- commit - create minimal dummy prototype for dockerized spring cloud microservice project

## Create docker-compose file for MySQL database
- create `database-service.yml` file for MySQL database
  - map `springcloud-sbsuite\mysql` as mysql volume created in project root folder
  - add `mysql` to gitiignore file
  - create `database/initdb/Init.sql` file for database with users initialization
    - inventorydb with users `inventoryuser` and `inventoryadmin`
    - orderdb with users `orderuser` and `orderadmin`
    - productdb with users `productuser` and `productadmin`
- test `database-service.yml` file - database created Ok
- commit - create docker-compose file for MySQL database

## Connect to databases
- add mysql dependency to all service modules
  - ERROR: Could not find artifact mysql:mysql-connector-java:pom:unknown in netflix-candidates (https://artifactory-oss.prod.netflix.net/artifactory/maven-oss-candidates)
    - see [MySQL-Connector-Dependency](WorkLogNotes/MySQL-Connector-Dependency.md)
- add flyway dependency to all service modules
  - change `<artifactId>flyway-core</artifactId>` to `<artifactId>flyway-mysql</artifactId>`
  - create db/migration folder in all service modules with minimalistic `V1__init_database.sql`
  - add hibernate and flyway properties to all service modules
    ```properties
    spring.jpa.database=mysql
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    #spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=validate
    spring.jpa.defer-datasource-initialization=false
    #Show SQL
    spring.jpa.properties.hibernate.show_sql=true
    #Format SQL
    spring.jpa.properties.hibernate.format_sql=true
    #Show bind values
    logging.level.org.hibernate.orm.jdbc.bind=trace
    logging.level.org.hibernate.type.descriptor.sql=trace
  
    spring.datasource.username=XXXXXuser
    spring.datasource.password=password
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/XXXXXdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
  
    #spring.flyway.locations=classpath:db/migration/common,classpath:db/migration/{vendor}
    spring.flyway.locations=classpath:db/migration
    spring.flyway.user=XXXXXadmin
    spring.flyway.password=password
    ```
- add `@Entity` to basic entities in all service modules
- test on local - Ok
- test on docker - services started but can't connect to database defined in `database-service.yml`
- commit - connect to databases

# May 23, 2023 - persistency layer
- add `logging.level.org.hibernate.orm.jdbc.bind=trace` to all service modules application.properties
  - from udemy `Spring framework 6` course - `133. Flyway Migration Script Configuration`
- commit - add `logging.level.org.hibernate.orm.jdbc.bind=trace` to all service modules application.properties

## Add order-service persistency layer generate DDL scripts
- create order specific entities with hibernate annotations to link related entities
- use spring schema generation to generate sql script for flyway
  - from udemy `Spring framework 6` course - `128. Schema Script Generation`
    - `springframeworkguru/spring-6-rest-mvc` branch [75-db-create-scripts](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/75-db-create-scripts)
    ```
    spring.jpa.properties.jakarta.persistence.schema-generation.scripts.action=drop-and-create
    spring.jpa.properties.jakarta.persistence.schema-generation.scripts.create-source=metadata
    spring.jpa.properties.jakarta.persistence.schema-generation.scripts.drop-target=drop-and-create.sql
    spring.jpa.properties.jakarta.persistence.schema-generation.scripts.create-target=drop-and-create.sql
    ``` 
    
  - after run application check root of the project `springcloud-sbsuite\drop-and-create.sql`
- move and rename `drop-and-create.sql` to `order-service/src/main/resources/db/migration/V1__init_database.sql`
  ```sql
  -- replace 
      alter table order_header 
              add constraint FKbkj7uhdpxqe8qb2b1g6poijwt 
              foreign key (customer_id) 
              references customer (id);
      alter table order_line 
              add constraint FKoujl67v3lk4glhmln31imw1wo 
              foreign key (order_header_id) 
              references order_header (id);

        -- with
            
        create table order_header(
                id        bigint not null auto_increment,
                customer_id bigint,
                ...
                constraint order_customer_fk FOREIGN KEY (customer_id) references customer (id),
                PRIMARY KEY (`id`)
        ) engine = InnoDB;
      
        create table order_line (
                id bigint not null auto_increment,
                created_date timestamp,
                last_modified_date timestamp,
                version bigint,
                product_id bigint,
                quantity_ordered integer,
                order_header_id bigint,
                constraint order_header_pk FOREIGN KEY (order_header_id) references order_header(id),
                #        constraint order_line_product_fk FOREIGN KEY (product_id) references product(id)
                primary key (id)
        ) engine=InnoDB;
       ```
  - generate few test record for customer, order_header and order_line tables
  - comment out `spring.jpa.properties.jakarta.persistence.schema-generation.scripts.*` properties
  - clean `orderdb` database
  - run service to allow flyway to create and populate tables 
- test on local - Ok
- commit - add order-service persistency layer generate DDL scripts

# May 24, 2023 - persistency layer
## remove @Data annotation from entities 
- Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.
  - from [93. Creating JPA Entities](https://www.udemy.com/course/spring-framework-6-beginner-to-guru/learn/lecture/33674952#overview)
  - test on local - Ok
- commit - remove @Data annotation from entities

## Add services and jpa repositories with tests
- OrderService
  - CustomerRepository
  - OrderHeaderRepository
  - OrderLineRepository
  - TestCustomersWithOrders
- ProductService
  - ProductRepository
  - TestProducts
- InventoryService
  - InventoryRepository
  - TestInventory
- commit - add services and jpa repositories with tests

## Add mapstruct dependencies
- [Using MapStruct with Project Lombok](https://springframework.guru/using-mapstruct-with-project-lombok/)
- [Quick Guide to MapStruct](https://www.baeldung.com/mapstruct)
- [Udemy 97. MapStruct Dependencies and Configuration 5min](https://www.udemy.com/course/spring-framework-6-beginner-to-guru/learn/lecture/33721954#overview)
- add following dependencies to all service modules

```
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>${org.mapstruct.version}</version>
</dependency>

<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.10.1</version>
  <configuration>
      <source>17</source>
      <target>17</target>
      <annotationProcessorPaths>
          <path>
              <groupId>org.mapstruct</groupId>
              <artifactId>mapstruct-processor</artifactId>
              <version>${org.mapstruct.version}</version>
          </path>
          <path>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <version>${lombok.version}</version>
          </path>
          <path>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok-mapstruct-binding</artifactId>
              <version>0.2.0</version>
          </path>
      </annotationProcessorPaths>
      <compilerArgs>
          <compilerArg>-Amapstruct.defaultComponentModel=spring</compilerArg>
      </compilerArgs>
  </configuration>
</plugin>
```

- commit - add mapstruct dependencies

# May 25, 2023 - mapstruct to convert entities to dto and back 

## Add mapstruct to convert entities to dto in product-service
- fix dummy ProductDto (remove beer specifics)
- add mappper interface `ProductMapper`
  - add `DateMapper` to map `OffsetDateTime` to `Timestamp` and back
  - mark `ProductMapper` with `@Mapper(uses = {DateMapper.class})` to make `productMapper` aware of `OffsetDateTime` to `Timestamp` conversion 
- add product mapper to `ProductServiceImpl`
  - call mapping for all products returned by `findAll().stream()`
      ```java
      public class ProductServiceImpl implements ProductService {
  
          @Autowired
          ProductRepository productRepository;
          @Autowired
          ProductMapper productMapper;
  
          @Override
          public List<ProductDto> fetchProducts(){
              List<ProductDto> products = productRepository.findAll()
                                                          .stream()
                                                          .map(productMapper::productToProductDto)
                                                          .collect(Collectors.toList());
              return products;
          }
      }
      ```
- add `@SpringBootTest class ProductServiceTest{}`
- test on local - Ok
- commit - add mapstruct to convert entities to dto in product-service

## Failed attempt to test ProductService as DataJpaTest
- replace `@SpringBootTest` with `@DataJpaTest` in `ProductServiceTest`
  - failed with error `Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test`
    - Failed to load ApplicationContext for [MergedContextConfiguration@446626a7 testClass = com.springcloud.sbsuite.orders.services.ProductServiceTest
  - Rollback
- commit - failed attempt to test ProductService as DataJpaTest

## Add integration test for ProductController
- modify `ProductController` to use `ProductService` instead of return dummy string
- mark test class with `IT` suffix `ProductControllerIT`

```java
@SpringBootTest
class ProductControllerIT {
	@Autowired
	ProductController productController;

	@Test
	void testListProducts() {

		List<ProductDto> products = productController.getProducts();
		assertNotNull(products);
		// assert products size > 0
		ProductDto product = products.get(0);
		assertNotNull(product);
		assertEquals("Product 1", product.getName());
	}
}
```
- run ProductControllerIT on local - Ok
- test on local - Ok
- commit - add integration test for ProductController

## Add and test get product by id
- add `fetchProductById` to `ProductService`
  ```java
    public Optional<ProductDto> fetchProductById(Long id) {
      return Optional.ofNullable(productMapper.productToProductDto(
      productRepository.findById(id).orElse(null)
      )
      );
    }
  ```
  
- add `NotFoundException` to be thown by `ProductController` if product not found
- add test in `ProductControllerIT` and `ProductServiceTest`
- commit - add and test get product by id

## Add and test get product by id in TestApiGateway http client
- add `@PathVariable Long id` to `getProductById` in `ProductController`
  - Fix null id value
  - In the given code snippet, the getProductById method in the ProductController class is expecting a Long parameter named id. However, when the GET request is made to the endpoint /product/1, the id parameter is not being populated correctly, resulting in a null value.
    - To fix this issue, you can modify the code in the following way:
  
      ```java
    
        @GetMapping(value = "/{id}")
        public ProductDto getProductById(@PathVariable Long id) {
          ProductDto dto = productService.fetchProductById(id).orElseThrow(NotFoundException::new);
          return dto;
        }
      ```
    - By adding the @PathVariable annotation before the id parameter, you inform Spring that the value for id should be extracted from the path variable in the request URL. This way, the id parameter will be correctly populated with the value from the request URL, such as /product/1.
- commit - add and test get product by id in TestApiGateway http client

## Replace ProductDto in inventory and order services with ProductDto from product-service
- commit - replace ProductDto in inventory and order services with ProductDto from product-service

## Replace dummy `EurekaClient2ApplicationTests` in all services with specific to service app test
- commit - replace dummy `EurekaClient2ApplicationTests` in all services with specific to service app test

## Add mapstruct to convert entities to dto in order-service
- [Testing MapStruct](https://mapstruct.org/development/testing-mapstruct/)
- !!! [Quick Guide to MapStruct](https://www.baeldung.com/mapstruct)
  - [Use Mapper in Another Mapper with Mapstruct and Java](https://www.baeldung.com/java-mapstruct-nested-mapping)
- TODO - Fail to properly instantiate DateMapper in Non-Spring test

## Add test for OrderController and OrderService with mapstruct mapper for OrderLineDto and OrderLine
- commit - add test for OrderController and OrderService with mapstruct mapper for OrderLineDto and OrderLine


# May 26, 2023 - Adding CRUD ops to services with mapstruct to convert entities to dto and back
## Duplicate OrderLineDto in inventory and product services
- commit - duplicate OrderLineDto in inventory and product services
- commit - duplicate OrderLineDto in store service

## add mapstruct to convert OrderHeader entity to dto and back in order-service
- create AddressDto, ContactDto with mappers
- move OrderStatus.java to dto package 
- create OrderHeaderDto and OrderHeaderMapper
  - comment out back reference to CustomerDto in OrderHeaderDto
- during `fetchOrderHeaders()` test throws exception
  - LazyInitializationException: failed to lazily initialize a collection of role: 
  - com.springcloud.sbsuite.orders.domain.OrderHeader.orderLines: could not initialize proxy - no Session
  - [Eager/Lazy Loading in Hibernate](https://www.baeldung.com/hibernate-lazy-eager-loading)
   - FIX by adding `fetch = FetchType.EAGER`
   - `@OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)`
- commit - add mapstruct to convert OrderHeader entity to dto and back in order-service

## update OrderHeader table address for testing purposes in order-service
- add V2__add_address_to_order-header.sql migration
- commit - update OrderHeader table address for testing purposes in order-service

## add mapstruct to convert Customer entity to dto and back in order-service
- add V3__add_address_to_customer.sql migration
- add CustomerDto and CustomerMapper
- add `, fetch = FetchType.EAGER` to `Set<OrderHeader> orders`
- commit - add mapstruct to convert Customer entity to dto and back in order-service

## Duplicate dtos from order-service in store-service, product-service, inventory-service
- AddressDto
- ContactDto
- CustomerDto
- OrderHeaderDto
- OrderStatus
- commit - Duplicate dtos from order-service in store-service, product-service, inventory-service

# May 29, 2023 - continue on mapping entities to dto and back, add CRUD ops to all services 
## Adding Mapping entities ops to inventory service with mapstruct to convert entities to dto and back
- add InventoryDto and InventoryMapper
- add `public Optional<Inventory> findByProductId(Long productId);` to InventoryRepository
- InventoryService and InventoryServiceImpl.java
- ERRORs in starting InventoryServiceTest.java
  - java.lang.IllegalStateException: Failed to load ApplicationContext for 
  - Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'inventoryController': Injection of autowired dependencies failed
  - Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'mycloud.config.test.var' in value "${mycloud.config.test.var}"
  - FIXED by commenting out `@Value("${mycloud.config.test.var}") private String configVar;`
  - TODO - why only inventory service can see mycloud.config.test.var from config-server-repo/inventory-service/application.properties
- fix TestApiGateway.http
- commit - Adding Mapping entities ops to inventory service, test inventory service and inventory controller

## Adding Create, Update and Delete ops to inventory service
-commit - Adding Create, Update and Delete ops to inventory service
- TODO - add controller test

# May 30, 2023 - continue adding CRUD ops to all services
## Adding Create, Update and Delete ops to product service
- TODO - add controller test
- commit - Adding Create, Update and Delete ops to product service

## Adding fetchById for orderHeader and Customer to Order service
- commit - Adding fetchById for orderHeader and Customer to Order service

# May 31, 2023 - continue adding CRUD ops to all services
## Adding Create, Update and Delete ops to Order service
- commit - add default constructor to OrderHeaderDto
- commit - add default constructor to AddressDto and ContactDto
- commit - Adding Create, Update and Delete ops to OrderHeader and Customer in order service

## Java Bean Validation Maven Dependencies 2min
110. Java Bean Validation Maven Dependencies 2min - [59-mvn-validation-deps branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/59-mvn-validation-deps)
- add validation dependencies to pom.xml in all services
  ```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
  ```
- commit - add Java Bean Validation Maven Dependencies

## Adding Validation to ProductDto in product service
- see 111. Controller Binding Validation 3min in udemy spring-6
- add `@NotBlank` and `@NotNull` to ProductDto
  ```java
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String description;
  ```

  - create `createProduct()` in ProductController
    ```java
      @PostMapping
      public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
          ProductDto dto = productService.createProduct(productDto);
          return new ResponseEntity<>(dto, HttpStatus.CREATED);
      }
    ```
- add create product test
  ```java
	@Test
	void testPostProduct() {
		ProductDto product = new ProductDto().builder().name("test product").description("test product description").build();
		ProductDto newProduct = productController.createProduct(product).getBody();
		assertNotNull(newProduct);
		assertEquals("test product", newProduct.getName());
		assertEquals("test product description", newProduct.getDescription());
		product = productController.getProductById(newProduct.getId());
		assertNotNull(product);
		assertEquals("test product", product.getName());
		assertEquals("test product description", product.getDescription());
	}
  ```
  - add `@Valid` to `public ProductDto createProduct(@Valid @RequestBody ProductDto productDto)`
      ```java
      @PostMapping("/")
      public ResponseEntity<ProductDto> createProduct(@Validated @RequestBody ProductDto productDto) {
        ProductDto dto = productService.saveProduct(productDto).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
      }
      ```
- create class ProductControllerWebIT.java for testing with MockMVC (see 70. MockMVC Configuration 7min in udemy spring-6)
  - you need to add `import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;` to resolve `post` in `mockMvc.perform(`
  - Fix error Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'productController': 
    - Unsatisfied dependency expressed through field 'productService': 
    - No qualifying bean of type 'com.springcloud.sbsuite.orders.services.ProductService' available: 
    - expected at least 1 bean which qualifies as autowire candidate. 
    - Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
    - to fix add `@MockBean ProductService productService;` 
      ```java
          @WebMvcTest(ProductController.class)
          public class ProductControllerWebIT {
          @Autowired
          MockMvc mockMvc;
      
              @Autowired
              ProductController productController;
      
              @Autowired
              ObjectMapper objectMapper;
              @Test
              void testCreateNewBeer() throws Exception {
                  ProductDto product = new ProductDto().builder().name("test product").description("test product description").build();
      
                  mockMvc.perform(post("/product/")
                                  .accept(MediaType.APPLICATION_JSON)
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(product)));
              }
          }
      ```
- in ProductControllerWebIT create invalid product to test validation
  - `ProductDto product = new ProductDto().builder().description("").build();`
  - test runs Ok **but should fail**
- commit - Adding Validation to ProductDto in product service (Validation doesn't work)


# June 1, 2023 - adding validation in product controller 

## continue with "Adding Validation to ProductDto in product service"
- fix ProductControllerWebIT.java
  - see "70. MockMVC Configuration 7min" in udemy spring-6
  - see "111. Controller Binding Validation 3min" in udemy spring-6
  - remove `@Autowired ProductController productController;` from ProductControllerWebIT.java
  - add `given(productService.saveProduct(any(ProductDto.class))).willReturn(Optional.ofNullable(product));` before call to `mockMvc.perform(post("/product/")`
  - add `.andExpect(status().isCreated());` after call to `mockMvc.perform(post("/product/")`
  - add `testCreateNewInvalidProduct()` to test create invalid product with `.andExpect(status().isBadRequest());` after call to `mockMvc.perform(post("/product/")`
    ```java
      @Test
      void testCreateNewInvalidProduct() throws Exception {
          ProductDto product = new ProductDto().builder().description("").build();
          mockMvc.perform(post("/product/")
                          .accept(MediaType.APPLICATION_JSON)
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(objectMapper.writeValueAsString(product)))
                  .andExpect(status().isBadRequest());
      }
    ```
- actually validation worked. I was expecting exception but it didn't happen.
- returned status is 400 - which means "bad request"
  - java.lang.AssertionError: Status expected:<201> but was:<400>
- once I send valid Dto, test passes and status is 201 - which means "created"
  - this means that validation works
- with invalid dto controller's method `createProduct(@Validated @RequestBody ProductDto productDto)` wasn't called because dto didn't pass validation
- **Note** that I removed '/' from PostMapping in ProductController
  - `@PostMapping("/")` to `@PostMapping()`
  - TODO - do same for all controllers i.e. remove '/' from `@GetMapping("/")` and remove trailing '/' from all http client tests
- commit - Fixing Validation to ProductDto in product service (Validation works)

- See `WorkLogNotes/GPTChat-Throw-exception-on-bean-validation.md` for notes on how to throw exception on bean validation
- commit - add notes on how to throw exception on bean validation
 
## Fix @GetMapping("/") in all controllers
- remove '/' from `@GetMapping("/")` in all controllers
- trailing '/' from `String storeInventoryUrl = String.format("http://%s:8765/inventory", hostnameurl);` in Eclient2ServiceRestTemplateImpl:
- remove trailing '/' from all http client tests
- commit - Fix GetMapping root url in all controllers

## Handle Validation Errors

- 112. Custom Validation Handler 4min
   - [61-custom-validation-error-handler branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/61-custom-validation-error-handler)
     - add `MvcResult mvcResult = mockMvc.perform(post(post...` to `ProductControllerWebIT:testCreateNewInvalidProduct()`
         ```java
            @Test
            void testCreateNewInvalidProduct() throws Exception {
            ProductDto product = new ProductDto().builder().build();
    
            given(productService.saveProduct(any(ProductDto.class))).willReturn(Optional.ofNullable(product));
    
            MvcResult mvcResult = mockMvc.perform(post("/product")
                               .accept(MediaType.APPLICATION_JSON)
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(objectMapper.writeValueAsString(product)))
                       .andExpect(status().isBadRequest()).andReturn();
            
            System.out.println(mvcResult.getResponse().getContentAsString());
           }
         ```
       - add `@ControllerAdvice` to `ControllerExceptionHandler`
         ```java
         @ControllerAdvice
         public class ControllerExceptionHandler {
    
             @ExceptionHandler(MethodArgumentNotValidException.class)
             ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){
                 return ResponseEntity.badRequest().body(exception.getBindingResult().getFieldErrors());
             }
         }
         ```
       - check out `mvcResult.getResponse().getContentAsString()` too much info that "name" and "description" are null and rejected
         
- commit - add ControllerExceptionHandler to handle validation errors

## Handle Validation Errors with custom errror message in response body
- 113. Custom Error Body 4min
  - [62-custom-error-message branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/62-custom-error-message)

- modify ControllerExceptionHandler to return custom error message
    ```java
    @ControllerAdvice
    public class ControllerExceptionHandler {
  
        @ExceptionHandler(MethodArgumentNotValidException.class)
        ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){
  
            List errorList = exception.getFieldErrors().stream()
                    .map(fieldError -> {
                        Map<String, String > errorMap = new HashMap<>();
                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                        return errorMap;
                    }).collect(Collectors.toList());
  
            return ResponseEntity.badRequest().body(errorList);
        }
    }
    ```
- ProductControllerWebIT:testCreateNewInvalidProduct() test will print much nicer message 
  - `[{"name":"must not be blank"},{"description":"must not be blank"},{"name":"must not be null"},{"description":"must not be null"}]`
- consider to add `.andExpect(jsonPath("$.length()", is(4)))` to testCreateNewInvalidProduct() to check that there are 4 errors
- commit - add custom error message to ControllerExceptionHandler

# June 2, 2023 - adding JPA validation in product controller

## Clean up and sync Dtos in services
- removed
  - ProductInventoryDto
  - ProductOrderDto
  - ProductOrderLineDto
- sync InventoryDto with all services
- remove `import jakarta.persistence.Embeddable;` from *Dto.java
- commit - Clean up and sync Dtos in services

## Add JPA validation in product controller
- 114. JPA Validation 3min
  - [branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/64-jpa-validation)
- add JPA validation to Product.java
  - `@NotBlank(message = "Product name must not be blank")`
  - `@NotNull(message = "Product name must not be null")`
  - `@NotBlank(message = "Product description must not be blank")`
  - `@NotNull(message = "Product description must not be null")`
- create `ProductServiceTest::saveNewInvalidProduct()` test empty product for JPA validation
  ```java
	@Rollback
	@Transactional
	@Test
	void saveNewInvalidProduct() {
		ProductDto product = new ProductDto().builder().build();
		ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
		assertNotNull(newProduct);
        ...
	}
  ``` 
- failed as expected
    ```
    jakarta.validation.ConstraintViolationException: Validation failed for classes [com.springcloud.sbsuite.orders.domain.Product] during persist time for groups [jakarta.validation.groups.Default, ]
    List of constraint violations:[
    ConstraintViolationImpl{interpolatedMessage='must not be blank', propertyPath=description, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.NotBlank.message}'}
    ConstraintViolationImpl{interpolatedMessage='must not be null', propertyPath=description, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.NotNull.message}'}
    ConstraintViolationImpl{interpolatedMessage='must not be null', propertyPath=name, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.NotNull.message}'}
    ConstraintViolationImpl{interpolatedMessage='must not be blank', propertyPath=name, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.NotBlank.message}'}
    ]
    ```
- add assertThrows to `ProductServiceTest::saveNewInvalidProduct()` test
  ```java
    @Rollback
    @Transactional
    @Test
    void saveNewInvalidProduct() {
        ProductDto product = new ProductDto().builder().build();
        assertThrows(ConstraintViolationException.class, () -> productService.saveProduct(product).orElseThrow(NotFoundException::new));
    }
  ```
- commit - add JPA validation in product entity and test

## Add JPA constraint validation in product service
- 115. Database Constraint Validation 4min
  - [65-database-constraint-validation branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/65-database-constraint-validation)
- use `jakarta.validation.constraints.Size;` and `jakarta.persistence.Column`
  ``` 
    - @NotBlank
      @NotNull
      @Size(min = 2, max = 100)
      @Column(length = 100)
      String name;
  ```
  - create test `ProductServiceTest::saveNewInvalidProduct()` test empty product for JPA validation
    ```java
      	void saveInvalidProductWithTooShortName() {
			ProductDto product = new ProductDto().builder().name("E").build();
			ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
			assertNotNull(newProduct);
        }
    ```
    - throws `ConstraintViolationException` as expected
      ```
      jakarta.validation.ConstraintViolationException: Validation failed for classes [com.springcloud.sbsuite.orders.domain.Product] during persist time for groups [jakarta.validation.groups.Default, ]
        List of constraint violations:[
        ConstraintViolationImpl{interpolatedMessage='must not be blank', propertyPath=description, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.NotBlank.message}'}
        ConstraintViolationImpl{interpolatedMessage='size must be between 2 and 100', propertyPath=name, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.Size.message}'}
        ConstraintViolationImpl{interpolatedMessage='must not be null', propertyPath=description, rootBeanClass=class com.springcloud.sbsuite.orders.domain.Product, messageTemplate='{jakarta.validation.constraints.NotNull.message}'}
        ]
      ```
- wrap `productService.saveProduct(product)` in `assertThrows(ConstraintViolationException.class, () -> productService.saveProduct(product).orElseThrow(NotFoundException::new));`
  ```
  void saveInvalidProductWithTooShortName() {
		assertThrows(ConstraintViolationException.class, () -> {
			ProductDto product = new ProductDto().builder().name("E").build();
			ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
			assertNotNull(newProduct);
		});
	}
  ```
  
- commit - add JPA constraint validation in product service

## JPA Validation Error Handler
- 117. JPA Validation Error Handler 2min
  - [67-jpa-violoation-error-data branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/67-jpa-violoation-error-data)
- add `@ExceptionHandler ResponseEntity handleJPAViolations(TransactionSystemException exception)` to `CustomErrorController`
```
@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler
    ResponseEntity handleJPAViolations(TransactionSystemException exception){
        return ResponseEntity.badRequest().build();
    }
```

- 118. JPA Validation Error Message 5min
  - [68-jpa-validation-error-msg branch](https://github.com/springframeworkguru/spring-6-rest-mvc/tree/68-jpa-validation-error-msg)
  - code in this branch doesn't reflect what John did in the video to retrieve error messages in `ResponseEntity handleJPAViolations(TransactionSystemException exception){}`
- commit - add JPA validation error handler (skipped)

# June 5, 2023 - 
## sync InventoryDto
  - remove `import com.springcloud.sbsuite.inventory.domain.BaseEntity; jakarta.persistence.Entity;`
- commit - sync InventoryDto
## Udemy springframework 6 - Section 13 - CSV File loading

# June 7, 2023 - watch JMS from "Spring Boot Microservices with Spring Cloud and Docker" course
- [Section 13 - JMS Messaging](C:\IntelliJ_WS-SpringGuru\Spring Boot Microservices with Spring Cloud and Docker Course\13-14-JMS Messaging.md)

# June 8, 2023 - watch JMS from "Spring Boot Microservices with Spring Cloud and Docker" course
- [Section 14 - JMS Messaging](C:\IntelliJ_WS-SpringGuru\Spring Boot Microservices with Spring Cloud and Docker Course\13-14-JMS Messaging.md)

## TODO: fix pom parent dependencies in all modules
- Fixing Parent POM
  - already has `<parent><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-parent</artifactId><version>3.0.5</version></parent>`
- fix `<parent>` in all child modules
- move `<dependencyManagement>` from child modules to parent pom
- move `<artifactId>spring-boot-maven-plugin</artifactId>` from child modules to parent pom
- **Rollback** because it's unclear how these modules will be built in docker-compose driven build
  
      
# June 10, 2023 - Saturday

- move all dtos in all services to new package com.springcloud.sbsuite.dto
  - one package name for dto for less sync effort
- commit - move all dtos in all services to new package com.springcloud.sbsuite.dto

# June 14, 2023 - Refactoring folders and packages in all services

## Order Service folders refactory
- commit - move all order-service dtos package com.springcloud.sbsuite.dto
- rename order-service package com.springcloud.sbsuite.store to com.springcloud.sbsuite.orders
  - rename `@RequestMapping("/order")` api entrypoint in order-service OrderController.java to `@RequestMapping("/orders")`
  - commit - rename order-service package com.springcloud.sbsuite.store to com.springcloud.sbsuite.orders
- Accidentally rename `store` package to `orders` in product and store services 
- refactor Product and Store service package
- commit - refactor Product and Store service package

## Add Kafka messagging service to order-service
- create branch `kafka`
- follow - **04:21:11** - Event Driven Architecture using Kafka [@](https://youtu.be/mPPhcU7oWDU?t=15672)
  - install kafka server [@](https://youtu.be/mPPhcU7oWDU?t=15808)
    - start docker as described in [quickstart kafka docker](https://developer.confluent.io/quickstart/kafka-docker/)
    - create kafka-service.yml [@](https://youtu.be/mPPhcU7oWDU?t=15850)
    - run `docker-compose -f kafka-service.yml up -d`
      - we'll start kafka server from IntelliJ IDE 
      - confirm kafka server is running in docker desktop
        - zookeeper 4ecf1865e09b confluentinc/cp-zookeeper:7.3.2 Running
        - broker b64ef8fbe873 confluentinc/cp-kafka:7.3.2 Running 9092:9092
    - verify broker is running `/c/IntelliJ_WS_SpringBootWorkshop/springcloud-sbsuite$ docker logs -f broker`   (copilot generated `docker-compose -f kafka-service.yml ps`)
  - look for required dependecy [@](https://docs.spring.io/spring-kafka/docs/current/reference/html/#quick-tour) 
  - add `spring-kafka` dependency in order-service pom.xml

      ```xml
      <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
        <!-- Removed by me version>3.0.7</version -->
      </dependency>
      ```
  - add kafka properties in order-service application.yml
    ```
    # Kafka Properties
    spring.kafka.bootstrap-servers=localhost:9092
    spring.kafka.template.default-topic=orderTopic
    spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
    spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
    spring.kafka.producer.properties.spring.json.type.mapping=event:com.springcloud.sbsuite.event.OrderPlacedEvent
    ```
  - inject `KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;` in `OrderService.java` [@](https://youtu.be/mPPhcU7oWDU?t=16369)
    ```
    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    ```
  - create `com.springcloud.sbsuite.event.OrderPlacedEvent`
  - send event to kafka `kafkaTemplate.send("orderTopic", new OrderPlacedEvent(productId, quantity));`
  - we need to add rest api endpoint to send order to kafka
    ``` 
    @GetMapping("/createorder")
    public String createOrder() {
    return orderService.placeOrder(1L, 1);
    }
    ``` 
    - add `orderService.placeOrder(
  - ??? create notification service project [@](https://youtu.be/mPPhcU7oWDU?t=16737) @ 04:38:57
      - OR ??? make inventory-service listen to kafka topic `orderTopic` [@](https://youtu.be/mPPhcU7oWDU?t=16737) @ 04:38:57
      - copy `OrderPlacedEvent` class to inventory-service
      - add kafka dependency in inventory-service pom.xml
      - add kafka properties in inventory-service application.yml
      - add `@KafkaListener(topics = "orderTopic")` in `InventoryServiceApplication.java`
        ```java
          @KafkaListener(topics = "notificationTopic")
          public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
              // send out an email notification
             log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
          }
        ``` 
        
- add test call to create order in TestApiGateway.http http client
  ```
  ### send DUMMY create order to order-service
  GET {{gateway-host}}/order-service/orders/createorder
  ```
- run all services and execute `send DUMMY create order to order-service`
  ```
  GET http://localhost:8765/order-service/orders/createorder
  
  HTTP/1.1 200 OK
  Content-Type: text/plain;charset=UTF-8
  Content-Length: 23
  Date: Wed, 14 Jun 2023 21:19:01 GMT
  >>>  Event sent successfully
  Response code: 200 (OK); Time: 1478ms (1 s 478 ms); Content length: 23 bytes (23 B)
  ```
- check inventory service logs for kafka message - **NONE**
- commit - add kafka messaging to order-service

## Fix kafka topic listening in inventory-service
- change topic listener in inventory-service from `notificationTopic` to `orderTopic` 
  - `@KafkaListener(topics = "orderTopic")`
- run all services and execute `send DUMMY create order to order-service`
  - confirm inventory-service logs show kafka message
  ```
  com.springcloud.sbsuite.inventory.InventoryServiceApplication [INFO ] 2023-06-14 17:33:46.015 - Received Order Notification for Product - 1
  ```
  
- commit - fix kafka topic listening in inventory-service

## Merge database-service and kafka-service compose files
- docker-compose.yml - docker compose file for all services to run within docker
- kill springcloud-sbsuite docker containers (with their images?)
  - b64ef8fbe873   confluentinc/cp-kafka:7.3.2       "/etc/confluent/dock…"   18 hours ago   Up 18 hours   0.0.0.0:9092->9092/tcp              broker                               
  - 4ecf1865e09b   confluentinc/cp-zookeeper:7.3.2   "/etc/confluent/dock…"   18 hours ago   Up 18 hours   2181/tcp, 2888/tcp, 3888/tcp        zookeeper                            
  - 211837a8dbe8   mysql:8.0                         "docker-entrypoint.s…"   3 weeks ago    Up 3 weeks    0.0.0.0:3306->3306/tcp, 33060/tcp   springcloud-sbsuite-scms-sbsuite-db-1
- merging files to docker-compose-local-dev-env.yml
  - `database-service.yml` - docker compose file for mysql 
  - `kafka-service.yml` - docker compose file for kafka
- start docker compose with `docker-compose -f docker-compose-local-dev-env.yml up -d`
  - confirm mysql and kafka servers are running in docker desktop
  - $ docker ps
    CONTAINER ID   IMAGE                             NAMES
    4d2360ab0d50   confluentinc/cp-kafka:7.3.2       scms-broker
    b6fa26c1ce32   mysql:8.0                         scms-sbsuite-db
    8607d64835ad   confluentinc/cp-zookeeper:7.3.2   scms-zookeeper

- run all services and test with http-client `TestApiGateway.http`
- remove `database-service.yml` and `kafka-service.yml`
- commit - merge database-service and kafka-service compose files

## Move OrderPlacedEvent to common event package in all services
- sync event package in order-service, inventory-service, product-service and store-service
- commit - move OrderPlacedEvent to common event package in all services

## Add Store app listener for order event and Fix issue that only one listener receive kafka message
- see [GPTChat](WorkLogNotes/GPTChat-Kafka-Multi-Listener-Events.md)
  -  Only one event listener in group will receive event from one topic.   All event listeners of one topic in different groups will receive event.
- commit - Add Store app listener for order event and Fix issue that only one listener receive kafka message

## use groupId in kafka listener and skip groupId definition in application.properties
- By defining the groupId directly in the @KafkaListener annotation, it takes precedence over any other configuration sources, such as the application properties. Therefore, the groupId specified in the annotation will be used for that specific listener.
- However, it's important to note that if you have multiple listeners within the same application and you want them to share the same consumer group ID, you will need to define the groupId consistently for all of them, either through the annotation or the application properties.
  - see [GPTChat](WorkLogNotes/GPTChat-Kafka-Multi-Listener-Events.md)
- commit - use groupId in kafka listener and skip groupId definition in application.properties

## Multi-Method Listeners
- [Set Mapping Types in the Producer](https://www.baeldung.com/spring-kafka#multi-method-listeners)
  - see how we can configure our application to send various kinds of objects to the same topic and then consume them
  - configProps.put(JsonSerializer.TYPE_MAPPINGS, "greeting:com.baeldung.spring.kafka.Greeting, farewell:com.baeldung.spring.kafka.Farewell");
- how I can map more than one mapping for kafka producer in application properties file?
  - see [GPTChat](WorkLogNotes/GPTChat-Kafka-Multi-Listener-Events.md)

# June 26, 2023 - Nuxt Store App
## Create dummy nuxt store app
- [create nuxt store app](https://nuxt.com/docs/getting-started/installation)
  - `npx nuxi@latest init store-app`
  - `cd store-app` & `npm install` & `npm run dev`
  - open http://127.0.0.1:3000/
- commit - Create dummy nuxt store app

## Basic UI Layout with Nuxt
### add bootstrap to nuxt app
- follow [Nuxt styling](https://nuxt.com/docs/getting-started/styling#external-stylesheets)
- same as [Nuxt 3 Crash Course #7 - Adding Tailwind CSS | The Net Ninja](https://youtu.be/dvanqBUoxhc?list=PL4cUxeGkcC9haQlqdCQyYmL_27TesCGPC&t=36)
- TODO: add bootstrap to nuxt app (as installed dependency)
    - `npm install bootstrap`
    - add bootstrap css to `nuxt.config.js`
      ```js
      css: [
        'bootstrap/dist/css/bootstrap.css'
      ],
    ```
### Add default layout
- add `store-app/layouts/default.vue`

### Add Nav header component
- `store-app/components/Nav.vue`

### Add home page
- `store-app/pages/index.vue`

- commit - Basic UI Layout with Nuxt

# June 25, 2023 - Nuxt Store App layouting UI
- branch `nuxt-store-app`
## Add Error page
- see [Nuxt Error Page](WorkLogNotes/GPTChat-NuxtErrorPage.md)
- check [Custom error page in nuxt 3 application not working](https://stackoverflow.com/questions/76552735/custom-error-page-in-nuxt-3-application-not-working/76553062#76553062)
  - In project, I have layouts (with default.vue) and pages (with index.vue) folders. Wherever I put the error.vue file, the system won't recognize it.
  - ![](assets/errorPages.png)
  - After I've add app.vue and remove wrong error.vue files - everything start working.
- commit - Add Error page
- commit - add handleError btn with clearError

# June 28, 2023 - Nuxt Store App layouting UI
## Add Products index page
- add `store-app/pages/products/index.vue` with fetch from fake store 'https://fakestoreapi.com/products'
- commit - Add Products index page

## Add Products Detail page
- add `store-app/pages/products/[id].vue` with fetch from fake store 'https://fakestoreapi.com/products'
- layout detail page with flexbox
- commit - Add Products Detail page

## Add Product Card component
- add `store-app/components/ProductCard.vue`
- commit - Add Product Card component

## Add ProductDetails component
- add `store-app/components/ProductDetails.vue`
- remove product details from `store-app/pages/products/[id].vue` and use ProductDetails component to render details
- commit - Add ProductDetails component

## Move ProductCard and ProductDetails to product folder in components
- Move ProductCard and ProductDetails to product folder in components
- rename ProductCard.vue to Card.vue and ProductDetails.vue to Details.vue
- nuxt router will find them as `<ProductCard>` and `<ProductDetails>`
- commit - Move ProductCard and ProductDetails to product folder in components
- commit - Minor styling ProductDetails

# June 29, 2023 - Nuxt Store App layouting UI
## Apply bootstrap style to product page and ProductCard
- [Bootstrap Card component](https://getbootstrap.com/docs/5.0/components/card/)
- commit - Apply bootstrap style to product page and ProductCard
- commit - Add gaps around cards in product page
- commit - Apply horizontal layout to ProductCard
- commit - Apply bootstrap style to ProductDetail Card

# June 30, 2023 - Nuxt Store App Add Pinia store
- branch `nuxt-store-app-pinia`

## Add Pinia store dependency and minimal state
- [Pinia home](https://pinia.vuejs.org/)
- article [Adding Pinia to Nuxt 3](https://dev.to/tao/adding-pinia-to-nuxt-3-2023-3l77)
  - [Install Pinia](https://pinia.vuejs.org/ssr/nuxt.html)
    - run `npm install pinia @pinia/nuxt`
      - cause `npm ERR! code ERESOLVE` add `"overrides": {"vue": "latest"}` to package.json
        - run `npm install` again and then `npm install pinia @pinia/nuxt`
        - succeed -> `"dependencies": {"@pinia/nuxt": "^0.4.11","pinia": "^2.1.4"}`
    - add `pinia` to `nuxt.config.js`
      ```js
      export default {
        modules: [
          '@pinia/nuxt'
        ],
      }
      ```
    - add [Auto imports]
    - stores/shop.ts
    ```ts
    import { defineStore } from 'pinia'
  
    export const useShopStore = defineStore('shop', {
    state: () => ({
      count: 0,
      shops: [{id: 1,name: 'Best Buy',}]
    }),
    })
    ```
    - add `store-app/pages/index.vue` with `setup()` and `useShopStore()`
    ```
    <script setup>
     import { useShopStore } from '@/stores/shop'
     const shop = useShopStore()
    </script>
    <template>
        <NuxtLayout>
            <div>
                <h5>Counter: {{shop.count}}</h5>
                <ul>
                    <li v-for="shop in shop.shops" :key="shop.id">{{shop.name}}</li>
                </ul>
            </div>
        </NuxtLayout>
    </template>
    ```
- commit - Add Pinia store dependency and minimal state
- commit - decompose count and shops from shop store

## Fetch products from fake store in pinia store
- Is axios module been deperecated? https://axios.nuxtjs.org/setup
  - Axios module supports Nuxt 2. Nuxt 3 users can use the new isomorphic $fetch API (migration guide).
- Use fake store [api](https://fakestoreapi.com/) [Docs](https://fakestoreapi.com/docs)
- fetch products in shop::fetchProducts() and render them in products/index.vue
  - TODO: remove `@ts-ignore` before `this.products = result;` in shop.ts
- use reference wrap arounf products `const {products} = storeToRefs(shop)` in products/index.vue
- TODO: products aren't rendered in products/index.vue on entry
- commit - Fetch products from fake store in pinia store

## Use typescript in pinia store
- create Product.ts in models folder with `Product` interface
  - follow [Job interface|object](https://youtu.be/H-hXNym2CK8?list=PL4cUxeGkcC9gCtAuEdXTjNVE5bbMFo5OD&t=421)
- still have an error `typescript error TS2352: Conversion of type 'Ref<unknown>' to type 'Product[]' may be a mistake because neither type sufficiently overlaps with the other.`
  - in `this.products = data as Product[];`
- commit - add `Product` interface and use typescript in pinia store

# July 2, 2023 - Pinia store

## Error in products rendering (stackoverflow)
Nuxt page doesn't render array from Pinia store [published](https://stackoverflow.com/questions/76599821/nuxt-page-doesnt-render-array-from-pinia-store)

- I have Nuxt page for products list with `fetchProducts()` method from pinia store
- product list NOT rendered on page 1st visit.

```
<script setup>
import { storeToRefs}   from "pinia";
import { useShopStore } from '@/stores/shop'

const shop = useShopStore();
shop.fetchProducts();
const {products} = storeToRefs(shop)
const hasProducts = computed(() => products.value.length > 0);

</script>

<template>
    <NuxtLayout>
        <div class="container">
            <h2>Products</h2>
            <div class="container" v-if="hasProducts">
	              <div class="row row-cols-1 gap-3">
                    <ProductCard :product="product" v-for="product in products" :key="product.id"/>
                </div>
            </div>
            <div v-else>
                <h3>Products NOT loaded</h3><br>
            </div>
        </div>
    </NuxtLayout>
</template>
```

As you can see on following snapshot products are loaded and available in pinia store, but array `products` on page is empty.

![](assets/markdown-img-paste-20230702122616710.png)

If I go back to home and return to product page products are rendered without any problem or warnings in console.

Any suggestion what I can do to fix or even investigate the issue?

PS. Store code if it's matter here

```
import { defineStore } from 'pinia'

import Product  from "@/models/Product";

export const useShopStore = defineStore('shop', {
    state: () => ({
        //products: <Product[]>[]
        products: [] as Product[]
    }),
    actions: {
        fetchProducts: async function () {
            try {
                const {data} = await useFetch('https://fakestoreapi.com/products');
                //TODO - fix this  typescript error `TS2352: Conversion of type 'Ref<unknown>' to type 'Product[]' may be a mistake because neither type sufficiently overlaps with the other.`
                // @ts-ignore
                this.products = data as Product[];
            } catch (error) {
                return error
            }
        },
    }
})
```

- similar question: [(Nuxt 3) Computed property from Pinia store doesn't affect the template](https://stackoverflow.com/questions/75521777/nuxt-3-computed-property-from-pinia-store-doesnt-affect-the-template?rq=2)

### My Answer

I'm not sure how valid it is, but assign `this.products = data.value as Product[];` instead of `this.products = data as Product[];` seems to fix the issue.   

```ts
fetchProducts: async function () {
  try {
    const {data} = await useFetch('https://fakestoreapi.com/products');
    this.products = data.value as Product[];
  } catch (error) {
    //showTooltip(error) - let the form component display the error
    return error
  }
}
```

Please let me know if it's valid solution or not. I'm still learning Vue 3 and Nuxt 3 and not sure that using `useFetch` in pinia store is a good idea.

- This also Solved - fix this  typescript error `TS2352: Conversion of type 'Ref<unknown>' to type 'Product[]' may be a mistake because neither type sufficiently overlaps with the other.`
  - no need for `// @ts-ignore`

- commit - Fix error in products rendering

# July 3, 2023 - Nuxt Store App Add Vuetify
- merge nuxt-store-app-pinia into nuxt-store-app
- C:\IntelliJ_WS_SpringBootWorkshop\springcloud-sbsuite\store-app>npm install

## Add Vuetify 
- nuxt vuetify module - https://nuxt.com/modules/nuxt-vuetify
  - `npm install --save-dev @invictus.codes/nuxt-vuetify`
  - add vuetify config to nuxt.config.ts
    
    ```ts
    modules: [
    '@invictus.codes/nuxt-vuetify'
    ],
    vuetify: {
        vuetifyOptions: {
            // @TODO: list all vuetify options
        },
        moduleOptions: {
            treeshaking: true,
            useIconCDN: true,
            // vite-plugin-vuetify options
            styles: true,
            autoImport: true,
            importLabComponents: true, 
        }
    }
    ```
    
- remove Bootstrap styles from nuxt.config.ts
- insert vuetify into default layout
- replace plain html in Nav.vue with Vuetify `<<v-app-bar>`
- commit - Add Vuetify, remove Bootstrap and add Vuetify to default layout and nav bar

## Add Vuetify to products page
- add Vuetify to products/index.vue and product/_id.vue
- replace plain html in Card.vue and Details.vue with Vuetify
- commit - Add Vuetify to products page

# July 4, 2023 - Revisit Store-service
## Add Store mysql database to docker-compose-local-dev-env.yml
- create store mysql db in database/initdb/Init.sql
- run docker-compose-local-dev-env.yml
- confirm store db is created
- commit - Add Store mysql database to docker-compose-local-dev-env.yml

# July 5, 2023 - Revisit Store-service, Vuetify store-admin UI app
## Add storeId to Inventory-service database
- commit - Add storeId to Inventory-service database

## Add JPA, lombok, mapstruct mysql-connector-java, flyway dependencies to store-service pom
- add database to store-service
- add storeId to order-service and inventory-service database
- copy dependencies and annotationProcessorPaths from product-service pom
  - add maven repository https://repo.maven.apache.org/maven2
- add `StoreRepository  extends JpaRepository<Store, Long>`
- add `class StoreServiceImpl implements StoreService`
  - with `@Autowired private StoreRepository storeRepository;`
    - `@Autowired` cause **runtime error** `Field storeRepository in com.springcloud.sbsuite.store.services.StoreServiceImpl required a bean of type 'com.springcloud.sbsuite.store.repositories.StoreRepository' that could not be found.`
    - remove `@Autowired` for `storeRepository` in StoreServiceImpl - runtime error gone
- add `db/migration/V1__init_database.sql` to store-service
  - flyway migration **not triggered** on empty database
  - Fix: comment `# spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration`
    - if uncommented in any other service - flyway migration **NOT** triggered
- ERROR `SchemaManagementException: Schema-validation: missing table [store]`
  - Fix - By mistake crete teble `stores` instead of `store` in `db/migration/V1__init_database.sql`
- restore `@Autowired`	in `@Autowired private StoreRepository storeRepository;`
  - still working and db tables created. TODO: Why?!!!
- commit - Add JPA, lombok, mapstruct mysql-connector-java, flyway dependencies to store-service pom

## Merge nuxt-store-app branch into master
- test apis with http-client
  - TODO: - Not an error but need attention: -  "path": "/inventory/product/1"  "message": "query did not return a unique result: 5",
commit - Merge nuxt-store-app branch into master

## Create store-admin Vutify app
- create branch `vuetify-store-admin-ui`

July 6-7, 2023 - work on Vue+Vuetify admin app to see how Vue development differs from Nuxt
- work on Vue+Vuetify admin app to see how Vue development differs from Nuxt
- see details in [vuetify-store-admin-ui/WorkLogNotes/WorkLog - Микросервисы на Spring Cloud.md](vuetify-store-admin-ui/WorkLogNotes/WorkLog - Микросервисы на Spring Cloud.md)

# July 8, 2023 - research on Nuxt+Pinia+Vuetify vs. Vue+Pinia+Vuetify
- freeze branch `vuetify-store-admin-ui`
- remove dummy subproject `store-admin` folder 
- will continue store-admin UI development in Nuxt
- commit - minor worklog update on research on Nuxt+Pinia+Vuetify vs. Vue+Pinia+Vuetify

# July 10, 2023 - Nuxt+Pinia+Vuetify storeadmin UI app
## Remove prototype-service dummy project
- commit - remove prototype-service dummy project

## Minor error in http-client test After adding storeId to Inventory-service database
- TODO: - minor error in http-client test - "GET {{gateway-host}}/inventory-service/inventory/product/1"  "message": "query did not return a unique result: 5",
- commit - Minor error in http-client test After adding storeId to Inventory-service database

## Create storeadmin UI app
- choose name `storeadmin` vs `store-admin` as project name to avoid confusion with `store-admin` in `vuetify-store-admin-ui` branch
- copy Nuxt3 `store-app` to `storeadmin`
- commit - Create storeadmin UI app

## Add dev json-server
- `Михаил Непомнящий` - Youtube channel - [json-server Локальный сервер для проектов и тестирования](https://youtu.be/odwOkxkmVH8)
  - install for dev-t `npm i -D json-server`
  - [npm json-server](https://www.npmjs.com/package/json-server)
  - [typicode/json-server](https://github.com/typicode/json-server)
  - run json-server from package.json `"scripts":{"server": "json-server -w server/db.json -p 3001"}`
  - run json-server with app - https://youtu.be/odwOkxkmVH8?t=634
    - install concurrently to run json-server in background `nmp i -D concurrently`
    - in package.json `"scripts":{"dev": "concurrently \"npm run server\" \"npm run start\"}`
  - `npm i -D json-server concurrently`
  - to run json-server with app add to package.json `"scripts":{"jsondev": "concurrently \"npm run server\" \"npm run dev\"",}`
- in `shop.ts` replace `const {data} = await useFetch('https://fakestoreapi.com/products');` with
  - `const {data} = await useFetch('http://localhost:3099/products');`
- now you can run `storeadmin>npm run jsondev` to run json-server and app concurrently
- commit - Add dev json-server

## Add dummy unstyled MenuCard component
- [Images, assets & public folder with Nuxt 3](https://youtu.be/tWQ2LWplmDc?t=68)
  - assign alias to asset folder in `nuxt.config.ts` - [@](https://youtu.be/tWQ2LWplmDc?t=228)
  - [vite-svg-loader](https://youtu.be/tWQ2LWplmDc?t=483)
  - [icones.js.org](https://youtu.be/tWQ2LWplmDc?t=536)
  - add svg icon as vue component [@](https://youtu.be/tWQ2LWplmDc?t=622)
- commit - Add dummy unstyled MenuCard component

##  Add SCSS support
- [Using Preprocessors](https://nuxt.com/docs/getting-started/styling#using-preprocessors)
  - install `npm install sass`
  - add `<style lang="scss" scoped>` to `MenuCard.vue` before that `lang="scss"` was caused error message `Cannot find module 'sass'` 
- commit - Add SCSS support  

# July 11, 2023 - Nuxt+Pinia+Vuetify storeadmin UI app
## Move Shops to db.json
- add fetchShops() to `shop.ts` pinia store
- add interface `Shop` to `shop.ts`
- call fetchShops() in `MenuCard.vue` in `script setup`
- commit - Move Shops to db.json

## Add currentShop to pinia store
- add `currentShop` to `shop.ts` pinia store
- add random values to shops in db.json
- refactor template in pages/index.vue to use `currentShop` from pinia store
  - add random image from pinia's currentShop `<v-img :src="currentShop.image" width="200" height="200"></v-img>`
  - print pinia's currentShop object `<p>Current Shop from Pinia store: {{ currentShop }}</p>`
- commit - Add currentShop to pinia store

## Add dummy page to avoid warning messages and make currewnShop nullable
- create `dummy.vue` page as placeholder for not existing routes
  - point menu items to `pages\dummy\dummy.vue` to avoid warning about not existing page
- replace `currentShop<Shop>: null` with `currentShop: null as Shop | null`
  - `currentShop: null as Shop | null,` - TODO: this variable reset on every new visit to the page
    -  and/or not visible in Nav.vue component
- commit - Add dummy page to avoid warning messages and make currewnShop nullable

# July 12, 2023 - Nuxt+Pinia+Vuetify storeadmin UI app
## remove `v-flex` from pages/index.vue
- cause `[Vue warn]: Failed to resolve component: v-flex` warning
- commit - remove `v-flex` from pages/index.vue
- commit - minor layout refactoring pages/index.vue

# July 13, 2023 - Nuxt+Pinia+Vuetify storeadmin UI app
## Show current shop name in Nav.vue button
- need to resolve earlier issue with `currentShop` reset on every new visit to the page and/or not visible in Nav.vue component
- add `currentShop` to `Nav.vue` component
- add `setCurrentShop` action to `shop.ts` pinia store
- use `setCurrentShop` pinia action in watcher in product/index.vue
  - `watch(selectedStoreId, (newData) => {shopStore.setCurrentShop(shop)}`
- now `currentShop` is visible in Nav.vue component
  - but come back from product page to index page reset selected shop to null (still visible in Nav.vue component)
- commit - Show current shop name in Nav.vue button
- commit - minor cleaning code and layout refactoring pages/index.vue
- commit - remove dummy counter from pinia shop.ts store

## set selected shop in v-select on mounted
- add on mount hook to set selected store in v-select component
- use currentShop to set v-model selectedStoreId to preselect v-select component
- commit - set selected shop in v-select on mounted

# July 16, 2023 - Nuxt+Pinia+Vuetify storeadmin UI app
## Add inventory data in db.json
- setup [DBeaver Community Free Universal Database Tool](https://dbeaver.io/download/)
  - resolve issue with mysql driver [Connection between DBeaver & MySQL](https://stackoverflow.com/questions/61749304/connection-between-dbeaver-mysql)
  - connect to project databases and export inventory table to json file
  - insert inventory data to db.json
- commit - Add inventory data in db.json

## Add inventory (with price) to pinia store
- modify insert inventory data in db.json by adding price in store
- add stores to shops in db.json
- add Inventory interface to models
- add inventory array and fetchInventory to pinia store
- commit - Add inventory (with price) to pinia store

## Add Inventory pages
- Add Inventory pages
  - add `inventory/index.vue` page (as copy of `product/index.vue` page)
    - fetch inventory in `setup()` function `shop.fetchInventory(shop.currentShop.id)`
      - fetch products for selected active store `useFetch('http://localhost:3099/inventory?store_id=' + store_id)` 
    - render primitive div for each inventory in template `<div v-for="item in inventory" :key="item.id"><span>{{item.name}}</span></div>`` 
- in home page add link to inventory page in MenuCard component
- commit - Add Inventory pages

# July 17, 2023 - Nuxt+Pinia+Vuetify storeadmin UI app
## Render inventory in Inventory page as table
- vuetify 2 `<v-data-table>` changed to `<v-table>` - see [vuetify tables](https://next.vuetifyjs.com/en/components/tables/)
- commit - Render inventory in Inventory page as table
- commit - styling table header and adding action icons

## Add Confirm dialog component with reference
- Add `storeadmin/components/ConfirmDialog.vue` component
- read how to use `ref` to component in Composition API [@](https://stackoverflow.com/questions/71093658/how-to-get-refs-using-composition-api-in-vue3)
  - copy component from VueJs 2 project
  - remove use i18n from component
  - use composition api way to use `ref` for child component
    - add component with reference `<ConfirmDialog ref="confirm" />` 
    - init reference to `confirm` `const confirm = ref(null)`
    - use reference in `openConfirmDialog` function `confirm.value.openConfirmDialog()`
- add `deleteProduct` function on delete icon click which call open ConfirmDialog component
  - open ConfirmDialog component with `confirm.value.open("Confirm", "question to confirm").then(decision => {... check response ...})`
- commit - Add Confirm dialog component with reference

## Add inventory edit form dialog component with reference
- repeat same steps as for ConfirmDialog component
- commit - Add inventory edit form dialog component with reference

# July 18, 2023 - Store-service API
## Store-service - Populate products_in_store table with products with price 
- add data to products_in_store table in migration sql script
- commit - Store-service - Populate products_in_store table with products with price

## add controller method to fetch products for store
- add price in ProductsInStore entity
  - Error in price mapping in ProductsInStore entity 
    - org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: 
      - [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: 
      - Unable to determine SQL type name for column 'price' of table 'products_in_store'
      - see `WorkLogNotes/GPTChat-JPA-Price-mapping.md`
  - TODO - refactor  `@ManyToOne private Store store;` this is very expencive to include duplicated store json part in every product JSON entity in response
    - ? add `@JsonIgnore` to `@ManyToOne private Store store;` to avoid duplicated store json part in every product JSON entity in response
    - ? add `@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})` to `@ManyToOne private Store store;` to avoid duplicated store json part in every product JSON entity in response
- add ProductsInStoreRepository
  - add `public List<ProductsInStore> findByStoreId(Long storeId);` method to select products for store
- add StoreService `fetchProductsInStore` method
  - add `public List<ProductsInStore> fetchProductsInStore(Long storeId) {return productsInStoreRepository.findByStoreId(storeId);}`
- add mapping `@GetMapping("/inventory/{id}")` in StoreController
  - retrieve products for store Id from products_in_store table
  - HTTP Error 404 in response to `GET {{gateway-host}}/store/inventory/1`
    - fix load balancer mapping in `api-gateway/src/main/resources/application.properties`
      - add `**` to `spring.cloud.gateway.routes[2].predicates[0]=Path=/store/inventory/**`
      - this fix issue with `GET {{gateway-host}}/store/inventory/1` but TODO: refactor all routes to use `**` instead of detailed unique pathes for each service
  - add test `GET {{gateway-host}}/store/inventory/1` to TestApiGateway.http
 - commit - add controller method to fetch products for store

## Rename dummy Eclient2Service to InventoryRestService
- commit - Rename dummy Eclient2Service to InventoryRestService

# July 19, 2023 - Store-service API
## Put json-server to separate branch `json-server-development` and freeze it
- will switch to use real database in respective services
- commit - Put json-server to separate branch `json-server-development` and freeze it
- back to master branch

## Refactor routes in api-gateway to use `**` instead of detailed unique pathes for each service
- google search `spring cloud gateway routes predicates`
  - [Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html)
- load balancer routes in `api-gateway/src/main/resources/application.properties`
  ```
  spring.cloud.gateway.routes[2].id=store
  spring.cloud.gateway.routes[2].uri=lb://store-service
  spring.cloud.gateway.routes[2].predicates[0]=Path=/store/inventory/**
  spring.cloud.gateway.routes[2].predicates[1]=Method=GET
  ```
  - create routes for each service in api-gateway `application.properties`
  - use only path predicate `Path=/entry/**` for each service, where `entry` is a root url mapping from each service controller
- commit - Refactor routes in api-gateway to use `**` instead of detailed unique pathes for each service

## Read stores and ProductsInStore from store-service
- add `@JsonIgnore` to `ProductsInStore.java` entity to avoid duplicated store json part in every product JSON entity in response
- replace `useFetch('http://localhost:3099/shops');` with `useFetch('http://localhost:8765/store');`
- replace `useFetch('http://localhost:3099/inventory?store_id=' + store_id);` with `useFetch('http://localhost:8765/store/inventory/' + store_id);`
  - Error in `const { data } = await useFetch('http://localhost:8765/store/inventory/' + store_id);`
    - `data.value` is empty
    - Very strange because browser http://localhost:8765/store/inventory/1 returns correct JSON data
    - also http://localhost:8765/store have no issues with reading data from app and no complains about CORS
    - to be continued ...

## Enable CORS in ApiGatewayApplication
- see [Enable CORS in ApiGatewayApplication](WorkLogNotes/GPTChat-Enable-CORS-in-api-gateway.md)
- This is resolved issue with CORS in api-gateway
- commit - Enable CORS in ApiGatewayApplication

## Read stores and ProductsInStore from store-service (continued)
- in `storeadmin/stores/shop.ts`
  - replace `useFetch('http://localhost:3099/shops');` with `useFetch('http://localhost:8765/store');`
  - replace `useFetch('http://localhost:3099/inventory?store_id=' + store_id);` with `useFetch('http://localhost:8765/store/inventory/' + store_id);`
- introduce intefaces ProductsInStore.ts and Shop.ts in `storeadmin/models/`
- add `public List<Store> getStores()` to StoreController
- commit - Read stores and ProductsInStore from store-service

# July 20, 2023 - Store-service API

## Introduce composite dto for ProductsInStore
- create `store-service/src/main/java/com/springcloud/sbsuite/dto/ProductsInStoreDto.java`
  - add frontend interface dto for ProductsInStoreDto
- change StoreController to return composite ProductsInStoreDto (for now it is just a copy of ProductsInStore with dummy quantity and description)
- render Products in store in `storeadmin/pages/inventory/index.vue`
- commit - Introduce composite dto for ProductsInStore

# July 29, 2023 - Store-service API

- commit - fix typo in `ProductsInStore` in shop.ts 

## Select composite ProductsInStoreDto from store-service and inventory-service
### select product inventory for specific store
- No need for this now [Select Entities by array of Fields](WorkLogNotes/GPTChat-Select Entities by array of Fields.md)
- add `List<Inventory> findByStoreId(Long storeId)` to Inventory repository
- add `List<InventoryDto> getStoreInventory(@PathVariable Long id)` to InventoryController
- test `GET {{gateway-host}}/inventory-service/inventory/store/1` in TestApiGateway.http
- commit - select product inventory for specific store

### add getting store inventory RestTemplate call to store-service
- Read [how to read list for entities from RestTemplate](WorkLogNotes/GPTChat-restTemplate which return list of entities.md)
- add `List<InventoryDto> getInventoryInStore(Long storeId);` to InventoryRestService interface
- implement `getInventoryInStore` in InventoryRestServiceImpl
- test `GET {{gateway-host}}/store/inventory/1` in TestApiGateway.http
  - java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class com.springcloud.sbsuite.dto.InventoryDto 
    - storeInventory = {ArrayList@19888}  size = 5
      0 = {LinkedHashMap@19894}  size = 6
      "id" -> {Integer@15511} 1
      "version" -> {Integer@15513} 0
      "createdDate" -> "2023-07-05T12:05:00+0000"
      "lastModifiedDate" -> "2023-07-05T12:05:00+0000"
      "productId" -> {Integer@15511} 1
      "quantity" -> {Integer@15511} 1
  - see - [Get list of JSON objects with Spring RestTemplate](https://www.baeldung.com/spring-resttemplate-json-list)
- test in storeadmin app
- commit - add getting store inventory RestTemplate call to store-service
- commit - rename `eclient2` package with RestTemplates to `restclient` in store-service

### select productDtos for specific ids from product-service
- right now store-service returns only product id, price and quantity in store, we need to retrieve product's name and description from product-service for specific store
- we'll ask product-service for product names and descriptions for array of product ids
- see [Select Entities by array of Fields](WorkLogNotes/GPTChat-Select Entities by array of Fields.md)
- add `List<Product> findByIdIn(List<Long> ids);` to ProductRepository
- add `List<ProductDto> fetchProductsByIds(List<Long> ids);` to ProductService
- add `public List<ProductDto> getProductsByIds(@PathVariable List<Long> ids)` mapped as `@GetMapping("/select/{ids}")` to ProductController
- test `GET {{gateway-host}}/product-service/product/select/1,2,3` in TestApiGateway.http
- commit - select productDtos for specific ids from product-service

### create RestTemplate call to product-service to get productDtos for specific ids
- add `ProductRestService` interface with method `List<ProductDto> getProductsInStore(List<Long> ids)`
- add `ProductRestServiceImpl` with method `List<ProductDto> getProductsInStore(List<Long> ids)`
- add call to `productDtos = productRestService.getProductsInStore(products.stream().map(ProductsInStore::getProductId).toList());` in StoreController
- test in storeadmin app - product name and description are rendered in inventory page
- commit - create RestTemplate call to product-service to get productDtos for specific ids

# August 2, 2023 - storeadmin app
## Clean up storeadmin inventory related page and store
- remove `import ConfirmDialog from "@/components/ConfirmDialog";` in `storeadmin/pages/inventory/index.vue`
  - old fashion declaration of ConfirmDialog component is not recognized as typescript export and discovered by Nuxt
- remove commented out code in `storeadmin/stores/shop.ts`
- commit - Clean up storeadmin inventory related page and store

# August 7, 2023 - storeadmin app
## ts lint and fix name productId property in ProductsInStoreDto and rename inventory to productsInStore in pinia store
- cast product to ProductInStore
- use `@ts-ignore` to ignore ts lint error
- rename `inventory: [] as ProductsInStore[]` to `productsInStore: [] as ProductsInStore[]`
- commit - ts lint and fix name productId property in ProductsInStoreDto and rename inventory to productsInStore in pinia store
- fix promise return type `.then(decision => {...})` to `.then((decision: boolean) => {...})`
- fix ` {{ shop.currentShop.name }} ` to ` {{ shop.currentShop?.name }} ` to remove warning `__VLS_ctx.shop.currentShop' is possibly 'null'.ts(18047)`
- commit - fix ts lint warnings

## Add ShoppingCart component and addProductToCart and removeProductFromCart actions to table with products in store
- add actions with icon mapped to `addProductToCart` and `removeProductFromCart` to `storeadmin/inventory/index.vue`
  - select mdi icons from https://materialdesignicons.com/  or https://pictogrammers.com/library/mdi/icon/cart-arrow-down/
- create `ProductInCart` and `ShoppingCart` interfaces
  - add them to inventory/index.vue
- create `components\shop\Cart.vue` component
  - add it to `storeadmin/pages/inventory/index.vue`
- TODO: - add computed total price of products in cart
- commit - Add ShoppingCart component and addProductToCart and removeProductFromCart actions to table with products in store

## Fix computed total of products in cart
- How I can "compute" total from ShoppingCart passed to vue component as property?
- Assuming your component is named Cart.vue:

    ```vue
    I apologize for the ongoing confusion. It seems that I misunderstood your initial question. If you've aliased the prop as ShoppingCart using const { cart: ShoppingCart } = defineProps(['cart']), you need to use ShoppingCart as the alias, not cart.

Here's the correct code:

```vue

<template>
  <div>
    <!-- Your template content here -->
    <p>Total Products in Cart: {{ total }}</p>
  </div>
</template>

<script setup lang="ts">
import { defineProps, computed } from 'vue';

interface ProductInCart {
  // Define your ProductInCart properties here
  quantity: number;
}

interface ShoppingCart {
  products: ProductInCart[];
}

const { cart: ShoppingCart } = defineProps(['cart']); // Use ShoppingCart as the alias
               ^ this is the alias (NOT a type)
const total = computed(() => {
  return ShoppingCart.products.reduce((total, product) => total + product.quantity, 0);
});
</script>
```


User
YES!!!

```
const { cart: shoppingCart } = defineProps(['cart'])

const total = computed(() => {
   return shoppingCart.products.reduce((total, product) => total + product.quantity, 0)
}) 
```


My mistake - I mistreat `cart: shoppingCart` which means "decompose cart from component properties and use shoppingCart as variable name". I was thinking that this is cart of type ShoppingCart ... My mistake!

- commit - Fix computed total of products in cart

# August 8, 2023 - storeadmin app
## Refactor how to declare ShoppingCart property in Cart.vue component
- all two declarations are valid, but second one is more readable and typescript friendly

```ts
// first declaration
const { cart: shoppingCart } = defineProps(['cart'])// Use shoppingCart as the alias

// following declaration need to import ShoppingCart interface
import ShoppingCart from "~/models/ShoppingCart";

// second declaration
const { cart: shoppingCart } = defineProps<{ cart: ShoppingCart }>();

```

- commit - Refactor how to declare ShoppingCart property in Cart.vue component

## Style shop/Cart.vue component
- It should be rendered as a small ellipsis with cart mdi icon and total number of products in it.
- see [GPTChat-ShopCartComponent](./GPTChat-ShopCartComponent.md)
- commit - Style shop/Cart.vue component
- commit - render cart in `storeadmin/pages/inventory/index.vue` in header section
- commit - rewrite CSS styles to SCSS

## Create ShoppingCartDetail component to send order to order-service
- create `components\shop\CartDetail.vue` component
  - render products in cart - Fixed issue with access property alias in template html code 
  - see [GPTChat-Fixing Undefined ShoppingCart Error](WorkLogNotes/GPTChat-Fixing Undefined ShoppingCart Error.md)
- emit `openCartDetail` event from `components\shop\Cart.vue` component on click on cart icon
- catch `openCartDetail` event in `storeadmin/pages/inventory/index.vue` and open `showCartDetail` to true
  - <ShopCart :cart="shoppingCart" @openCartDetail="openCartDetail"/>
  - to access `openCart()` method within component you need to make it exposed `defineExpose({ openCart })`
  - see [call method in child component](https://stackoverflow.com/questions/55316490/vue-best-practice-for-calling-a-method-in-a-child-component)
- commit - Create ShoppingCartDetail component to send order to order-service
- commit - render ShoppingCartDetail content as v-table
- commit - add `deleteProductAction` and `addProductAction` handlers to `ShoppingCartDetail` component
- commit - validate shopping cart to prevent order with empty shopping cart

# August 9, 2023 - storeadmin app
## TODO: 
- ~~try change `ref(null)` to `ref()`~~
```ts
<script setup lang="ts">
const childRef = ref()

const callSayHello = () => {
  childRef.value.sayHello()
}
</script>

<template>
  <child ref="childRef"></child>
</template>
```


## Resolve tslint warnings in ShoppingCartDetail
- missing imports ProductInCart (replace ProductInStore)
- rename `deleteProduct` to `deleteProductAction`
- change `ref(null)` to `ref()`
- commit - Resolve tslint warnings in ShoppingCartDetail
- commit - fix nullable type warning in pages/index.vue
  - change `:src="currentShop?.image"` in `<v-img :src="currentShop.image" width="800" height="200"></v-img>`

- Stuck with Mapping for OrderHeader to OrderHeaderDto (DateMapper)

# August 10, 2023 - OrderService
- google `mapstruct OffsetDateTime to Timestamp mapper`
- [Quick Guide to MapStruct](https://www.baeldung.com/mapstruct)
- TODO: try set same Date format for Dto and entity

## Create `mapstruct-mapper-issues` branch to investigate mapping issues
- failed test `saveOrderHeader` in OrderServiceTest.java
  - order saved with proper number of OrderLines but only one empty OrderLine had been returned after mapstruct mapping entity to dto
- rename `orderHeaderDtoToOrderLine` to `addressDtoToAddress` in `AddressMapper`
- rename `orderHeaderDtoToOrderLine` to `contactDtoToContact` in `ContactMapper`
- Error in test MapperTest::testAddressMappingInOrderHeader
  - java.lang.NullPointerException: Cannot invoke "com.springcloud.sbsuite.orders.mappers.AddressMapper.addressDtoToAddress(com.springcloud.sbsuite.dto.AddressDto)" because "this.addressMapper" is null
- commit - Create `mapstruct-mapper-issues` branch to investigate mapping issues