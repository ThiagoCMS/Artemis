package br.ufrpe.artemis.servico.negocio;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

import br.ufrpe.artemis.endereco.negocio.EnderecoNegocio;
import br.ufrpe.artemis.infra.Sessao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;
import br.ufrpe.artemis.pessoa.negocio.PessoaNegocio;
import br.ufrpe.artemis.servico.dao.ServicoDao;
import br.ufrpe.artemis.servico.dominio.Categoria;
import br.ufrpe.artemis.servico.dominio.Servico;
import br.ufrpe.artemis.servico.dominio.Subcategoria;

public class ServicoNegocio {

    public void inserirServicoNoBanco(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.inserirNoBanco(servico);
    }

    public void deletarServicoDoBanco(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.deletarDoBanco(servico);
    }

    public ArrayList<Servico> listarSevicosSub(int idSub){
        ServicoDao banco = new ServicoDao();
        ArrayList<Servico> list = banco.recuperarDoBancoSub(idSub);
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        Pessoa pessoa = pessoaNegocio.recuperarPessoaPorUsuario(Sessao.instance.getUsuario().getId());
        EnderecoNegocio enderecoNegocio = new EnderecoNegocio();
        LatLng latLng = new LatLng(pessoa.getEndereco().getLat(), pessoa.getEndereco().getLng());
        ArrayList<Servico> list1 = new ArrayList<>();
        for (Servico servico:list) {
            servico.getPessoa().setEndereco(enderecoNegocio.recuperarEndereco(servico.getPessoa().getEndereco().getId()));
            double lat = servico.getPessoa().getEndereco().getLat();
            double lng = servico.getPessoa().getEndereco().getLng();
            LatLng latLng1 = new LatLng(lat, lng);
            if(SphericalUtil.computeDistanceBetween(latLng, latLng1) < 70000){
                list1.add(servico);
            }
        }
        return list1;
    }

    public ArrayList<Servico> listarSevicosUs(int idUsuario){
        ServicoDao banco = new ServicoDao();
        PessoaNegocio negocio = new PessoaNegocio();
        Pessoa pessoa = negocio.recuperarPessoaPorUsuario(idUsuario);
        ArrayList<Servico> list = banco.recuperarDoBancoUs(pessoa.getId());
        return list;
    }

    public Servico infoTelaServico(int id){
        ServicoDao banco = new ServicoDao();
        Servico servico = banco.recuperarServico(id);
        return servico;
    }

    public ArrayList<Categoria> listarCategoria(){
        ServicoDao banco = new ServicoDao();
        ArrayList<Categoria> listaCategoria = banco.recuperarListaCategoria();
        return listaCategoria;
    }

    public ArrayList<Subcategoria> listarSubcategoria(int idcategoria){
        ServicoDao banco = new ServicoDao();
        ArrayList<Subcategoria> listaSubcategoria = banco.recuperarListaSubcategoria(idcategoria);
        return listaSubcategoria;
    }

    public Servico pegarServico(int id){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarServico(id);
    }

    public void editarServico(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.atualizarServico(servico);
    }

    public Pessoa recuperarPessoa(int id){
        PessoaNegocio negocio = new PessoaNegocio();
        return negocio.recuperarPessoaPorUsuario(id);
    }
}
