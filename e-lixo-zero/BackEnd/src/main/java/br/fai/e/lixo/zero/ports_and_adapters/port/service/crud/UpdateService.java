package br.fai.e.lixo.zero.ports_and_adapters.port.service.crud;

public interface UpdateService<T> {

    boolean update(final int id, final T entity);
}