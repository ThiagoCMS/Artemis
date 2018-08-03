package br.ufrpe.artemis.avaliacao.negocio;

import br.ufrpe.artemis.avaliacao.dao.AvaliacaoDao;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;

public class AvaliacaoNegocio {

    public void inserirAvaliacao(Avaliacao avaliacao){
        AvaliacaoDao dao = new AvaliacaoDao();
        dao.inserirAvaliacao(avaliacao);
    }


}
