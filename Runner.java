import java.util.Arrays;

abstract class UF
{
    protected int[] id;

    public UF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }

    public String toString()
    {
        return str(id);
    }

    protected String str(int[] a)
    {
        StringBuilder b = new StringBuilder();

        for (int i : a)
        {
            b.append(i);
            b.append(' ');
        }

        return b.toString();
    }

    public abstract boolean connected(int p, int q);
    public abstract void union(int p, int q);
}

class QuickFindUF extends UF
{
    public QuickFindUF(int N)
    {
        super(N);
    }

    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
    }

    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++)
        {
            if (id[i] == pid) 
            {
                id[i] = qid;
            }
        }
    }
}

class QuickUnionUF extends UF
{
    protected int[] sz;
    public QuickUnionUF(int N)
    {
        super(N);
        sz = new int[N];
        Arrays.fill(sz, 1);
    }

    private int root(int i)
    {
        while (i != id[i]) 
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (sz[i] > sz[j])
             { id[j] = i; sz[i] += sz[j]; }
        else { id[i] = j; sz[j] += sz[i]; }
    }

    public String toString()
    {
        return super.toString() + " (" + str(sz) + ")";
    }
}

class Runner
{
    public static void main(String args[])
    {
        System.out.println("Hello Java");

        int size = 10;
        UF[] algs = { new QuickFindUF(size), new QuickUnionUF(size) };

        for (UF uf: algs)
        {
            System.out.println(uf.getClass().getName());
            System.out.println(uf.toString());

            uf.union(1, 3);
            uf.union(5, 9);
            uf.union(8, 3);
            System.out.println(uf.toString());
            System.out.println(uf.connected(1, 8));
        }
    }
}