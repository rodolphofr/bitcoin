package br.com.rodltda.app.request.converter;

import static java.util.Arrays.stream;

import java.util.Map;
import java.util.regex.Pattern;

import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ext.ParamConverter;

public class SortQueryParamConverter implements ParamConverter<Sort> {
    private static final String COMMA = ",";
    private static final String TWO_BULLET_POINTS = ":";

    private static final Map<String, Direction> ORDER_MAP = 
        Map.of("asc", Direction.Ascending, "desc", Direction.Descending);

    private static final Pattern SORT_QUERY_PATTERN = 
        Pattern.compile("^((asc|desc):\\w+)(,(asc|desc):\\w+)*");

    @Override
    public Sort fromString(String value) {
        if (value.isBlank())
            return Sort.empty();

        if (!SORT_QUERY_PATTERN.matcher(value).find())
            throw new BadRequestException("The sort query has been invalidated.");

        final Sort sort = Sort.empty();

        stream(value.split(COMMA))
                .map(param -> param.split(TWO_BULLET_POINTS))
                .forEach(param -> {
                    var order = param[0];
                    var column = param[1];

                    sort.and(column, ORDER_MAP.get(order));
                });

        return sort;
    }

    @Override
    public String toString(Sort sort) {
        throw new UnsupportedOperationException("No serializer implemeted");
    }
}
