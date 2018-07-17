package br.ufrpe.artemis.Usuario.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.artemis.Pessoa.GUI.homeActivity;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Usuario.Dominio.Usuario;
import br.ufrpe.artemis.Usuario.Negocio.UsuarioNegocio;

import static br.ufrpe.artemis.R.*;

public class LoginActicity extends AppCompatActivity {
    private Button botaoRegistro;
    private EditText cpfLogin;
    private EditText senhaLogin;
    private Button botaoLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);

        setButtons();

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
    }

    private void setButtons(){
        cpfLogin = findViewById(R.id.cpfId);
        botaoLogar = findViewById(R.id.botaoLoginId);
        botaoRegistro = findViewById(R.id.botaoRegistroId);
        senhaLogin = findViewById(R.id.senhaId);
    }

    private void login(){
        if(validarLogin()){
            Intent intent = new Intent(LoginActicity.this, homeActivity.class);
            intent.putExtra("id", String.valueOf(recuperarId()));
            startActivity(intent);
        }else{
            Toast.makeText(LoginActicity.this,"Cpf/Senha incorreto(s).",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean validarLogin(){
        UsuarioNegocio negocio = new UsuarioNegocio();
        String cpf = cpfLogin.getText().toString().trim();
        String senha = senhaLogin.getText().toString().trim();
        return negocio.verificarUsuario(cpf, senha);
    }

    private int recuperarId(){
        UsuarioNegocio negocio = new UsuarioNegocio();
        String cpf = cpfLogin.getText().toString().trim();
        Usuario usuario = negocio.recuperarUsuario(cpf);
        return usuario.getId();
    }

    public void telaRegistro(){
        startActivity(new Intent(LoginActicity.this,CriarContaUsuarioActivity.class));
    }
}
