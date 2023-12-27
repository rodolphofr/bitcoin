package br.com.rodltda.app.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.rodltda.infra.db.entity.OrderStatus;
import br.com.rodltda.infra.db.entity.OrderType;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderResponse(BigDecimal price, 
    OrderType type, 
    OffsetDateTime date, 
    OrderStatus status, 
    String userId) {

}
