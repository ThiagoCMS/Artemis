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
import cursoandroid.com.semiprojeto.Usuario.Dao.UsuarioDao;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;
import cursoandroid.com.semiprojeto.Usuario.Negocio.UsuarioNegocio;

public class CriarContaUsuarioActivity extends AppCompatActivity {
    private EditText nomeRegistro;
    private EditText cpfRegistro;
    private EditText senhaRegistro;
    private EditText confirmaSenhaRegistro;
    private Button botaoRegistrar;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nomeRegistro = findViewById(R.id.nomeRegistroId);
        cpfRegistro = findViewById(R.id.cpfRegistroId);
        senhaRegistro = findViewById(R.id.senhaRegistroId);
        confirmaSenhaRegistro = findViewById(R.id.confirmarSenhaRegistroId);
        botaoRegistrar = findViewById(R.id.botaoRegistrarId);

        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificaSenha(senhaRegistro.getText().toString(), confirmaSenhaRegistro.getText().toString())) {
                    criarObjetoUsuario(cpfRegistro.getText().toString(), senhaRegistro.getText().toString());
                } else {
                    Toast.makeText(CriarContaUsuarioActivity.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
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

    public Boolean verificaSenha(String senha, String outraSenha) {
        if (senha.equals(outraSenha)) {
            return true;
        } else {
            return false;
        }
    }
}