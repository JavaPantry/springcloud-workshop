# spring call restTemplate which return list of entities
To make a REST API call using Spring's RestTemplate and retrieve a list of entities, you can follow these steps:

* Define your entity class: Create a Java class that represents the entity returned by the REST API.
* Set up RestTemplate: Make sure you have RestTemplate configured in your Spring application. You can achieve this by adding RestTemplate as a bean in your Spring configuration.
* Make the API call: Use RestTemplate to call the API endpoint and retrieve the list of entities.
* Here's an example of how you can do this:

Assuming you have an entity class MyEntity like this:

```java
public class MyEntity {
private Long id;
private String name;

    // Getters and setters (and constructors if needed)
}
```

Step 2: Set up RestTemplate as a bean in your Spring configuration:

```java
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

Step 3: Make the API call:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class MyService {

    private final RestTemplate restTemplate;

    @Autowired
    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MyEntity> getEntitiesFromApi() {
        String apiUrl = "http://example.com/api/entities"; // Replace with the actual API URL

        ResponseEntity<List<MyEntity>> response =
                restTemplate.exchange(apiUrl, HttpMethod.GET, null,
                                      new ParameterizedTypeReference<List<MyEntity>>() {
                                      });

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Handle error if needed
            return null;
        }
    }
}
```

In this example, we inject the RestTemplate bean into the MyService class and use the exchange method to make a GET request to the API endpoint. 
The ParameterizedTypeReference is used to help Spring deserialize the response into a List<MyEntity>.

Please note that in a real-world scenario, you should handle exceptions, error cases, and possibly add more configuration to RestTemplate (e.g., setting headers, authentication, etc.), but this example should give you a basic idea of how to make a REST API call using RestTemplate and get a list of entities in return.

## restTemplate return LinkedHashMap in list. How to convert it to list of my entity InventoryDto?

If the RestTemplate is returning a List of LinkedHashMap objects and you want to convert them to a list of your custom entity InventoryDto, you'll need to perform the conversion manually. This involves iterating through the List of LinkedHashMap objects and creating new InventoryDto instances for each entry. To simplify the process, you can use the ObjectMapper from Jackson (a popular JSON processing library) to handle the conversion.

Here's how you can do it:

Step 1: Make sure you have the Jackson library added to your project. You can add it as a dependency using Maven or Gradle.

Step 2: Create a method to convert the List<LinkedHashMap> to a List<InventoryDto>:

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MyService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public MyService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<InventoryDto> getInventoryDtoListFromApi() {
        String apiUrl = "http://example.com/api/inventory"; // Replace with the actual API URL

        ResponseEntity<List<Map<String, Object>>> response =
                restTemplate.exchange(apiUrl, HttpMethod.GET, null,
                                      new ParameterizedTypeReference<List<Map<String, Object>>>() {
                                      });

        if (response.getStatusCode().is2xxSuccessful()) {
            List<Map<String, Object>> mapList = response.getBody();

            // Convert the List of LinkedHashMap to List of InventoryDto
            return convertToInventoryDtoList(mapList);
        } else {
            // Handle error if needed
            return null;
        }
    }

    private List<InventoryDto> convertToInventoryDtoList(List<Map<String, Object>> mapList) {
        return mapList.stream()
                .map(this::convertToInventoryDto)
                .collect(Collectors.toList());
    }

    private InventoryDto convertToInventoryDto(Map<String, Object> map) {
        // Use ObjectMapper to convert Map to InventoryDto
        return objectMapper.convertValue(map, InventoryDto.class);
    }
}
```

In the above code, we're using the ObjectMapper to convert each LinkedHashMap to an InventoryDto. The convertToInventoryDtoList method takes the list of LinkedHashMap objects and converts it to a list of InventoryDto instances. The convertToInventoryDto method handles the conversion of a single LinkedHashMap to an InventoryDto.

Make sure to inject the ObjectMapper bean into your Spring configuration or application context so that it can be used in the MyService class.

By doing this, you should be able to convert the List of LinkedHashMap objects returned by RestTemplate into a List of your custom entity InventoryDto.