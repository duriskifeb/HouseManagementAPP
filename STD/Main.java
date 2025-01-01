package STD;

import java.util.Scanner;
import STD.Util.InputUtilities; // Import kelas InputUtilities

public class Main {
    private static RumahTree rumahTree = new RumahTree();

    public static void main(String[] args) {
        UserTree userTree = new UserTree();
        Scanner scanner = new Scanner(System.in);

        // Preload data rumah
        rumahTree.preloadingData();

        // Tambahkan data admin default
        User adminUser = new User("admin", "admin123", "082142387119", "admin");
        userTree.insert(adminUser);

        // Ini buat account user dummy
        userTree.insert(new User("penjual1", "password1", "081234567890", "penjual"));
        userTree.insert(new User("penjual2", "password2", "081234567891", "penjual"));
        userTree.insert(new User("pembeli1", "beli1", "081234567892", "pembeli"));
        userTree.insert(new User("pembeli2", "beli2", "081234567893", "pembeli"));

        while (true) {
            try {
                InputUtilities.cls();
                System.out.println("\n=== Rumah Sale ===");
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Keluar\n");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Konsumsi karakter newline

                InputUtilities.pressEnter();

                switch (pilihan) {
                    case 1:
                        menuRegister(scanner, userTree);
                        break;

                    case 2:
                        menuLogin(scanner, userTree);
                        break;

                    case 3:
                        System.out.println("Keluar dari aplikasi...\n");
                        InputUtilities.cls();
                        return;

                    default:
                        System.out.println("\n[ Pilihan tidak valid. Silakan pilih menu yang tersedia. ]");
                        InputUtilities.pressEnter();
                }
            } catch (Exception e) {
                System.out.println("\n[ Input tidak valid. Masukkan angka sesuai menu yang tersedia. ]");
                scanner.nextLine(); // Konsumsi input yang tidak valid
                InputUtilities.pressEnter();
            }
        }
    }

    private static void menuRegister(Scanner scanner, UserTree userTree) {
        InputUtilities.cls();
        System.out.print("\nMasukkan username: ");
        String username = scanner.nextLine();

        if (username.equalsIgnoreCase("admin")) {
            System.out.println("\n[ Tidak dapat mendaftar sebagai admin. Akun admin sudah tersedia. ]");
            InputUtilities.pressEnter();
            return;
        }

        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        System.out.print("Masukkan no telepon: ");
        String noTelepon = scanner.nextLine();

        System.out.print("Masukkan role (penjual/pembeli): ");
        String role = scanner.nextLine();

        if (!role.equalsIgnoreCase("penjual") && !role.equalsIgnoreCase("pembeli")) {
            System.out.println("\n[ Role tidak valid. Hanya 'penjual' atau 'pembeli' yang diperbolehkan. ]");
            InputUtilities.pressEnter();
            return;
        }

        User newUser = new User(username, password, noTelepon, role.toLowerCase());
        userTree.insert(newUser);
        System.out.println("\n[ Pendaftaran berhasil! ]");
        InputUtilities.pressEnter();
    }

    private static void menuLogin(Scanner scanner, UserTree userTree) {
        InputUtilities.cls();
        System.out.print("\nMasukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        User user = userTree.search(username);
        if (user != null && user.validatePassword(password)) {
            System.out.println("\n[ Login berhasil ]");
            InputUtilities.pressEnter();
            redirectToRoleMenu(user, scanner);
        } else {
            System.out.println("\n[ Username atau password salah. ]");
            InputUtilities.pressEnter();
        }
    }

    private static void redirectToRoleMenu(User user, Scanner scanner) {
        switch (user.getRole().toLowerCase()) {
            case "admin":
                adminMenu(scanner, user);
                break;
            case "penjual":
                penjualMenu(scanner, user);
                break;
            case "pembeli":
                pembeliMenu(scanner, user);
                break;
            default:
                System.out.println("\n[ Role tidak valid. Silakan hubungi dukungan. ]");
                InputUtilities.pressEnter();
        }
    }

    private static void adminMenu(Scanner scanner, User user) {
        MenuAdmin menuAdmin = new MenuAdmin(rumahTree, null);
        menuAdmin.tampilkanMenuAdmin(scanner, user.getUsername());
    }

    private static void penjualMenu(Scanner scanner, User user) {
        MenuPenjual menuPenjual = new MenuPenjual(rumahTree);
        menuPenjual.tampilkanMenu(scanner, user.getUsername());
    }

    private static void pembeliMenu(Scanner scanner, User user) {
        MenuPembeli menuPembeli = new MenuPembeli(rumahTree);
        menuPembeli.tampilkanMenuPembeli(scanner, user); // Berikan objek user
    }
}
