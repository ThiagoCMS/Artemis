package cursoandroid.com.semiprojeto.Pessoa.Negocio;

import android.content.Context;

import cursoandroid.com.semiprojeto.Pessoa.Dao.PessoaDao;
import cursoandroid.com.semiprojeto.Pessoa.Dominio.Pessoa;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;

public class PessoaNegocio {

    public Pessoa criarPessoa(Usuario usuario, String nome){
        Pessoa pessoa = new Pessoa();
        pessoa.setIdUsuario(usuario.getId());
        pessoa.setNome(nome);
        return pessoa;
    }

    public void inserirPessoaBanco(Pessoa pessoa, Context ctx){
        PessoaDao banco = new PessoaDao();
        banco.escreverNoBanco(ctx);
        banco.inserirNoBanco(pessoa);
    }

    /*public Boolean existeUsuario(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        return banco.existeNoBanco(usuario);
    }

    public Boolean verificarUsuario(Usuario usuario, Context ctx){
        UsuarioDao banco = new UsuarioDao();
        banco.escreverNoBanco(ctx);
        return banco.lerDoBanco(usuario);
    }*/

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
