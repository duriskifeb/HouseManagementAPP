package STD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import STD.Util.InputUtilities;

public class MenuPenjual {
    private RumahTree rumahTree;

    public MenuPenjual(RumahTree rumahTree) {
        this.rumahTree = rumahTree;

        // Data dummy untuk penjual
        rumahTree.tambahRumah("Rumah Sejahtera", "Jl. Kebahagiaan No. 1", 500000000, "penjual1");
        rumahTree.tambahRumah("Rumah Damai", "Jl. Perdamaian No. 2", 750000000, "penjual1");
        rumahTree.tambahRumah("Rumah Maju", "Jl. Kemajuan No. 3", 600000000, "penjual2");
    }

    public void tampilkanMenu(Scanner scanner, String username) {
        while (true) {
            InputUtilities.cls();
            System.out.println("--- Menu Penjual ---");
            System.out.println("\n[ Selamat datang, " + username + " ]\n");
            System.out.println("1. Tambah Rumah");
            System.out.println("2. Lihat Rumah Saya");
            System.out.println("3. Cek History Transaksi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            InputUtilities.pressEnter();

            switch (pilihan) {
                case 1:
                    tambahRumah(scanner, username);
                    InputUtilities.pressEnter();
                    break;
                case 2:
                    lihatRumahSaya(username);
                    InputUtilities.pressEnter();
                    break;
                case 3:
                    cekHistoryTransaksi(username);
                    InputUtilities.pressEnter();
                    break;
                case 4:
                    System.out.println("Keluar dari Menu Penjual...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    InputUtilities.pressEnter();
            }
        }
    }

    private void tambahRumah(Scanner scanner, String username) {
        InputUtilities.cls();
        System.out.println("\n--- Tambah Rumah ---");
        System.out.print("\nMasukkan Nama Rumah: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Alamat Rumah: ");
        String alamat = scanner.nextLine();

        int harga = 0;
        boolean validHarga = false;
        while (!validHarga) {
            System.out.print("Masukkan Harga Rumah: ");
            if (scanner.hasNextInt()) {
                harga = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
                if (harga > 0) {
                    validHarga = true;
                } else {
                    System.out.println("Harga rumah harus lebih besar dari 0.");
                }
            } else {
                System.out.println("Harga harus menggunakan angka, bukan huruf!");
                scanner.nextLine(); // Membersihkan buffer jika input bukan angka
            }
        }

        rumahTree.tambahRumah(nama, alamat, harga, username);
        System.out.println("Rumah berhasil ditambahkan!");
    }

    private void lihatRumahSaya(String username) {
        InputUtilities.cls();
        System.out.println("--- Rumah Saya ---");
        List<Rumah> rumahSaya = rumahTree.getRumahByOwner(username);

        if (rumahSaya.isEmpty()) {
            System.out.println("Anda belum menambahkan rumah.");
        } else {
            int nomor = 1;
            boolean adaRumahBelumTerjual = false;

            for (Rumah rumah : rumahSaya) {
                if (!rumah.isTerjual()) {
                    System.out.println(
                            nomor + ". Nama: " + rumah.getNama() + ", Alamat: " + rumah.getAlamat() + ", Harga: "
                                    + rumah.getHarga());
                    nomor++;
                    adaRumahBelumTerjual = true;
                }
            }

            if (!adaRumahBelumTerjual) {
                System.out.println("Semua rumah Anda sudah terjual.");
                for (Rumah rumah : rumahSaya) {
                    if (rumah.isTerjual()) {
                        System.out.println(
                                "Rumah \"" + rumah.getNama() + "\" telah dibeli oleh " + rumah.getPembeli() + ".");
                    }
                }
            }
        }
    }

    private void cekHistoryTransaksi(String username) {
        InputUtilities.cls();
        System.out.println("--- History Transaksi ---");
        List<Rumah> rumahSaya = rumahTree.getRumahByOwner(username);

        boolean adaTransaksi = false;
        for (Rumah rumah : rumahSaya) {
            if (rumah.getPembeli() != null) {
                System.out.println("Rumah Anda yang bernama '" + rumah.getNama() + "' di alamat '" + rumah.getAlamat()
                        + "' dengan harga " + rumah.getHarga() + " telah dibeli oleh pembeli: " + rumah.getPembeli());
                adaTransaksi = true;
            }
        }

        if (!adaTransaksi) {
            System.out.println("Tidak ada transaksi yang tersedia saat ini untuk penjual " + username);
        }
    }
}
