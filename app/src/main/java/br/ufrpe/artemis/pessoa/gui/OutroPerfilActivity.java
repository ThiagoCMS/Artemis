package br.ufrpe.artemis.pessoa.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class OutroPerfilActivity extends AppCompatActivity {
    private TextView nome;
    private TextView telefone;
    private TextView email;
    private TextView cidade;
    private Button botaoAnuncios;
    private Button botaoComentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outro_perfil);
        setTela();
    }

    private void setTela(){
        nome = findViewById(R.id.nomeUsuarioPrestadoraId);
        botaoAnuncios = findViewById(R.id.botaoanuncioPrestId);
        botaoComentarios = findViewById(R.id.botaoComentariosPrestId);
        email = findViewById(R.id.emailPrestadoraId);
        cidade = findViewById(R.id.enderecoPrestadoraId);
        telefone = findViewById(R.id.telefonePrestadoraId);
        setPessoa();
    }

    private void setPessoa(){
        Bundle extras = getIntent().getExtras();
        int idUsuario = extras.getInt("id");
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        nome.setText(pessoa.getNome());
        email.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());
        cidade.setText(pessoa.getEndereco().getCidade());
    }
}
