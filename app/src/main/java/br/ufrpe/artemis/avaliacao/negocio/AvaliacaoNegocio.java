package br.ufrpe.artemis.avaliacao.negocio;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ufrpe.artemis.avaliacao.dao.AvaliacaoDao;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.dominio.Classificacao;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
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
        if (!listaNotas.isEmpty()) {
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

    public List<Pessoa> retornarPrestadorasAvaliadas(){
        AvaliacaoDao dao = new AvaliacaoDao();
        List<Pessoa> list = dao.retornarPrestadorasAvaliadas();
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int i = 0;
        for(Pessoa p: list){
            p = pessoaNegocio.recuperarPessoaPorId(p.getId());
            list.remove(i);
            list.add(i, p);
            i++;
        }
        return list;
    }

    public Map<Pessoa, HashMap<Pessoa, Double>> retornarMapAvaliacoes(){
        AvaliacaoDao dao = new AvaliacaoDao();
        List<Avaliacao> list = dao.retornarTudo();
        for(Avaliacao a: list){
            for(Avaliacao b: list){
                if(a != null && b != null) {
                    boolean id = a.getId() != b.getId();
                    boolean cli = a.getCliente().getId() == b.getCliente().getId();
                    boolean prest = a.getPrestadora().getId() == b.getPrestadora().getId();
                    if (id && cli && prest) {
                        list.set(list.indexOf(a), null);
                        break;
                    }
                }
            }
        }
        while(list.contains(null)){
            list.remove(null);
        }
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Map<Pessoa, HashMap<Pessoa, Double>> map = new HashMap<>();
        for(Avaliacao avaliacao: list){
            if(avaliacao.getCliente().getId() == Sessao.instance.getPessoa().getId()){
                avaliacao.setCliente(Sessao.instance.getPessoa());
            }else {
                avaliacao.setCliente(pessoaNegocio.recuperarPessoaPorId(avaliacao.getCliente().getId()));
            }
            avaliacao.setPrestadora(pessoaNegocio.recuperarPessoaPorId(avaliacao.getPrestadora().getId()));
            map.put(avaliacao.getCliente(), new HashMap<Pessoa, Double>());
        }
        for(Avaliacao avaliacao: list){
            double media = (avaliacao.getNotaAtendimento() + avaliacao.getNotaPreco() + avaliacao.getNotaQualidade())/3;
            map.get(avaliacao.getCliente()).put(avaliacao.getPrestadora(), media);
        }
        return map;
    }
}
