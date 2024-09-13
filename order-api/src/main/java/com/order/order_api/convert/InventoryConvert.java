package com.order.order_api.convert;

import org.springframework.stereotype.Component;

import com.order.order_api.dtos.InventoryCheckDto;
import com.order.order_api.dtos.OrderModelDto;

@Component
public class InventoryConvert {
    
    public InventoryCheckDto inventoryConvert(OrderModelDto orderModelDto){
        InventoryCheckDto inventoryCheckDto = new InventoryCheckDto();
        inventoryCheckDto.setProductId(orderModelDto.getProductId());
        inventoryCheckDto.setStockQuantity(orderModelDto.getStockQuantity());
        return inventoryCheckDto;
    }
}
