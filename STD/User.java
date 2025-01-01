package STD;

public class User {
    private String username;
    private String password;
    private String phone;
    private String role;
    private double saldo;

    public User(String username, String password, String phone, String role) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void isiUlangSaldo(double jumlah) {
        if (jumlah > 0) {
            this.saldo += jumlah;
            System.out.println("Saldo berhasil ditambahkan sebesar: " + jumlah);
        } else if (jumlah < 0) {
            if (this.saldo + jumlah >= 0) { // Cek apakah saldo cukup untuk pengurangan
                this.saldo += jumlah; // Jika jumlah negatif, saldo akan berkurang
                System.out.println("Saldo berhasil dikurangi sebesar: " + Math.abs(jumlah));
            } else {
                System.out.println("Saldo tidak cukup untuk melakukan transaksi.");
            }
        }
    }    

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
