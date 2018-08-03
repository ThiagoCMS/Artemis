package br.ufrpe.artemis.chat.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.chat.dominio.Mensagem;
import br.ufrpe.artemis.chat.negocio.ChatNegocio;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.avaliacao.gui.FormularioActivity;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class ChatActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button btn_send_message;
    private ArrayList<Mensagem> list_mensagem;
    private Chat chat;
    private Button oculto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listView = findViewById(R.id.idChatList);
        editText = findViewById(R.id.idChatText);
        btn_send_message = findViewById(R.id.idChatButtom);
        oculto = findViewById(R.id.botaoOculto);

        ChatNegocio chatNegocio = new ChatNegocio();
        Bundle extras = getIntent().getExtras();
        chat = chatNegocio.retornarChat(Integer.parseInt(String.valueOf(extras.getString("id"))));
        list_mensagem = chatNegocio.recuperarMensagens(chat.getId());
        final ChatCustomAdapter chatCustomAdapter = new ChatCustomAdapter(list_mensagem);
        listView.setAdapter(chatCustomAdapter);
        textoBotao();

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoMensagem = editText.getText().toString();
                if (textoMensagem.isEmpty()) {
                    return;
                }
                PessoaNegocio pessoaNegocio = new PessoaNegocio();
                Mensagem mensagem = new Mensagem(chat, pessoaNegocio.recuperarPessoaPorUsuario(Sessao.instance.getUsuario().getId()), textoMensagem);
                list_mensagem.add(mensagem);
                ChatNegocio chatNegocio1 = new ChatNegocio();
                chatNegocio1.inserirMensagem(mensagem);
                chatCustomAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        oculto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotaoOculto();
            }
        });

    }

    private void textoBotao(){
        if(chat.getPessoa1().getUsuario().getId() == Sessao.instance.getUsuario().getId()){
            oculto.setText("Responder formulário");
        }else{
            oculto.setText("Liberar formulário");
        }
    }

    private void abrirFormulario(){
        Intent intent = new Intent(ChatActivity.this, FormularioActivity.class);
        intent.putExtra("id", String.valueOf(chat.getPessoa2().getId()));
        startActivity(intent);
    }

    private void clickBotaoOculto(){
        if(chat.getPessoa2().getUsuario().getId() == Sessao.instance.getUsuario().getId()){
            Toast.makeText(this, "Formulário liberado", Toast.LENGTH_SHORT).show();
        }else{
            abrirFormulario();
        }
    }
}
