package br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.ponto;

import br.fai.lds.e_lixo_zero.domain.PontoColetaModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.crud.CrudService;

import java.util.List;

public interface PontoColetaService extends CrudService<PontoColetaModel> {
    List<PontoColetaModel> findByCidade(final String cidade);
}
