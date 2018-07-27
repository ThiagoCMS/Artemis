package br.ufrpe.artemis.Pessoa.Negocio;

import br.ufrpe.artemis.Pessoa.Dao.PessoaDao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
//import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class PessoaNegocio {

    public void inserirPessoaBanco(Pessoa pessoa){
        PessoaDao banco = new PessoaDao();
        banco.inserirNoBanco(pessoa);
    }

    public Pessoa recuperarPessoaPorUsuario(int id){
        PessoaDao banco = new PessoaDao();
        return banco.recuperarDoBancoPorUsuario(id);
    }

    public Pessoa recuperarPessoaPorId(int id){
        PessoaDao banco = new PessoaDao();
        return banco.recuperarDoBanco(id);
    }

    public void alterarPessoa(Pessoa pessoa) {
        PessoaDao banco = new PessoaDao();
        banco.alterarPerfil(pessoa);
    }

}
