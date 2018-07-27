package br.ufrpe.artemis.Pessoa.Negocio;

import br.ufrpe.artemis.Endereco.Negocio.EnderecoNegocio;
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

}
