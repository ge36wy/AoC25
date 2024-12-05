import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            ArrayList<Pair> rules = new ArrayList<>();
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] s = line.split("\\|");
                rules.add(new Pair(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> update = new ArrayList<>();
                String[] s = line.split(",");
                boolean valid = true;
                for (String str: s) update.add(Integer.parseInt(str));
                for (Pair pair: rules){
                    if (update.contains(pair.b)){
                        int start = update.indexOf(pair.b);
                        if(update.subList(start + 1, update.size()).contains(pair.a)){
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid) total += update.get((update.size() - 1) / 2);
            }
                System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}