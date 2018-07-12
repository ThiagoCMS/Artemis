package cursoandroid.com.semiprojeto.Usuario.Negocio;

import android.content.Context;
import android.icu.lang.UScript;

import cursoandroid.com.semiprojeto.Usuario.Dao.UsuarioDao;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;
import cursoandroid.com.semiprojeto.Usuario.GUI.LoginActicity;

public class UsuarioNegocio {

    public Usuario criarUsuario(String cpf, String senha){
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setSenha(senha);
        return usuario;
    }

    public void inserirUsuarioBanco(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        banco.inserirNoBanco(usuario);
    }

    public Boolean existeUsuario(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        return banco.existeNoBanco(usuario);
    }

    public Boolean verificarUsuario(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        return banco.lerDoBanco(usuario);
    }

    public Usuario recuperarUsuario(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        return banco.recuperarDoBanco(usuario);
    }

    public Usuario recuperarUsuario(int id, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        return banco.recuperarDoBanco(id);
    }

    public Boolean verificarSenha(int id, String senha, Context ctx){
        Usuario usuario = recuperarUsuario(id, ctx);
        if(usuario.getSenha().equals(senha)){
            return true;
        } return false;
    }

    public void alterarSenha(int id, String senha, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        Usuario usuario = recuperarUsuario(id, ctx);
        usuario.setSenha(senha);
        banco.alterarSenhaUsuario(usuario);

    }
}
