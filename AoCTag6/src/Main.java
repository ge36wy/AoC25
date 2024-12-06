import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    static Orientation orientation = Orientation.UP;
    static HashSet<Position> visited = new HashSet<>();
    static HashMap<Orientation, Orientation> changeDir = new HashMap<>();
    static HashMap<Orientation, Position> transform = new HashMap<>();

    static Position start;
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            ArrayList<String> area = new ArrayList<>();
            changeDir.put(Orientation.UP, Orientation.RIGHT);
            changeDir.put(Orientation.RIGHT, Orientation.DOWN);
            changeDir.put(Orientation.DOWN, Orientation.LEFT);
            changeDir.put(Orientation.LEFT, Orientation.UP);
            transform.put(Orientation.UP, new Position(-1, 0));
            transform.put(Orientation.DOWN, new Position(1, 0));
            transform.put(Orientation.LEFT, new Position(0, -1));
            transform.put(Orientation.RIGHT, new Position(0, 1));
            Position current = null;
            int i = 0;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                area.add(line);
                if (line.contains("^")){
                    current = new Position(i, line.indexOf("^"));
                    start = current;
                    visited.add(current);
                }
                i++;
            }
            while (true){
                assert current != null;
                int x = current.x;
                int y = current.y;
                Position trans = transform.get(orientation);
                if(x + trans.x < 0 || x + trans.x >= area.size() || y + trans.y < 0 || y + trans.y >= area.get(0).length()) break;
                if (area.get(x + trans.x).charAt(y + trans.y) == '#'){
                    orientation = changeDir.get(orientation);
                    continue;
                }
                current = new Position(x + trans.x, y + trans.y);
                visited.add(current);
            }
            for (i = 0; i < area.size(); i++){
                for (int j = 0; j < area.get(0).length(); j++){
                    int finalI = i;
                    int finalJ = j;
                    if(area.get(i).charAt(j) == '.' && visited.stream().anyMatch(p -> p.x == finalI && p.y == finalJ)){
                        if(isLoop(area, i, j)) total++;
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLoop(ArrayList<String> area, int i, int j){
        orientation = Orientation.UP;
        ArrayList<String> newArea = new ArrayList<>(area);
        HashSet<Position> visitedLoop = new HashSet<>();
        String s = newArea.get(i);
        newArea.set(i, s.substring(0, j) + "#" + s.substring(j + 1));
        Position current = start;
        visitedLoop.add(start);
        while (true){
            assert current != null;
            int x = current.x;
            int y = current.y;
            Position trans = transform.get(orientation);
            if(x + trans.x < 0 || x + trans.x >= newArea.size() || y + trans.y < 0 || y + trans.y >= newArea.get(0).length()) return false;
            if (newArea.get(x + trans.x).charAt(y + trans.y) == '#'){
                orientation = changeDir.get(orientation);
                continue;
            }
            current = new Position(x + trans.x, y + trans.y, orientation);
            if(visitedLoop.contains(current)) {
                return true;
            }
            visitedLoop.add(current);
        }
    }
}