package inventarisMrAudio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class penyimpananBarang extends AbstractTableModel {
    List<barang> list = new ArrayList<>();
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public int getColumnCount(){
        return 3;
    }

    @Override
    synchronized public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getNamaBarang();
            case 1:
                return list.get(rowIndex).getJumlahBarang();
            case 2:
                return list.get(rowIndex).getKeadaanBarang();
            default:
                return "";
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column) {
            case 0:
                return "Nama Barang";
            case 1:
                return "Jumlah Barang";
            case 2:
                return "KeadaanBarang";
            default:
                return "";
        }
    }
    
    public void tambahBarang(barang b){
        list.add(b);
        fireTableRowsInserted(getRowCount(), getColumnCount());
    }
    
    public void editBarang(int i, barang b) {
        if (i >= 0 && i < list.size()) {
            list.set(i, b);
            fireTableRowsUpdated(i, i);
        }
    }

    public void hapusBarang(int row) {
        if (row >= 0 && row < list.size()) {
            list.remove(row);
            fireTableRowsDeleted(row, row); 
        }
    }

    public barang get(int row) {
        return list.get(row);
    }

}
