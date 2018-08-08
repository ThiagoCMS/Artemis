package br.ufrpe.artemis.chat.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.chat.dominio.Mensagem;
import br.ufrpe.artemis.chat.negocio.ChatNegocio;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.avaliacao.gui.FormularioActivity;
import br.ufrpe.artemis.R;

public class ChatActivity extends AppCompatActivity {
    private EditText editText;
    private Button btEnviar;
    private List<Mensagem> listMensagem;
    private Chat chat;
    private Button oculto;
    private ChatCustomAdapter chatCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTela();
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEnviar();
            }
        });
        oculto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBotaoOculto();
            }
        });
    }

    private void setTela(){
        ListView listView = findViewById(R.id.idChatList);
        editText = findViewById(R.id.idChatText);
        btEnviar = findViewById(R.id.idChatButtom);
        oculto = findViewById(R.id.botaoOculto);
        ChatNegocio chatNegocio = new ChatNegocio();
        Bundle extras = getIntent().getExtras();
        chat = chatNegocio.retornarChat(Integer.parseInt(String.valueOf(extras.getString("id"))));
        listMensagem = chatNegocio.recuperarMensagens(chat.getId());
        chatCustomAdapter = new ChatCustomAdapter(listMensagem);
        listView.setAdapter(chatCustomAdapter);
        if(chat.getPessoa1().getId() == Sessao.instance.getPessoa().getId()){
            oculto.setText("Responder formulário");
        }else{
            oculto.setText("Liberar formulário");
        }
    }

    private void clickBotaoOculto(){
        if(chat.getPessoa2().getUsuario().getId() == Sessao.instance.getPessoa().getId()){
            Toast.makeText(this, "Formulário liberado", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(ChatActivity.this, FormularioActivity.class);
            intent.putExtra("id", String.valueOf(chat.getPessoa2().getId()));
            startActivity(intent);
        }
    }

    private void clickEnviar(){
        String textoMensagem = editText.getText().toString();
        if (textoMensagem.isEmpty()) {
            return;
        }
        Mensagem mensagem = new Mensagem(chat, Sessao.instance.getPessoa(), textoMensagem);
        listMensagem.add(mensagem);
        ChatNegocio chatNegocio1 = new ChatNegocio();
        chatNegocio1.inserirMensagem(mensagem);
        chatCustomAdapter.notifyDataSetChanged();
        editText.setText("");
    }
}
