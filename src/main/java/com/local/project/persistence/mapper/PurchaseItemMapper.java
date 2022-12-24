package com.local.project.persistence.mapper;

import com.local.project.domain.PurchaseItem;
import com.local.project.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, PurchaseMapper.class})
public interface PurchaseItemMapper {

   @Mappings({
      @Mapping(source = "id.idProducto", target = "productId"),
      @Mapping(source = "cantidad", target = "quantity"),
      @Mapping(source = "estado", target = "active")
   })
   PurchaseItem toPurchaseItem(CompraProducto compraProducto);

   @InheritInverseConfiguration
   @Mappings({
      @Mapping(target = "compra", ignore = true),
      @Mapping(target = "producto", ignore = true),
      @Mapping(target = "id.idCompra", ignore = true)
   })
   CompraProducto toCompraProducto(PurchaseItem purchaseItem);

}
