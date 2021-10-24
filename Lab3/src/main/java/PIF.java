import org.apache.commons.lang3.tuple.Pair;
import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<Pair<String, Integer>> pif;
    private final SymbolTable symTable;

    public PIF(SymbolTable symTable) {
        this.symTable = symTable;
        this.pif = new ArrayList<>();
    }

    public void add(String token) {
        if (symTable.containsElement(token)) {
            pif.add(Pair.of(token, symTable.getPosition(token)));
        } else {
            pif.add(Pair.of(token, -1));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair<String, Integer> pair : pif) {
            stringBuilder.append(pair.getLeft())
                    .append(" : ")
                    .append(pair.getRight())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
