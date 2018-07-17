package br.ufrpe.artemis.Pessoa.Negocio;

import android.content.Context;

import br.ufrpe.artemis.Pessoa.Dao.PessoaDao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class PessoaNegocio {

    public void inserirPessoaBanco(Pessoa pessoa, Context ctx){
        PessoaDao banco = new PessoaDao();
        banco.escreverNoBanco(ctx);
        banco.inserirNoBanco(pessoa);
    }

    public Pessoa recuperarPessoa(Usuario usuario, Context ctx){
        PessoaDao banco = new PessoaDao();
        banco.escreverNoBanco(ctx);
        return banco.recuperarDoBanco(usuario);
    }

    public Pessoa recuperarPessoa(int id, Context ctx){
        PessoaDao banco = new PessoaDao();
        banco.escreverNoBanco(ctx);
        return banco.recuperarDoBanco(id);
    }
}
