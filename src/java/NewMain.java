
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.mock.ItemOrcamentoMock;
import java.sql.SQLException;
import java.util.ArrayList;


public class NewMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*  
      System.out.println(md5("123"));
      ArrayList<Orcamento> orcs = new OrcamentoDAO().listar(2);   
        orcs.forEach((_item) -> {
            System.out.println(_item.getId()); 
                        
        });*/
    /*    Orcamento o = new Orcamento();
        OrcamentoDAO odao = new OrcamentoDAO();

        o.setCliente(new Cliente(1));
        o.setResponsavel(new Usuario(1));
        o.setDataInsercao("1");
        o.setDataInicio("1");
        o.setDataPrevista("1");
        o.setRelatorio("1");

        System.out.println(odao.inserir(o));

        ArrayList<Orcamento> orcs = new OrcamentoDAO().listar(1);
        orcs.forEach((_item) -> {
            System.out.println(_item.getId());
            System.out.println(_item.getDataInsercao());
            System.out.println(_item.getDataInicio());

        });

    }*/
    /*
   Orcamento o = new Orcamento(50);
   OrcamentoDAO odao = new OrcamentoDAO();
   System.out.println(odao.excluir(o));*/
    
    
            ArrayList<ItemOrcamento> itens;
            Orcamento o = new Orcamento(61);
            ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
            itens = idao.listar(o);
             
            itens.forEach((i) -> {
                System.out.println(i.getId());
                System.out.println(i.getProduto().getNome());
                System.out.println(i.getQuantidade());

            });
            
            
            
}
}