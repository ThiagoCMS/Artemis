package br.ufrpe.artemis.Usuario.Negocio;

import android.content.Context;

import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.Usuario.Dao.UsuarioDao;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;

public class UsuarioNegocio {

    public void inserirUsuarioBanco(Usuario usuario, Pessoa pessoa, Context ctx){
        UsuarioDao banco = new UsuarioDao(ctx);
        banco.inserirNoBanco(usuario);
        usuario = recuperarUsuario(usuario.getCpf(), ctx);
        pessoa.setIdUsuario(usuario.getId());
        PessoaNegocio negocio = new PessoaNegocio();
        negocio.inserirPessoaBanco(pessoa, ctx);
    }

    public Boolean existeUsuario(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao(ctx);
        return banco.existeNoBanco(usuario);
    }

    public Boolean verificarUsuario(String cpf, String senha, Context ctx){
        UsuarioDao banco = new UsuarioDao(ctx);
        return banco.verificarLogin(cpf, senha);
    }

    public Usuario recuperarUsuario(String cpf, Context ctx){
        UsuarioDao banco = new UsuarioDao(ctx);
        return banco.recuperarDoBanco(cpf);
    }

    public Usuario recuperarUsuario(int id, Context ctx){
        UsuarioDao banco = new UsuarioDao(ctx);
        return banco.recuperarDoBanco(id);
    }

    public Boolean verificarSenha(int id, String senha, Context ctx){
        Usuario usuario = recuperarUsuario(id, ctx);
        if(usuario.getSenha().equals(senha)){
            return true;
        } return false;
    }

    public void alterarSenha(int id, String senha, Context ctx){
        UsuarioDao banco = new UsuarioDao(ctx);
        Usuario usuario = recuperarUsuario(id, ctx);
        usuario.setSenha(senha);
        banco.alterarSenhaUsuario(usuario);

    }
}
