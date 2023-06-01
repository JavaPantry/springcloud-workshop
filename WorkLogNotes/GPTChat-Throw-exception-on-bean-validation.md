In spring mvc controller validation done by @Validated annotation added to request body argument.
Is it possible to make spring throw an exception instead reply with status 400 - which means "bad request"?

Yes, it is possible to make Spring MVC throw an exception instead of replying with a status of 400 (bad request) when validation fails. 
By default, Spring MVC uses the MethodArgumentNotValidException exception to handle validation errors and responds with a 400 status code. 
However, you can customize this behavior by implementing an exception handler in your application.

To make Spring MVC throw an exception instead, you can create a custom exception class that represents the validation error, 
and then define an exception handler method to handle it. Here's an example:

Create a custom exception class to represent the validation error. You can name it something like ValidationException:
```java
public class ValidationException extends RuntimeException {
// Add any additional properties or methods if needed
}
```

Define an exception handler method in your controller or a separate @ControllerAdvice class to handle the ValidationException:

```java
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationException(ValidationException ex) {
        // You can perform additional actions here if needed, such as logging
        throw ex;
    }
}
```

In this example, the @ExceptionHandler annotation is used to specify that the handleValidationException method should handle ValidationException specifically.
The @ResponseStatus annotation sets the HTTP status code to 400 (bad request) for the response.

Modify your controller method to throw the ValidationException when validation fails:
```java
@PostMapping("/your-endpoint")
public void yourControllerMethod(@Validated @RequestBody YourRequestObject request) {
// If validation fails, throw the ValidationException
if (/* validation condition */) {
throw new ValidationException();
}

    // Handle the valid request
}
```

By throwing the ValidationException in your controller method, it will trigger the exception handler defined in CustomExceptionHandler, 
and the response will have a status of 400 (bad request). 
You can customize the ValidationException class further by adding properties or methods to provide additional information about the validation error, if necessary.

Note that you can also customize the exception handling further by returning a custom response object or using @ResponseBody to return a response body with error details. 
The example above throws the exception to mimic the default behavior of Spring MVC when validation fails.