import java.util.*;

class CircularQueue {
    private int[] queue;
    private int front, rear, size, capacity;
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int order) {
        if (isFull()) {
            System.out.println("The queue is full! Cannot take more orders.");
            return;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = order;
        size++;
        System.out.println("Order " + order + " added.");
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("The queue is empty! No orders to serve.");
            return -1;
        }
        int order = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Order " + order + " served.");
        if (isEmpty()) {
            front = rear = -1;
        }
        return order;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The queue is empty! No orders.");
            return;
        }
        System.out.print("Current orders: ");
        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("The queue is empty! No orders to peek.");
            return -1;
        }
        System.out.println("Next order to serve: " + queue[front]);
        return queue[front];
    }

    public void cancelOrder(int order) {
        if (isEmpty()) {
            System.out.println("The queue is empty! No orders to cancel.");
            return;
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (queue[(front + i) % capacity] == order) {
                index = (front + i) % capacity;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Order " + order + " not found in the queue.");
            return;
        }
        for (int i = index; i != rear; i = (i + 1) % capacity) {
            queue[i] = queue[(i + 1) % capacity];
        }
        rear = (rear - 1 + capacity) % capacity;
        size--;
        System.out.println("Order " + order + " canceled.");
        if (isEmpty()) {
            front = rear = -1;
        }
    }

    public int countOrders() {
        System.out.println("Total orders in the queue: " + size);
        return size;
    }

    public void clearQueue() {
        front = rear = -1;
        size = 0;
        System.out.println("All orders cleared.");
    }

    public void modifyOrder(int oldOrder, int newOrder) {
        if (isEmpty()) {
            System.out.println("The queue is empty! No orders to modify.");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (queue[(front + i) % capacity] == oldOrder) {
                queue[(front + i) % capacity] = newOrder;
                System.out.println("Order " + oldOrder + " modified to " + newOrder + ".");
                return;
            }
        }
        System.out.println("Order " + oldOrder + " not found in the queue.");
    }

    public boolean searchOrder(int order) {
        if (isEmpty()) {
            System.out.println("The queue is empty! No orders to search.");
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (queue[(front + i) % capacity] == order) {
                System.out.println("Order " + order + " is in the queue.");
                return true;
            }
        }
        System.out.println("Order " + order + " not found in the queue.");
        return false;
    }

    public double calculateAverageOrderValue() {
        if (isEmpty()) {
            System.out.println("The queue is empty! Cannot calculate average.");
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += queue[(front + i) % capacity];
        }
        double average = (double) sum / size;
        System.out.println("Average order value: " + average);
        return average;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the maximum number of orders the pizza parlor can accept:");
        int maxOrders = scanner.nextInt();
        CircularQueue orderQueue = new CircularQueue(maxOrders);

        while (true) {
            System.out.println("\nPizza Parlor System:");
            System.out.println("1. Place an order");
            System.out.println("2. Serve an order");
            System.out.println("3. Display orders");
            System.out.println("4. Peek next order");
            System.out.println("5. Cancel an order");
            System.out.println("6. Count total orders");
            System.out.println("7. Clear all orders");
            System.out.println("8. Modify an order");
            System.out.println("9. Search for an order");
            System.out.println("10. Calculate average order value");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the order number to place: ");
                    int order = scanner.nextInt();
                    orderQueue.enqueue(order);
                    break;

                case 2:
                    orderQueue.dequeue();
                    break;

                case 3:
                    orderQueue.display();
                    break;

                case 4:
                    orderQueue.peek();
                    break;

                case 5:
                    System.out.print("Enter the order number to cancel: ");
                    int cancelOrder = scanner.nextInt();
                    orderQueue.cancelOrder(cancelOrder);
                    break;

                case 6:
                    orderQueue.countOrders();
                    break;

                case 7:
                    orderQueue.clearQueue();
                    break;

                case 8:
                    System.out.print("Enter the old order number: ");
                    int oldOrder = scanner.nextInt();
                    System.out.print("Enter the new order number: ");
                    int newOrder = scanner.nextInt();
                    orderQueue.modifyOrder(oldOrder, newOrder);
                    break;

                case 9:
                    System.out.print("Enter the order number to search: ");
                    int searchOrder = scanner.nextInt();
                    orderQueue.searchOrder(searchOrder);
                    break;

                case 10:
                    orderQueue.calculateAverageOrderValue();
                    break;

                case 11:
                    System.out.println("Exiting the system. Have a great day!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
