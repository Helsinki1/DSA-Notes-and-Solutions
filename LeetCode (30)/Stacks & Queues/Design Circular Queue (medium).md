```java
class MyCircularQueue {

    private int[] queue;
    private int front, rear, numElements;

    public MyCircularQueue(int k) {
        queue = new int[k];
    }
    
    public boolean enQueue(int value) {
        if (numElements==queue.length) return false;
        queue[rear] = value;
        if (++rear == queue.length) rear = 0;
        numElements++;
        return true;
    }
    
    public boolean deQueue() {
        if (numElements==0) return false;
        if (++front == queue.length) front = 0;
        numElements--;
        return true;
    }
    
    public int Front() {
        return (numElements==0)? -1 : queue[front];
    }
    
    public int Rear() {
        if (numElements==0) return -1;
        if (rear==0) return queue[queue.length-1];
        return queue[rear - 1];
    }
    
    public boolean isEmpty() {
        return numElements == 0;
    }
    
    public boolean isFull() {
        return numElements == queue.length;
    }
}
```
