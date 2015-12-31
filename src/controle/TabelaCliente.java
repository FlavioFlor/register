package controle;

import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import modelo.Cliente;

public class TabelaCliente extends AbstractTableModel{

    private List<Cliente> dados;
    private String[] colunas = new String[] {"Nome Cliente","Data de Nascimento","Email"};
    
    public TabelaCliente (List<Cliente>lista){
        this.dados = lista;
        this.addTableModelListener(new TableModelListener() {
           @Override
           public void tableChanged(TableModelEvent tme) {
               int linha = tme.getFirstRow();
               Cliente c = dados.get(linha);
           }
       } );
    }
    
    public Cliente getRow(int linha){
         return this.dados.get(linha);
    }
    
    public void addRow(Cliente cli){
        this.dados.add(cli);
        fireTableDataChanged();
    }
    public void removeRow(int linha){
        this.dados.remove(linha);
        fireTableRowsDeleted(linha, linha);
    }
    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return true;
    }
    @Override
    public String getColumnName(int col){
        return colunas[col];
    }
    @Override
    public int getRowCount() {
      return dados.size();
    }

    @Override
    public int getColumnCount() {
       return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return dados.get(linha).getNome();
            case 1: return dados.get(linha).getIdade();
            case 2: return dados.get(linha).getEmail();
        }
        return "Não Localizado";
    }
    @Override
    public void setValueAt(Object valor, int linha, int coluna){
        if( valor == null) return;        
        switch(coluna){
            case 0: dados.get(linha).setNome( (String) valor);
            case 1: dados.get(linha).setIdade((String)valor);
            case 2: dados.get(linha).setEmail((String) valor);
                
        this.fireTableRowsUpdated(linha, linha);
        } 
        this.fireTableRowsUpdated(linha, linha);
    }
    
};
    

