package br.ufrpe.artemis.avaliacao.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.avaliacao.dominio.Avaliacao;
import br.ufrpe.artemis.infra.ArtemisApp;

public class AvaliacaoListAdapter extends ArrayAdapter<Avaliacao> {
    private List<Avaliacao> elementos;
    public AvaliacaoListAdapter(List<Avaliacao> elementos) {
        super(ArtemisApp.getContext(), R.layout.linha_avaliacao, elementos);
        this.elementos = elementos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ArtemisApp.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_avaliacao, parent, false);
        TextView nomeAvaliadora = rowView.findViewById(R.id.nomeAvaliadoraId);
        TextView notaPreco = rowView.findViewById(R.id.notaPrecoId);
        TextView notaQualidade = rowView.findViewById(R.id.notaQualidadeId);
        TextView notaAtendimento = rowView.findViewById(R.id.notaAtendimentoId);
        TextView comentario = rowView.findViewById(R.id.comentarioId);

        String nomePrestadora = elementos.get(position).getCliente().getNome();
        nomeAvaliadora.setText(nomePrestadora);

        Double notaPrecoDouble = elementos.get(position).getNotaPreco();
        notaPreco.setText(notaPrecoDouble.toString());

        Double notaQualidadeDouble = elementos.get(position).getNotaQualidade();
        notaQualidade.setText(notaQualidadeDouble.toString());

        Double notaAtendimentoDouble = elementos.get(position).getNotaAtendimento();
        notaAtendimento.setText(notaAtendimentoDouble.toString());

        comentario.setText(elementos.get(position).getComentario());

        return rowView;



    }
}