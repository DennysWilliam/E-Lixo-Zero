package br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.crud;

public interface UpdateService<T> {

    boolean update(final int id, final T entity);
}
