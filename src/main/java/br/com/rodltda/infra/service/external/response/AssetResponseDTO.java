package br.com.rodltda.infra.service.external.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AssetResponseDTO {
    
    /**
     * The asset ID.
     */
    @JsonProperty("asset_id")
    private String assetId;

    /**
     * The name of the asset
     */
    @JsonProperty("name")
    private String name;

    /**
     * A value indicating whether the asset is a cryptocurrency
     */
    @JsonProperty("type_is_crypto")
    private Integer typeIsCrypto;

    /**
     * USD price of the asset
     */
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;

    @JsonProperty("data_start")
    private String dataStart;

    @JsonProperty("data_end")
    private String dataEnd;
}
