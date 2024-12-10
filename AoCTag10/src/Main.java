import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            ArrayList<Pair> trailhead = new ArrayList<>();
            int i = 0;
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < line.length(); j++){
                    int x = Integer.parseInt(String.valueOf(line.charAt(j)));
                    if (x == 0) trailhead.add(new Pair(i, j));
                    row.add(x);
                }
                map.put(i, row);
                i++;
            }
            for (Pair p: trailhead){
                ArrayList<Pair> trails = new ArrayList<>();
                trails.add(p);
                for (i = 1; i <= 9; i++){
                    ArrayList<Pair> nextStep =  new ArrayList<>();
                    for (Pair trail: trails){
                        nextStep.addAll(getNeighbors(map, trail, i));
                    }
                    if (nextStep.size() == 0) break;
                    trails = nextStep;
                }
                total += trails.size();
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Pair> getNeighbors(HashMap<Integer, ArrayList<Integer>> map, Pair p, int x){
        ArrayList<Pair> output = new ArrayList<>();
        try {
            int y = map.get(p.x).get(p.y + 1);
            if (y == x) output.add(new Pair(p.x, p.y + 1));
        } catch (Exception ignored) {}

        try {
            int y = map.get(p.x).get(p.y - 1);
            if (y == x) output.add(new Pair(p.x, p.y - 1));
        } catch (Exception ignored) {}

        try {
            int y = map.get(p.x + 1).get(p.y);
            if (y == x) output.add(new Pair(p.x + 1, p.y));
        } catch (Exception ignored) {}

        try {
            int y = map.get(p.x - 1).get(p.y);
            if (y == x) output.add(new Pair(p.x - 1, p.y));
        } catch (Exception ignored) {}

        return output;
    }
}