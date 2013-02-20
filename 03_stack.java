import java.util.Iterator;

class LinkedStackOfStrings
{
    private Node first = null;

    private class Node
    {
        String item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void push(final String newItem)
    {
        first = new Node()
        {{
            item = newItem;
            next = first;
        }};
    }

    public String pop()
    {
        String item = first.item;
        first = first.next;
        return item;
    }
}

class ArrayStackOfStrings
{
    private String[] s;
    private int N = 0;

    public ArrayStackOfStrings()
    {
        s = new String[1];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push(String item)
    {
        if (N == s.length)
        {
            resize(s.length * 2);
        }
        s[N++] = item;
    }

    public String pop()
    {
        String item = s[--N];
        s[N] = null;

        if (N > 0 && N == s.length / 4)
        {
            resize(s.length / 2);
        }

        return item;
    }

    private void resize(int capacity)
    {
        String[] copy = new String[capacity];

        System.arraycopy(s, 0, copy, 0, N);

        s = copy;
    }
}

class QueueOf<T> implements Iterable<T>
{
    private class Node
    {
        T item;
        Node next;
    }

    private class QueueIterator implements Iterator<T>
    {
        private Node current = first;

        public boolean hasNext() { return current != null; }
        public void remove() {}
        public T next()
        {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<T> iterator() { return new QueueIterator(); }

    private Node first, last;

    public void enqueue(final T newItem)
    {
        Node oldlast = last;
        last = new Node()
        {{
            item = newItem;
            next = null;
        }};
        if (isEmpty())
        {
            first = last;
        }
        else
        {
            oldlast.next = last;
        }
    }

    public T dequeue()
    {
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty()
    {
        return first == null;
    }
}

class StackRunner
{
    public static void main(String args[])
    {
        ArrayStackOfStrings stack = new ArrayStackOfStrings();
        for (String arg : args)
        {
            if (arg.equals("-"))
            {
                if (!stack.isEmpty())
                    System.out.println(stack.pop());
            }
            else
            {
                stack.push(arg);
            }
        }
    }
}

class QueueRunner
{
    public static void main(String args[])
    {
        QueueOf<String> queue = new QueueOf<String>();
        for (String arg : args)
        {
            if (arg.equals("-"))
            {
                if (!queue.isEmpty())
                    System.out.println(queue.dequeue());
            }
            else
            {
                queue.enqueue(arg);
            }
        }

        for (String item: queue)
        {
            System.out.println(item);
        }
    }
}