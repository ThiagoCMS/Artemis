package br.ufrpe.artemis.avaliacao.negocio;

import java.util.ArrayList;
import br.ufrpe.artemis.avaliacao.dao.AvaliacaoDao;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.dominio.Classificacao;
import br.ufrpe.artemis.infra.Sessao;

public class AvaliacaoNegocio {
    public void inserirAvaliacao(Avaliacao avaliacao){
        AvaliacaoDao dao = new AvaliacaoDao();
        dao.inserirAvaliacao(avaliacao);
    }

    public ArrayList<Avaliacao> retornarAvaliacoes(int id){
        AvaliacaoDao banco = new AvaliacaoDao();
        return banco.recuperarNotas(id);
    }

    public Classificacao notasPrestadora(int id) {
        AvaliacaoDao banco = new AvaliacaoDao();
        ArrayList<Avaliacao> listaNotas = banco.recuperarNotas(id);
        Classificacao classificacao = new Classificacao();
        double mediaPreço = 0;
        double mediaAtendimento = 0;
        double mediaQualidade = 0;
        for (Avaliacao obj : listaNotas) {
            mediaPreço += obj.getNotaPreco();
            mediaAtendimento += obj.getNotaAtendimento();
            mediaQualidade += obj.getNotaQualidade();
        }
        if (listaNotas.size() > 0) {
            mediaPreço = mediaPreço / listaNotas.size();
            mediaAtendimento = mediaAtendimento / listaNotas.size();
            mediaQualidade = mediaQualidade / listaNotas.size();
        }
        classificacao.setMediaPreco(mediaPreço);
        classificacao.setMediaAtendimento(mediaAtendimento);
        classificacao.setMediaQualidade(mediaQualidade);
    return classificacao;
    }

    public double mediaGeral(int id){
        Classificacao classificacao = notasPrestadora(id);
        double total = classificacao.getMediaPreco() + classificacao.getMediaAtendimento() + classificacao.getMediaQualidade();
        double media = total/3;
        return media;
    }
}
