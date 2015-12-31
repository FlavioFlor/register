package visao;

import controle.ClienteDAO;
import controle.ConBanco;
import java.awt.Container;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import controle.FabricaJanela;
import controle.TabelaCliente;
import controle.VerificaCampos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import modelo.Cliente;

public class JanelaCliente extends JFrame implements ActionListener{

    private Container cont;
    private JLabel jlNome,jlData,jlEmail;
    private JTextField txtNome,txtEmail;
    private JFormattedTextField ftfData;
    private MaskFormatter mfData;
    private JButton btSalvarCli,btNovoCli,btEdiCli,btBuscaCli
                ,btExcCli;
    private JPanel painelCadastroCli;
    private JTable tabelaCli;
    private TabelaCliente tabCli;
    ////////////////////////////////////////////////////////////////////////////////////////
    private FabricaJanela fab;
   
    public JanelaCliente() throws Exception{
		this.cont = super.getContentPane();
		this.fab = new FabricaJanela(this.cont);
                ///////////////////////////////////////////////
		this.painelCadastroCli = fab.constPainel("Cadastro & Consulta de Clientes","Clientes", null);
                        //LABEL 
                this.jlNome = fab.constJLabel(25, 0, "Nome :", painelCadastroCli);
		this.jlEmail = fab.constJLabel(25, 30, "E-mail :", painelCadastroCli);
		this.jlData = fab.constJLabel(25, 60, "Idade :",painelCadastroCli);
			//Texts
		this.txtNome = fab.constJTextField(80,40,25,"true",painelCadastroCli);
                this.txtEmail = fab.constJTextField(80, 70,25,"true",painelCadastroCli);
                this.mfData = fab.consMask("##/##/####");
                this.ftfData = fab.constFormattedTextField(80, 100, mfData, painelCadastroCli);
                        //Table
                this.tabCli = new TabelaCliente(ClienteDAO.listarTodos());
                this.tabelaCli = fab.constJTable(25, 200, tabCli, painelCadastroCli);
                        //Buttons
                this.btNovoCli = fab.constJButton(360, 40, "Limpar",painelCadastroCli);
                this.btNovoCli.addActionListener(this);
                this.btNovoCli.setActionCommand("novo");
                //botao salvar
                this.btSalvarCli = fab.constJButton(360, 80, "Salvar",painelCadastroCli);
                this.btSalvarCli.addActionListener(this);
                this.btSalvarCli.setActionCommand("salvar");
                this.btExcCli = fab.constJButton(360, 120, "Excluir",painelCadastroCli);
                this.btExcCli.addActionListener(this);
                this.btExcCli.setActionCommand("excluir");
                
                this.confJanela();
    }
    
    private void limpaCampos(){
            this.txtNome.setText("");
            this.txtEmail.setText("");
            this.ftfData.setText("");          
    }                
    private void confJanela() {
		super.setSize(800, 600);
		super.setVisible(true);
		super.setLocation(400, 80);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setTitle("Cadastro de Pessoas");
		super.setDefaultCloseOperation(JanelaCliente.EXIT_ON_CLOSE);                
                
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //Eventos de botoes
    @Override
    public void actionPerformed(ActionEvent e) {
       switch (e.getActionCommand()){
           case "salvar":
               try {
                    
                    Cliente cli = new Cliente();
                    cli.setNome(this.txtNome.getText());
                    VerificaCampos.verificaCampos(this.txtNome.getText());
                    
                    cli.setEmail(this.txtEmail.getText());
                    VerificaCampos.verificaCampos(this.txtEmail.getText());
                    
                    cli.setIdade(this.ftfData.getText());
                    VerificaCampos.verificaCampos(this.ftfData.getText());
                 
                    VerificaCampos.verificaAddCli(cli);
                    ClienteDAO.adicionar(cli);
                    JOptionPane.showMessageDialog(null, "Cadastrado !");
                    this.tabCli.addRow(cli);
                    limpaCampos();
                    
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null,e1.getMessage());
                    limpaCampos();
                }finally {
                   ConBanco.fecharCon();
                }
           break;
           case "novo":
               try {
                   limpaCampos();
               } catch (Exception e2) {
                   JOptionPane.showMessageDialog(null, e2.getMessage());
               }finally {
                   ConBanco.fecharCon();
                }
           break;
           case "excluir":
               try {
                    Cliente c = new Cliente();                   
                    if(this.tabelaCli.getSelectedRow()== -1){
                        JOptionPane.showMessageDialog(null, "Clique no cliente desejado !");
                    }else{                        
                        c.setNome(this.tabCli.getRow(this.tabelaCli.getSelectedRow()).getNome());
                        String x = c.getNome();                            
                        c = VerificaCampos.verificaExcCli(x,c);                       
                        if(c == null){
                            break;
                        }else{
                        ClienteDAO.remover(c);
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
                        this.tabCli.removeRow(this.tabelaCli.getSelectedRow());
                        }
                    }
                   
               } catch (Exception e4){
                   e4.getMessage();
               }
            finally {
                ConBanco.fecharCon();
            }
           break;
           case "editar":
               try {
                   Cliente c = new Cliente();                           
                   if(this.tabelaCli.getSelectedRow()== -1){
                        JOptionPane.showMessageDialog(null, "Clique no cliente desejado !");
                    }else{
                    c.setNome(this.tabCli.getRow(this.tabelaCli.getSelectedRow()).getNome());
                    this.txtNome.setText(c.getNome());
                    }
                    
               } catch (Exception e5) {
               }
           finally{
              ConBanco.fecharCon();
           }
       }
    }
}