package br.ufrpe.artemis.Servico.GUI;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.Servico.Dominio.Servico;
import br.ufrpe.artemis.Servico.Negocio.ServicoNegocio;

public class MeusServicosActivity extends AppCompatActivity {
    private Button button;
    private ListView list;
    private ArrayList<Servico> listaServicos;
    List selections;
    int count;
    ServicoNegocio negocio;
    ArrayAdapter<String> teAdaptador;
    ArrayList<String> arrayAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);

        //djair
        negocio = new ServicoNegocio();
        selections = new ArrayList();
        count = 0;


        setTela();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irCriarServico();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editarServico(position);
            }
        });

        //djair

        list.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)
            {
                if(checked){
                    selections.add(listaServicos.get(position));


                    count ++;
                    mode.setTitle(count+" Selected");


                }else{
                    selections.remove(listaServicos.get(position));
                    count--;
                    mode.setTitle(count+" Selected");
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.my_menu, menu);



                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if(item.getItemId()==R.id.id_delete){
                    for(Object Item : selections){
                        listaServicos.remove(Item);
                        negocio.deletarServicoDoBanco((Servico) Item);
                        String string = ((Servico) Item).getNome();
                        arrayAdaptador.remove(string);

                    }
                    teAdaptador.notifyDataSetChanged();
                    mode.finish();
                    return true;
                }







                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                count = 0;
                selections.clear();

            }


        });


    }

    private void editarServico(int position){
        Intent intent = new Intent(MeusServicosActivity.this, EditarServicoActivity.class);
        intent.putExtra("id", String.valueOf(listaServicos.get(position).getId()));
        startActivity(intent);
        MeusServicosActivity.this.finish();
    }

    private void irCriarServico(){
        startActivity(new Intent(MeusServicosActivity.this, CriarServicoActivity.class));
        MeusServicosActivity.this.finish();
    }

    private void setTela(){
        button = findViewById(R.id.buttonId);
        list = findViewById(R.id.listId);

        listaServicos = negocio.listarSevicosUs(Sessao.instance.getUsuario().getId());
        arrayAdaptador = listarNomeServicos();
        teAdaptador = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayAdaptador
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ED0621"));
                return view;
            }
        };

        list.setAdapter(teAdaptador);
    }

    private ArrayList<String> listarNomeServicos(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < listaServicos.size() ; i++){
            list.add(listaServicos.get(i).getNome());
        }
        return list;
    }



}
