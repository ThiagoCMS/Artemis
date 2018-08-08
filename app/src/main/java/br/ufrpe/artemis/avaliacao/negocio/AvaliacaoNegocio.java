package br.ufrpe.artemis.avaliacao.negocio;

import java.util.List;

import br.ufrpe.artemis.avaliacao.dao.AvaliacaoDao;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.dominio.Classificacao;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;

public class AvaliacaoNegocio {
    public void inserirAvaliacao(Avaliacao avaliacao){
        AvaliacaoDao dao = new AvaliacaoDao();
        dao.inserirAvaliacao(avaliacao);
    }

    public List<Avaliacao> retornarAvaliacoes(int id){
        AvaliacaoDao banco = new AvaliacaoDao();
        return banco.recuperarNotas(id);
    }

    public Classificacao notasPrestadora(int id) {
        AvaliacaoDao banco = new AvaliacaoDao();
        List<Avaliacao> listaNotas = banco.recuperarNotas(id);
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        for (Avaliacao avaliacao: listaNotas) {
            avaliacao.setCliente(pessoaNegocio.recuperarPessoaPorId(avaliacao.getCliente().getId()));
            avaliacao.setPrestadora(pessoaNegocio.recuperarPessoaPorId(avaliacao.getPrestadora().getId()));
        }
        Classificacao classificacao = new Classificacao();
        double mediaPreco = 0;
        double mediaAtendimento = 0;
        double mediaQualidade = 0;
        for (Avaliacao obj : listaNotas) {
            mediaPreco += obj.getNotaPreco();
            mediaAtendimento += obj.getNotaAtendimento();
            mediaQualidade += obj.getNotaQualidade();
        }
        if (listaNotas.isEmpty()) {
            mediaPreco = mediaPreco / listaNotas.size();
            mediaAtendimento = mediaAtendimento / listaNotas.size();
            mediaQualidade = mediaQualidade / listaNotas.size();
        }
        classificacao.setMediaPreco(mediaPreco);
        classificacao.setMediaAtendimento(mediaAtendimento);
        classificacao.setMediaQualidade(mediaQualidade);
    return classificacao;
    }

    public double mediaGeral(int id){
        Classificacao classificacao = notasPrestadora(id);
        double total = classificacao.getMediaPreco() + classificacao.getMediaAtendimento() + classificacao.getMediaQualidade();
        return total/3;
    }
}
