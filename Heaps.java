import java.util.*;
class maxandminheap{
    public static void maxheapify(int[] arr, int n, int i){
        int lar=i;
        int left=2*i+1;
        int right=2*i+2;
        if(left<n && arr[left]>arr[lar]){
            lar=left;
        }
        if(right<n && arr[right]>arr[lar]){
            lar=right;
        }
        if(lar!=i){
            int temp=arr[i];
            arr[i]=arr[lar];
            arr[lar]=temp;
            maxheapify(arr,n,lar);
        }
    }
    public static void maxbuildheap(int[] arr){
        int n=arr.length;
        for(int i=(n/2)-1;i>=0;i--){
            maxheapify(arr,n,i);
        }
    }
    public static void minbuildheap(int[] arr){
        int n=arr.length;
        for(int i=(n/2)-1;i>=0;i--){
            minheapify(arr,n,i);
        }
    }
    public static void minheapify(int[] arr, int n, int i){
        int small=i;
        int left=2*i+1;
        int right=2*i+2;
        if(left<n && arr[left]<arr[small]){
            small=left;
        }
        if(right<n && arr[right]<arr[small]){
            small=right;
        }
        if(small!=i){
            int temp=arr[i];
            arr[i]=arr[small];
            arr[small]=temp;
            maxheapify(arr,n,small);
        }
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the number of students");
        int n = sc.nextInt();
        int[] marks = new int[n];

        for(int i=0;i<n;i++){
            marks[i]=sc.nextInt();
        }
        int[] marks1=marks;
        maxbuildheap(marks);
        System.out.println(marks[0]);
        minbuildheap(marks1);
        System.out.println(marks1[0]);
    }
}
//in java priorityqueue is inbuilt as a minheap
//for it to be made to work as maxheap
//syntax is
//PriorityQueue<datatype> pq = new PriorityQueue<>(Collections.reverseOrder());
