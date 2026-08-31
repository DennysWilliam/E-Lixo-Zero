package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud;

public interface UpdateDao<T> {

    void updateInformation(final int id, final T entity);
}
