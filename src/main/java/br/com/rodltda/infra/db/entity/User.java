package br.com.rodltda.infra.db.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity(name = "User")
@UserDefinition
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    @Column(length = 50, nullable = false)
    private String name;

    @Setter
    @Column(length = 14, nullable = false)
    private String cpf;

    @Setter
    @Username
    @Column(length = 50, nullable = false)
    private String username;

    @Setter
    @Password
    @Column(length = 100, nullable = false)
    private String password;

    @Setter
    @Roles
    @Column(length = 55, nullable = false)
    private String role;
}
