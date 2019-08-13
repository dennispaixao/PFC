
import br.com.artenativa.dao.ItemOrcamentoDAO;
import br.com.artenativa.dao.OrcamentoDAO;
import br.com.artenativa.dao.ProdutoDAO;
import br.com.artenativa.model.Cliente;
import br.com.artenativa.model.ItemOrcamento;
import br.com.artenativa.model.Orcamento;
import br.com.artenativa.model.Produto;
import br.com.artenativa.model.Usuario;
import br.com.artenativa.util.MD5;
import br.com.artenativa.util.ParseDates;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dennis
 */
public class teste {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
      /*Split
        String teste = "0,1,2,3,4,5,6";
        String [] t = teste.split(",");
        System.out.println(t[3]);
        System.out.println("aaa");
        if(teste.matches("[a-zA-Z0-9]*")){
            System.out.println("ok");
        }   */
  
    //separa a data em array de string e converte em array de int 
    /*date
    String[] sData=  "2019-05-22".split("-");
    int dia = Integer.parseInt(sData[2]);
    int mes = Integer.parseInt(sData[1])-1;//mes inicia em 0
    int ano = Integer.parseInt(sData[0]);
    //cria um date
    Date date = new Date();
    Calendar.Builder b = new Calendar.Builder().setDate(ano, mes, dia);
 
    //cria um hash a partir da data passada no setDate
    Long u =b.build().getTime().getTime() ;
    String unix= u.toString().substring(0,u.toString().length()-3);
    System.out.println(unix);
    date.setTime(Long.parseLong(unix) * 1000L);
    
    System.out.println(date);
    
    
    
    Orcamento o = new Orcamento();
    
    o.setDataInsercao("1");
    o.setDataInicio("2");
    o.setDataPrevista("2");
    o.setCliente(new Cliente(1));
    o.setResponsavel(new Usuario(1));
    o.setRelatorio("aaaa");
    
    OrcamentoDAO odao = new  OrcamentoDAO();
    
    int idO= odao.inserir(o);
    

    ItemOrcamento i = new ItemOrcamento();
    i.setOrcamento(new Orcamento(idO));
    i.setProduto(new Produto(8));
    i.setQuantidade(10);
    
    ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
    
    idao.inserir(i);
     */ 
    /*
    OrcamentoDAO odao = new OrcamentoDAO();
    Orcamento o = new Orcamento(53);
    o = odao.buscar(o); 
    o.setId(27);
   ItemOrcamentoDAO idao =  new ItemOrcamentoDAO();

   ItemOrcamento i = idao.temItemNoOrcamento(o, new Produto(11));
   i.setQuantidade(5);
   idao.alterarItem(i);
   
   
   System.out.println(i.getQuantidade());

    Orcamento o = new OrcamentoDAO().buscar(new Orcamento(119));
    o.setValor(100);
    new OrcamentoDAO().alterar(o);
    */
    /*
    Produto p = new Produto(3);
    ProdutoDAO pdao = new ProdutoDAO();
    p = pdao.buscar(p);
    p.setQtEstoque(-3);
    pdao.alterar(p);

    } 
    
    ItemOrcamentoDAO idao = new ItemOrcamentoDAO();
    ItemOrcamento i = new ItemOrcamento(243);
    i = idao.buscar(i);
    
    System.out.println(i.getProduto().getNome());
    */
  
        System.out.println(MD5.md5("123"));
    
    
}}
