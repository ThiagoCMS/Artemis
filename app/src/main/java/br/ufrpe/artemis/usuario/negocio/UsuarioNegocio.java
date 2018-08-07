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

    public void inserirUsuarioBanco(Usuario usuario, Pessoa pessoa, Endereco endereco){
        UsuarioDao banco = new UsuarioDao();
        banco.inserirNoBanco(usuario);
        usuario = recuperarUsuario(usuario.getCpf());
        pessoa.setUsuario(usuario);
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        enderecoNegocio.inserirEndereco(endereco);
        endereco = enderecoNegocio.recuperarUltimoEndereco();
        pessoa.setEndereco(endereco);
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
