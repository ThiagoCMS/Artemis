package br.ufrpe.artemis.Servico.Negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.Servico.Dao.ServicoDao;
import br.ufrpe.artemis.Servico.Dominio.Servico;

public class ServicoNegocio {

    public Servico criarServico(String nome, String texto, int idSubCategoria, int idUsuario){
        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setTexto(texto);
        servico.setIdSubCategoria(idSubCategoria);
        servico.setIdUsuario(idUsuario);
        return servico;
    }

    public void inserirServicoNoBanco(Servico servico, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        banco.inserirNoBanco(servico);
    }

    public void deletarServicoDoBanco(Servico servico, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        banco.deletarDoBanco(servico);
    }

    public ArrayList<Servico> listarSevicosSub(int idSub, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        ArrayList<Servico> list = banco.recuperarDoBancoSub(idSub);
        return list;
    }

    public ArrayList<Servico> listarSevicosUs(int idUsuario, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        ArrayList<Servico> list = banco.recuperarDoBancoUs(idUsuario);
        return list;
    }

    public ArrayList<String> listarServicoStr(ArrayList<Servico> list){
        ArrayList<String> list1 = new ArrayList<String>();
        for(int i = 0; i < list.size() ; i++){
            list1.add(list.get(i).getNome());
        }
        return list1;
    }

    public ArrayList<String> infoTelaServico(int id, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        Servico servico = banco.recuperarServico(id);
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoa(servico.getIdUsuario(), ctx);
        String subCategoria = banco.recuperarSubCategoria(servico.getIdSubCategoria());
        String categoria = banco.recuperarCategoria(servico.getIdSubCategoria());
        ArrayList<String> list = new ArrayList<>();
        list.add(servico.getNome());
        list.add(servico.getTexto());
        list.add(categoria);
        list.add(subCategoria);
        list.add(pessoa.getNome());
        return list;
    }
}
