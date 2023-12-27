package br.com.rodltda.app.request.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.quarkus.panache.common.Sort;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

@Provider
public class QueryParamConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType == Sort.class)
            return (ParamConverter<T>) new SortQueryParamConverter();

        return null;
    }
}
