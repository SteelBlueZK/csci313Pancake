public class LinkedQueue<E> implements Queue<E> {

    private static class Node<E>{
        Node<E> _next = null;
        E _value = null;
        public Node(){

        }
        public Node(E element){
            _value = element;
        }
    }

    Node<E> _head = null;
    Node<E> _tail = null;
    int _size = 0;

    @Override
    public void enqueue(E element) {
        if (isEmpty()){
            _head = new Node<>(element);
            _tail = _head;
        } else {
            _tail._next = new Node<>(element);
            _tail = _tail._next;
        }
        _size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            return null;
        else {
            E temp = _head._value;
            _head = _head._next;
            if (_head == null) {
                _tail = null;
            }
            _size--;
            return temp;
        }
    }

    @Override
    public E first() {
        if (isEmpty())
            return null;
        else
            return _head._value;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }
}
