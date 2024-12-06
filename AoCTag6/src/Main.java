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
            //int total = 0;
            ArrayList<String> area = new ArrayList<>();
            Orientation orientation = Orientation.UP;
            HashSet<Position> visited = new HashSet<>();
            HashMap<Orientation, Orientation> changeDir = new HashMap<>();
            changeDir.put(Orientation.UP, Orientation.RIGHT);
            changeDir.put(Orientation.RIGHT, Orientation.DOWN);
            changeDir.put(Orientation.DOWN, Orientation.LEFT);
            changeDir.put(Orientation.LEFT, Orientation.UP);
            HashMap<Orientation, Position> transform = new HashMap<>();
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
            System.out.println(visited.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}