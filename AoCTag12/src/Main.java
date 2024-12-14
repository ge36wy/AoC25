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
            long total = 0;
            ArrayList<ArrayList<Character>> field = new ArrayList<>();
            ArrayList<ArrayList<Pair>> groups = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                ArrayList<Character> l = new ArrayList<>();
                for (Character c: line.toCharArray()){
                    l.add(c);
                }
                field.add(l);
            }
            for (int i = 0; i < field.size(); i++){
                for (int j = 0; j < field.get(i).size(); j++){
                    if (field.get(i).get(j) == '.') continue;
                    ArrayList<Pair> group = new ArrayList<>();
                    Character current = field.get(i).get(j);
                    Queue<Pair> queue = new LinkedList<>();
                    queue.add(new Pair(i, j));
                    while (!queue.isEmpty()){
                        Pair p = queue.poll();
                        if (field.get(p.x).get(p.y) == current) {
                            field.get(p.x).set(p.y, '.');
                            group.add(p);
                            queue.addAll(getNeighbors(field, p));
                        }
                    }
                    groups.add(group);
                }
            }
            for (ArrayList<Pair> group: groups){
                int area = group.size();
                int circ = group.size() * 4;
                for (Pair p: group){
                    if (group.contains(new Pair(p.x + 1, p.y))) circ--;
                    if (group.contains(new Pair(p.x - 1, p.y))) circ--;
                    if (group.contains(new Pair(p.x, p.y + 1))) circ--;
                    if (group.contains(new Pair(p.x, p.y - 1))) circ--;
                }
                total += (long) area * circ;
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Pair> getNeighbors(ArrayList<ArrayList<Character>> field, Pair current){
        ArrayList<Pair> result = new ArrayList<>();
        if (current.x > 0) result.add(new Pair(current.x - 1, current.y));
        if (current.x < field.size() - 1) result.add(new Pair(current.x + 1, current.y));
        if (current.y > 0) result.add(new Pair(current.x, current.y - 1));
        if (current.y < field.get(0).size() - 1) result.add(new Pair(current.x, current.y + 1));
        return result;
    }
}