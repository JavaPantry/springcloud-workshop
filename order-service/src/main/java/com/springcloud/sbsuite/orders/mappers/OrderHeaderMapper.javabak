package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHeaderMapper {
	private CycleAvoidingMappingContext cycleAvoidingMappingContext = new CycleAvoidingMappingContext();

	@Autowired
	private DateMapper dateMapper; // = new DateMapper();
	@Autowired
	private OrderLineMapper orderLineMapper; //  = new OrderLineMapperImpl();
	@Autowired
	private AddressMapper addressMapper; //  = new AddressMapperImpl();

	public OrderHeader dtoToEntity(OrderHeaderDto orderHeaderDto){
		if ( orderHeaderDto == null ) {
			return null;
		}

		OrderHeader.OrderHeaderBuilder orderHeader = OrderHeader.builder();

		cycleAvoidingMappingContext.storeMappedInstance( orderHeaderDto, orderHeader );

		orderHeader.orderLines( orderLineDtoListToOrderLineList( orderHeaderDto.getOrderLines(), cycleAvoidingMappingContext ) );

		orderHeader.name( orderHeaderDto.getName() );
		orderHeader.shippingAddress( addressMapper.dtoToEntity( orderHeaderDto.getShippingAddress() ) );
		orderHeader.billToAddress( addressMapper.dtoToEntity( orderHeaderDto.getBillToAddress() ) );
		orderHeader.orderStatus( orderHeaderDto.getOrderStatus() );

		return orderHeader.build();
	}
	public OrderHeaderDto entityToDto(OrderHeader orderHeader){
		if ( orderHeader == null ) {
			return null;
		}

		OrderHeaderDto.OrderHeaderDtoBuilder orderHeaderDto = OrderHeaderDto.builder();

		cycleAvoidingMappingContext.storeMappedInstance( orderHeader, orderHeaderDto );

		orderHeaderDto.orderLines( orderLineListToOrderLineDtoList( orderHeader.getOrderLines(), cycleAvoidingMappingContext ) );

		orderHeaderDto.id( orderHeader.getId() );
		if ( orderHeader.getVersion() != null ) {
			orderHeaderDto.version( orderHeader.getVersion().intValue() );
		}
		orderHeaderDto.createdDate( dateMapper.asOffsetDateTime( orderHeader.getCreatedDate() ) );
		orderHeaderDto.lastModifiedDate( dateMapper.asOffsetDateTime( orderHeader.getLastModifiedDate() ) );
		orderHeaderDto.name( orderHeader.getName() );
		orderHeaderDto.shippingAddress( addressMapper.entityToDto( orderHeader.getShippingAddress() ) );
		orderHeaderDto.billToAddress( addressMapper.entityToDto( orderHeader.getBillToAddress() ) );
		orderHeaderDto.orderStatus( orderHeader.getOrderStatus() );

		return orderHeaderDto.build();
	}

	protected List<OrderLine> orderLineDtoListToOrderLineList(List<OrderLineDto> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
		List<OrderLine> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
		if ( target != null ) {
			return target;
		}

		if ( list == null ) {
			return null;
		}

		List<OrderLine> list1 = new ArrayList<OrderLine>( list.size() );
		cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

		for ( OrderLineDto orderLineDto : list ) {
			list1.add( orderLineMapper.dtoToEntity( orderLineDto, cycleAvoidingMappingContext ) );
		}

		return list1;
	}

	protected List<OrderLineDto> orderLineListToOrderLineDtoList(List<OrderLine> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
		List<OrderLineDto> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
		if ( target != null ) {
			return target;
		}

		if ( list == null ) {
			return null;
		}

		List<OrderLineDto> list1 = new ArrayList<OrderLineDto>( list.size() );
		cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

		for ( OrderLine orderLine : list ) {
			list1.add( orderLineMapper.entityToDto( orderLine, cycleAvoidingMappingContext ) );
		}

		return list1;
	}


}
