import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            HashSet<Point> antinodes = new HashSet<>();
            HashMap<Character, ArrayList<Point>> nodes = new HashMap<>();
            long total;
            int i = 0;
            int width = 0;
            int height;
            while ((line = reader.readLine()) != null) {
                width = line.length();
                for (int j = 0; j < line.length(); j++){
                    Character current = line.charAt(j);
                    if(current != '.'){
                        if (!nodes.containsKey(current)) nodes.put(current, new ArrayList<>());
                        nodes.get(current).add(new Point(i, j));
                    }
                }
                i++;
            }
            height = i;
            for (ArrayList<Point> points: nodes.values()){
                for (i = 0; i < points.size(); i++){
                    for (int j = i + 1; j < points.size(); j++) {
                        for (int z = 0; z < Math.max(width, height); z++) {
                            Point p1 = points.get(i);
                            Point p2 = points.get(j);
                            HashSet<Point> antis = new HashSet<>();
                            if (p1.equals(p2)) continue;
                            if (p1.x == p2.x) {
                                int dy = Math.abs(p1.y - p2.y);
                                antis.add(new Point(p1.x, p1.y + z * dy));
                                antis.add(new Point(p1.x, p1.y - z * dy));
                            } else {
                                int yDif = p1.y - p2.y;
                                int xDif = p1.x - p2.x;
                                antis.add(new Point(p1.x + z * xDif, p1.y + z * yDif));
                                antis.add(new Point(p2.x - z * xDif, p2.y - z * yDif));
                            }
                            antinodes.addAll(antis);
                        }
                    }
                }
            }
            int finalHeight = height;
            int finalWidth = width;
            total = antinodes.stream().filter(x -> x.x >= 0 && x.x < finalHeight && x.y >= 0 && x.y < finalWidth).count();
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}