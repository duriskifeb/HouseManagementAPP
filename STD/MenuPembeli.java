package STD;

import java.util.Scanner;

import STD.Util.InputUtilities;

import java.util.ArrayList;

public class MenuPembeli {
    private RumahTree rumahTree;
    private ArrayList<Rumah> historyTransaksi; // Menyimpan history transaksi pembeli
    private ArrayList<Double> historyTopup; // Menyimpan history top-up saldo

    // Konstruktor menerima RumahTree
    public MenuPembeli(RumahTree rumahTree) {
        this.rumahTree = rumahTree;
        this.historyTransaksi = new ArrayList<>(); // Inisialisasi history transaksi
        this.historyTopup = new ArrayList<>(); // Inisialisasi history top-up
    }

    public void tampilkanMenuPembeli(Scanner scanner, User user) {
        while (true) {
            InputUtilities.cls();
            System.out.println("[ Selamat datang, di menu Penjual ]");
            System.out.println("[ Saldo Anda: " + user.getSaldo() + "]");
            System.out.println("\n--- Menu Pembeli ---");
            System.out.println("1. Tampilkan Rumah");
            System.out.println("2. Beli Rumah");
            System.out.println("3. Tampilkan History Transaksi");
            System.out.println("4. Isi Ulang Saldo");
            System.out.println("5. Tampilkan History Top-up");
            System.out.println("6. Keluar\n");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    tampilkanRumah();
                    break;
                case 2:
                    beliRumah(scanner, user);
                    break;
                case 3:
                    tampilkanHistoryTransaksi();
                    break;
                case 4:
                    isiUlangSaldo(scanner, user);
                    break;
                case 5:
                    tampilkanHistoryTopup();
                    break;
                case 6:
                    System.out.println("Keluar dari Menu Pembeli...");
                    return; // Keluar dari menu pembeli
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void tampilkanRumah() {
        rumahTree.tampilkanRumah(); // Tampilkan data rumah dari RumahTree
        InputUtilities.pressEnter();
    }

    private void beliRumah(Scanner scanner, User user) {
        System.out.print("Masukkan nama rumah yang ingin dibeli: ");
        String namaRumah = scanner.nextLine();

        Rumah rumah = rumahTree.cariRumah(namaRumah); // Cari rumah berdasarkan nama

        if (rumah != null) {
            System.out.println("Saldo Anda sebelum pembelian: " + user.getSaldo()); // Debugging saldo sebelum pembelian
            if (user.getSaldo() >= rumah.getHarga()) {
                // Simulasikan pembelian rumah
                System.out.println("Anda berhasil membeli rumah: " + rumah.getNama());
                historyTransaksi.add(rumah); // Tambahkan rumah yang dibeli ke dalam history transaksi
                rumahTree.hapusRumah(rumah.getNama()); // Hapus rumah yang sudah dibeli dari RumahTree
                user.isiUlangSaldo(-rumah.getHarga()); // Kurangi saldo pembeli sesuai harga rumah
                rumah.setPembeli(user.getUsername()); // Set pembeli rumah
                System.out.println("Saldo Anda setelah pembelian: " + user.getSaldo()); // Debugging saldo setelah
                                                                                        // pembelian
            } else {
                System.out.println("Saldo Anda tidak cukup untuk membeli rumah ini.");
            }
        } else {
            System.out.println("Rumah dengan nama '" + namaRumah + "' tidak ditemukan, silakan cek ulang stok.");
        }
    }

    private void tampilkanHistoryTransaksi() {
        if (historyTransaksi.isEmpty()) {
            System.out.println("Anda belum membeli rumah.");
        } else {
            System.out.println("\n--- History Transaksi ---");
            for (int i = 0; i < historyTransaksi.size(); i++) {
                Rumah rumah = historyTransaksi.get(i);
                System.out.println((i + 1) + ". Nama: " + rumah.getNama() + ", Alamat: " + rumah.getAlamat()
                        + ", Harga: " + rumah.getHarga());
            }
        }
    }

    private void isiUlangSaldo(Scanner scanner, User user) {
        while (true) {
            System.out.print("Masukkan jumlah saldo yang ingin diisi: ");

            // Mengecek apakah input adalah angka
            if (scanner.hasNextDouble()) {
                double jumlah = scanner.nextDouble();
                scanner.nextLine(); // Membersihkan buffer

                if (jumlah <= 0) {
                    System.out.println("Jumlah saldo harus lebih besar dari 0.");
                } else {
                    user.isiUlangSaldo(jumlah); // Tambahkan saldo ke akun pengguna
                    historyTopup.add(jumlah); // Tambahkan jumlah top-up ke historyTopup
                    System.out.println("Saldo berhasil diisi ulang. Saldo Anda sekarang: " + user.getSaldo());
                    break; // Keluar dari loop setelah saldo berhasil diisi
                }
            } else {
                System.out.println("Gagal isi saldo ngab! Masukkan angka, bukan huruf.");
                scanner.nextLine(); // Membersihkan buffer jika input bukan angka
            }
        }
    }

    private void tampilkanHistoryTopup() {
        if (historyTopup.isEmpty()) {
            System.out.println("Anda belum melakukan top-up saldo.");
        } else {
            System.out.println("\n--- History Top-up ---");
            for (int i = 0; i < historyTopup.size(); i++) {
                System.out.println((i + 1) + ". Jumlah: " + historyTopup.get(i));
            }
        }
    }
}
