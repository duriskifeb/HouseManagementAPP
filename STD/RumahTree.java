package STD;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RumahTree {
    private RumahNode root;

    // Menambahkan rumah baru ke dalam tree
    public void tambahRumah(String nama, String alamat, int harga, String owner) {
        Rumah rumahBaru = new Rumah(nama, alamat, harga, owner);
        if (root == null) {
            root = new RumahNode(rumahBaru);
        } else {
            insert(root, rumahBaru);
        }
        System.out.println("[ Rumah berhasil ditambahkan ]\n" + rumahBaru);
    }

    // Fungsi untuk memasukkan rumah baru ke dalam tree
    private void insert(RumahNode node, Rumah rumah) {
        if (node.rumah.getNama().equalsIgnoreCase(rumah.getNama())) {
            node.addRumahToOwnerList(rumah);
            return;
        }

        boolean added = false;
        for (int i = 0; i < node.anakNodes.size(); i++) {
            RumahNode anakNode = node.anakNodes.get(i);
            if (anakNode.rumah.getNama().compareToIgnoreCase(rumah.getNama()) > 0) {
                node.anakNodes.add(i, new RumahNode(rumah));
                added = true;
                break;
            }
        }

        if (!added) {
            node.anakNodes.add(new RumahNode(rumah));
        }
    }

    // Menampilkan daftar rumah
    public void tampilkanRumah() {
        if (root == null) {
            System.out.println("Belum ada rumah yang ditambahkan.");
        } else {
            System.out.println("Daftar Rumah yang di jual :");
            inOrderTraversal(root);
        }
    }

    private void inOrderTraversal(RumahNode node) {
        if (node != null) {
            for (Rumah rumah : node.rumahListByOwner) {
                System.out.println(rumah);
            }
            for (RumahNode anakNode : node.anakNodes) {
                inOrderTraversal(anakNode);
            }
        }
    }

    public Rumah cariRumah(String namaRumah) {
        return cariRumah(root, namaRumah);
    }

    private Rumah cariRumah(RumahNode node, String namaRumah) {
        if (node == null) {
            return null;
        }
        if (node.rumah.getNama().equalsIgnoreCase(namaRumah)) {
            return node.rumah;
        }
        for (RumahNode anakNode : node.anakNodes) {
            Rumah rumah = cariRumah(anakNode, namaRumah);
            if (rumah != null) {
                return rumah;
            }
        }
        return null;
    }

    public void hapusRumah(String namaRumah) {
        root = hapus(root, namaRumah);
    }

    private RumahNode hapus(RumahNode node, String namaRumah) {
        if (node == null) {
            return null;
        }

        if (node.rumah.getNama().equalsIgnoreCase(namaRumah)) {
            if (!node.anakNodes.isEmpty()) {
                RumahNode pengganti = node.anakNodes.remove(0);
                pengganti.anakNodes.addAll(node.anakNodes);
                return pengganti;
            }
            return null; // Hapus node tanpa anak
        }

        for (int i = 0; i < node.anakNodes.size(); i++) {
            RumahNode anakNode = node.anakNodes.get(i);
            node.anakNodes.set(i, hapus(anakNode, namaRumah));
        }
        return node;
    }

    public void preloadingData() {
        tambahRumah("Rumah Asri", "Jl. Kebun Raya No. 1", 500000000, "Pemilik A");
        tambahRumah("Villa Hijau", "Jl. Taman Hijau No. 5", 750000000, "Pemilik B");
        tambahRumah("Apartemen Elit", "Jl. Sudirman No. 10", 1000000000, "Pemilik C");
        tambahRumah("Rumah Harmoni", "Jl. Melati Indah No. 15", 650000000, "Pemilik D");
        tambahRumah("Cluster Lavender", "Jl. Lavender No. 8", 800000000, "Pemilik E");
    }

    public List<Rumah> getRumahByOwner(String username) {
        List<Rumah> result = new ArrayList<>();
        getRumahByOwner(root, username, result);
        return result;
    }

    private void getRumahByOwner(RumahNode node, String username, List<Rumah> result) {
        if (node == null) {
            return;
        }

        for (Rumah rumah : node.rumahListByOwner) {
            if (rumah.getOwner().equalsIgnoreCase(username)) {
                result.add(rumah);
            }
        }

        for (RumahNode anakNode : node.anakNodes) {
            getRumahByOwner(anakNode, username, result);
        }
    }
}
