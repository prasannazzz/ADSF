import java.util.*;
class StackUsingLinkedList {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }
    private Node top;
    private int size;

    public StackUsingLinkedList() {
        top = null;
        size = 0;
    }
    public boolean isEmpty() {
        return top == null;
    }
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println(data + " pushed to stack.");
    }
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return top.data;
    }
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        int poppedData = top.data;
        top = top.next;
        size--;
        return poppedData;
    }
    public int size() {
        return size;
    }
    public int search(int element) {//Important can deduce others through same approach
        Node temp = top;
        int position = 1;
        while (temp != null) {
            if (temp.data == element) {
                return position;
            }
            temp = temp.next;
            position++;
        }
        return -1;
    }
    public boolean contains(int element) {
        return search(element) != -1;
    }
    public void clear() {
        top = null;
        size = 0;
        System.out.println("Stack has been cleared.");
    }
    public int getBottomElement() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        Node temp = top;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.data;
    }
    public void duplicateTop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot duplicate.");
            return;
        }
        push(top.data); // Simply push the current top element again
        System.out.println("Top element duplicated.");
    }
    public void swapTopTwo() {
        if (size < 2) {
            System.out.println("Not enough elements to swap.");
            return;
        }
        int first = pop();
        int second = pop();
        push(first);
        push(second);
        System.out.println("Top two elements swapped.");
    }
    public void reverse() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        Node prev = null;
        Node current = top;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        top = prev;
        System.out.println("Stack reversed.");
    }
    public List<Integer> getAllElements() {
        List<Integer> elements = new ArrayList<>();
        Node temp = top;
        while (temp != null) {
            elements.add(temp.data);
            temp = temp.next;
        }
        return elements;
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        Node temp = top;
        System.out.println("Stack elements:");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Stack Using Linked List ===");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Get Size");
            System.out.println("5. Search");
            System.out.println("6. Check if Contains");
            System.out.println("7. Get Bottom Element");
            System.out.println("8. Duplicate Top");
            System.out.println("9. Swap Top Two");
            System.out.println("10. Reverse Stack");
            System.out.println("11. Clear Stack");
            System.out.println("12. Display Stack");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter a number to push: ");
                    int data = scanner.nextInt();
                    stack.push(data);
                    break;
                case 2:
                    int popped = stack.pop();
                    if (popped != -1) {
                        System.out.println("Popped element: " + popped);
                    }
                    break;
                case 3:
                    int top = stack.peek();
                    if (top != -1) {
                        System.out.println("Top element: " + top);
                    }
                    break;
                case 4:
                    System.out.println("Stack size: " + stack.size());
                    break;
                case 5:
                    System.out.print("Enter an element to search: ");
                    int searchElement = scanner.nextInt();
                    int position = stack.search(searchElement);
                    if (position != -1) {
                        System.out.println("Element found at position: " + position);
                    } else {
                        System.out.println("Element not found in stack.");
                    }
                    break;
                case 6:
                    System.out.print("Enter an element to check if it exists: ");
                    int containsElement = scanner.nextInt();
                    if (stack.contains(containsElement)) {
                        System.out.println("Element exists in stack.");
                    } else {
                        System.out.println("Element not found in stack.");
                    }
                    break;
                case 7:
                    int bottom = stack.getBottomElement();
                    if (bottom != -1) {
                        System.out.println("Bottom element: " + bottom);
                    }
                    break;
                case 8:
                    stack.duplicateTop();
                    break;
                case 9:
                    stack.swapTopTwo();
                    break;
                case 10:
                    stack.reverse();
                    break;
                case 11:
                    stack.clear();
                    break;
                case 12:
                    stack.display();
                    break;
                case 13:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}