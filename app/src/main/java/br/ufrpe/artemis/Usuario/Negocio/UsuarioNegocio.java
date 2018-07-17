package br.ufrpe.artemis.Usuario.Negocio;

import android.content.Context;

import br.ufrpe.artemis.Infra.ArtemisApp;
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

    public Boolean existeUsuario(Usuario usuario){
        UsuarioDao banco = new UsuarioDao();
        return banco.existeNoBanco(usuario);
    }

    public Boolean verificarUsuario(String cpf, String senha){
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
        if(usuario.getSenha().equals(senha)){
            return true;
        } return false;
    }

    public void alterarSenha(int id, String senha){
        UsuarioDao banco = new UsuarioDao();
        Usuario usuario = recuperarUsuario(id);
        usuario.setSenha(senha);
        banco.alterarSenhaUsuario(usuario);
    }

    public void login(String cpf){
        Usuario usuario = recuperarUsuario(cpf);
        Sessao.instance.setUsuario(usuario);

    }
}
