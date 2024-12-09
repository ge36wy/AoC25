import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<Integer> sys = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                boolean file = true;
                int index = 0;
                for (int i = 0; i < line.length(); i++){
                    int curr = Integer.parseInt(String.valueOf(line.charAt(i)));
                    int insert;
                    if (file){
                        insert = index;
                        index++;
                    } else {
                        insert = -1;
                    }
                    for (int j = 0; j < curr; j++) sys.add(insert);
                    file = !file;
                }
            }
            int end = sys.size();
            for (int i = 0; i < end; i++){
                if (sys.get(i) > -1){
                    total += sys.get(i) * i;
                }else{
                    int x = findLast(sys, end);
                    total += (long) sys.get(x) * i;
                    end = x;
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> reverse(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }

    public static int findLast(ArrayList<Integer> list, int index){
        for (int i = index - 1; i >= 0; i--){
            if (list.get(i) > -1) return i;
        }
        return -1;
    }
}