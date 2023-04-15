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

## April 15, 2023 - Remove Gradle dependencies from root pom project
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