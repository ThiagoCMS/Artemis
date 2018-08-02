package br.ufrpe.artemis.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.usuario.dominio.Usuario;
import br.ufrpe.artemis.usuario.negocio.UsuarioNegocio;

public class AlterarSenhaActivity extends AppCompatActivity {
    private EditText senhaAtual;
    private EditText novaSenha;
    private EditText confirmarNovaSenha;
    private Button botaoAlterarSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
        senhaAtual = findViewById(R.id.senhaAtualId);
        novaSenha = findViewById(R.id.senhaNovaId);
        confirmarNovaSenha = findViewById(R.id.confirmarSenhaNovaId);
        botaoAlterarSenha = findViewById(R.id.buttonAlterarSenhaId);

        botaoAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioNegocio negocio = new UsuarioNegocio();
                if(!verificarSenha()){
                    Toast.makeText(AlterarSenhaActivity.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                }else if(compararSenhas()){
                    negocio.alterarSenha(novaSenha.getText().toString());
                    Toast.makeText(AlterarSenhaActivity.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AlterarSenhaActivity.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean verificarSenha(){
        Usuario usuario = Sessao.instance.getUsuario();
        String senha = senhaAtual.getText().toString();
        if(usuario.getSenha().equals(senha)){
            return true;
        }
        return false;
    }

    private boolean compararSenhas(){
        String senha = novaSenha.getText().toString();
        String senha1 = confirmarNovaSenha.getText().toString();
        if(senha.equals(senha1)){
            return true;
        }
        return false;
    }
}
