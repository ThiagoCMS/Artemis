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
        PessoaNegocio negocio = new PessoaNegocio();
        Pessoa pessoa = negocio.recuperarPessoa(idUsuario);
        ArrayList<Servico> list = banco.recuperarDoBancoUs(pessoa.getId());
        return list;
    }

    public Servico infoTelaServico(int id){
        ServicoDao banco = new ServicoDao();
        Servico servico = banco.recuperarServico(id);
        return servico;
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

    public Servico pegarServico(int id){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarServico(id);
    }

    public void editarServico(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.atualizarServico(servico);
    }

    public Pessoa recuperarPessoa(int id){
        PessoaNegocio negocio = new PessoaNegocio();
        return negocio.recuperarPessoa(id);
    }
}
