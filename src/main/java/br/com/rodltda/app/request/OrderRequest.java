package br.com.rodltda.app.request;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.rodltda.infra.db.entity.Order;
import br.com.rodltda.infra.db.entity.OrderStatus;
import br.com.rodltda.infra.db.entity.OrderType;
import br.com.rodltda.infra.db.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
    @NotNull(message = "The price cannot be null") BigDecimal price, 
    @NotNull(message = "The order type cannot be null") @JsonProperty("order_type") OrderType orderType, 
    @NotBlank(message = "The user id cannot be null") @JsonProperty("user_id") String userId) {

    public Order toEntity(User user) {
        Order order = new Order();
        order.setPrice(price);
        order.setType(orderType);
        order.setDate(new Timestamp(Instant.now().toEpochMilli()));
        order.setUser(user);

        order.setStatus(orderType == OrderType.BUY
            ? OrderStatus.PROCESSED
            : OrderStatus.SENT);

        return order;
    }    
}
