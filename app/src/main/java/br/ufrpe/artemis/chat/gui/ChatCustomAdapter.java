package br.ufrpe.artemis.chat.gui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

import br.ufrpe.artemis.chat.dominio.Mensagem;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.R;

public class ChatCustomAdapter extends ArrayAdapter<Mensagem> {
    private List<Mensagem> mensagens;
    private LayoutInflater layoutInflater;

    public ChatCustomAdapter(List<Mensagem> mensagens) {
        super(ArtemisApp.getContext(),R.layout.list_item_message_send,mensagens);
        this.mensagens = mensagens;
        layoutInflater = (LayoutInflater)ArtemisApp.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(mensagens.get(position).getPessoa().getId() == Sessao.instance.getPessoa().getId())
            view = layoutInflater.inflate(R.layout.list_item_message_send, null);
        else
            view = layoutInflater.inflate(R.layout.list_item_message_recv,null);
        BubbleTextView textMessage = (BubbleTextView)convertView.findViewById(R.id.text_message);
        textMessage.setText(mensagens.get(position).getTexto());
        return view;
    }
}