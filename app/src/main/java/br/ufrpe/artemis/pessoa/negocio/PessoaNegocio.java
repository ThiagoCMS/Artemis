package br.ufrpe.artemis.pessoa.negocio;

import br.ufrpe.artemis.endereco.negocio.EnderecoNegocio;
import br.ufrpe.artemis.infra.Auxiliar;
import br.ufrpe.artemis.pessoa.dao.PessoaDao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.usuario.dominio.Usuario;
import br.ufrpe.artemis.usuario.negocio.UsuarioNegocio;

public class PessoaNegocio {

    public void inserirPessoa(Pessoa pessoa){
        PessoaDao banco = new PessoaDao();
        banco.inserirPessoa(pessoa);
    }

    public Pessoa recuperarPessoaPorUsuario(int id){
        PessoaDao banco = new PessoaDao();
        Pessoa pessoa = banco.recuperarPessoaPorUsuario(id);
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        pessoa.setEndereco(enderecoNegocio.recuperarEndereco(pessoa.getEndereco().getId()));
        return pessoa;
    }

    public Pessoa recuperarPessoaPorId(int id){
        PessoaDao banco = new PessoaDao();
        Pessoa pessoa = banco.recuperarPessoa(id);
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
        byte[] bytes = Auxiliar.bitmapToByte(pessoa.getFotoPerfil());
        banco.alterarImagemPerfil(pessoa,bytes);
    }

    public Pessoa recuperarPessoaPorCpf(String cpf){
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        Usuario usuario = usuarioNegocio.recuperarUsuario(cpf);
        Pessoa pessoa;
        if (usuario != null) {
            PessoaDao pessoaDao = new PessoaDao();
            pessoa = pessoaDao.recuperarPessoaPorUsuario(usuario.getId());
        } else {
            pessoa = null;
        }
        return pessoa;
    }
}
