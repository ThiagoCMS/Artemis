package br.ufrpe.artemis.pessoa.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.pessoa.dominio.Classificacao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class FormularioActivity extends AppCompatActivity {
    private RatingBar precoServico;
    private RatingBar qualidadeServico;
    private RatingBar atendimentoServico;
    private TextView comentarioServico;
    private Button enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        setTela();
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastro();
            }
        });
    }

    private void setTela(){
        precoServico = findViewById(R.id.precoRatingId);
        qualidadeServico = findViewById(R.id.qualidadeRatingId);
        atendimentoServico = findViewById(R.id.atendimentoRatingId);
//        comentarioServico = findViewById(R.id.comentariosId);
//        enviar = findViewById(R.id.enviarId);

    }

    private void cadastro(){
        Classificacao classificacao = new Classificacao();
        classificacao.setNotaPreco(precoServico.getRating());
        classificacao.setNotaQualidade(qualidadeServico.getRating());
        classificacao.setNotaAtendimento(atendimentoServico.getRating());
        classificacao.setComentario(comentarioServico.getText().toString().trim());
        Pessoa pessoa = new Pessoa ();
        Toast.makeText(ArtemisApp.getContext(), "Obrigada pela Avaliação", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FormularioActivity.this, HomeActivity.class));
        FormularioActivity.this.finish();


    }
}
