package br.fai.e.lixo.zero.ports_and_adapters.port.service.crud;

public interface CrudService<T> extends CreateService<T>, DeleteService, FindService<T>, UpdateService<T> {
}