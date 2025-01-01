package STD;

import java.util.Scanner;

public class MenuAdmin {

    private RumahTree rumahTree;
    private MultiLinkedList transaksiList; // Menyimpan transaksi (termasuk pembelian rumah dan isi ulang)

    public MenuAdmin(RumahTree rumahTree, MultiLinkedList transaksiList) {
        this.rumahTree = rumahTree;
        this.transaksiList = transaksiList; // Inisialisasi transaksi list
    }

    public void tampilkanMenuAdmin(Scanner scanner, String username) {
        while (true) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("Selamat datang, " + username + "!");
            System.out.println("1. Lihat Semua Produk");
            System.out.println("2. Lihat Semua Transaksi");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (pilihan) {
                case 1:
                    lihatSemuaProduk();
                    break;
                case 2:
                    lihatSemuaTransaksi(scanner);
                    break;
                case 3:
                    System.out.println("Keluar dari Menu Admin...");
                    return; // Keluar dari loop dan kembali ke menu sebelumnya
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void lihatSemuaProduk() {
        System.out.println("\n--- Daftar Semua Rumah ---");
        rumahTree.tampilkanRumah();
    }

    private void lihatSemuaTransaksi(Scanner scanner) {
        System.out.println("\n--- Pilih Jenis Transaksi ---");
        System.out.println("1. Transaksi Pembelian Rumah");
        System.out.println("2. Transaksi Isi Ulang");
        System.out.println("3. Kembali");
        System.out.print("Pilih menu: ");
        
        int pilihan = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (pilihan) {
            case 1:
                tampilkanTransaksiPembelianRumah();
                break;
            case 2:
                tampilkanTransaksiIsiUlang();
                break;
            case 3:
                return; // Kembali ke menu admin
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private void tampilkanTransaksiPembelianRumah() {
        System.out.println("\n--- Daftar Transaksi Pembelian Rumah ---");
        transaksiList.tampilkanTransaksiPembelianRumah();
    }

    private void tampilkanTransaksiIsiUlang() {
        System.out.println("\n--- Daftar Transaksi Isi Ulang ---");
        transaksiList.tampilkanTransaksiIsiUlang();
    }

    // Menambahkan transaksi baru (baik pembelian rumah atau isi ulang)
    public void tambahTransaksi(Transaksi transaksi) {
        transaksiList.tambahTransaksi(transaksi);
    }
}
