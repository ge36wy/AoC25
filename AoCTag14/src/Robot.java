import java.util.Objects;

public class Robot {
    long xCord;
    long yCord;
    long xVel;
    long yVel;

    public Robot(long xCord, long yCord, long xVel, long yVel){
        this.xCord = xCord;
        this.yCord = yCord;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return xCord == robot.xCord && yCord == robot.yCord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCord, yCord);
    }
}
