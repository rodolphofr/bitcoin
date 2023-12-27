package br.com.rodltda.app.resource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.rodltda.infra.service.external.CoinAPIService;
import br.com.rodltda.infra.service.external.response.AssetResponseDTO;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bitcoin")
@Resource
public class BitcoinResource {
    
    @Inject
    @RestClient
    CoinAPIService coinAPIService;

    @GET
    @PermitAll
    @Path("/assets/{asset_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllAssetsById(@PathParam("asset_id") String assetId) {
        var assets = coinAPIService.getAllAssetsByAssetId(assetId);
        
        return Optional.ofNullable(assets)
            .filter(a -> a.isEmpty() == false)
            .map(Response::ok)
            .orElse(Response.status(404))
            .build();
    }

    @GET
    @PermitAll
    @Path("/assets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AssetResponseDTO> listAllAssets(@QueryParam("asset_ids") Set<String> assetIds) {
        return coinAPIService.getAllAssets(assetIds);
    }
}
