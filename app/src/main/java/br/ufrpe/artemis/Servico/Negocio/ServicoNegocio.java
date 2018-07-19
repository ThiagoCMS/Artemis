package br.ufrpe.artemis.Servico.Negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.Servico.Dao.ServicoDao;
import br.ufrpe.artemis.Servico.Dominio.Categoria;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Dominio.Subcategoria;

public class ServicoNegocio {

    public Servico criarServico(String nome, String texto, int idSubCategoria, int idUsuario){
        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setTexto(texto);
        servico.setIdSubCategoria(idSubCategoria);
        servico.setIdUsuario(idUsuario);
        return servico;
    }

    public void inserirServicoNoBanco(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.inserirNoBanco(servico);
    }

    public void deletarServicoDoBanco(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.deletarDoBanco(servico);
    }

    public ArrayList<Servico> listarSevicosSub(int idSub){
        ServicoDao banco = new ServicoDao();
        ArrayList<Servico> list = banco.recuperarDoBancoSub(idSub);
        return list;
    }

    public ArrayList<Servico> listarSevicosUs(int idUsuario){
        ServicoDao banco = new ServicoDao();
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

    public ArrayList<String> infoTelaServico(int id){
        ServicoDao banco = new ServicoDao();
        Servico servico = banco.recuperarServico(id);
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoa(servico.getIdUsuario());
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

    public ArrayList<Categoria> listarCategoria(){
        ServicoDao banco = new ServicoDao();
        ArrayList<Categoria> listaCategoria = banco.recuperarListaCategoria();
        return listaCategoria;
    }

    public ArrayList<Subcategoria> listarSubcategoria(int idcategoria){
        ServicoDao banco = new ServicoDao();
        ArrayList<Subcategoria> listaSubcategoria = banco.recuperarListaSubcategoria(idcategoria);
        return listaSubcategoria;
    }
}
