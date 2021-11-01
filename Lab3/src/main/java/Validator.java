import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    private final List<String> reservedWords = new ArrayList<String>(Arrays.asList("and", "or", "def", "destroy", "check", "then", "else", "for", "while", "in", "int", "char", "bool", "return", "read", "print", "program", "sqrt"));
    private final List<String> reservedCharacters = new ArrayList<String>(Arrays.asList("^", "=", "==", ">", ">=", "<", "<=", "!=", "+", "-", "/", "*", "%", ",", ";", "(", ")", "{", "}", "[", "]", "!"));

    public boolean checkIfIdentifier(String token) throws Exception {
        if (!reservedWords.contains(token) && !reservedCharacters.contains(token)) {
            if (checkIfIdentifierIsCorrect(token)) {
                return true;
            } else {
                throw new Exception("Identifier is not correct!");
            }
        }
        return false;
    }

    public boolean checkIfConstant(String token) {
        if (!(reservedWords.contains(token)) && !(reservedCharacters.contains(token)) && (checkIfString(token) || checkIfChar(token) || verifyIfNumeric(token))) {
            return true;
        }
        return false;
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

    public boolean verifyIfNumeric(String token) {
        String regex = "^([0-9]*)$";
        return Pattern.matches(regex, token);
    }

    public boolean checkIfIdentifierIsCorrect(String identifier) {
        String regex = "^([a-zA-Z][a-zA-Z0-9]*$)";
        return Pattern.matches(regex, identifier);
    }
}
