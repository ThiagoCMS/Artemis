package cursoandroid.com.semiprojeto.Usuario.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.com.semiprojeto.Pessoa.Dominio.Pessoa;
import cursoandroid.com.semiprojeto.Pessoa.Negocio.PessoaNegocio;
import cursoandroid.com.semiprojeto.R;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;
import cursoandroid.com.semiprojeto.Usuario.Negocio.UsuarioNegocio;

public class CriarContaUsuarioActivity extends AppCompatActivity {
    private EditText nomeRegistro;
    private EditText cpfRegistro;
    private EditText emailRegistro;
    private EditText senhaRegistro;
    private EditText confirmaSenhaRegistro;
    private Button botaoRegistrar;
    private Boolean erro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta_usuario);
        nomeRegistro = findViewById(R.id.cpfId);
        cpfRegistro = findViewById(R.id.cpfRegistroId);
        emailRegistro = findViewById(R.id.emailRegistroId);
        senhaRegistro = findViewById(R.id.senhaRegistroId);
        confirmaSenhaRegistro = findViewById(R.id.confirmarSenhaRegistroId);
        botaoRegistrar = findViewById(R.id.botaoRegistrarId);


        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos()){
                    criarObjetoUsuario(cpfRegistro.getText().toString(), senhaRegistro.getText().toString());
                }
            }
        });

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

    private void criarObjetoUsuario(String cpf, String senha) {
        UsuarioNegocio negocio = new UsuarioNegocio();
        Usuario usuario = negocio.criarUsuario(cpf, senha);
        if (!negocio.existeUsuario(usuario, this)) {
            negocio.inserirUsuarioBanco(usuario, this);
            usuario = negocio.recuperarUsuario(usuario, this);
            PessoaNegocio negocio1 = new PessoaNegocio();
            Pessoa pessoa = negocio1.criarPessoa(usuario, nomeRegistro.getText().toString());
            negocio1.inserirPessoaBanco(pessoa, this);
            Toast.makeText(CriarContaUsuarioActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            CriarContaUsuarioActivity.this.finish();
        } else {
            Toast.makeText(CriarContaUsuarioActivity.this, "CPF já registrado", Toast.LENGTH_SHORT).show();
        }

    }

}