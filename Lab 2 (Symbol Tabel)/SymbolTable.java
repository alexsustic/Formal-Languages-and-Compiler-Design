import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
    private final List<String> reservedWords = new ArrayList<String>(Arrays.asList("and", "or", "def", "destroy", "check", "then", "else", "for", "while", "in", "int", "char", "bool", "return", "read", "print", "program", "sqrt"));
    private final List<String> reservedCharacters = new ArrayList<String>(Arrays.asList("^", "=", "==", ">", ">=", "<", "<=", "!=", "+", "-", "/", "*", "%", ",", ";", "(", ")", "{", "}", "[", "]", "!"));
    private int size = 0;

    public SymbolTable() {
        this.symbolTable = new Node[10];
    }

    public int getPosition(String value) {
        return value.length() % 10;
    }

    public int insert(String value) throws Exception {
        if (reservedWords.contains(value) || reservedCharacters.contains(value)) {
            return -1;
        }

        if (!(checkIfChar(value) || checkIfString(value) || StringUtils.isNumeric(value))) {
            throwsErrorIfIdentifierNotCorrect(value);
        }

        int pos = getPosition(value);
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
        this.size++;
        return pos;
    }

    public void throwsErrorIfIdentifierNotCorrect(String identifier) throws Exception {
        String regex = "^([a-zA-Z][a-zA-Z0-9]*$)";
        if (!Pattern.matches(regex, identifier)) {
            System.out.println(identifier + "\n");
            throw new Exception("Identifier wrong!");
        }
    }

    public boolean checkIfChar(String token) {
        if (token.length() == 3) {
            String firstChar = String.valueOf(token.charAt(0));
            String middleChar = String.valueOf(token.charAt(1));
            String lastChar = String.valueOf(token.charAt(2));
            return firstChar.equals("'") && !middleChar.contains("'") && lastChar.equals("'");
        }
        return false;
    }

    public boolean checkIfString(String token) {
        char firstChar = token.charAt(0);
        char lastChar = token.charAt(token.length() - 1);

        if (token.length() >= 3) {
            String restOfToken = token.substring(1, token.length() - 1);
            return firstChar == '"' && lastChar == '"' && !restOfToken.contains("\"");
        }
        return false;
    }

    public boolean containsElement(String elem) {
        int pos = getPosition(elem);
        Node firstNode = this.symbolTable[pos];
        if(firstNode == null){
            return false;
        }
        while (firstNode.getNext() != null) {
            if (firstNode.getValue().equals(elem)) {
                return true;
            }
            firstNode = firstNode.getNext();
        }

        if(firstNode.getValue().equals(elem))
            return true;

        return false;
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