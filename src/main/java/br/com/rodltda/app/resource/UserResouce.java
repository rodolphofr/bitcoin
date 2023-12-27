package br.com.rodltda.app.resource;

import br.com.rodltda.app.request.PageRequest;
import br.com.rodltda.app.request.UserRequest;
import br.com.rodltda.app.response.PageResponse;
import br.com.rodltda.app.response.UserResponse;
import br.com.rodltda.infra.db.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Resource
public class UserResouce {
    
    @POST
    @PermitAll
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid UserRequest userRequest) {
        User user = userRequest.toEntity();
        User.persist(user);
        return Response.status(Status.CREATED).entity(new UserResponse(user)).build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public PageResponse<UserResponse, User> getUsers(@BeanParam PageRequest pageRequest) {
        PanacheQuery<User> page = User.findAll(pageRequest.getSort())
            .page(pageRequest.getPage(), pageRequest.getSize());

        return new PageResponse<>(page, UserResponse::new);
    }
}
