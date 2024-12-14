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
            int width = 103;
            int height = 101;
            int time = 100;
            ArrayList<Robot> initial = new ArrayList<>();
            ArrayList<Robot> endRobots = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(" ");
                String[] pos = nums[0].split(",");
                String[] vel = nums[1].split(",");
                initial.add(new Robot(Long.parseLong(pos[0].split("=")[1]), Long.parseLong(pos[1]), Long.parseLong(vel[0].split("=")[1]), Long.parseLong(vel[1])));
            }
            for (Robot r: initial){
                endRobots.add(moveRobot(r, time, width, height));
            }
            long topLeft = 0;
            long topRight = 0;
            long bottomLeft = 0;
            long bottomRight = 0;
            for (Robot r: endRobots){
                if (r.xCord < (height - 1) / 2 && r.yCord < (width - 1) / 2) topLeft++;
                if (r.xCord < (height - 1) / 2 && r.yCord > (width - 1) / 2) topRight++;
                if (r.xCord > (height - 1) / 2 && r.yCord < (width - 1) / 2) bottomLeft++;
                if (r.xCord > (height - 1) / 2 && r.yCord > (width - 1) / 2) bottomRight++;
            }
            total = topLeft * topRight * bottomLeft * bottomRight;
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Robot moveRobot(Robot startRobot, int time, int width, int height){
        long xCord = startRobot.xCord;
        long yCord = startRobot.yCord;
        long xVel = startRobot.xVel;
        long yVel = startRobot.yVel;
        for (int i = 0; i < time; i++){
            xCord += xVel;
            yCord += yVel;
        }
        xCord = xCord % height;
        if (xCord < 0) {
            xCord = xCord + height;
        }
        yCord = yCord % width;
        if (yCord < 0){
            yCord = yCord + width;
        }
        return new Robot(xCord, yCord, xVel, yVel);
    }

}