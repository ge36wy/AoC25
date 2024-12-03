import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)").matcher(line);
                while (matcher.find()){
                    Matcher nums = Pattern.compile("\\d{1,3}").matcher(matcher.group());
                    int a = 1;
                    while (nums.find()){
                        a *= Integer.parseInt(nums.group());
                    }
                    total += a;
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}