public interface Queue<E> {

    void enqueue(E element);
    E dequeue();
    E first();
    int size();
    boolean isEmpty();

}
