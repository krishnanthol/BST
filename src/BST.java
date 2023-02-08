public class BST<E extends Comparable<E>>
{
    private TreeNode<E> root;
    private int size;
    private String st;

    public BST()
    {
        root = null;
        size = 0;
    }

    public void add(E val)
    {
        if(root == null)
        {
            root = new TreeNode<E>(val);
            size++;
        }
        else
        {
            add(root, val);
        }
    }

    private void add(TreeNode<E> curr, E val)
    {
        int comp = val.compareTo(curr.value);
        if(comp < 0)
        {
            if(curr.left == null)
            {
                curr.left = new TreeNode<E>(val);
                size++;
            }
            else
            {
                add(curr.left,val);
            }
        }

        else if (comp > 1)
        {
            if(curr.right == null)
            {
                curr.right = new TreeNode<E>(val);
                size++;
            }
            else
            {
                add(curr.right,val);
            }
        }
    }
    private class TreeNode <E extends Comparable<E>>
    {
        private E value;
        private TreeNode<E> left;
        private TreeNode<E> right;

        private TreeNode(E val)
        {
            value = val;
            left = right = null;
        }

        public String toString()
        {
            return value.toString();
        }
    }

    public void print()
    {
        if (root == null)
            System.out.print("Empty Tree");
        print(root, 0, "");
        System.out.println();
    }

    private void print(TreeNode<E> curr, int depth, String s)
    {
        if (curr == null)
            return;
        for (int i = 1; i <= depth - 1; i++)
            System.out.print("|\t");
        if (depth == 0)
            System.out.println("[" + curr.value + "] <- root (L___ left, R___ right)");
        if (depth > 0)
        {
            System.out.print(s);
            System.out.println(curr.value);
        }
        print(curr.left, depth + 1, "L___"); // indicates left or "less than" side
        print(curr.right, depth + 1, "R___");  // indicate right or "greater than" side
    }

    public String inOrder()
    {
        if(root == null)
        {
           st += "[]";
        }
        inOrder(root);
        return st;
    }

    private void inOrder(TreeNode<E> curr)
    {
        if(root.left == null)
        {

        }
    }

    public static void main (String[] args)
    {
        BST<Integer> binary = new BST<>();
        binary.add(2);
        binary.add(5);
        binary.add(7);
        binary.add(1);
        binary.add(8);
        binary.add(1);
        binary.print();
        System.out.print(binary.size);
    }
}
