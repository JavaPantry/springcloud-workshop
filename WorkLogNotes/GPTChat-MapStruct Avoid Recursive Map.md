User
How to avoid recursive map to dto in mapstruct

I have two entities, one is a parent and the other is a child. The child has a reference to the parent and the parent has a list of children.

```java
@Entity
public class OrderHeader extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<OrderLine> orderLines;
}

@Entity
public class OrderLine extends BaseEntity {

    @ManyToOne
    private OrderHeader orderHeader;

    private Long productId;
    private Integer quantityOrdered;

}
```

ChatGPT
When using MapStruct to map entities to DTOs, you might encounter issues with circular references causing infinite recursion. To avoid this, you can follow these steps:

Use Different DTOs: Create separate DTOs for the parent and child entities, each with the relevant fields you want to map. This way, you won't have circular references in your DTOs.

Custom Mapping: Instead of relying solely on MapStruct's automatic mapping, create custom mapping methods for the parent and child entities to DTOs. In these methods, manually map the fields you need, avoiding the circular references.

Here's an example of how you can achieve this:

Create DTOs for OrderHeader and OrderLine:

```java
public class OrderHeaderDTO {
private String name;
private Set<OrderLineDTO> orderLines;

    // Getters and setters
}

public class OrderLineDTO {
private Long productId;
private Integer quantityOrdered;

    // Getters and setters
}
```

Then, create a custom mapper interface for each entity:

```java
@Mapper(componentModel = "spring")
public interface OrderHeaderMapper {
OrderHeaderMapper INSTANCE = Mappers.getMapper(OrderHeaderMapper.class);

    @Mapping(target = "orderLines", ignore = true)
    OrderHeaderDTO orderHeaderToDTO(OrderHeader orderHeader);

    default Set<OrderLineDTO> orderLinesToDTO(Set<OrderLine> orderLines) {
        if (orderLines == null) {
            return null;
        }
        return orderLines.stream()
                .map(this::orderLineToDTO)
                .collect(Collectors.toSet());
    }

    OrderLineDTO orderLineToDTO(OrderLine orderLine);
}

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

    OrderLineDTO orderLineToDTO(OrderLine orderLine);
}
```

By manually mapping the child entities in the parent mapper's custom method and using the child mapper for the child entity, you avoid circular references and infinite recursion.

Finally, you can use the mappers to convert your entities to DTOs:

```java
OrderHeaderDTO orderHeaderDTO = OrderHeaderMapper.INSTANCE.orderHeaderToDTO(orderHeader);
```

Remember to adjust the mapping logic and field names to match your actual use case.