package STD;

public class UserTree {
    private Node root;

    public UserTree() {
        root = null;
    }

    // Menambahkan pengguna ke dalam tree
    public void insert(User user) {
        root = insertRec(root, user);
    }

    // Rekursif untuk menambahkan node baru
    private Node insertRec(Node root, User user) {
        if (root == null) {
            root = new Node(user);
            return root;
        }

        // Menambahkan node ke kiri atau kanan berdasarkan username
        if (user.getUsername().compareTo(root.user.getUsername()) < 0) {
            root.left = insertRec(root.left, user);
        } else if (user.getUsername().compareTo(root.user.getUsername()) > 0) {
            root.right = insertRec(root.right, user);
        }

        return root;
    }

    // Mencari pengguna berdasarkan username
    public User search(String username) {
        return searchRec(root, username);
    }

    private User searchRec(Node root, String username) {
        if (root == null || root.user.getUsername().equals(username)) {
            return root != null ? root.user : null;
        }

        if (username.compareTo(root.user.getUsername()) < 0) {
            return searchRec(root.left, username);
        }

        return searchRec(root.right, username);
    }
}

