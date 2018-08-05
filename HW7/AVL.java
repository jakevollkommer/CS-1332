import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Jake Vollkommer
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        size = 0;
        for (T t : data) {
            //add(t);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        root = add(data, root);
    }

    /**
     * Recursive helper method for add that traverses the tree
     *
     * @param data the data to add to the tree
     * @param node the current node in the tree during traversal
     * @return the root of the new tree after the add
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {
        if (node == null) {
            node = new AVLNode<T>(data);
            setHeightBF(node);
            size++;
            return node;
        }
        if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(data, node.getRight()));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        }
        setHeightBF(node);

        if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() <= -1) {
                node.setLeft(leftRotation(node.getLeft()));
            }
            node = rightRotation(node);
        }
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() >= 1) {
                node.setRight(rightRotation(node.getRight()));
            }
            node = leftRotation((node));
        }
        return node;
    }

    /**
     * Performs a right rotation
     *
     * @param node node to rotate about
     * @return new parent node
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        setHeightBF(node);
        setHeightBF(temp);
        return temp;
    }

    /**
     * Performs a left rotation
     *
     * @param node node to rotate about
     * @return new parent node
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        setHeightBF(node);
        setHeightBF(temp);
        return temp;
    }

    /**
     * Sets the height and balance factor of a node
     *
     * @param node node to update height and balance factor of
     */
    private void setHeightBF(AVLNode<T> node) {
        if (node != null) {
            int leftHeight = -1;
            int rightHeight = -1;
            if (node.getLeft() != null) {
                leftHeight = node.getLeft().getHeight();
            }
            if (node.getRight() != null) {
                rightHeight = node.getRight().getHeight();
            }
            node.setHeight(Math.max(leftHeight, rightHeight) + 1);
            node.setBalanceFactor(leftHeight - rightHeight);
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        AVLNode<T> removed = new AVLNode<T>(null);
        root = remove(data, root, removed);
        size--;
        setHeightBF(root);

        return removed.getData();
    }

    /**
     * Recursive helper function for remove()
     *
     * @param data the data to remove
     * @param node the current node in the traversal
     * @param removed the node that contains the data to remove
     * @return node
     */
    private AVLNode<T> remove(T data, AVLNode<T> node, AVLNode<T> removed) {
        if (node == null) {
            throw new NoSuchElementException("Cannot remove null from tree");
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft(), removed));
        }
        if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight(), removed));
        }
        if (data.equals(node.getData())) {
            if (removed.getData() == null) {
                removed.setData(node.getData());
            }
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            AVLNode<T> dataNode = new AVLNode<T>(null);
            node.setRight(findSuccessor(node.getRight(), dataNode));
            node.setData(dataNode.getData());
            AVLNode<T> temp = node.getRight();
            setHeightBF(temp);
            if (temp != null) {
                if (temp.getBalanceFactor() > 1) {

                    if (temp.getLeft().getBalanceFactor() <= -1) {
                        temp.setLeft(leftRotation(temp.getLeft()));
                    }
                    temp = rightRotation(temp);
                }
                if (temp.getBalanceFactor() < -1) {
                    if (temp.getRight().getBalanceFactor() >= 1) {
                        temp.setRight(rightRotation(temp.getRight()));
                    }
                    temp = leftRotation((temp));
                }
            }
            node.setRight(temp);

        }
        setHeightBF(node);
        if (node != null) {
            if (node.getBalanceFactor() > 1) {

                if (node.getLeft().getBalanceFactor() <= -1) {
                    node.setLeft(leftRotation(node.getLeft()));
                }
                node = rightRotation(node);
            }
            if (node.getBalanceFactor() < -1) {
                if (node.getRight().getBalanceFactor() >= 1) {
                    node.setRight(rightRotation(node.getRight()));
                }
                node = leftRotation((node));
            }
        }
        return node;
    }

    /**
     * Finds predecessor and performs rotations
     *
     * @param node current node in the traversal
     * @param dataNode node to store the data in
     * @return predecessor
     */
    private AVLNode<T> findSuccessor(AVLNode<T> node, AVLNode<T> dataNode) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null) {
            dataNode.setData(node.getData());
            return node.getLeft();
        }
        node.setLeft(findSuccessor(node.getLeft(), dataNode));

        AVLNode<T> temp = node.getLeft();

        setHeightBF(temp);
        if (temp != null) {
            if (temp.getBalanceFactor() > 1) {

                if (temp.getLeft().getBalanceFactor() <= -1) {
                    temp.setLeft(leftRotation(temp.getLeft()));
                }
                temp = rightRotation(temp);
            }
            if (temp.getBalanceFactor() < -1) {
                if (temp.getRight().getBalanceFactor() >= 1) {
                    temp.setRight(rightRotation(temp.getRight()));
                }
                temp = leftRotation((temp));
            }
        }
        node.setLeft(temp);
        setHeightBF(node);
        if (node != null) {
            if (node.getBalanceFactor() > 1) {

                if (node.getLeft().getBalanceFactor() <= -1) {
                    node.setLeft(leftRotation(node.getLeft()));
                }
                node = rightRotation(node);
            }
            if (node.getBalanceFactor() < -1) {
                if (node.getRight().getBalanceFactor() >= 1) {
                    node.setRight(rightRotation(node.getRight()));
                }
                node = leftRotation((node));
            }
        }
        return node;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        return get(data, root);
    }

    /**
     * Recursive function to traverse the tree and get the data
     *
     * @param data the data to get
     * @param node the current node in the traversal
     * @return the data in the tree matching the data passed in
     */
    private T get(T data, AVLNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Data not in tree");
        } else if (data.compareTo(node.getData()) > 0) {
            return get(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return get(data, node.getLeft());
        }
        return node.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        return contains(data, root);
    }

    /**
     * Recursive function that traverses the tree to find the node
     *
     * @param data the data to search for
     * @param node the current node in the traversal
     * @return whether the tree contains the data
     */
    private boolean contains(T data, AVLNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return contains(data, node.getLeft());
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> results = new ArrayList<T>();
        if (root == null) {
            return results;
        }
        return preorder(results, root);
    }

    /**
     * Recursive helper function for preorder() which traverses the tree
     *
     * @param list the current list containing the preorder of the tree
     * @param current the current node in the traversal
     * @return the update list containing the preorder of the tree
     */
    private List<T> preorder(List<T> list, AVLNode<T> current) {
        list.add(current.getData());
        if (current.getLeft() != null) {
            list = preorder(list, current.getLeft());
        }
        if (current.getRight() != null) {
            list = preorder(list, current.getRight());
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        List<T> results = new ArrayList<T>();
        if (root == null) {
            return results;
        }
        return postorder(results, root);
    }

    /**
     * Recursive helper function for postorder() which traverses the tree
     *
     * @param list the current list containing the postorder of the tree
     * @param current the current node in the traversal
     * @return the update list containing the postorder of the tree
     */
    private List<T> postorder(List<T> list, AVLNode<T> current) {
        if (current.getLeft() != null) {
            list = postorder(list, current.getLeft());
        }
        if (current.getRight() != null) {
            list = postorder(list, current.getRight());
        }
        list.add(current.getData());
        return list;
    }

    @Override
    public List<T> inorder() {
        List<T> results = new ArrayList<T>();
        if (root == null) {
            return results;
        }
        return inorder(results, root);
    }

    /**
     * Recursive helper function for inorder() which traverses the tree
     *
     * @param list the current list containing the inorder of the tree
     * @param current the current node in the traversal
     * @return the update list containing the inorder of the tree
     */
    private List<T> inorder(List<T> list, AVLNode<T> current) {
        if (current.getLeft() != null) {
            list = inorder(list, current.getLeft());
        }
        list.add(current.getData());
        if (current.getRight() != null) {
            list = inorder(list, current.getRight());
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<T>(size);
        if (root == null) {
            return list;
        }
        Queue<AVLNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            AVLNode<T> temp = queue.poll();
            list.add(temp.getData());
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
