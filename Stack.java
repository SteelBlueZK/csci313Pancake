public interface Stack<E> {
    public abstract void push(E value);
    public abstract E pop();
    public abstract E top();
    public abstract int size();
    public abstract boolean isEmpty();
}
