package br.ufrpe.artemis.pessoa.gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.ufrpe.artemis.endereco.dominio.Endereco;
import br.ufrpe.artemis.infra.Auxiliar;
import br.ufrpe.artemis.infra.HttpDataHandler;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.R;

public class EditarPerfilActivity extends AppCompatActivity {
    private EditText nomeEditar;
    private EditText telefoneEditar;
    private EditText emailEditar;
    private EditText ruaEditar;
    private EditText numeroEditar;
    private EditText cidadeEditar;
    private Button alterar;
    private Button alterarFoto;
    public static final int RESULT_LOAD_IMAGE = 1;
    private boolean error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        setTela();
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarPessoa();
            }
        });
        alterarFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmaps = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                alterarFoto(bitmaps);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTela(){
        nomeEditar = findViewById(R.id.nomeId);
        telefoneEditar = findViewById(R.id.telefoneId);
        emailEditar = findViewById(R.id.pessoaEmailId);
        ruaEditar = findViewById(R.id.ruaEnderecoId);
        numeroEditar = findViewById(R.id.numEnderecoId);
        cidadeEditar = findViewById(R.id.cidadeEnderecoId);
        alterar = findViewById(R.id.btAlterarId);
        alterarFoto = findViewById(R.id.editarFotoId);
        setPessoa();
    }

    private void setPessoa(){
        Pessoa pessoa = Sessao.instance.getPessoa();
        nomeEditar.setText(pessoa.getNome());
        emailEditar.setText(pessoa.getEmail());
        telefoneEditar.setText(pessoa.getTelefone());
        Endereco endereco = pessoa.getEndereco();
        ruaEditar.setText(endereco.getRua());
        numeroEditar.setText(endereco.getNumero());
        cidadeEditar.setText(endereco.getCidade());
    }

    public void alterarPessoa(){
        if(validarCampos()) {
            String rua = ruaEditar.getText().toString().trim();
            String numero = numeroEditar.getText().toString().trim();
            String cidade = cidadeEditar.getText().toString().trim();
            String end = rua.replace(" ", "+") + "+" + numero + "+" + cidade.replace(" ", "+");
            new GetCoordinates().execute(end);
        }
    }


    private boolean validarCampos(){
        boolean erro = true;
        if(validarNome()){
            erro = false;
        }
        if(validarEmail()){
            erro = false;
        }
        if(validarTelefone()){
            erro= false;
        }
        if(validarRua()){
            erro = false;
        }
        if(validarNumero()){
            erro = false;
        }
        if(validarCidade()){
            erro= false;
        }
        return erro;
    }

    private boolean validarTelefone(){
        boolean erro = false;
        String telefone = telefoneEditar.getText().toString().trim();
        if(telefone.isEmpty()){
            erro = true;
            telefoneEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarRua(){
        boolean erro = false;
        String rua = ruaEditar.getText().toString().trim();
        if(rua.isEmpty()){
            erro = true;
            ruaEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNumero(){
        boolean erro = false;
        String numero = numeroEditar.getText().toString().trim();
        if(numero.isEmpty()){
            erro = true;
            numeroEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarCidade(){
        boolean erro = false;
        String cidade = cidadeEditar.getText().toString().trim();
        if(cidade.isEmpty()){
            erro = true;
            cidadeEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarNome(){
        boolean erro = false;
        String nome = nomeEditar.getText().toString().trim();
        if(nome.isEmpty()){
            erro = true;
            nomeEditar.setError("Campo em branco");
        }
        return erro;
    }

    private boolean validarEmail(){
        boolean erro = false;
        String email = emailEditar.getText().toString().trim();
        if(email.isEmpty()){
            erro = true;
            emailEditar.setError("Campo em branco");
        }
        return erro;
    }

    private void alterarInformacoes(double lat, double lng){
        if(error){
            Toast.makeText(this, "endereço não localizado", Toast.LENGTH_SHORT).show();
            return;
        }
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = Sessao.instance.getPessoa();
        String nome = nomeEditar.getText().toString().trim();
        String telefone = telefoneEditar.getText().toString().trim();
        String email = emailEditar.getText().toString().trim();
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        String rua = ruaEditar.getText().toString().trim();
        String numero = numeroEditar.getText().toString().trim();
        String cidade = cidadeEditar.getText().toString().trim();
        Endereco endereco = pessoa.getEndereco();
        endereco.setNumero(numero);
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        endereco.setLat(lat);
        endereco.setLng(lng);
        pessoa.setEndereco(endereco);
        pessoaNegocio.alterarPessoa(pessoa);
        Sessao.instance.setPessoa(pessoa);
        startActivity(new Intent(EditarPerfilActivity.this, PerfilActivity.class));
        EditarPerfilActivity.this.finish();
    }

    public void onBackPressed(){
        startActivity(new Intent(EditarPerfilActivity.this, PerfilActivity.class));
        EditarPerfilActivity.this.finish();
    }

    public void alterarFoto(Bitmap bitmap){
        Bitmap bitmapComp = Auxiliar.comprimirImagem(bitmap);
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Sessao.instance.getPessoa().setFotoPerfil(bitmapComp);
        pessoaNegocio.alterarFotoPerfil(Sessao.instance.getPessoa());
    }

    private class GetCoordinates extends AsyncTask<String,Void,String> {
        ProgressDialog dialog = new ProgressDialog(EditarPerfilActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait....");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response;
            try{
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=AIzaSyCI1NeacBsowhHvQTPcCLV9bZ5aUgJBm8M",address);
                response = http.getHTTPData(url);
                return response;
            }
            catch (Exception ex) {}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                String ok = (String) jsonObject.get("status");
                if(ok.equals("OK")) {
                    double lat = (double) ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                            .getJSONObject("location").get("lat");
                    double lng = (double) ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                            .getJSONObject("location").get("lng");
                    if(dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    alterarInformacoes(lat, lng);
                }else{
                    error = true;
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
