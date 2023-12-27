package br.com.rodltda.app.response.assembler;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import br.com.rodltda.app.response.OrderResponse;
import br.com.rodltda.infra.db.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderResponseAssembler implements ResponseAssembler<OrderResponse, Order> {
    private static final ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");

    @Override
    public OrderResponse assemble(Order order) {
        return new OrderResponse(order.getPrice(), 
            order.getType(), 
            toOffsetDatetime(order.getDate()), 
            order.getStatus(), 
            order.getUser().getId());
    }

    private OffsetDateTime toOffsetDatetime(Timestamp timestamp) {
        return OffsetDateTime
            .ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZONE_ID);
    }
}
