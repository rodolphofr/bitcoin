package br.com.rodltda.infra.service.external;

import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.rodltda.infra.service.external.response.AssetResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient(configKey = "coinApi")
@ClientHeaderParam(name = "X-CoinAPI-Key", value = "${coinApi.api-key}") // TODO: jwt
public interface CoinAPIService {
    
    /**
     * https://docs.coinapi.io/market-data/rest-api/metadata/list-all-assets-by-asset-id
     * 
     * @param assetId - The asset ID
     * @return all assets by asset ID
     */
    @GET
    @Path("/assets/{asset_id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<AssetResponseDTO> getAllAssetsByAssetId(@PathParam("asset_id") String assetId);

    /**
     * https://docs.coinapi.io/market-data/rest-api/metadata/list-all-assets
     * 
     * @param assetIdList - asset identifiers used to filter response
     * @return retrieves all assets.
     */
    @GET
    @Path("/assets")
    @Produces(MediaType.APPLICATION_JSON)
    List<AssetResponseDTO> getAllAssets(@QueryParam("filter_asset_id") Set<String> assetIds);
}
