package br.ufrpe.artemis.Usuario.Negocio;

import android.content.Context;

import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.Criptografia.Criptografia;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.Usuario.Dao.UsuarioDao;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class UsuarioNegocio {

    public void inserirUsuarioBanco(Usuario usuario, Pessoa pessoa){
        UsuarioDao banco = new UsuarioDao();
        banco.inserirNoBanco(usuario);
        usuario = recuperarUsuario(usuario.getCpf());
        pessoa.setIdUsuario(usuario.getId());
        PessoaNegocio negocio = new PessoaNegocio();
        negocio.inserirPessoaBanco(pessoa);
    }

    public boolean existeUsuario(String cpf){
        UsuarioDao banco = new UsuarioDao();
        return banco.existeNoBanco(cpf);
    }

    public Usuario verificarUsuario(String cpf, String senha){
        UsuarioDao banco = new UsuarioDao();
        return banco.verificarLogin(cpf, senha);
    }

    public Usuario recuperarUsuario(String cpf){
        UsuarioDao banco = new UsuarioDao();
        return banco.recuperarDoBanco(cpf);
    }

    public Usuario recuperarUsuario(int id){
        UsuarioDao banco = new UsuarioDao();
        return banco.recuperarDoBanco(id);
    }

    public Boolean verificarSenha(int id, String senha){
        Usuario usuario = recuperarUsuario(id);
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString(senha);
        if(usuario.getSenha().equals(senhaCriptografada)){
            return true;
        } return false;
    }

    public void alterarSenha(String senha){
        UsuarioDao banco = new UsuarioDao();
        Usuario usuario = Sessao.instance.getUsuario();
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString(senha);
        usuario.setSenha(senhaCriptografada);
        banco.alterarSenhaUsuario(usuario);
    }

    public Usuario login(String cpf, String senha){
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString(senha);
        Usuario usuario = verificarUsuario(cpf, senhaCriptografada);
        if(usuario != null){
            Sessao.instance.setUsuario(usuario);
        }
        return usuario;
    }
}
