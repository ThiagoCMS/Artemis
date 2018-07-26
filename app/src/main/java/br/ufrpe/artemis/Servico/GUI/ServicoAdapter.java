package br.ufrpe.artemis.Servico.GUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;

public class ServicoAdapter extends ArrayAdapter<Servico> {

    private final Context context;
    private ArrayList<Servico> elementos;

    public ServicoAdapter(@NonNull Context context, @NonNull ArrayList<Servico> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }





    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nomeServico = rowView.findViewById(R.id.nomeServico);
        TextView nomePrestador = rowView.findViewById(R.id.nomePrestador);
        TextView classificação = rowView.findViewById(R.id.classificacao);

        nomeServico.setText((CharSequence) elementos.get(position).getNome());
        nomePrestador.setText(elementos.get(position).getPessoa().getNome());


        return rowView;

    }



}
