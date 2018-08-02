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

public class MeusServicosAdapter extends ArrayAdapter<Servico> {
    private ArrayList<Servico> elementos;
    public MeusServicosAdapter( @NonNull ArrayList<Servico> elementos) {
        super(ArtemisApp.getContext(), R.layout.linha, elementos);
        this.elementos = elementos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ArtemisApp.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_meus_servicos, parent, false);
        setarTela(rowView, position);
        return rowView;

    }

    private void setarTela(View rowView, int position){
        TextView nomeServico = rowView.findViewById(R.id.servicoId);
        nomeServico.setText(elementos.get(position).getNome());
    }

}
