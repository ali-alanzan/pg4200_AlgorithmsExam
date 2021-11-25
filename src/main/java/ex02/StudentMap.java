package ex02;

import java.util.ArrayList;

public class StudentMap<K extends Comparable<K>,V> implements MyMap<K,V>{

    protected class TreeNode {

        public K studentId;
        public V name;
        public TreeNode left;

        public TreeNode right;

        public TreeNode() {
        }

    }

    private TreeNode root;
    private int size;

    private ArrayList<TreeNode> studentNodesTrees = new ArrayList<TreeNode>();

    public StudentMap(ArrayList<TreeNode> studentNodes) {
        this.studentNodesTrees = new ArrayList<TreeNode>(10);
        for (int i = 0; i < studentNodes.size(); i++){
            TreeNode newNode = new TreeNode();
            studentNodes.add(newNode);
        }
    }

    @Override
    public void put(K key, V value) {

        if (isFull()){
            throw new IndexOutOfBoundsException();
        }

        int index = getFirstDigit(key);

         root = studentNodesTrees.get(index);

         root = insert( key, value, root);
    }

    public TreeNode insert(K key, V value ,TreeNode subTree){

        if(subTree == null){

            TreeNode node = new TreeNode();
            node.studentId = key;
            node.name = value;
            size++;
            return node;
        }

        int cmp = key.compareTo(subTree.studentId);

        if(cmp < 0 ){
            subTree.left = insert(key, value, subTree);
            return subTree;
        }

        if (cmp > 0) {
            subTree.right = insert(key, value, subTree.right);
            return subTree;
        }

        assert cmp == 0;
        subTree.name = value;

        return subTree;
    }

    @Override
    public void delete(K key) {

        int index = getFirstDigit(key);

        //get the tree from the index that's equal to the first digit
        root = studentNodesTrees.get(index);

        root = delete(key, root);

    }

    private TreeNode delete (K key, TreeNode subTreeRoot){
        if (subTreeRoot == null) {

            return null;
        }

        int cmp = key.compareTo(subTreeRoot.studentId);

        if (cmp < 0) {
            subTreeRoot.left = delete(key, subTreeRoot.left);
            return subTreeRoot;
        }

        if (cmp > 0) {
            subTreeRoot.right = delete(key, subTreeRoot.right);
            return subTreeRoot;
        }


        assert cmp == 0;

        size--;

        if (subTreeRoot.left == null) {
            return subTreeRoot.right;
        }

        if (subTreeRoot.right == null) {
            return subTreeRoot.left;
        }

        /*
            Both children are present
         */
        assert subTreeRoot.left != null && subTreeRoot.right != null;

        TreeNode tmp = subTreeRoot;
        subTreeRoot = min(tmp.right);
        subTreeRoot.right = deleteMin(tmp.right);
        subTreeRoot.left = tmp.left;

        return subTreeRoot;

    }

    private TreeNode min (TreeNode subTreeRoot){
        if(subTreeRoot.left == null){
            return subTreeRoot;
        }

        return min(subTreeRoot.left);
    }

    private TreeNode deleteMin (TreeNode subTreeRoot){
        if(subTreeRoot.left == null){
            return subTreeRoot.right;
        }
        subTreeRoot.left = deleteMin(subTreeRoot.left);

        return subTreeRoot;
    }

    @Override
    public V get(K key) {

        return get(key, root);
    }

    private V get (K key, TreeNode subTreeRoot){
        if(subTreeRoot == null){
            return null;
        }

      int cmp = key.compareTo(subTreeRoot.studentId);

        if(cmp == 0){
            return subTreeRoot.name;
        }else if (cmp > 0){
            return get(key,subTreeRoot.right);
        }else if (cmp < 0){
            return get(key, subTreeRoot.left);
        }

        return null;
    }

    public V getByName(String search_name){

        for (int i = 0; i < studentNodesTrees.size(); i++){

            return getByName(search_name, studentNodesTrees.get(i));
        }

        return null;
    }

    public V getByName( String name, TreeNode subTreeRoot){
        if(subTreeRoot.name == name){
            return subTreeRoot.name;
        }

        if(subTreeRoot.left != null){
           return getByName(name, subTreeRoot.left);
        }
        if (subTreeRoot.right != null){
            return getByName(name, subTreeRoot.right);
        }

        return subTreeRoot.name;
    }

    @Override
    public int size() {
        return size;
    }

    public int getFirstDigit(K id){
        String stringId = id.toString();

        char firstDigitChar = stringId.charAt(0);

        int firstDigit = Character.getNumericValue(firstDigitChar);

        return firstDigit;

    }

    @Override
    public boolean isEmpty() {
        return studentNodesTrees.isEmpty();
    }

    public boolean isFull(){
        return studentNodesTrees.size() == 10;
    }


}
