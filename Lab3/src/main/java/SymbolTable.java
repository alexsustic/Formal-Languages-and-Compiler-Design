import org.apache.commons.lang3.tuple.Pair;

class Node {
    private Node next;
    private String value;

    public Node(String value) {
        this.value = value;
        this.next = null;
    }

    public Node getNext() {
        return this.next;
    }

    public String getValue() {
        return this.value;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

public class SymbolTable {
    private final Node[] symbolTable;

    public SymbolTable() {
        this.symbolTable = new Node[10];
    }

    public int getHashValue(String value) {
        return value.length() % 10;
    }

    public Pair<Integer, Integer> getPosition(String value) {
        int first_position = value.length() % 10;
        int second_position = 0;
        int ok = 0;
        Node firstNode = this.symbolTable[first_position];
        if (firstNode == null) {
            return Pair.of(-1, -1);
        }

        while (firstNode.getNext() != null) {
            if (firstNode.getValue().equals(value)) {
                ok = 1;
                break;
            }
            second_position++;
            firstNode = firstNode.getNext();
        }
        if (firstNode.getValue().equals(value)) {
            ok = 1;
        }

        if (ok == 1)
            return Pair.of(first_position, second_position);
        return Pair.of(-1, -1);
    }


    public int insert(String value) throws Exception {

        int pos = getHashValue(value);
        Node newNode = new Node(value);

        if (this.symbolTable[pos] == null) {
            this.symbolTable[pos] = newNode;
        } else {
            Node firstNode = this.symbolTable[pos];
            while (firstNode.getNext() != null) {
                if (firstNode.getValue().equals(value)) {
                    return -1;
                }
                firstNode = firstNode.getNext();
            }
            if (firstNode.getValue().equals(value)) {
                return -1;
            }
            firstNode.setNext(newNode);
        }
        return pos;
    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.symbolTable.length; i++) {
            result = result + "At " + i + ": ";

            Node firstNode = this.symbolTable[i];

            while (firstNode != null) {
                result = result + firstNode.getValue() + " ";
                firstNode = firstNode.getNext();
            }

            result = result + "\n";
        }
        return result;
    }
}