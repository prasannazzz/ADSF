import java.util.*;
class Node {
    int parent;
    int rank;
    Node() {
        parent =-1;
        rank = 0;
    }
}
class Edge {
    int src;
    int dst;
    int wt;
    Edge(int src, int dst, int wt) {
        this.src = src;
        this.dst = dst;
        this.wt = wt;
    }
}
public class Kruskals {
    static ArrayList<Node> dsuf = new ArrayList<>();
    static ArrayList<Edge> mst = new ArrayList<>();
    public static int find(int v) {
        if (dsuf.get(v).parent == -1) {
            return v;
        }
        return dsuf.get(v).parent = find(dsuf.get(v).parent);
    }

    public static void unionOp(int fromP, int toP) {
        if (dsuf.get(fromP).rank > dsuf.get(toP).rank) {
            dsuf.get(toP).parent = fromP;
        } else if (dsuf.get(fromP).rank < dsuf.get(toP).rank) {
            dsuf.get(fromP).parent = toP;
        } else {
            dsuf.get(fromP).parent = toP;
            dsuf.get(toP).rank++;
        }
    }
    public static void kruskals(ArrayList<Edge> edgeList, int V, int E) {
        // Sorting edges based on weight
        Collections.sort(edgeList, Comparator.comparingInt(e -> e.wt));
        int i = 0, j = 0;
        while (i < V - 1 && j < E) {
            int fromP = find(edgeList.get(j).src);
            int toP = find(edgeList.get(j).dst);
            if (fromP == toP) {
                j++;
                continue;
            }
            unionOp(fromP, toP);
            mst.add(edgeList.get(j));
            i++;
        }
    }
    public static void printMST(ArrayList<Edge> mst) {
        //System.out.println("MST formed is:");
        int mstSum = 0;
        for (Edge e : mst) {
            System.out.println("src: " + e.src + "  dst: " + e.dst + "  wt: " + e.wt);
            mstSum += e.wt; // Add the weight of each edge to the total
        }
        System.out.println("Total weight of MST: " + mstSum);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //   System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        //   System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        for (int i = 0; i < V; i++) {
            dsuf.add(new Node());
        }
        ArrayList<Edge> edgeList = new ArrayList<>();
        // System.out.println("Enter edges (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int wt = sc.nextInt();
            edgeList.add(new Edge(from, to, wt));
        }

        kruskals(edgeList, V, E);
        printMST(mst);

        sc.close();
    }
}
