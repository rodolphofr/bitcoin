package br.com.rodltda.app.response;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.rodltda.app.response.assembler.ResponseAssembler;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageResponse<T, V> {
    private final PanacheQuery<V> query;
    private final ResponseAssembler<T, V> assembler;

    public PageResponse(PanacheQuery<V> query, ResponseAssembler<T, V> assembler) {
        this.query = Objects.requireNonNull(query);
        this.assembler = Objects.requireNonNull(assembler);
    }

    public Collection<T> getItems() {
        return query.list().stream().map(assembler::assemble).collect(toList());
    }

    public long getTotal() {
        return query.count();
    }

    @JsonProperty("page")
    public int getPageIndex() {
        return query.page().index;
    }

    @JsonProperty("size")
    public int getPageSize() {
        return query.page().size;
    }

    @JsonProperty("has_next_page")
    public boolean isHasNextPage() {
        return query.hasNextPage();
    }
}
