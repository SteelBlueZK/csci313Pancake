public class ArrayStack<E> implements Stack<E>{
    private E [] _data;
    private int _size = 0;
    public ArrayStack(){
        _data = (E[])new Object[10];
    }
    public ArrayStack(int StartSize){
        _data = (E[])new Object[StartSize];
    }

    @Override
    public void push(E value) {
        if (_size == _data.length) {
            E[] temp = (E[])new Object[_data.length * 2];
            for(int i = 0; i < _data.length; i++){
                temp[i] = _data[i];
            }
            _data = temp;
        }
        _data[_size] = value;
        _size += 1;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null;
        }
        E temp = _data[_size - 1];
        _size -= 1;
        return temp;
    }

    @Override
    public E top() {
        if (isEmpty()){
            return null;
        }
        return _data[_size - 1];
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
