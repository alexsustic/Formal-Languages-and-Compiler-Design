import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFromFile {

    public List<String> readLinesFromFile(String filename)  {
        try {
            return Files.readAllLines(Paths.get(filename));
        }
        catch(IOException ex){
            System.out.println(ex.toString());
        }
        return null;
    }
}
