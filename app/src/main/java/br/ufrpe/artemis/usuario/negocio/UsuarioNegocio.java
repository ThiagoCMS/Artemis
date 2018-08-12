package br.ufrpe.artemis.usuario.negocio;


import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.endereco.negocio.EnderecoNegocio;
import br.ufrpe.artemis.infra.criptografia.Criptografia;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.usuario.dao.UsuarioDao;
import br.ufrpe.artemis.usuario.dominio.Usuario;

public class UsuarioNegocio {

    public void inserirUsuario(Usuario usuario, Pessoa pessoa, Endereco endereco){
        UsuarioDao banco = new UsuarioDao();
        banco.inserirUsuario(usuario);
        usuario = recuperarUsuario(usuario.getCpf());
        pessoa.setUsuario(usuario);
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        enderecoNegocio.inserirEndereco(endereco);
        endereco = enderecoNegocio.recuperarUltimoEndereco();
        pessoa.setEndereco(endereco);
        PessoaNegocio negocio = new PessoaNegocio();
        negocio.inserirPessoa(pessoa);
    }

    public boolean existeUsuario(String cpf){
        UsuarioDao banco = new UsuarioDao();
        return banco.existeUsuario(cpf);
    }

    public Usuario verificarUsuario(String cpf, String senha){
        UsuarioDao banco = new UsuarioDao();
        return banco.verificarLogin(cpf, senha);
    }

    public Usuario recuperarUsuario(String cpf){
        UsuarioDao banco = new UsuarioDao();
        return banco.recuperarUsuario(cpf);
    }

    public void alterarSenha(String senha){
        UsuarioDao banco = new UsuarioDao();
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString(senha);
        Sessao.instance.getPessoa().getUsuario().setSenha(senhaCriptografada);
        banco.alterarSenhaUsuario(Sessao.instance.getPessoa().getUsuario());
    }

    public void alterarSenha(Usuario usuario){
        UsuarioDao banco = new UsuarioDao();
        Criptografia criptografia = new Criptografia();
        usuario.setSenha(criptografia.criptografarString(usuario.getSenha()));
        banco.alterarSenhaUsuario(usuario);
    }

    public Usuario login(String cpf, String senha){
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString(senha);
        Usuario usuario = verificarUsuario(cpf, senhaCriptografada);
        if(usuario != null){
            PessoaNegocio pessoaNegocio = new PessoaNegocio();
            Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(usuario.getId());
            Sessao.instance.setPessoa(pessoa);
        }
        return usuario;
    }
}
