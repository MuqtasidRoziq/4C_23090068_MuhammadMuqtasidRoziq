package inventarisHadroh;

public class barang {
    String namaBarang, keadaanBarang; 
    int jumlahBarang;
    
    public barang(String nb, int jb, String kb){
        this.namaBarang = nb;
        this.jumlahBarang = jb;
        this.keadaanBarang = kb;
    }
    
    public String getNamaBarang(){
        return namaBarang;
    }
    
    public int getJumlahBarang(){
        return jumlahBarang;
    }
    
    public String getKeadaanBarang(){
        return keadaanBarang;
    }
}
