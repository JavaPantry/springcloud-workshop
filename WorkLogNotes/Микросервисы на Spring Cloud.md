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
    - WebSecurityConfigurerAdapter is deprecated and should use component-based security configuration. You'll have to create a SecurityFilterChain bean for HTTPSecurity and shouldn't extend WebSecurityConfigurerAdapter as other answer suggested. Please refer https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter for more details.
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

289. Secure Inventory Service with Spring Security 4min
-
