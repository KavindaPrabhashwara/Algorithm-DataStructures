package assignmentAVL;

//creating the class
public class Tree {

	//Declaring variables
	private int value;
	private int height;
	private int xAxis;
	private int yAxis;
	
	private Tree parent;
	private Tree leftChild;
	private Tree rightChild;
	

	public Tree() {
		value = 0;
		height = 1;
		parent = null;
		leftChild = null;
		rightChild = null;
	}

	public Tree(int value) {
		this();
		this.value = value;
	}


	public int getValue() {
		return value;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.xAxis = x;
	}
	
	public int getX() {
		return xAxis;
	}
	
	public void setY(int y) {
		this.yAxis = y;
	}
	
	public int getY() {
		return yAxis;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void changeHeight(int number) {
		this.height += number;
	}
	

	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}

	public Tree getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Tree leftChild) {
		this.leftChild = leftChild;
	}

	public void addLeftChild(int value) {
		setLeftChild(new Tree(value));
	}

	public Tree getRightChild() {
		return rightChild;
	}

	public void setRightChild(Tree rightChild) {
		this.rightChild = rightChild;
	}

	public void addRightChild(int value){
		setRightChild(new Tree(value));
	}

}
