package STD;

import java.util.LinkedList;

public class MultiLinkedList {

    private LinkedList<Transaksi> transaksiList;

    public MultiLinkedList() {
        transaksiList = new LinkedList<>();
    }

    // Menambahkan transaksi ke dalam list
    public void tambahTransaksi(Transaksi transaksi) {
        transaksiList.add(transaksi);
    }

    // Menampilkan transaksi isi ulang
    public void tampilkanTransaksiIsiUlang() {
        if (transaksiList.isEmpty()) {
            System.out.println("Tidak ada transaksi isi ulang.");
        } else {
            for (Transaksi transaksi : transaksiList) {
                if (transaksi.getJenisTransaksi().equals("Isi Ulang")) {
                    System.out.println("1. " + transaksi.getPembeli() + " berhasil isi ulang senilai " + transaksi.getJumlahUang() + " pada " + transaksi.getTanggal());
                }
            }
        }
    }

    // Menampilkan transaksi pembelian rumah
    public void tampilkanTransaksiPembelianRumah() {
        if (transaksiList.isEmpty()) {
            System.out.println("Tidak ada transaksi pembelian rumah.");
        } else {
            for (Transaksi transaksi : transaksiList) {
                if (transaksi.getJenisTransaksi().equals("Pembelian Rumah")) {
                    System.out.println("1. Rumah " + transaksi.getRumahNama() + " dibeli oleh " + transaksi.getPembeli() + " dari " + transaksi.getPenjual() + " pada " + transaksi.getTanggal());
                }
            }
        }
    }

    // Mendapatkan daftar transaksi
    public LinkedList<Transaksi> getTransaksiList() {
        return transaksiList;
    }
}
