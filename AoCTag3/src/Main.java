import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuilder text = new StringBuilder();
            boolean enabled = true;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
            Matcher matcher = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|don't|do").matcher(text.toString());
            while (matcher.find()){
                String next = matcher.group();
                if(Objects.equals(next, "do")){
                    enabled = true;
                    continue;
                }
                if (Objects.equals(next, "don't")){
                    enabled = false;
                    continue;
                }
                if (enabled) {
                    Matcher nums = Pattern.compile("\\d{1,3}").matcher(next);
                    int a = 1;
                    while (nums.find()) {
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