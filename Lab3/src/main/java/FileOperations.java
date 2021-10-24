

import org.apache.commons.lang3.tuple.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileOperations {

    public static List<Pair<Integer,String>> getTokensFromFile(String fileName){
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get(fileName));
            List<Pair<Integer,String>> tokens = new ArrayList<>();
            for(int i=0; i< lines.size(); i++){
                String[] tokens_on_one_line = lines.get(i).split(" ");
                for(String token: tokens_on_one_line)
                    if(!token.isEmpty()) {
                        tokens.add(Pair.of(i, token));
                    }
                }
            return tokens;
        }
        catch(IOException ex){
            System.out.println(ex.toString());
        }
        return null;
    }

    public static void writeSymbolTable(String fileName, SymbolTable symTable){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(symTable.toString());
            fileWriter.close();
        }
        catch (IOException ex){
            System.out.println(ex.toString());
        }
    }

    public static void writePIF(String fileName, PIF pif){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(pif.toString());
            fileWriter.close();
        }
        catch (IOException ex){
            System.out.println(ex.toString());
        }
    }


}
