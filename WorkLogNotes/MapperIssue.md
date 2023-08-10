# Issue with MapStruct Mapper

I have the following entity classes

```
public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
}

public class OrderHeader {

    private String name;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billToAddress;

}
```

and corresponding DTOs

```
public class AddressDTO {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}

public class OrderHeaderDTO{
    private String name;
    private AddressDTO shippingAddress;
    private AddressDTO billToAddress;
}
```

I have a MapStruct mappers defined as follows

```
@Mapper
public interface AddressMapper {
	Address orderHeaderDtoToOrderLine(AddressDto addressDto);
	AddressDto orderHeaderToOrderHeaderDto(Address address);
}

@Mapper(uses = {AddressMapper.class})
public interface OrderHeaderMapper {
	OrderHeader orderHeaderDtoToOrderHeader(OrderHeaderDto orderHeaderDto);
	OrderHeaderDto orderHeaderToOrderHeaderDto(OrderHeader orderHeader);
}
```

When I try to run test code

```
		AddressDto fakeAddress = new AddressDto().builder()
				.streetAddress("123 Main St")
				.city("Anytown")
				.state("NY")
				.zipCode("12345").build();

		OrderHeaderDto orderHeaderDto = new OrderHeaderDto().builder()
				.name("test order")
				.billToAddress(fakeAddress)
				.shippingAddress(fakeAddress)
				.orderStatus(OrderStatus.COMPLETED)
				.build();

		OrderHeaderMapper orderHeaderMapper = Mappers.getMapper(OrderHeaderMapper.class);
		OrderHeader orderHeader = orderHeaderMapper.orderHeaderDtoToOrderHeader(orderHeaderDto);
```

I get the following error

java.lang.NullPointerException: Cannot invoke "com.springcloud.sbsuite.orders.mappers.AddressMapper.orderHeaderDtoToOrderLine(com.springcloud.sbsuite.dto.AddressDto)" because "this.addressMapper" is null


## MapStruct date mapping issue

I have an entity class with a Timestamp field

```
@Entity
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());
}
```

and Dto class with an OffsetDateTime field

```
public class BaseEntityDto {

    private Long id;
    private OffsetDateTime createdDate;
}
```

I created a DateMapper as follows

```
@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp ts){
        if (ts != null){
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime){
        if(offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
```

I don't have issue to convert Dto to entity however when I try to convert entity to Dto, I get the following error

Can't map property "Timestamp createdDate" to "OffsetDateTime createdDate". Consider to declare/implement a mapping method: "OffsetDateTime map(Timestamp value)".


## MapStruct ambiguous mapping issue
Ambiguous mapping methods found for mapping property "Timestamp createdDate" to OffsetDateTime: 
    OffsetDateTime DateMapper.asOffsetDateTime(Timestamp ts), OffsetDateTime DateMapper.map(Timestamp value). See https://mapstruct.org/faq/#ambiguous for more info.