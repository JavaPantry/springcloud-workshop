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
  - `private final Eclient2Service eclient2Service;` with constructor
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
  - rename package `com.springcloud.sbsuite.eurekaclient2.api` to `com.springcloud.sbsuite.store.api` 
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
    - Failed to load ApplicationContext for [MergedContextConfiguration@446626a7 testClass = com.springcloud.sbsuite.store.services.ProductServiceTest
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
  - com.springcloud.sbsuite.store.domain.OrderHeader.orderLines: could not initialize proxy - no Session
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
    - No qualifying bean of type 'com.springcloud.sbsuite.store.services.ProductService' available: 
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
    jakarta.validation.ConstraintViolationException: Validation failed for classes [com.springcloud.sbsuite.store.domain.Product] during persist time for groups [jakarta.validation.groups.Default, ]
    List of constraint violations:[
    ConstraintViolationImpl{interpolatedMessage='must not be blank', propertyPath=description, rootBeanClass=class com.springcloud.sbsuite.store.domain.Product, messageTemplate='{jakarta.validation.constraints.NotBlank.message}'}
    ConstraintViolationImpl{interpolatedMessage='must not be null', propertyPath=description, rootBeanClass=class com.springcloud.sbsuite.store.domain.Product, messageTemplate='{jakarta.validation.constraints.NotNull.message}'}
    ConstraintViolationImpl{interpolatedMessage='must not be null', propertyPath=name, rootBeanClass=class com.springcloud.sbsuite.store.domain.Product, messageTemplate='{jakarta.validation.constraints.NotNull.message}'}
    ConstraintViolationImpl{interpolatedMessage='must not be blank', propertyPath=name, rootBeanClass=class com.springcloud.sbsuite.store.domain.Product, messageTemplate='{jakarta.validation.constraints.NotBlank.message}'}
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