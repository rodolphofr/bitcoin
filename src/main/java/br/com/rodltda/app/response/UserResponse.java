package br.com.rodltda.app.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.rodltda.infra.db.entity.User;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponse(String id, 
        String name, 
        String username, 
        String role) {

    public UserResponse(User user) {
        this(user.getId(), 
            user.getName(), 
            user.getUsername(), 
            user.getRole());
    }
}
