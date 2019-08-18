package com.github.byference.data.structure.core.tree;

/**
 * IntAvl
 * TODO
 *  1、删除时最好保证树的递增或者递减
 *
 * @author byference
 * @since 2019-08-17
 */
public class IntAvl {


    private Node root;





    private boolean delete(Node node, int value) {

        // root is null
        if (node == null) {
            return false;
        }

        // 找到要删除的节点
        Node deleteNode = getNodeByValue(node, value);
        if (deleteNode == null) {
            return false;
        }

        // 如果是root 节点
        if (deleteNode == root) {

            Node leftChild = deleteNode.leftChild;
            Node rightChild = deleteNode.rightChild;

            if (leftChild == null && rightChild == null) {
                root = null;
                return true;
            }

            if (leftChild != null && rightChild != null) {

                root = rightChild;
                rightChild.parent = null;

                Node temp = rightChild;
                Node target;

                do {
                    target = temp;
                    temp = temp.leftChild;
                } while (temp != null);

                target.leftChild = leftChild;
                leftChild.parent = target;

                rebuild(target);
                return true;
            }

            if (leftChild == null) {
                root = rightChild;
                rightChild.parent = null;
            }

            if (rightChild == null) {
                root = leftChild;
                leftChild.parent = null;
            }

        }


        // 左树最大值或者右树最小值（这里使用左树最大值）
        Node targetNode = getTargetNode(deleteNode);

        Node parent;

        if (deleteNode == targetNode) {

            // 1、如果删除的节点没有子节点
            parent = deleteNode.parent;
            if (deleteNode.value > parent.value) {
                parent.rightChild = null;
            } else {
                parent.leftChild = null;
            }

        } else {

            // 2、删除的节点有子节点

            // 将左树最大值 设置给要删除的节点
            deleteNode.value = targetNode.value;
            parent = targetNode.parent;


            if (targetNode.value < root.value) {

                // 左树: targetNode 覆盖 deleteNode
                parent.rightChild = null;
            } else {

                // 右树: targetNode 覆盖 deleteNode
                parent.leftChild = null;
            }

        }

        rebuild(parent);
        return true;
    }


    public boolean delete(int value) {

        return delete(root, value);
    }


    private Node getTargetNode(Node target) {

        Node maxNode;
        Node temp = target;

        if (target.value < root.value) {
            // 左树取最大右子节点
            do {
                maxNode = temp;
                temp = temp.rightChild;
            } while (temp != null);

        } else if (target.value > root.value){
            // 右树取最小左子节点
            do {
                maxNode = temp;
                temp = temp.leftChild;
            } while (temp != null);

        } else {
            maxNode = target;
        }

        return maxNode;
    }


    private Node getNodeByValue(Node node, int value) {

        Node temp = node;

        do {

            if (value > temp.value) {
                temp = temp.rightChild;
            } else if (value < temp.value) {
                temp = temp.leftChild;
            } else {
                return temp;
            }
        } while (temp != null);

        return null;
    }


    private boolean put(Node node, int value) {

        // 如果root为空，初始化根节点
        if (node == null) {
            root = new Node(value);
            return true;
        }

        // 找到 pivot
        Node temp = node;
        Node pivot;

        do {
            pivot = temp;
            if (value > temp.value) {
                // 右子节点
                temp = temp.rightChild;
            } else if (value < temp.value) {
                // 左子节点
                temp = temp.leftChild;
            } else {
                return true;
            }
        } while (temp != null);

        Node newNode = new Node(pivot, value);

        if (value > pivot.value) {
            // 右子节点
            pivot.rightChild = newNode;
        } else {
            // 左子节点
            pivot.leftChild = newNode;
        }

        // 平衡二叉树
        rebuild(pivot);

        return true;
    }


    public boolean put(int value) {

        return put(root, value);
    }


    private void rebuild(Node node) {

        while (node != null) {
            // 操作
            int nodeHeight = getNodeHeight(node);
            if (nodeHeight > 1) {
                // 左树高
                if (node.leftChild.leftChild != null) {
                    // 左子节点
                    rightRotate(node.leftChild);
                } else if (node.leftChild.rightChild != null) {
                    // 右子节点
                    leftRightRotate(node.leftChild);
                }
            } else if (nodeHeight < -1) {
                // 右树高
                if (node.rightChild.rightChild != null) {
                    // 右子节点
                    leftRotate(node.rightChild);
                } else if (node.rightChild.leftChild != null) {
                    // 左子节点
                    rightLeftRotate(node.rightChild);
                }
            }
            node = node.parent;
        }
    }


    private int getNodeHeight(Node node) {

        if (node == null) {
            return 0;
        }
        return getChildDepth(node.leftChild) - getChildDepth(node.rightChild);
    }


    private int getChildDepth(Node node) {

        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getChildDepth(node.leftChild), getChildDepth(node.rightChild));
    }


    /**
     * leftRotate:
     *  1          1
     *   \          \
     *    3          2            2
     *   /     ->     \    ->    / \
     *  2              3        1   3
     *
     * @return pivot
     */
    private Node rightLeftRotate(Node pivot) {

        // 先右旋再左旋
        Node node = rightRotate(pivot.leftChild);
        return leftRotate(node);
    }


    /**
     * leftRightRotate:
     *     3               3
     *    /               /
     *   1               2             2
     *    \     ->      /     ->      / \
     *     2           1             1   3
     *
     * @return pivot
     */
    private Node leftRightRotate(Node pivot) {

        // 先左旋再右旋
        Node node = leftRotate(pivot.rightChild);
        return rightRotate(node);
    }


    /**
     * leftRotate:
     * 1
     *  \
     *   2               2
     *    \     ->      / \
     *     3           1   3
     *
     * @return pivot
     */
    private Node leftRotate(Node pivot) {

        // is null
        if (pivot == null) {
            return null;
        }

        // 获取 pivot.parent
        Node parent = pivot.parent;

        // 左旋
        parent.rightChild = pivot.leftChild;
        pivot.leftChild = parent;

        // 如果 parent 是root节点，则将pivot设置为root节点，否则则将 pivot 设置为 parent.parent 的有左子节点
        if (parent == root) {
            root = pivot;
        } else {
            parent.parent.rightChild = pivot;
        }

        // 重置父节点
        pivot.parent = parent.parent;
        parent.parent = pivot;

        return pivot;
    }


    /**
     * rightRotate:
     *     3
     *    /
     *   2               2
     *  /      ->       / \
     * 1               1   3
     *
     * @return pivot
     */
    private Node rightRotate(Node pivot) {

        // is null
        if (pivot == null) {
            return null;
        }

        // 获取 pivot.parent
        Node parent = pivot.parent;

        // 左旋
        parent.leftChild = pivot.rightChild;
        pivot.rightChild = parent;


        // 如果 parent 是root节点，则将pivot设置为root节点，否则则将 pivot 设置为 parent.parent的有左子节点
        if (parent == root) {
            root = pivot;
        } else {
            parent.parent.leftChild = pivot;
        }

        // 重置父节点
        pivot.parent = parent.parent;
        parent.parent = pivot;

        return pivot;
    }


    class Node {

        Node parent;

        Node leftChild;

        Node rightChild;

        int value;

        Node(Node parent, Node leftChild, Node rightChild, int value) {
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.value = value;
        }

        Node(int value) {
            this(null, null, null, value);
        }


        Node(Node parent, int value) {
            this(parent, null, null, value);
        }

        @Override
        public String toString() {
            return String.format("{ %s }", value);
        }
    }

}