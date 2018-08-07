package br.ufrpe.artemis.avaliacao.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;

public class FormularioActivity extends AppCompatActivity {
    private RatingBar precoServico;
    private RatingBar qualidadeServico;
    private RatingBar atendimentoServico;
    private TextView comentarioServico;
    private TextView nomePrestadora;
    private Button enviar;
    private Pessoa prestadora;


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
        comentarioServico = findViewById(R.id.comentariosId);
        enviar = findViewById(R.id.enviarId);
        nomePrestadora = findViewById(R.id.nomePrestadoraId);
        Bundle extras = getIntent().getExtras();
        int idPrestadora = Integer.parseInt(String.valueOf(extras.getString("id")));
        PessoaNegocio negocio = new PessoaNegocio();
        prestadora = negocio.recuperarPessoaPorId(idPrestadora);
        nomePrestadora.setText(prestadora.getNome());
    }

    private void cadastro(){
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNotaPreco(precoServico.getRating());
        avaliacao.setNotaQualidade(qualidadeServico.getRating());
        avaliacao.setNotaAtendimento(atendimentoServico.getRating());
        avaliacao.setComentario(comentarioServico.getText().toString().trim());
        avaliacao.setPrestadora(prestadora);
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(Sessao.instance.getUsuario().getId());
        avaliacao.setCliente(pessoa);
        AvaliacaoNegocio negocio = new AvaliacaoNegocio();
        negocio.inserirAvaliacao(avaliacao);
        Toast.makeText(ArtemisApp.getContext(), "Obrigada pela Avaliação", Toast.LENGTH_SHORT).show();
        FormularioActivity.this.finish();
    }
}

