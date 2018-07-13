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
        nomeRegistro = findViewById(R.id.nomeRegistroId);
        cpfRegistro = findViewById(R.id.cpfRegistroId);
        emailRegistro = findViewById(R.id.emailId);
        senhaRegistro = findViewById(R.id.senhaRegistroId);
        confirmaSenhaRegistro = findViewById(R.id.confirmarSenhaRegistroId);
        botaoRegistrar = findViewById(R.id.botaoRegistrarId);


        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erro = false;
                if (nomeRegistro.getText().toString().isEmpty() ){
                    nomeRegistro.setError("Campo em branco");
                    erro = true;

                }if(cpfRegistro.getText().toString().isEmpty()) {
                    cpfRegistro.setError("Campo em branco");
                    erro = true;

                }else if (cpfRegistro.getText().toString().length() != 11){
                    cpfRegistro.setError("Cpf deve conter 11 digitos");
                    erro = true;

                }/*else if (!(converterInteiro(cpfRegistro.getText().toString()))){
                    cpfRegistro.setError("Cpf deve conter apenas numeros");
                    erro = true;

                }*/if(emailRegistro.getText().toString().isEmpty()){
                    emailRegistro.setError("Campo em branco");
                    erro = true;

                }if(senhaRegistro.getText().toString().length() <6) {
                    senhaRegistro.setError("A senha deve conter pelo menos 6 caracteres");
                    erro = true;

                }else if (confirmaSenhaRegistro.getText().toString().isEmpty()){
                    confirmaSenhaRegistro.setError("Campo em branco");

                }else if(!(senhaRegistro.getText().toString().equals(confirmaSenhaRegistro.getText().toString()))){
                    confirmaSenhaRegistro.setError("Senhas não correspondem");
                    erro = true;

                } if(!(erro)){
                    criarObjetoUsuario(cpfRegistro.getText().toString(), senhaRegistro.getText().toString());

                }
            }
        });

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


    public Boolean converterInteiro(String cpf) {
        try{
            int foo = Integer.parseInt(cpf);
            return true;

        }catch (Exception e){
            return false;
        }
    }

}