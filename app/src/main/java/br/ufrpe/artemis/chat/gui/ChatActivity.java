package br.ufrpe.artemis.chat.gui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.chat.dominio.Mensagem;
import br.ufrpe.artemis.chat.negocio.ChatNegocio;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class ChatActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private FloatingActionButton btn_send_message;
    private ArrayList<Mensagem> list_mensagem;
    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listView = findViewById(R.id.idChatList);
        editText = findViewById(R.id.idChatText);
        btn_send_message = findViewById(R.id.idChatButtom);


        ChatNegocio chatNegocio = new ChatNegocio();
        Bundle extras = getIntent().getExtras();
        chat = chatNegocio.retornarChat(Integer.parseInt(String.valueOf(extras.getString("id"))));
        list_mensagem = chatNegocio.recuperarMensagens(chat.getId());
        final ChatCustomAdapter chatCustomAdapter = new ChatCustomAdapter(list_mensagem);
        listView.setAdapter(chatCustomAdapter);

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

    }
}
