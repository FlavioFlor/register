package controle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class ConBanco {
 
    private static Session sessao;
    private static Transaction trans;
    
    public static void abrirCon()throws Exception{
       try{ 
        ConBanco.sessao=HibernateUtil.getSessionFactory().openSession();
        ConBanco.trans=ConBanco.sessao.beginTransaction();
       }catch(Exception e ){
           throw new Exception(e);  
       } 
        
    }
    public static void fecharCon(){
        if (ConBanco.sessao.isOpen() && ConBanco.sessao!=null) {
          //HibernateUtil.getSessionFactory().close();
           ConBanco.sessao.close();
        }
        
    }

    public static Session getSessao() {
        return sessao;
    }

    public static void setSessao(Session sessao) {
        ConBanco.sessao = sessao;
    }

    public static Transaction getTrans() {
        return trans;
    }

    public static void setTrans(Transaction trans) {
        ConBanco.trans = trans;
    }
}
