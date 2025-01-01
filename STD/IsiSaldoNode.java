package STD;

// Node untuk menyimpan riwayat isi saldo
public class IsiSaldoNode {
    private double jumlah;
    private IsiSaldoNode next;

    public IsiSaldoNode(double jumlah) {
        this.jumlah = jumlah;
        this.next = null;
    }

    public double getJumlah() {
        return jumlah;
    }

    public IsiSaldoNode getNext() {
        return next;
    }

    public void setNext(IsiSaldoNode next) {
        this.next = next;
    }
}
