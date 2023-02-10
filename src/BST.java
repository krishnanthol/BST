public class BST<E extends Comparable<E>>
{
    private TreeNode<E> root;
    private int size;
    private String st = "";

    public BST()
    {
        root = null;
        size = 0;
    }

    public int size()
    {
        return size;
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

    public boolean contains(E val)
    {
        if(root.value == val)
        {
            return true;
        }
        else
        {
            TreeNode<E> temp = root;
            while(val.compareTo(temp.value) != 0)
            {
                if(val.compareTo(temp.value) < 0)
                {
                    if(temp.left != null)
                        temp = temp.left;
                    else
                        return false;
                }
                else if(val.compareTo(temp.value) > 0)
                {
                    if(temp.right != null)
                        temp = temp.right;
                    else
                        return false;
                }
            }
            return true;
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

        else if (comp > 0)
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
        if(size == 0)
        {
           return "[]";
        }
        else
        {
            st+="[";
            inOrder(root);
            String temp = st.substring(0,st.length()-2) + "]";
            st = "";
            return temp;
        }
    }
    private void inOrder(TreeNode<E> curr)
    {
        if(curr != null)
        {
            inOrder(curr.left);
            st+=curr.value+", ";
            inOrder(curr.right);
        }
    }

    public String preOrder()
    {
        if(size == 0)
        {
            return "[]";
        }
        else
        {
            st+="[";
            preOrder(root);
            String temp = st.substring(0,st.length()-2) + "]";
            st = "";
            return temp;
        }
    }
    public void preOrder(TreeNode<E> curr)
    {
        if(curr != null)
        {
            st+=curr.value+", ";
            preOrder(curr.left);
            preOrder(curr.right);
        }
    }

    public String postOrder()
    {
        if(size == 0)
        {
            return "[]";
        }
        else
        {
            st+="[";
            postOrder(root);
            String temp = st.substring(0,st.length()-2) + "]";
            st = "";
            return temp;
        }
    }
    public void postOrder(TreeNode<E> curr)
    {
        if(curr != null)
        {
            postOrder(curr.left);
            postOrder(curr.right);
            st+=curr.value+", ";
        }
    }

    public static void main (String[] args)
    {
        String word = "kaleidoscope";
        BST<Character> bst = new BST<>();
        for (int i = 0; i < word.length(); i++)
            bst.add(word.charAt(i));
        System.out.println("Size => "+bst.size());
        System.out.println("In Order => "+bst.inOrder());
        System.out.println("Pre Order => "+bst.preOrder());
        System.out.println("Post Order => "+bst.postOrder());
        System.out.println("Contains 'i' => "+bst.contains('i'));
        System.out.println("Contains 't' => "+bst.contains('t'));
    }
}
