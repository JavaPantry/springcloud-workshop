# Spring Data JPA mapping issue

for entity class

```java

@Entity
public class ProductsInStore extends BaseEntity {
@ManyToOne
private Store store;

    @NotBlank
    @NotNull
    private Long productId;

    @NotBlank
    @NotNull
    @Column(precision=10, scale=2)
    private Double price;
}
```

and database table

```sql

create table products_in_store(
id bigint not null auto_increment,
store_id bigint,
product_id bigint,
price decimal(10,2),
) engine = InnoDB;
```

I have an error "Unable to determine SQL type name for column 'price' of table 'products_in_store'"

The error you're encountering is due to the mismatch between the Java field type and the corresponding database column type for the "price" attribute in your entity class.

In your entity class, you have defined the "price" attribute as a Double, but in the database table, you have specified the corresponding column as decimal(10,2).

To resolve this issue, you should update the field type in your entity class to match the database column type. 
In this case, you should use BigDecimal instead of Double for the "price" attribute.

Here's an updated version of your entity class:

```java
@Entity
public class ProductsInStore extends BaseEntity {
@ManyToOne
private Store store;

    @NotBlank
    @NotNull
    private Long productId;

    @NotBlank
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
```

By using BigDecimal, you can accurately represent decimal values with precision and scale, matching the decimal(10,2) column type in the database.

FIXED: - After making this change, recompile and run your application, and the error should be resolved