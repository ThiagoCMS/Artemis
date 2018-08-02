package br.ufrpe.artemis.chat.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.chat.negocio.ChatNegocio;
import br.ufrpe.artemis.R;

public class ListaConversasActivity extends AppCompatActivity {
    private ListView listViewChat;
    private ArrayList<Chat> listaChat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_conversas);

        listViewChat = findViewById(R.id.listViewChats);
        ChatNegocio chatNegocio = new ChatNegocio();
        listaChat = chatNegocio.recuperarChats();
        ArrayAdapter adapterChat = new ChatListAdapter(listaChat);
        listViewChat.setAdapter(adapterChat);

        listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaConversasActivity.this, ChatActivity.class);
                intent.putExtra("id", String.valueOf(listaChat.get(position).getId()));
                startActivity(intent);
            }
        });


    }

}
