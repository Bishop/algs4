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

class FixedCapacityStackOfStrigs
{
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrigs(int capacity)
    {
        s = new String[capacity];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push(String item)
    {
        s[N++] = item;
    }

    public String pop()
    {
        return s[--N];
    }
}

class StackRunner
{
    public static void main(String args[])
    {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
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