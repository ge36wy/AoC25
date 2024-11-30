import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            ArrayList<Integer> numbers = new ArrayList<>();
            while ((line = reader.readLine()) != null) {

            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}