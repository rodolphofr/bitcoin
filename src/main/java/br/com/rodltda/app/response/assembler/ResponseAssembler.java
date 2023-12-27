package br.com.rodltda.app.response.assembler;

@FunctionalInterface
public interface ResponseAssembler<T, V> {
    T assemble(V value);   
}
