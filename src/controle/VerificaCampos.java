
package controle;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class VerificaCampos {
    
    public static void verificaCampos(String txt)throws RegraTratamentos{
    
        if((txt.isEmpty()) || (txt == null)){
            throw new RegraTratamentos("Campo obrigatorio vazio !");
        }
    }
    public static void verificaAddCli(Cliente c) throws RegraTratamentos, Exception {
        
        List <Cliente> lista = ClienteDAO.listarTodos();       
        for (Cliente cli : lista) {
            if(cli.getNome().equals(c.getNome())) {
              throw new RegraTratamentos("Não é possivel salvar, cliente já¡ existe !");
            }
        }
    }
    
    public static Cliente verificaExcCli(String x,Cliente clie)throws RegraTratamentos, Exception{
        List <Cliente> lista = ClienteDAO.listarTodos();                
                    
            for (Cliente cli : lista) {
                if (cli.getNome().equals(x)) {
                int resposta =  JOptionPane.showConfirmDialog(null,"Você tem certeza "
                + "que quer excluir Cliente "
                +cli.getNome()+" ?"); 
                    if (resposta == JOptionPane.YES_OPTION) {
                        clie = cli;
                        return clie;
                    }
                    else if(resposta == JOptionPane.NO_OPTION){
                        return null;
                    }else if (resposta == JOptionPane.CANCEL_OPTION){
                        return null;
                    }else{
                        return null;
                    }
               }
            }
        return null;               
    }
    
    public static void verificaAtualizar(String txt)throws RegraTratamentos{
    
        if((txt.isEmpty()) || (txt == null)){
            throw new RegraTratamentos("Por favor preencher todos os campos obrigatorios !");
        }
    }
    
    public static Cliente atualizaCampos(Cliente c,String x) throws RegraTratamentos,Exception{   
        List <Cliente> lista = ClienteDAO.listarTodos();
        
        for (Cliente cli : lista) {
            if(cli.getNome().equals(x)){
                cli = c;
                return cli;
            }
        }
        return null;
    }
}
