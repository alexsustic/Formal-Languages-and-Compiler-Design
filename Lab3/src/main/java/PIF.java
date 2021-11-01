import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<Pair<String, Pair<Integer, Integer>>> pif;
    private final SymbolTable symTable;

    public PIF(SymbolTable symTable) {
        this.symTable = symTable;
        this.pif = new ArrayList<>();
    }

    public void add(String token) {
        pif.add(Pair.of(token, symTable.getPosition(token)));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair<String, Pair<Integer, Integer>> pair : pif) {
            stringBuilder.append(pair.getLeft())
                    .append(" : ")
                    .append(pair.getRight().getLeft())
                    .append(" ")
                    .append(pair.getRight().getRight())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
