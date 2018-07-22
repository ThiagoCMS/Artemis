package br.ufrpe.artemis.Servico.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class ServicoActivity extends AppCompatActivity {
    private TextView cateSubcate;
    private TextView titulo;
    private TextView descricao;
    private TextView nomePrestador;
    private TextView classificacaoPrestador;
    private Button contratar;
    private int idServico;
    private Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

        setTela();
    }

    private void setTela(){
        setView();
        getId();
        getList();
        setText();
    }

    private void setView(){
        cateSubcate = findViewById(R.id.textViewCategoria);
        titulo = findViewById(R.id.textViewTitulo);
        descricao = findViewById(R.id.textViewDescricao);
        nomePrestador = findViewById(R.id.textViewNomePres);
        classificacaoPrestador = findViewById(R.id.textViewClassif);
        contratar = findViewById(R.id.botaoContratar);
    }

    private void getList(){
        ServicoNegocio negocio = new ServicoNegocio();
        servico = negocio.infoTelaServico(idServico);
    }

    private void setText(){
        titulo.setText(servico.getNome());
        descricao.setText(servico.getTexto());
        cateSubcate.setText(servico.getSubcategoria().getCategoria().getNome() + "/" + servico.getSubcategoria().getNome());
        nomePrestador.setText(servico.getPessoa().getNome());
        classificacaoPrestador.setText("");
    }

    private void getId(){
        Bundle extras = getIntent().getExtras();
        idServico = Integer.parseInt(extras.getString("id"));
    }
}
