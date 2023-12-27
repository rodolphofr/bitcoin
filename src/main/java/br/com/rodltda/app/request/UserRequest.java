package br.com.rodltda.app.request;

import br.com.rodltda.infra.db.entity.Role;
import br.com.rodltda.infra.db.entity.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
    @NotBlank(message = "The name cannot be blank") String name, 
    @NotBlank(message = "The CPF cannot be blank") String cpf, 
    @NotBlank(message = "The username cannot be blank") String username,
    @NotBlank(message = "The password cannot be blank") String password) {
    
    public User toEntity() {
        User entity = new User();
        entity.setCpf(cpf);
        entity.setName(name);
        entity.setPassword(BcryptUtil.bcryptHash(password));
        entity.setUsername(username);
        entity.setRole(Role.USER.name());

        return entity;
    }
}
