package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud;

public interface CreateDao<T> {

    int add(final T entity);
}
