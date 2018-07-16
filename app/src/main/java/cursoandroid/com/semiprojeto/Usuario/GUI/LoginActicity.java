package cursoandroid.com.semiprojeto.Usuario.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.com.semiprojeto.Pessoa.GUI.homeActivity;
import cursoandroid.com.semiprojeto.R;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;
import cursoandroid.com.semiprojeto.Usuario.Negocio.UsuarioNegocio;

import static cursoandroid.com.semiprojeto.R.*;

public class LoginActicity extends AppCompatActivity {
    private Button botaoRegistro;
    private EditText cpf;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);

        cpf = findViewById(R.id.cpfId);
        botaoLogar = findViewById(R.id.botaoLoginId);
        botaoRegistro = findViewById(R.id.botaoRegistroId);
        senha = findViewById(R.id.senhaId);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarLogin(cpf.getText().toString(), senha.getText().toString())){
                    Toast.makeText(LoginActicity.this,"Login efetuado com suscesso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new Intent(LoginActicity.this,homeActivity.class));
                    intent.putExtra("id", String.valueOf(recuperarId()));
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActicity.this,"Cpf/Senha incorreto(s).",Toast.LENGTH_SHORT).show();
                }
            }
        });

       botaoRegistro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LoginActicity.this,CriarContaUsuarioActivity.class));
           }
       });
    }

    public Boolean verificarLogin(String cpf, String senha){
        UsuarioNegocio negocio = new UsuarioNegocio();
        usuario = negocio.criarUsuario(cpf, senha);
        return negocio.verificarUsuario(usuario, this);
    }

    private int recuperarId(){
        return usuario.getId();
    }
}
