package cursoandroid.com.semiprojeto.Usuario.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.com.semiprojeto.R;
import cursoandroid.com.semiprojeto.Usuario.Dominio.Usuario;
import cursoandroid.com.semiprojeto.Usuario.Negocio.UsuarioNegocio;

public class AlterarSenhaActivity extends AppCompatActivity {
    private EditText senhaAtual;
    private EditText novaSenha;
    private EditText confirmarNovaSenha;
    private Button botaoAlterarSenha;
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        senhaAtual = findViewById(R.id.senhaAtualId);
        novaSenha = findViewById(R.id.senhaNovaId);
        confirmarNovaSenha = findViewById(R.id.confirmarSenhaNovaId);
        botaoAlterarSenha = findViewById(R.id.buttonAlterarSenhaId);

        Bundle extra = getIntent().getExtras();
        idUsuario = Integer.parseInt(extra.getString("id"));

        botaoAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioNegocio negocio = new UsuarioNegocio();
                if(!negocio.verificarSenha(idUsuario, senhaAtual.getText().toString(), AlterarSenhaActivity.this)){
                    Toast.makeText(AlterarSenhaActivity.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                }else if(novaSenha.getText().toString().equals(confirmarNovaSenha.getText().toString())){
                    negocio.alterarSenha(idUsuario, novaSenha.getText().toString(), AlterarSenhaActivity.this);
                    Toast.makeText(AlterarSenhaActivity.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AlterarSenhaActivity.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
