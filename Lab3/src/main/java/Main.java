import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Main {

    public static String execute(String filePath, String symbolTableFileName, String pifFileName){
       SymbolTable symbolTable = new SymbolTable();
       PIF pif = new PIF(symbolTable);

        List<Pair<Integer,String>> tokens = FileOperations.getTokensFromFile(filePath);
        Pair<Integer,String> last_token = Pair.of(0,"");
        try{
            for (Pair<Integer,String> token : tokens){
                last_token = token;
                symbolTable.insert(token.getValue());
                pif.add(token.getValue());
            }
            FileOperations.writeSymbolTable(symbolTableFileName, symbolTable);
            FileOperations.writePIF(pifFileName, pif);
        } catch (Exception e) {
            return "Program is incorrect !\n" + "Token: " + last_token.getValue() + " at line " + (last_token.getKey() + 1) + "\n" ;
        }
       return "Program is correct !\n";
    }

    public static void main(String[] args){
        String result1 = execute("src/main/java/Programs/p1.txt", "ST1", "PIF1");
        String result2 = execute("src/main/java/Programs/p2.txt", "ST2", "PIF2");
        String result3 = execute("src/main/java/Programs/p2err.txt", "ST2err", "PIF2err");
        String result4 = execute("src/main/java/Programs/p3.txt", "ST3", "PIF3");

        System.out.println("For p1.txt the result is : " + result1);
        System.out.println("For p2.txt the result is : " + result2);
        System.out.println("For p2err.txt the result is : " + result3);
        System.out.println("For p3.txt the result is : " + result4);
    }

}
