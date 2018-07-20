package br.ufrpe.artemis.Servico.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class ServicoActivity extends AppCompatActivity {
    private TextView cateSubcate;
    private TextView titulo;
    private TextView descricao;
    private TextView nomePrestador;
    private TextView classificacaoPrestador;
    private Button contratar;
    private int idServico;
    private ArrayList<String> info;

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
        info = negocio.infoTelaServico(idServico);
    }

    private void setText(){
        titulo.setText(info.get(0));
        descricao.setText(info.get(1));
        cateSubcate.setText(info.get(2) + "/" + info.get(3));
        nomePrestador.setText(info.get(4));
        classificacaoPrestador.setText("");
    }

    private void getId(){
        Bundle extras = getIntent().getExtras();
        idServico = Integer.parseInt(extras.getString("id"));
    }
}
