package controle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

public class FabricaJanela  {

	private Container cont;
	private JTabbedPane painelPai;
	
	
	public FabricaJanela(Container cont){
		this.cont = cont;
		this.cont.setLayout(new BorderLayout());
                
		this.painelPai = new JTabbedPane();
                Border borda = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
                painelPai.setBorder(borda);
                painelPai.setBackground(Color.LIGHT_GRAY);
		this.cont.add(this.painelPai,BorderLayout.CENTER);
	}
	
	public JLabel constJLabel(int x,int y,String titulo, JPanel painel) {
		JLabel lb = new JLabel(titulo);
                lb.setFont(new Font("Italic", Font.CENTER_BASELINE, 14));
		lb.setBounds(x, y, 300, 100);
		painel.add(lb);
		return lb;
	}
        public JCheckBox constJCheckBox(int x,int y,String titulo, JPanel painel) {
		JCheckBox jcb = new JCheckBox(titulo);
                jcb.setFont(new Font("Italic", Font.CENTER_BASELINE, 14));
                jcb.setBackground(Color.LIGHT_GRAY);
                jcb.setBounds(x, y, 120, 30);
		painel.add(jcb);
		return jcb;
	}
	
	public JTextField constJTextField(int x,int y,int tam,String edt,JPanel painel) {
		JTextField txt = new JTextField(tam);
		txt.setBounds(x, y, 230, 20);
                boolean edit = Boolean.parseBoolean(edt);
                txt.setEditable(edit);
		painel.add(txt);
		return txt;
	}

	public MaskFormatter consMask(String x) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(x);
                        mask.setValidCharacters("0123456789");
                    	mask.setPlaceholderCharacter(' ');
                        
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return mask;
	}
	
	public JFormattedTextField constFormattedTextField(int x,int y, MaskFormatter mf,JPanel painel){
		JFormattedTextField jft = new JFormattedTextField(mf);
		jft.setBounds(x, y, 100, 20);
		painel.add(jft);
		return jft;
	}
        
        public JComboBox<Object> constJComboBox(int x, int y, List<Object> lista,JPanel painel) {
		
		JComboBox<Object> combo = new JComboBox<>();
		for (Object opcao : lista) {
			combo.addItem(opcao);                       
                        combo.setEditable(true);
		}
                combo.setBounds(x, y, 100, 20);
		painel.add(combo);
		return combo;
	}
        
	
        
        public JButton constJButton(int x, int y,String titulo,JPanel painel){
        JButton jbBt = new JButton(titulo);
            jbBt.setBounds(x, y, 110, 30);
            jbBt.setFont(new Font("Dialog", Font.CENTER_BASELINE, 14));
            jbBt.setBackground(Color.LIGHT_GRAY);
            painel.add(jbBt);
            return jbBt;
        }
       
        public JTable constJTable(int x, int y,TableModel mod,JPanel painel){
            
            JTable tab = new JTable(mod);
            JScrollPane scroll = new JScrollPane(tab);
            scroll.setBounds(x, y, 730, 325);
            painel.add(scroll);
            return tab;
        }
        
    public JPanel constPainel(String titulo, String titulo2, LayoutManager layout){

     JPanel painel = new JPanel(layout);
     Border borda = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE);
     painel.setBorder(BorderFactory.createTitledBorder(borda, titulo2));
     painel.setOpaque(true);
     painel.setBackground(Color.LIGHT_GRAY);
     painel.setVisible(true);
     this.painelPai.addTab(titulo,painel);
     
     return painel;
    }
}
