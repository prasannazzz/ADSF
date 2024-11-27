import java.util.Scanner;

class Node {
    int data; // Data stored in the node
    Node left, right; // Pointers to the left and right child
    boolean visited; // Used to track whether the node has been visited

    // Constructor to initialize the node
    public Node(int data) {
        this.data = data; // Assign the data
        this.left = null; // Initialize left child as null
        this.right = null; // Initialize right child as null
        this.visited = false; // Initialize visited flag as false
    }
}

class Tree {
    Node root; // Root of the binary search tree

    // Constructor to initialize the tree
    public Tree() {
        this.root = null; // Start with an empty tree
    }

    // Method to insert a node into the binary search tree
    public void insert(int data) {
        Node newNode = new Node(data); // Create a new node with the given data

        if (root == null) {
            root = newNode; // Set the new node as the root if the tree is empty
            return;
        }

        Node curr = root; // Start traversal from the root
        Node parent = null; // To keep track of the parent node

        // Traverse the tree to find the correct position for the new node
        while (curr != null) {
            parent = curr; // Update the parent node
            if (data < curr.data) {
                curr = curr.left; // Move to the left child if data is smaller
            } else {
                curr = curr.right; // Move to the right child if data is larger
            }
        }

        // Attach the new node to the parent
        if (data < parent.data) {
            parent.left = newNode; // Set as the left child
        } else {
            parent.right = newNode; // Set as the right child
        }
    }

    // Method to find the in-order predecessor of a node
    private Node getPredecessor(Node curr) {
        if (curr == null || curr.left == null) {
            return null; // No predecessor if left child doesn't exist
        }
        Node temp = curr.left; // Start from the left child
        while (temp.right != null && temp.right != curr) {//imp
            temp = temp.right; // Move to the rightmost node in the left subtree
        }
        return temp; // Return the predecessor
    }

    // Perform in-order traversal using threading
    public void threadedTraversal() {
        Node curr = root; // Start traversal from the root

        // Continue traversal until all nodes are visited
        while (curr != null) {
            if (curr.left == null) {
                // If no left child, visit the current node
                System.out.print(curr.data + " ");
                curr = curr.right; // Move to the right child
            } else {
                // If a left child exists, find the in-order predecessor
                Node predecessor = getPredecessor(curr);

                if (predecessor.right == null) {
                    // If no thread exists, create one pointing to the current node
                    predecessor.right = curr;
                    curr = curr.left; // Move to the left child
                } else {
                    // If thread exists, remove the thread and visit the current node
                    predecessor.right = null;
                    System.out.print(curr.data + " ");
                    curr = curr.right; // Move to the right child
                }
            }
        }
    }
    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert a node");
            System.out.println("2. Perform inorder traversal");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the key to insert: ");
                    int key = scanner.nextInt();
                    tree.insert(key);
                    System.out.println("Node inserted successfully.");
                    break;

                case 2:
                    System.out.println("Inorder Traversal:");
                    tree.threadedTraversal();
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}




