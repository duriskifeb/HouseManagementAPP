package STD;

import java.util.ArrayList;

public class RiwayatPembelian {
    private ArrayList<String> riwayat;

    public RiwayatPembelian() {
        this.riwayat = new ArrayList<>();
    }

    // Menambahkan riwayat pembelian
    public void tambahRiwayat(String username, Rumah rumah) {
        riwayat.add("Pembeli: " + username + ", Rumah: " + rumah);
    }

    // Menampilkan riwayat pembelian
    public void tampilkanRiwayat() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat pembelian.");
        } else {
            System.out.println("\n--- Riwayat Pembelian ---");
            for (String entry : riwayat) {
                System.out.println(entry);
            }
        }
    }
}
