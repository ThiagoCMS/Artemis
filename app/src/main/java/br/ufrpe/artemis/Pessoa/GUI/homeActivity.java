package br.ufrpe.artemis.Pessoa.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufrpe.artemis.Infra.Sessao;
import br.ufrpe.artemis.Pessoa.Dominio.Pessoa;
import br.ufrpe.artemis.Pessoa.Negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

import br.ufrpe.artemis.Servico.GUI.SubDomiciliarActivity;
import br.ufrpe.artemis.Servico.GUI.SubEventosActivity;
import br.ufrpe.artemis.Servico.GUI.SubModaActivity;
import br.ufrpe.artemis.Servico.GUI.SubReformaActivity;
import br.ufrpe.artemis.Servico.GUI.SubSaudeActivity;
import br.ufrpe.artemis.Servico.GUI.SubTecnologiaActivity;
import br.ufrpe.artemis.Usuario.GUI.ConfiguracoesActivity;

public class homeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Pessoa pessoa;
    private String id;
    private ImageView saude;
    private ImageView reformas;
    private ImageView eventos;
    private ImageView domiciliares;
    private ImageView moda;
    private ImageView tecnologia;
    private TextView teste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        carregarPessoa(Integer.parseInt(id));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        saude = findViewById(R.id.saudeId);
        reformas = findViewById(R.id.reformaId);
        eventos = findViewById(R.id.eventosId);
        domiciliares = findViewById(R.id.domiciliarId);
        moda = findViewById(R.id.modaId);
        tecnologia = findViewById(R.id.tecnologiaId);

        saude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,SubSaudeActivity.class));
            }
        });
        reformas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,SubReformaActivity.class));
            }
        });
        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,SubEventosActivity.class));
            }
        });
        domiciliares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,SubDomiciliarActivity.class));
            }
        });
        moda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,SubModaActivity.class));
            }
        });
        tecnologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,SubTecnologiaActivity.class));
            }
        });
    }

    /*@Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.saudeId:
                startActivity(new Intent(homeActivity.this,SubCategoriaSaude.class));
                //break;
            case R.id.reformaId:
                startActivity(new Intent(homeActivity.this,SubCategoriaReforma.class));
                //break;
            case R.id.eventosId:
                startActivity(new Intent(homeActivity.this,SubCategoriaEventos.class));
                //break;
            case R.id.domiciliarId:
                startActivity(new Intent(homeActivity.this,SubCategoriaDomiciliar.class));
                //break;
            case R.id.modaId:
                startActivity(new Intent(homeActivity.this,SubCategoriaModa.class));
                //break;
            case R.id.tecnologiaId:
                startActivity(new Intent(homeActivity.this,SubCategoriaTecnologia.class));
                //break;



        }

    }*/


    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_profile) {
            //startActivity(new Intent(new Intent(homeActivity.this, opcoesActivity.class)));

        } else if (id == R.id.nav_config) {
            startActivity( new Intent(homeActivity.this, ConfiguracoesActivity.class).putExtra("id", this.id));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void carregarPessoa(int id){
        PessoaNegocio negocio = new PessoaNegocio();
        this.pessoa = negocio.recuperarPessoa(id);
    }
}
