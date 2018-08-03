package br.ufrpe.artemis.pessoa.negocio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.endereco.negocio.EnderecoNegocio;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.Aux;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dao.PessoaDao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class PessoaNegocio {

    public void inserirPessoaBanco(Pessoa pessoa){
        PessoaDao banco = new PessoaDao();
        banco.inserirNoBanco(pessoa);
    }

    public Pessoa recuperarPessoaPorUsuario(int id){
        PessoaDao banco = new PessoaDao();
        Pessoa pessoa = banco.recuperarDoBancoPorUsuario(id);
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        pessoa.setEndereco(enderecoNegocio.recuperarEndereco(pessoa.getEndereco().getId()));
        return pessoa;
    }

    public Pessoa recuperarPessoaPorId(int id){
        PessoaDao banco = new PessoaDao();
        Pessoa pessoa = banco.recuperarDoBanco(id);
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        pessoa.setEndereco(enderecoNegocio.recuperarEndereco(pessoa.getEndereco().getId()));
        return pessoa;
    }

    public void alterarPessoa(Pessoa pessoa) {
        PessoaDao banco = new PessoaDao();
        banco.alterarPerfil(pessoa);
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        enderecoNegocio.alterarEndereco(pessoa.getEndereco());
    }

    public void alterarFotoPerfil(Pessoa pessoa){
        PessoaDao banco = new PessoaDao();
        byte[] bytes = Aux.bitmapToByte(pessoa.getFotoPerfil());
        banco.alterarImagemPerfil(pessoa,bytes);
    }











}
