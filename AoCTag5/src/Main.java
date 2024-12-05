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
            int total = 0;
            ArrayList<Pair> rules = new ArrayList<>();
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] s = line.split("\\|");
                rules.add(new Pair(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> update = new ArrayList<>();
                ArrayList<Pair> errors;
                String[] s = line.split(",");
                for (String str: s) update.add(Integer.parseInt(str));
                errors = findErrors(update, rules);
                if(errors.size() == 0) continue;
                while (errors.size() > 0) {
                    //cringe
                    Collections.shuffle(errors);
                    for (Pair p : errors) {
                        int x = update.indexOf(p.a);
                        int y = update.indexOf(p.b);
                        int temp = update.get(y);
                        update.set(y, update.get(x));
                        update.set(x, temp);
                    }
                    errors = findErrors(update, rules);
                }
                total += update.get((update.size() - 1) / 2);
            }
                System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Pair> findErrors(ArrayList<Integer> update, ArrayList<Pair> rules){
        ArrayList<Pair> errors = new ArrayList<>();
        for (Pair pair: rules){
            if (update.contains(pair.b)){
                int start = update.indexOf(pair.b);
                if(update.subList(start + 1, update.size()).contains(pair.a)){
                    errors.add(pair);
                }
            }
        }
        return errors;
    }
}