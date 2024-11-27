import java.util.Scanner;

class Node {
    int data; // Value stored in the node
    Node left; // Pointer to the left child
    Node right; // Pointer to the right child

    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    void insert(int value) {
        root = insert(root, value);
        System.out.println("Inserted " + value);
    }

    boolean search(int value) {
        return search(root, value);
    }

    void remove(int value) {
        root = remove(root, value);
        System.out.println("Deleted " + value);
    }

    void display() {
        System.out.print("In-order Traversal: ");
        inOrder(root);
        System.out.println();
    }

    void update(int oldValue, int newValue) {
        if (!search(oldValue)) {
            System.out.println("Value " + oldValue + " not found in the tree.");
            return;
        }
        remove(oldValue);
        insert(newValue);
        System.out.println("Updated value " + oldValue + " to " + newValue);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.data) {
            node.left = insert(node.left, value);
        } else if (value > node.data) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    private boolean search(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.data) {
            return true;
        }
        if (value < node.data) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    private Node remove(Node node, int value) {
        if (node == null) {
            return node;
        }
        if (value < node.data) {
            node.left = remove(node.left, value);
        } else if (value > node.data) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node temp = minValueNode(node.right);
            node.data = temp.data;
            node.right = remove(node.right, temp.data);
        }
        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Binary Search Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Update");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Insert
                    System.out.print("Enter value to insert: ");
                    int insertValue = sc.nextInt();
                    tree.insert(insertValue);
                    break;

                case 2: // Search
                    System.out.print("Enter value to search: ");
                    int searchValue = sc.nextInt();
                    System.out.println("Searching for " + searchValue + ": " + (tree.search(searchValue) ? "Found" : "Not Found"));
                    break;

                case 3: // Delete
                    System.out.print("Enter value to delete: ");
                    int deleteValue = sc.nextInt();
                    tree.remove(deleteValue);
                    break;

                case 4: // Display
                    tree.display();
                    break;

                case 5: // Update
                    System.out.print("Enter value to update: ");
                    int oldValue = sc.nextInt();
                    System.out.print("Enter new value: ");
                    int newValue = sc.nextInt();
                    tree.update(oldValue, newValue);
                    break;

                case 6: // Exit
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}


