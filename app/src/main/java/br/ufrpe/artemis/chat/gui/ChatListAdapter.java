package br.ufrpe.artemis.chat.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.chat.dominio.Chat;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.R;


public class ChatListAdapter extends ArrayAdapter<Chat> {
    private ArrayList<Chat> elementos;

    public ChatListAdapter(@NonNull ArrayList<Chat> elementos) {
        super(ArtemisApp.getContext(), R.layout.linha_chat, elementos);
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ArtemisApp.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_chat, parent, false);
        TextView nomeOutraPessoaChat = rowView.findViewById(R.id.nomePrestadorChat);
        if(elementos.get(position).getPessoa1().getUsuario().getId() == Sessao.instance.getUsuario().getId()){
            nomeOutraPessoaChat.setText(elementos.get(position).getPessoa2().getNome());
        }else{
            nomeOutraPessoaChat.setText(elementos.get(position).getPessoa1().getNome());
        }

        return rowView;

    }

}