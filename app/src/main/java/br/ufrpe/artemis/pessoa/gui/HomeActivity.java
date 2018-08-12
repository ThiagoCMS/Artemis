package br.ufrpe.artemis.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.SearchView;
import android.widget.TextView;

import br.ufrpe.artemis.chat.gui.ListaConversasActivity;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.R;
import br.ufrpe.artemis.servico.gui.SearchResultsActivity;
import br.ufrpe.artemis.servico.gui.ServicosRecomendadosActivity;
import br.ufrpe.artemis.servico.gui.SubCategoriaActivity;
import br.ufrpe.artemis.usuario.gui.ConfiguracoesActivity;
import br.ufrpe.artemis.usuario.gui.LoginActivity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView saude;
    private ImageView reformas;
    private ImageView eventos;
    private ImageView domiciliares;
    private ImageView moda;
    private ImageView tecnologia;
    private TextView nomePerfil;
    private View hView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        hView =  navigationView.getHeaderView(0);
        setView();
        saude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherCategoria("1");
            }
        });
        reformas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherCategoria("3");
            }
        });
        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherCategoria("4");
            }
        });
        domiciliares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherCategoria("6");
            }
        });
        moda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherCategoria("5");
            }
        });
        tecnologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherCategoria("2");
            }
        });
    }

    private void setView(){
        saude = findViewById(R.id.saudeId);
        reformas = findViewById(R.id.reformaId);
        eventos = findViewById(R.id.eventosId);
        domiciliares = findViewById(R.id.domiciliarId);
        moda = findViewById(R.id.modaId);
        tecnologia = findViewById(R.id.tecnologiaId);
        nomePerfil = hView.findViewById(R.id.nomePerfilHomeId);
        nomePerfil.setText(Sessao.instance.getPessoa().getNome());
    }

    private void escolherCategoria(String id){
        Intent intent = new Intent(HomeActivity.this, SubCategoriaActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
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
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(HomeActivity.this,SearchResultsActivity.class);
                intent.putExtra("pesquisa",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean answer = false;
        if (id == R.id.action_settings) {
            answer = true;
        }else if(id == R.id.action_search){
            answer = true;
        }else {
            answer = super.onOptionsItemSelected(item);
        }
        return answer;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_logout) {
            logout();
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(HomeActivity.this, ConfiguracoesActivity.class));
        }else if (id== R.id.nav_profile){
            startActivity(new Intent(HomeActivity.this, PerfilActivity.class));
        } else if (id == R.id.nav_recommended) {
            startActivity(new Intent(HomeActivity.this, ServicosRecomendadosActivity.class));
        } else if(id == R.id.nav_chat){
            startActivity(new Intent(HomeActivity.this, ListaConversasActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout(){
        Sessao.instance.reset();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        HomeActivity.this.finish();
    }
}
