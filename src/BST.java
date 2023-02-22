import java.util.ArrayList;
import java.util.List;

public class BST<E extends Comparable<E>>
{
    private TreeNode<E> root;
    private int size;
    private String st = "";
    private List<E> inOrder = new ArrayList<E>();

    public BST()
    {
        root = null;
        size = 0;
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
            if(!this.contains(val))
            {
                add(root, val);
            }
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

    public E remove(E val)
    {
        if(this.contains(val))
        {
            TreeNode<E> temp = root;
            TreeNode<E> parent = null;

            while(val.compareTo(temp.value) != 0)
            {
                if(val.compareTo(temp.value) < 0)
                {
                    if(temp.left != null)
                    {
                        parent = temp;
                        temp = temp.left;
                    }
                }
                else if(val.compareTo(temp.value) > 0)
                {
                    if(temp.right != null)
                    {
                        parent = temp;
                        temp = temp.right;
                    }
                }
            }

            //leaf
            if(temp.left == null && temp.right == null)
            {
                if(temp.value.compareTo(parent.value) > 0)
                {
                    parent.right = null;
                }
                else
                {
                    parent.left = null;
                }
                size--;
                return null;
            }

            //has only one child
            if((temp.right != null && temp.left == null) || (temp.right == null && temp.left != null))
            {
                if(temp.value.compareTo(parent.value) > 0)
                {
                    if(temp.right != null)
                    {
                        parent.right = temp.right;
                    }
                    else
                    {
                        parent.right = temp.left;
                    }
                    size--;
                    return parent.right.value;
                }
                else
                {
                    if(temp.right != null)
                    {
                        parent.left = temp.right;
                    }
                    else
                    {
                        parent.left = temp.left;
                    }
                    size--;
                    return parent.left.value;
                }
            }

            //has 2 children
            if(temp.right != null && temp.left != null)
            {
                this.inOrder();
                E newValue = null;
                if(val.compareTo(root.value) == 0)
                {
                    newValue = inOrder.get(inOrder.indexOf(temp.value)+1);
                    this.remove(newValue);
                    root.value = newValue;
                }
                else
                {
                    if(temp.value.compareTo(parent.value) > 0)
                    {
                        newValue = inOrder.get(inOrder.indexOf(temp.value)+1);
                        this.remove(newValue);
                        parent.right.value = inOrder.get(inOrder.indexOf(temp.value)+1);
                    }
                    else
                    {
                        newValue = inOrder.get(inOrder.indexOf(temp.value)+1);
                        this.remove(newValue);
                        parent.left.value = inOrder.get(inOrder.indexOf(temp.value)+1);
                    }
                }
                inOrder.clear();
                return newValue;
            }

        }
        return val;
    }

    public boolean contains(E val)
    {
        if(root.value.compareTo(val) == 0)
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
            inOrder.add(curr.value);
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

    public void rotateRight()
    {
        if(root.left != null)
        {
            rotateRight(root);
        }
    }

    private void rotateRight(TreeNode<E> curr)
    {
        TreeNode<E> temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
        root = temp;
    }

    public void rotateLeft()
    {
        if(root.right != null)
        {
            rotateLeft(root);
        }
    }

    private void rotateLeft(TreeNode<E> curr)
    {
        TreeNode<E> temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
        root = temp;
    }

    public static void main (String[] args) {


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
        bst.print();
        bst.rotateLeft();
        System.out.println("In Order => "+bst.inOrder());
        System.out.println("Pre Order => "+bst.preOrder());
        System.out.println("Post Order => "+bst.postOrder());
        bst.rotateRight();
        bst.print();




        /*
        BST<Integer> tree = new BST<>();
        int[] nums = {45,13,6,77,23,5,54,24,19,99,24,72,17,18};
        for (int i: nums)
          tree.add(i);

        tree.print();
        tree.rotateRight();
        tree.print();
        tree.rotateLeft();
        tree.print();
        */

        /*
        BST<Integer> tree = new BST<>();
        int[] nums = {45, 13, 6, 77, 23, 5, 54, 24, 19, 99, 24, 72, 17, 18};
        for (int i : nums)
            tree.add(i);
        tree.print();
        System.out.println(tree.size());
        tree.remove(99); // Delete a leaf
        tree.print();
        System.out.println(tree.size());
        tree.remove(17); // Delete a 1-Child Node
        tree.print();
        System.out.println(tree.size());
        tree.remove(23); // Delete a 2-Child Node
        tree.print();
        System.out.println(tree.size());
        tree.remove(45); // Delete the root
        tree.print();
        System.out.println(tree.size());
         */
    }
}