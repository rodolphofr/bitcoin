package br.com.rodltda.app.resource;

import static io.restassured.RestAssured.given;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import br.com.rodltda.infra.service.external.response.AssetResponseDTO;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BitcoinIntegrationTest {

    @Test
    public void should_retrieve_all_assets_by_asset_id() {
        var assetId = "BTC";

        var assetsResponse = given()
                .when()
                .get("/bitcoin/assets/" + assetId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AssetResponseDTO[].class);

        assertThat(assetsResponse, notNullValue());

        for (var asset : assetsResponse) {
            assertThat(asset, notNullValue());
            assertThat(asset.getAssetId(), equalTo(assetId));
            assertThat(asset.getName(), equalToIgnoringCase("bitcoin"));
            assertThat(asset.getTypeIsCrypto(), is(1));
            assertThat(asset.getPriceUsd(), notNullValue());
        }
    }

    @Test
    public void when_invalid_asset_id__then_not_found() {
        var invalidAssetId = "-1";

        given()
                .when()
                .get("bitcoin/assets/" + invalidAssetId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void should_query_multi_assets_in_the_same_time() {
        var assetIds = List.of("BTC", "BRL", "ETH");

        var assetsResponse = given()
                .when()
                .get("bitcoin/assets?asset_ids=" + assetIds.stream().collect(joining(",")))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AssetResponseDTO[].class);

        assertThat(assetsResponse, notNullValue());
        assertThat(assetsResponse, arrayWithSize(assetIds.size()));

        for (var assetId : assetIds) {
            assertThat(assetId, anyOf(
                    equalTo(assetsResponse[0].getAssetId()),
                    equalTo(assetsResponse[1].getAssetId()),
                    equalTo(assetsResponse[2].getAssetId())));
        }
    }
}
