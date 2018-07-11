package cursoandroid.com.semiprojeto.Servico.Negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cursoandroid.com.semiprojeto.Servico.Dao.ServicoDao;
import cursoandroid.com.semiprojeto.Servico.Dominio.Servico;

public class ServicoNegocio {

    public Servico criarServico(String nome, String texto, int idSubCategoria, int idUsuario){
        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setTexto(texto);
        servico.setIdSubCategoria(idSubCategoria);
        servico.setIdUsuario(idUsuario);
        return servico;
    }

    public void inserirServicoNoBanco(Servico servico, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        banco.inserirNoBanco(servico);
    }

    public void deletarServicoDoBanco(Servico servico, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        banco.deletarDoBanco(servico);
    }

    public ArrayList<Servico> listarSevicosSub(int idSub, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        ArrayList<Servico> list = banco.recuperarDoBancoSub(idSub);
        return list;
    }

    public ArrayList<Servico> listarSevicosUs(int idUsuario, Context ctx){
        ServicoDao banco = new ServicoDao();
        banco.escreverNoBanco(ctx);
        ArrayList<Servico> list = banco.recuperarDoBancoUs(idUsuario);
        return list;
    }

    public ArrayList<String> listarServicoStr(ArrayList<Servico> list){
        ArrayList<String> list1 = new ArrayList<String>();
        while(list.size()>0){
            list1.add(list.get(0).getNome());
            list.remove(0);
        }
        return list1;
    }
}
