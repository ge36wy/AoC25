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
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            HashMap<Integer, Integer> amounts = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("   ");
                left.add(Integer.parseInt(split[0]));
                right.add(Integer.parseInt(split[1]));
            }
            for (int num: left){
                amounts.put(num, 0);
            }
            for (int num: right){
                if (amounts.containsKey(num)){
                    amounts.put(num, amounts.get(num) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> e: amounts.entrySet()){
                total += e.getKey() * e.getValue();
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}