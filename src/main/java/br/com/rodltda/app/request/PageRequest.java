package br.com.rodltda.app.request;

import org.jboss.resteasy.reactive.RestQuery;

import io.quarkus.panache.common.Sort;
import lombok.Getter;

@Getter
public class PageRequest {
    
    @RestQuery("size")
    private int size;

    @RestQuery("page")
    private int page;

    @RestQuery("sort")
    private Sort sort;
}
