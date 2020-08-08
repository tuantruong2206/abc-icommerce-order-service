package au.com.nab.icommerce.order.dto;

import au.com.nab.icommerce.order.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Order and its full DTO
 */
@Mapper(componentModel = "spring", uses ={})
public interface OrderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userid", target = "userid")
    @Mapping(source = "basketId", target = "basketId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    OrderDTO toDto(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userid", target = "userid")
    @Mapping(source = "basketId", target = "basketId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    Order toEntity(OrderDTO productDTO);
}
