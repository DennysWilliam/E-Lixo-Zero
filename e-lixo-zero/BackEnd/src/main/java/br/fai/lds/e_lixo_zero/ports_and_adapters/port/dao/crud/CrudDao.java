package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud;

public interface CrudDao<T> extends CreateDao<T>, DeleteDao,ReadDao<T>, UpdateDao<T> {
}
