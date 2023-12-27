package br.com.rodltda.infra.db.repository;

import br.com.rodltda.infra.db.entity.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<Order, String> {
    
}
