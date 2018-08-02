package br.ufrpe.artemis.servico.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.ufrpe.artemis.infra.ArtemisApp;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.dominio.Servico;

public class ServicoAdapter extends ArrayAdapter<Servico> {
    private ArrayList<Servico> elementos;
    public ServicoAdapter(@NonNull ArrayList<Servico> elementos) {
        super(ArtemisApp.getContext(), R.layout.linha, elementos);
        this.elementos = elementos;
    }
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ArtemisApp.getContext()
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
