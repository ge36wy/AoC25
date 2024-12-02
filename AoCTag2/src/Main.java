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
            ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                ArrayList<Integer> row = new ArrayList<>();
                for (String s: split){
                    row.add(Integer.parseInt(s));
                }
                rows.add(row);
            }
            for (ArrayList<Integer> row: rows){
                ArrayList<Integer> diff = new ArrayList<>();
                for (int i = 0; i < row.size() - 1; i++){
                    diff.add(row.get(i + 1) - row.get(i));
                }
                int max = Collections.max(diff);
                int min = Collections.min(diff);
                if (max < 4 && min > 0) total++;
                if (max < 0 && min > -4) total++;
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}