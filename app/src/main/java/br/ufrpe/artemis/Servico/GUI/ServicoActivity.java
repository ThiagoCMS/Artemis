package br.ufrpe.artemis.Servico.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

import br.ufrpe.artemis.Chat.Dominio.Chat;
import br.ufrpe.artemis.Chat.GUI.ChatActivity;
import br.ufrpe.artemis.Chat.Negocio.ChatNegocio;
import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.GUI.OutroPerfil;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class ServicoActivity extends AppCompatActivity {
    private TextView cateSubcate;
    private TextView titulo;
    private TextView descricao;
    private TextView nomePrestador;
    private TextView classificacaoPrestador;
    private Button btContratar;
    private Servico servico;
    private Button btChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
        setTela();
        btContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contratar();
            }
        });
        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comecarChat();
            }
        });
    }

    private void setTela(){
        cateSubcate = findViewById(R.id.textViewCategoria);
        titulo = findViewById(R.id.textViewTitulo);
        descricao = findViewById(R.id.textViewDescricao);
        nomePrestador = findViewById(R.id.textViewNomePres);
        classificacaoPrestador = findViewById(R.id.textViewClassif);
        btContratar = findViewById(R.id.botaoContratar);
        btChat = findViewById(R.id.botaoChat);
        setService();
    }

    private void setService(){
        Bundle extras = getIntent().getExtras();
        int id = Integer.parseInt(extras.getString("id"));
        ServicoNegocio negocio = new ServicoNegocio();
        servico = negocio.infoTelaServico(id);
        titulo.setText(servico.getNome());
        descricao.setText(servico.getTexto());
        cateSubcate.setText(servico.getSubcategoria().getCategoria().getNome() + "/" + servico.getSubcategoria().getNome());
        nomePrestador.setText(servico.getPessoa().getNome());
        classificacaoPrestador.setText("");
    }

    private void contratar(){
        Intent intent = new Intent(ServicoActivity.this, OutroPerfil.class);
        intent.putExtra("id", servico.getPessoa().getId());
        startActivity(intent);
    }
    private void comecarChat(){

        if(servico.getPessoa().getUsuario().getId()== Sessao.instance.getUsuario().getId()){
            Toast.makeText(ArtemisApp.getContext(),"Você não pode iniciar um chat com si mesmo",Toast.LENGTH_SHORT).show();

        }else{
            ChatNegocio chatNegocio = new ChatNegocio();
            PessoaNegocio pessoaNegocio = new PessoaNegocio();
            Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(Sessao.instance.getUsuario().getId());
            Chat chat = chatNegocio.iniciarChat(pessoa,servico.getPessoa());
            Intent intent = new Intent(ServicoActivity.this, ChatActivity.class);
            intent.putExtra("id", String.valueOf(chat.getId()));
            startActivity(intent);


        }

    }
}
