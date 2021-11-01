import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class LexicalAnalyzer {
    private SymbolTable symbolTable;
    private Validator validator = new Validator();

    public LexicalAnalyzer(SymbolTable symTable) {
        this.symbolTable = symTable;
    }

    public String analyze(List<Pair<Integer, String>> tokens, String symbolTableFileName, String pifFileName) {
        PIF pif = new PIF(symbolTable);
        Pair<Integer, String> last_token = Pair.of(0, "");
        try {
            for (Pair<Integer, String> token : tokens) {
                last_token = token;
                if (validator.checkIfConstant(token.getRight())) {
                    symbolTable.insert(token.getRight());
                } else {
                    if (validator.checkIfIdentifier(token.getRight())) {
                        symbolTable.insert(token.getRight());
                    }
                }
                pif.add(token.getValue());
            }
            FileOperations.writeSymbolTable(symbolTableFileName, symbolTable);
            FileOperations.writePIF(pifFileName, pif);
        } catch (Exception e) {
            return "Program is incorrect !\n" + "Token: " + last_token.getValue() + " at line " + (last_token.getKey() + 1) + "\n";
        }
        return "Program is correct !\n";
    }

}
