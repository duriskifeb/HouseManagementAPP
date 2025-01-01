package STD;

public class Transaksi {
    private String jenisTransaksi; // "Pembelian Rumah" atau "Isi Ulang"
    private String pembeli;
    private String penjual;
    private String rumahNama;
    private int jumlahUang; // Untuk transaksi isi ulang
    private String tanggal;

    // Konstruktor untuk transaksi pembelian rumah
    public Transaksi(String jenisTransaksi, String pembeli, String penjual, String rumahNama, String tanggal) {
        this.jenisTransaksi = jenisTransaksi;
        this.pembeli = pembeli;
        this.penjual = penjual;
        this.rumahNama = rumahNama;
        this.jumlahUang = 0; // Tidak ada uang untuk pembelian rumah
        this.tanggal = tanggal;
    }

    // Konstruktor untuk transaksi isi ulang
    public Transaksi(String jenisTransaksi, String pembeli, int jumlahUang, String tanggal) {
        this.jenisTransaksi = jenisTransaksi;
        this.pembeli = pembeli;
        this.penjual = null; // Tidak ada penjual untuk transaksi isi ulang
        this.rumahNama = null; // Tidak ada rumah yang dibeli
        this.jumlahUang = jumlahUang;
        this.tanggal = tanggal;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public String getPembeli() {
        return pembeli;
    }

    public String getPenjual() {
        return penjual;
    }

    public String getRumahNama() {
        return rumahNama;
    }

    public int getJumlahUang() {
        return jumlahUang;
    }

    public String getTanggal() {
        return tanggal;
    }

    @Override
    public String toString() {
        if (jenisTransaksi.equals("Pembelian Rumah")) {
            return "Pembelian Rumah: " + rumahNama + " oleh " + pembeli + " dari " + penjual + " pada " + tanggal;
        } else {
            return "Isi Ulang oleh " + pembeli + " sebesar " + jumlahUang + " pada " + tanggal;
        }
    }
}
