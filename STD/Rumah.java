package STD;

// Rumah.java
public class Rumah {
    private String nama;
    private String alamat;
    private int harga;
    private String owner;
    private String pembeli;
    private boolean terjual; // Status apakah rumah sudah terjual

    // Constructor
    public Rumah(String nama, String alamat, int harga, String owner) {
        this.nama = nama;
        this.alamat = alamat;
        this.harga = harga;
        this.owner = owner;
        this.terjual = false; // Default: rumah belum terjual
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getHarga() {
        return harga;
    }

    public String getOwner() {
        return owner;
    }

    public String getPembeli() {
        return pembeli;
    }

    public boolean isTerjual() {
        return terjual;
    }

    public void setTerjual(boolean terjual) {
        this.terjual = terjual;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
        this.terjual = true; // Jika pembeli diatur, rumah otomatis dianggap terjual
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Alamat: " + alamat + ", Harga: " + harga +
               ", Penjual: " + owner + 
               (terjual ? ", Sold Out": ", ");
    }
}
