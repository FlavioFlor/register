package controle;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import org.hibernate.Criteria;


public class ClienteDAO {
    public static void adicionar(Cliente c) throws Exception{
        try{
            ConBanco.abrirCon();
            ConBanco.getSessao().save(c);
            ConBanco.getTrans().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            ConBanco.getTrans().rollback();
        }finally{
            ConBanco.fecharCon();   
        }
    }
    public static void remover(Cliente c) throws Exception{
         try{
            ConBanco.abrirCon();
            if (c != null) {
                ConBanco.getSessao().delete(c);
            }
            ConBanco.getTrans().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            ConBanco.getTrans().rollback();
        }finally{
            ConBanco.fecharCon();    
        }
        
    }
   
    public static void atualizar(Cliente c) throws Exception{
         try{
            ConBanco.abrirCon();
            if (c != null) {
                ConBanco.getSessao().update(c);
            }           
            ConBanco.getTrans().commit();
        }catch(Exception e){           
            ConBanco.getTrans().rollback();
            throw new Exception(e);
        }finally{
            ConBanco.fecharCon();
            
        }
        
    }
    public static List<Cliente>listarTodos() throws Exception{
        List<Cliente> lista;
         try{
            ConBanco.abrirCon();
            Criteria crit = ConBanco.getSessao().createCriteria(Cliente.class);
            lista=crit.list();   
           return lista;
        }catch(Exception e){
            throw new Exception(e);    
        }finally{
            ConBanco.fecharCon();
            
        }
       
    }
}
