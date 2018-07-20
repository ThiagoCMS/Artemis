package br.ufrpe.artemis.Usuario.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.artemis.Infra.Criptografia.Criptografia;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;
import br.ufrpe.artemis.Usuario.Negocio.UsuarioNegocio;

public class CriarContaUsuarioActivity extends AppCompatActivity {
    private EditText nomeRegistro;
    private EditText cpfRegistro;
    private EditText emailRegistro;
    private EditText senhaRegistro;
    private EditText confirmaSenhaRegistro;
    private Button botaoRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta_usuario);

        setView();

        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCadastro();
            }
        });

    }

    private void setView(){
        nomeRegistro = findViewById(R.id.nomeRegistroId);
        cpfRegistro = findViewById(R.id.cpfRegistroId);
        emailRegistro = findViewById(R.id.emailRegistroId);
        senhaRegistro = findViewById(R.id.senhaRegistroId);
        confirmaSenhaRegistro = findViewById(R.id.confirmarSenhaRegistroId);
        botaoRegistrar = findViewById(R.id.botaoRegistrarId);
    }

    private void validarCadastro() {
        if (validarCampos()) {
            criarConta();
        }
    }

    private boolean validarCampos(){
        boolean erro = true;
        if(validarCpf()){
            erro = false;
        }
        if(validarNome()){
            erro = false;
        }
        if(validarEmail()){
            erro = false;
        }
        if(validarSenha()){
            erro = false;
        }
        return erro;
    }

    private boolean validarCpf(){
        boolean erro = false;
        String cpf = cpfRegistro.getText().toString();
        if(cpf.isEmpty()){
            erro = true;
            cpfRegistro.setError("Campo em branco");
        }else if(cpf.length() != 11){
            erro = true;
            cpfRegistro.setError("Cpf inválido");
        }
        return erro;
    }

    private boolean validarNome(){
        boolean erro = false;
        String nome = nomeRegistro.getText().toString();
        if(nome.isEmpty()){
            erro = true;
            nomeRegistro.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarEmail(){
        boolean erro = false;
        String email = emailRegistro.getText().toString();
        if(email.isEmpty()){
            erro = true;
            emailRegistro.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarSenha(){
        boolean erro = false;
        String senha = senhaRegistro.getText().toString();
        String confirmarSenha = confirmaSenhaRegistro.getText().toString();
        if(senha.isEmpty() && confirmarSenha.isEmpty()){
            erro = true;
            senhaRegistro.setError("Campo em branco");
            confirmaSenhaRegistro.setError("Campo em branco");
        } else if(senha.isEmpty()){
            erro = true;
            senhaRegistro.setError("Campo em branco");
        }else if(senha.length() < 6){
            erro = true;
            senhaRegistro.setError("A senha deve conter pelo menos 6 caracteres");
        }else if(!senha.equals(confirmarSenha)){
            erro = true;
            senhaRegistro.setError("As senhas devem ser iguais");
            confirmaSenhaRegistro.setError("As senhas devem ser iguais");
        }
        return erro;
    }

    private void criarConta(){
        UsuarioNegocio negocio = new UsuarioNegocio();
        Usuario usuario = new Usuario();
        String cpf = cpfRegistro.getText().toString().trim();
        String senha = senhaRegistro.getText().toString();
        usuario.setSenha(senha);
        usuario.setCpf(cpf);
        if(negocio.existeUsuario(cpf)){
            Toast.makeText(this, "Cpf já registrado", Toast.LENGTH_SHORT).show();
        }else{
            inserirUsuario(cpf, senha);
        }
    }

    private void inserirUsuario(String cpf, String  senha){
        String nome = nomeRegistro.getText().toString().trim();
        String email = emailRegistro.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarString(senha);
        usuario.setSenha(senhaCriptografada);
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);
        UsuarioNegocio negocio = new UsuarioNegocio();
        negocio.inserirUsuarioBanco(usuario, pessoa);
        Toast.makeText(CriarContaUsuarioActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
        CriarContaUsuarioActivity.this.finish();
    }
}