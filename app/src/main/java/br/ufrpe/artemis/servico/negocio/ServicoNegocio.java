package br.ufrpe.artemis.servico.negocio;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

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

    public List<Servico> listarSevicosSub(int idSub){
        ServicoDao banco = new ServicoDao();
        List<Servico> list = banco.recuperarDoBancoSub(idSub);
        Pessoa pessoa = Sessao.instance.getPessoa();
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

    public List<Servico> listarSevicosPessoa(int idPessoa){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarDoBancoUs(idPessoa);
    }

    public Servico infoTelaServico(int id){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarServico(id);
    }

    public List<Categoria> listarCategoria(){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarListaCategoria();
    }

    public List<Subcategoria> listarSubcategoria(int idcategoria){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarListaSubcategoria(idcategoria);
    }

    public Servico pegarServico(int id){
        ServicoDao banco = new ServicoDao();
        return banco.recuperarServico(id);
    }

    public void editarServico(Servico servico){
        ServicoDao banco = new ServicoDao();
        banco.atualizarServico(servico);
    }

    public List<Servico> listarServicos(){
        ServicoDao banco = new ServicoDao();
        return banco.retornarServicos();
    }
}
