public class ListStack<E> implements Stack<E> {

    private static class Node<E>{
        private E _element;
        private Node<E> _next;

        public Node(E ele) {
            _element = ele;
            _next = null;
        }

        public E get_element() {
            return _element;
        }

        public void set_element(E _element) {
            this._element = _element;
        }

        public Node<E> get_next() {
            return _next;
        }

        public void set_next(Node<E> _next) {
            this._next = _next;
        }
    }

    private Node<E> _head;
    private int _size = 0;

    public ListStack(){
        _head = null;
    }

    @Override
    public void push(E value) {
        Node<E> temp = new Node<>(value);
        temp.set_next(_head);
        _head = temp;
        _size++;
    }

    @Override
    public E pop() {
        if (this.isEmpty()){
            return null;
        }
        E temp = _head.get_element();
        _head = _head.get_next();
        _size--;
        return temp;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return _head.get_element();
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _head == null;
    }
}
