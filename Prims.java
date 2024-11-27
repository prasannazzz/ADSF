import java.util.*;
class PrimsAlgo {
    // Function to find the vertex with the minimum weight not yet included in MST
    int getMinVertex(int[] weight, boolean[] inMST, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < n; i++) {
            if (!inMST[i] && weight[i] < min) {
                min = weight[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Function to find MST using Prim's Algorithm
    void findMST(int n, int[][] graph) {
        int[] parent = new int[n];  // To store MST
        int[] weight = new int[n]; // To store minimum weights
        boolean[] inMST = new boolean[n]; // To track vertices in MST

        // Initialize all weights as INFINITE and inMST[] as false
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }

        // Start with the first vertex
        weight[0] = 0;
        parent[0] = -1;

        // MST will have n vertices
        for (int i = 0; i < n - 1; i++) {
            int u = getMinVertex(weight, inMST, n); // Pick minimum weight vertex not yet in MST
            inMST[u] = true; // Add the vertex to MST

            // Update weights of its neighbors
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < weight[v]) {
                    parent[v] = u;
                    weight[v] = graph[u][v];
                }
            }
        }

        // Print the MST
        System.out.println("Edges in the MST:");
        int totalCost = 0;
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " -- " + i + " == " + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }
        System.out.println("Total Cost = " + totalCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrimsAlgo prim = new PrimsAlgo();
        int n = 0; // Number of vertices
        int[][] graph = null; // Graph as adjacency matrix

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Initialize Graph");
            System.out.println("2. Find MST");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of vertices: ");
                    n = sc.nextInt();
                    graph = new int[n][n];
                    System.out.println("Enter the adjacency matrix:");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            graph[i][j] = sc.nextInt();
                        }
                    }
                    System.out.println("Graph initialized successfully.");
                    break;

                case 2:
                    if (graph == null || n == 0) {
                        System.out.println("Graph not initialized. Please initialize first.");
                    } else {
                        System.out.println("Calculating MST...");
                        prim.findMST(n, graph);
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
