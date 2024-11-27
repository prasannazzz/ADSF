import java.util.*;
class Edge{
    int src;
    int dest;
    int weight;
    Edge(int s, int v, int wt){
        this.src=s;
        this.dest=v;
        this.weight=wt;
    }
}
class prims{
    static class Pair implements Comparable<Pair>{
        int v;
        int weight;
        Pair(int v , int wt){
            this.v=v;
            this.weight=wt;
        }
        public int compareTo(Pair p2){
            return this.weight - p2.weight;
        }
    }
    public static void creategraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));
        graph[1].add(new Edge(1,3,7));
        graph[1].add(new Edge(1,2,1));
        graph[2].add(new Edge(2,4,3));
        graph[3].add(new Edge(3,5,1));
        graph[4].add(new Edge(4,3,2));
        graph[4].add(new Edge(4,5,5));
    }
    public static int primsalgo(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int mst=0;
        pq.add(new Pair(0,0));
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                mst+=curr.weight;
                for(int i=0;i<graph[curr.v].size();i++){
                    Edge e = graph[curr.v].get(i);
                    if(!vis[e.dest]){
                        pq.add(new Pair(e.dest,e.weight));
                    }
                }
            }
        }
        return mst;
    }
    public static int[] djikstra(ArrayList<Edge> graph[], int src){
        boolean vis[] = new boolean[graph.length];
        int[] dist= new int[graph.length];
        for(int i=0;i<graph.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src,0));
        while(!pq.isEmpty()){
            Pair curr=pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                for(int i=0;i<graph[curr.v].size();i++){
                    Edge e=graph[curr.v].get(i);
                    int u=e.src;
                    int v= e.dest;
                    if(!vis[v] && dist[u]+e.weight<dist[v]){
                        dist[v]=dist[u]+e.weight;
                        pq.add(new Pair(v,dist[v]));
                    }
                }
            }
        }
        return dist;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int v=6;
        ArrayList<Edge> graph[] = new ArrayList[v];
        creategraph(graph);
        int cost=primsalgo(graph);
        System.out.println(cost);
        int src=0;
        int[] dist=djikstra(graph,src);
        for(int i=0;i<dist.length;i++){
            System.out.print("Distance form " + i + " is " + dist[i]);
            System.out.println();
        }
    }
}