import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total;
            ArrayList<Long> numbers = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(" ");
                for (String s: nums) numbers.add(Long.parseLong(s));
            }
            for (int i = 0; i < 25; i++){
                ArrayList<Long> newNumbers = new ArrayList<>();
                for (Long num: numbers){
                    if (num == 0) {
                        newNumbers.add(1L);
                    } else if ((int)(Math.log10(num)+1) % 2 == 0) {
                        String s = String.valueOf(num);
                        newNumbers.add(Long.parseLong(s.substring(0, s.length() / 2)));
                        newNumbers.add(Long.parseLong(s.substring(s.length() / 2)));
                    } else {
                        newNumbers.add(num * 2024);
                    }
                }
                numbers = newNumbers;
            }
            total = numbers.size();
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}