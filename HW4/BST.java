import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collection;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author Jake Vollkommer
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        for (T item : data) {
            add(item);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        BSTNode<T> newNode = new BSTNode(data);
        if (size == 0) {
            root = newNode;
            size++;
        } else if (size == 1) {
            if (data.compareTo(root.getData()) > 0) {
                root.setRight(newNode);
            } else if (data.compareTo(root.getData()) < 0) {
                root.setLeft(newNode);
            }
            size++;
        } else {
            add(data, root);
        }
    }

    /**
     * Helper method for add to traverse the tree
     *
     * @param data the data to add to the tree
     * @param curr the current node in the traversal
     */
    public void add(T data, BSTNode<T> curr) {
        BSTNode<T> newNode = new BSTNode(data);
        if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                curr = curr.getLeft();
                add(data, curr);
            } else {
                curr.setLeft(newNode);
                size++;
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                curr = curr.getRight();
                add(data, curr);
            } else {
                curr.setRight(newNode);
                size++;
            }
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == 0) {
            throw new NoSuchElementException("Tree is empty");
        }
        if (data.equals(root.getData())) {
            T toReturn = root.getData();
            if (size == 1) {
                root = null;
                size--;
                return toReturn;
            } else {
                if (root.getRight() != null) {
                    //replace with successor
                    BSTNode<T> succParent = root;
                    BSTNode<T> rightChild = root.getRight();
                    BSTNode<T> successor = rightChild;
                    while (successor.getLeft() != null) {
                        succParent = successor;
                        successor = successor.getLeft();
                    }
                    T successorData = successor.getData();
                    remove(successorData);
                    root.setData(successorData);
                } else {
                    root = root.getLeft();
                    size--;
                }
                return toReturn;
            }
        } else {
            return remove(data, root, null);
        }
    }

    /**
     * Helper method for remove to traverse the tree
     *
     * @param data the data to be removed from the tree
     * @param curr the current node in the traversal
     * @param prev the parent node of curr
     * @return the removed data
     */
    public T remove(T data, BSTNode<T> curr, BSTNode<T> prev) {
        T toReturn = curr.getData();
        if (data.equals(curr.getData())) {
            //Remove this node
            if (curr.getLeft() == null) {
                if (curr.getRight() == null) {
                    //The curr is a leaf node
                    if (prev.getRight() != null && prev.getRight().getData().equals(toReturn)) {
                        prev.setRight(null);
                    } else if (prev.getLeft() != null && prev.getLeft().getData().equals(toReturn)) {
                        prev.setLeft(null);
                    }
                } else {
                    //The curr has one right child
                    if (prev.getData().compareTo(curr.getData()) > 0) {
                        //this node is the left child
                        prev.setLeft(curr.getRight());
                    } else {
                        //this node is the right child
                        prev.setRight(curr.getRight());
                    }
                }
                size--;
            } else if (curr.getRight() == null) {
                if (curr.getLeft() == null) {
                    //The curr is a leaf node
                    if (prev.getRight().getData().equals(curr)) {
                        prev.setRight(null);
                    } else if (prev.getLeft().getData().equals(curr)) {
                        prev.setLeft(null);
                    }
                } else {
                    //The curr has one left child
                    if (prev.getData().compareTo(curr.getData()) > 0) {
                        //this node is the left child
                        prev.setLeft(curr.getLeft());
                    } else {
                        //this node is the right child
                        prev.setRight(curr.getLeft());
                    }
                }
                size--;
            } else {
                //The curr has two children
                //replace with successor
                BSTNode<T> succParent = curr;
                BSTNode<T> rightChild = curr.getRight();
                BSTNode<T> successor = rightChild;
                while (successor.getLeft() != null) {
                    succParent = successor;
                    successor = successor.getLeft();
                }
                T successorData = successor.getData();
                remove(successorData);
                curr.setData(successorData);
            }
            return toReturn;
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                return remove(data, curr.getLeft(), curr);
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                return remove(data, curr.getRight(), curr);
            }
        }
        throw new NoSuchElementException("Data is not in tree");
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == 0) {
            throw new NoSuchElementException("Tree is empty");
        }
        if (data.equals(root.getData())) {
            return root.getData();
        } else {
            return get(data, root);
        }
    }

    /**
     * Helper method for get to traverse the tree
     *
     * @param data the data to get from the tree
     * @param curr the current node in the traversal
     * @return the data
     */
    public T get(T data, BSTNode<T> curr) {
        if (data.equals(curr.getData())) {
            return curr.getData();
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                return get(data, curr.getLeft());
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                return get(data, curr.getRight());
            }
        }
        throw new NoSuchElementException("Data is not in tree");
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == 0) {
            throw new NoSuchElementException("Tree is empty");
        }
        if (data.equals(root.getData())) {
            return true;
        } else {
            return contains(data, root);
        }
    }

    /**
     * Helper method for contains to traverse the tree
     *
     * @param data the data to search for
     * @param curr the current node in the traversal
     * @return whether the data is in the tree
     */
    public boolean contains(T data, BSTNode<T> curr) {
        if (data.equals(curr.getData())) {
            return true;
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                return contains(data, curr.getLeft());
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                return contains(data, curr.getRight());
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        ArrayList<T> list = new ArrayList<T>();
        if (size == 0) {
            return list;
        }
        return preorder(root, list);
    }

    /**
     * Helper method for preorder to traverse the tree
     *
     * @param curr the current node in the traversal
     * @param list the list containing the preorder
     * @return a preorder traversal of the current node
     */
    public List<T> preorder(BSTNode<T> curr, List<T> list) {
        if (curr.getData() != null) {
            list.add(curr.getData());
        }
        if (curr.getLeft() != null) {
            preorder(curr.getLeft(), list);
        }
        if (curr.getRight() != null) {
            preorder(curr.getRight(), list);
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        ArrayList<T> list = new ArrayList<T>();
        if (size == 0) {
            return list;
        }
        return postorder(root, list);
    }

    /**
     * Helper method for postorder to traverse the tree
     *
     * @param curr the current node in the traversal
     * @param list the list containing the postorder
     * @return a postorder traversal of the current node
     */
    public List<T> postorder(BSTNode<T> curr, List<T> list) {
        if (curr.getLeft() != null) {
            postorder(curr.getLeft(), list);
        }
        if (curr.getRight() != null) {
            postorder(curr.getRight(), list);
        }
        if (curr.getData() != null) {
            list.add(curr.getData());
        }
        return list;
    }

    @Override
    public List<T> inorder() {
        ArrayList<T> list = new ArrayList<T>();
        if (size == 0) {
            return list;
        }
        return inorder(root, list);
    }

    /**
     * Helper method for inorder to traverse the tree
     *
     * @param curr the current node in the traversal
     * @param list the list containing the inorder
     * @return a inorder traversal of the current node
     */
    public List<T> inorder(BSTNode<T> curr, List<T> list) {
        if (curr.getLeft() != null) {
            inorder(curr.getLeft(), list);
        }
        if (curr.getData() != null) {
            list.add(curr.getData());
        }
        if (curr.getRight() != null) {
            inorder(curr.getRight(), list);
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        ArrayList<T> list = new ArrayList<T>();
        if (size == 0) {
            return list;
        }
        BSTNode<T> curr = root;
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> toAdd = queue.remove();
            if (toAdd != null) {
                list.add(toAdd.getData());
            }
            if (toAdd.getLeft() != null) {
                queue.add(toAdd.getLeft());
            }
            if (toAdd.getRight() != null) {
                queue.add(toAdd.getRight());
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
        if (size == 0) {
            return -1;
        }
        return height(root);
    }

    /**
     * Helper method for height to traverse the tree
     *
     * @param curr current node in the traversal
     * @return the height of the node
     */
    public int height(BSTNode<T> curr) {
        if (curr.getLeft() == null && curr.getRight() == null) {
            return 0;
        } else if (curr.getLeft() == null) {
            //only has a right child
            return height(curr.getRight()) + 1;
        } else if (curr.getRight() == null) {
            //only has a left child
            return height(curr.getLeft()) + 1;
        } else {
            //has two children
            int leftHeight = height(curr.getLeft());
            int rightHeight = height(curr.getRight());
            if (leftHeight >= rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
