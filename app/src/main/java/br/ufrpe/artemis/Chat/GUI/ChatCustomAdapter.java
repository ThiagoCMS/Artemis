package br.ufrpe.artemis.Chat.GUI;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.github.library.bubbleview.BubbleTextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.artemis.Chat.Dominio.Mensagem;
import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.R;

public class ChatCustomAdapter extends ArrayAdapter<Mensagem> {

    private ArrayList<Mensagem> mensagens;
    private LayoutInflater layoutInflater;


    public ChatCustomAdapter(ArrayList<Mensagem> mensagens) {
        super(ArtemisApp.getContext(),R.layout.list_item_message_send,mensagens);


        this.mensagens = mensagens;
        layoutInflater = (LayoutInflater)ArtemisApp.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(mensagens.get(position).getPessoa().getUsuario().getId() == Sessao.instance.getUsuario().getId())
            view = layoutInflater.inflate(R.layout.list_item_message_send, null);
        else
            view = layoutInflater.inflate(R.layout.list_item_message_recv,null);

        BubbleTextView text_message = (BubbleTextView)view.findViewById(R.id.text_message);
        text_message.setText(mensagens.get(position).getMensagem());

        return view;

    }
}