package br.ufrpe.artemis.Chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.ufrpe.artemis.Chat.Dominio.Chat;
import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.R;

public class ChatAdapter extends ArrayAdapter<Chat> {
    private ArrayList<Chat> elementos;
    public ChatAdapter(ArrayList<Chat> elementos){
        super(ArtemisApp.getContext(), R.layout.linha_chat, elementos);
        this.elementos = elementos;
        //a
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ArtemisApp.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_chat, parent, false);
        setarItensTela(rowView, position);
        return rowView;
    }

    public void setarItensTela(View rowView, int position){
        TextView nomePrestador = rowView.findViewById(R.id.nomePrestadorChat);
        nomePrestador.setText(indentificarPessoa(rowView, position));
    }

    private String indentificarPessoa(View rowView, int position){
        int idUsuarioAtual = Sessao.instance.getUsuario().getId();
        if(elementos.get(position).getPessoa1().getUsuario().getId()==idUsuarioAtual){
            return elementos.get(position).getPessoa1().getNome();
        }else{
            return elementos.get(position).getPessoa2().getNome();
        }
    }
}
