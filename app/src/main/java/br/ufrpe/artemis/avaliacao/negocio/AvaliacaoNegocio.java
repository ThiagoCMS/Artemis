package br.ufrpe.artemis.avaliacao.negocio;

import java.util.ArrayList;
import br.ufrpe.artemis.avaliacao.dao.AvaliacaoDao;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;

public class AvaliacaoNegocio {
    public void inserirAvaliacao(Avaliacao avaliacao){
        AvaliacaoDao dao = new AvaliacaoDao();
        dao.inserirAvaliacao(avaliacao);
    }

    public ArrayList<Avaliacao> notasPrestadora(int id){
        AvaliacaoDao banco = new AvaliacaoDao();
        ArrayList<Avaliacao> lista = banco.recuperarNotas(id);
        return lista;
    }

}
