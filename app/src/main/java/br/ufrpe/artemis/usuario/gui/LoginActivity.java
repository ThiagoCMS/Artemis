package br.ufrpe.artemis.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.ufrpe.artemis.pessoa.gui.HomeActivity;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.usuario.dominio.Usuario;
import br.ufrpe.artemis.usuario.negocio.UsuarioNegocio;
import static br.ufrpe.artemis.R.*;

public class LoginActivity extends AppCompatActivity {
    private Button botaoRegistro;
    private EditText cpfLogin;
    private EditText senhaLogin;
    private Button botaoLogar;
    private Button botaoEsqueci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);
        setView();
        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
       botaoRegistro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               telaRegistro();
           }
       });
       botaoEsqueci.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               telaEsqueci();
           }
       });
    }

    private void setView(){
        cpfLogin = findViewById(R.id.cpfId);
        botaoLogar = findViewById(R.id.botaoLoginId);
        botaoRegistro = findViewById(R.id.botaoRegistroId);
        senhaLogin = findViewById(R.id.senhaId);
        botaoEsqueci = findViewById(id.botaoEsqueciId);
    }

    private void login(){
        if(logar() != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }else{
            Toast.makeText(LoginActivity.this,"Cpf/Senha incorreto(s).",Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario logar(){
        UsuarioNegocio negocio = new UsuarioNegocio();
        String cpf = cpfLogin.getText().toString().trim();
        String senha = senhaLogin.getText().toString();
        return negocio.login(cpf, senha);
    }

    public void telaRegistro(){
        startActivity(new Intent(LoginActivity.this,CriarContaUsuarioActivity.class));
    }
    public void telaEsqueci(){
        startActivity(new Intent(LoginActivity.this, EsqueciSenhaActivity.class));
    }
}
