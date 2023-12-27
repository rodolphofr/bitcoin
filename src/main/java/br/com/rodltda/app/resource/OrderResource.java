package br.com.rodltda.app.resource;

import br.com.rodltda.app.request.OrderRequest;
import br.com.rodltda.app.response.OrderResponse;
import br.com.rodltda.app.response.PageResponse;
import br.com.rodltda.app.response.assembler.OrderResponseAssembler;
import br.com.rodltda.infra.db.entity.Order;
import br.com.rodltda.infra.db.entity.User;
import br.com.rodltda.infra.db.repository.OrderRepository;
import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;

@Path("/orders")
@Resource
@RequiredArgsConstructor
public class OrderResource {
    
    private final OrderRepository orderRepository;
    private final OrderResponseAssembler orderResponseAssembler;
    
    @POST
    @Transactional
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context SecurityContext securityContext, @Valid OrderRequest orderRequest) { 
        /**
         * TODO: SecurityFilter should be created to abstract the query for the user credentials 
         */
        final User user = User.findById(orderRequest.userId());

        if (user == null)
            throw new NotFoundException("User not found");

        final String username = securityContext.getUserPrincipal().getName();

        if (!username.equals(user.getUsername()))
            throw new ForbiddenException("The user authenticated is different from the request \"user_id\"");

        Order order = orderRequest.toEntity(user);
        orderRepository.persist(order);

        return Response.status(Status.CREATED)
            .entity(orderResponseAssembler.assemble(order)).build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public PageResponse<OrderResponse, Order> getOrders(
        @DefaultValue("0") @QueryParam("page") int pageIndex,
        @DefaultValue("100") @QueryParam("size") int pageSize) {

        return new PageResponse<>(
            Order.findAll().page(pageIndex, pageSize), orderResponseAssembler);
    }
}
