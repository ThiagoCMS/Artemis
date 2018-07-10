package cursoandroid.com.semiprojeto.Usuario.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cursoandroid.com.semiprojeto.Pessoa.GUI.homeActivity;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;
import cursoandroid.com.semiprojeto.Usuario.Negocio.UsuarioNegocio;

import static cursoandroid.com.semiprojeto.R.*;

public class LoginActicity extends AppCompatActivity {
    private TextView botaoEsqueceu;
    private EditText login;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        login = findViewById(id.nomeRegistroId);
        botaoLogar = findViewById(id.botaoLoginId);
        botaoEsqueceu = findViewById(id.botaoRegistroId);
        senha = findViewById(id.senhaId);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarLogin(login.getText().toString(), senha.getText().toString())){
                    Toast.makeText(LoginActicity.this,"Login efetuado com suscesso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new Intent(LoginActicity.this,homeActivity.class));
                    intent.putExtra("id", String.valueOf(recuperarId()));
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActicity.this,"Cpf/Senha incorreto(s).",Toast.LENGTH_SHORT).show();
                }
            }
        });

       botaoEsqueceu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LoginActicity.this,CriarContaUsuarioActivity.class));
           }
       });
    }

    public Boolean verificarLogin(String cpf, String senha){
        UsuarioNegocio negocio = new UsuarioNegocio();
        Usuario usuario = negocio.criarUsuario(cpf, senha);
        return negocio.verificarUsuario(usuario, this);
    }

    private int recuperarId(){
        UsuarioNegocio negocio = new UsuarioNegocio();
        Usuario usuario = negocio.criarUsuario(login.getText().toString(), senha.getText().toString());
        Usuario usuario1 = negocio.recuperarUsuario(usuario, this);
        return usuario1.getId();
    }
}
