import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            int width = 103;
            int height = 101;
            int time = 10000;
            ArrayList<Robot> initial = new ArrayList<>();
            ArrayList<HashSet<Robot>> positions = new ArrayList<>();
            for (int i = 0; i < time; i++) positions.add(new HashSet<>());
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(" ");
                String[] pos = nums[0].split(",");
                String[] vel = nums[1].split(",");
                initial.add(new Robot(Long.parseLong(pos[0].split("=")[1]), Long.parseLong(pos[1]), Long.parseLong(vel[0].split("=")[1]), Long.parseLong(vel[1])));
            }
            for (Robot r: initial){
                moveRobot(r, time, width, height, positions);
            }
            for (int i = 0; i < positions.size(); i++){
                if (positions.get(i).size() == initial.size()){
                    System.out.println(i);
                    break;
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveRobot(Robot startRobot, int time, int width, int height, ArrayList<HashSet<Robot>> positions){
        long xCord = startRobot.xCord;
        long yCord = startRobot.yCord;
        long xVel = startRobot.xVel;
        long yVel = startRobot.yVel;
        for (int i = 0; i < time; i++){
            xCord += xVel;
            yCord += yVel;
            xCord = xCord % height;
            if (xCord < 0) {
                xCord = xCord + height;
            }
            yCord = yCord % width;
            if (yCord < 0){
                yCord = yCord + width;
            }
            positions.get(i).add(new Robot(xCord, yCord, xVel, yVel));
        }
    }

}