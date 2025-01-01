package STD;

import java.util.LinkedList;

public class RumahNode {
    Rumah rumah; // Rumah yang disimpan dalam node ini
    LinkedList<RumahNode> anakNodes; // List untuk anak-anak node (multi-tree)
    LinkedList<Rumah> rumahListByOwner; // List untuk rumah berdasarkan owner

    // Constructor untuk inisialisasi node
    public RumahNode(Rumah rumah) {
        this.rumah = rumah;
        this.anakNodes = new LinkedList<>(); // Inisialisasi list anak node
        this.rumahListByOwner = new LinkedList<>(); // Inisialisasi list rumah berdasarkan owner
        this.rumahListByOwner.add(rumah); // Menambahkan rumah pertama ke list
    }

    // Menambahkan anak node baru
    public void addAnakNode(RumahNode anakNode) {
        this.anakNodes.add(anakNode);
    }

    // Menambahkan rumah ke rumahListByOwner (untuk owner yang sama)
    public void addRumahToOwnerList(Rumah rumah) {
        if (!rumahListByOwner.contains(rumah)) { // Cegah duplikasi rumah
            this.rumahListByOwner.add(rumah);
        }
    }

    // Menghapus anak node tertentu
    public void removeAnakNode(RumahNode anakNode) {
        this.anakNodes.remove(anakNode);
    }

    // Mendapatkan list semua anak node
    public LinkedList<RumahNode> getAnakNodes() {
        return this.anakNodes;
    }

    // Mendapatkan list rumah berdasarkan owner
    public LinkedList<Rumah> getRumahListByOwner() {
        return this.rumahListByOwner;
    }

    // Mendapatkan informasi rumah dari node ini
    public Rumah getRumah() {
        return this.rumah;
    }

    @Override
    public String toString() {
        return "RumahNode{" +
                "rumah=" + rumah +
                ", anakNodes=" + anakNodes.size() +
                ", rumahListByOwner=" + rumahListByOwner.size() +
                '}';
    }
}
