class Node{
    private Node next;
    private String value;

    public Node(String value){
        this.value = value;
        this.next = null;
    }

    public Node getNext(){
        return this.next;
    }

    public String getValue(){
        return this.value;
    }

    public void setNext(Node next){
        this.next = next;
    }
}

public class SymbolTable {
    private Node[] symbolTable;
    private int size = 0;

    public SymbolTable(){
        this.symbolTable = new Node[10];
    }

    public boolean isEmpty() { return size == 0; }

    public int getSize() { return this.size;}

    public int getPosition(String value){
        return value.length() % 10;
    }

    public int insert(String value){
        int pos = getPosition(value);
        Node newNode = new Node(value);

        if(this.symbolTable[pos] == null){
            this.symbolTable[pos] = newNode;
        }
        else{
            Node firstNode = this.symbolTable[pos];
            while(firstNode.getNext() != null){
                if(firstNode.getValue().equals(value)){
                    return -1;
                }
                firstNode = firstNode.getNext();
            }
            firstNode.setNext(newNode);
        }
        this.size ++;
        return pos;
    }

    public void print() {
        System.out.println();
        for (int i = 0; i < this.symbolTable.length; i++) {
            System.out.print("At " + i + ":  ");

            Node firstNode = this.symbolTable[i];

            while (firstNode != null) {
                System.out.print(firstNode.getValue() + " ");
                firstNode = firstNode.getNext();
            }

            System.out.println();
        }
    }
}


