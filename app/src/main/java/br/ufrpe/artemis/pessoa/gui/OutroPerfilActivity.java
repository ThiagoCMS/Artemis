package br.ufrpe.artemis.pessoa.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.ufrpe.artemis.avaliacao.dominio.Classificacao;
import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;
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
    private ImageView imagem;
    private TextView ntPreço;
    private TextView ntQualidade;
    private TextView ntAtendimento;


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
        imagem = findViewById(R.id.imgView);
        ntPreço = findViewById(R.id.notaPrecosId);
        ntAtendimento = findViewById(R.id.notaAtendimentosId);
        ntQualidade = findViewById(R.id.notaQualidadesId);
        setPessoa();
    }

    private void setPessoa(){
        Bundle extras = getIntent().getExtras();
        int idUsuario = extras.getInt("id");
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
        Classificacao classificacao = avaliacaoNegocio.notasPrestadora(idUsuario);
        nome.setText(pessoa.getNome());
        email.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());
        cidade.setText(pessoa.getEndereco().getCidade());
        imagem.setImageBitmap(pessoa.getFotoPerfil());
        DecimalFormat df2 = new DecimalFormat(".##");
        ntPreço.setText("Preço - " +  df2.format(classificacao.getMediaPreco()));
        ntQualidade.setText("Qualidade - " +  df2.format(classificacao.getMediaQualidade()));
        ntAtendimento.setText("Atendimento - " +  df2.format(classificacao.getMediaAtendimento()));
    }
}
