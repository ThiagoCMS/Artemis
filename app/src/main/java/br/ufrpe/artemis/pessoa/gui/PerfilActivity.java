package br.ufrpe.artemis.pessoa.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.avaliacao.dominio.Classificacao;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.gui.MeusServicosActivity;
import br.ufrpe.artemis.servico.gui.ComentariosActivity;
import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;

public class PerfilActivity extends AppCompatActivity {
    private ImageView imagemUsuario;
    private TextView nomeUsuario;
    private TextView pessoaEmail;
    private TextView endereco;
    private TextView telefone;
    private Button botaoAnuncios;
    private Button botaoComentarios;
    private Button botaoEditar;
    private Classificacao classificacao;
    private TextView ntPreço;
    private TextView ntQualidade;
    private TextView ntAtendimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setTela();
        botaoAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anuncios();
            }
        });
        botaoComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opnioes();
            }
        });
        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });


    }
    private void setTela(){
        imagemUsuario = findViewById(R.id.imgView);
        nomeUsuario = findViewById(R.id.nomeUsuarioId);
        botaoAnuncios = findViewById(R.id.botaoanuncioId);
        botaoComentarios = findViewById(R.id.botaoComentariosId);
        botaoEditar = findViewById(R.id.botaoEditarId);
        pessoaEmail = findViewById(R.id.pessoaEmailId);
        endereco = findViewById(R.id.enderecoId);
        telefone = findViewById(R.id.telefoneId);
        ntPreço = findViewById(R.id.notaPrecoId);
        ntAtendimento = findViewById(R.id.notaAtendimentoId);
        ntQualidade = findViewById(R.id.notaQualidadeId);
        calcularNotas();
        setPessoa();
    }

    private void setPessoa(){
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        int idUsuario = Sessao.instance.getUsuario().getId();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(idUsuario);
        nomeUsuario.setText(pessoa.getNome());
        pessoaEmail.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());
        endereco.setText(pessoa.getEndereco().getCidade());
        imagemUsuario.setImageBitmap(pessoa.getFotoPerfil());
        ntPreço.setText("Preço - " + classificacao.getMediaPreco());
        ntQualidade.setText("Qualidade - " + classificacao.getMediaQualidade());
        ntAtendimento.setText("Atendimento - " + classificacao.getMediaAtendimento());
    }

    public void anuncios(){
        startActivity(new Intent(PerfilActivity.this, MeusServicosActivity.class));
    }

    public void opnioes(){

        startActivity(new Intent(PerfilActivity.this, ComentariosActivity.class));
    }

    public void editar(){
        startActivity(new Intent(PerfilActivity.this, EditarPerfilActivity.class));
        PerfilActivity.this.finish();
    }

    public void calcularNotas(){
        AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
        classificacao = new Classificacao();
        ArrayList<Avaliacao> listaNotas = avaliacaoNegocio.notasPrestadora(Sessao.instance.getUsuario().getId());
        double mediaPreço = 0;
        double mediaAtendimento = 0;
        double mediaQualidade = 0;
        for (Avaliacao obj: listaNotas) {
            mediaPreço+=obj.getNotaPreco();
            mediaAtendimento+=obj.getNotaAtendimento();
            mediaQualidade+=obj.getNotaQualidade();
        }
        if(listaNotas.size()>0) {
            mediaPreço = mediaPreço / listaNotas.size();
            mediaAtendimento = mediaAtendimento / listaNotas.size();
            mediaQualidade = mediaQualidade / listaNotas.size();
        }
        classificacao.setMediaPreco(mediaPreço);
        classificacao.setMediaAtendimento(mediaAtendimento);
        classificacao.setMediaQualidade(mediaQualidade);
    }
}

