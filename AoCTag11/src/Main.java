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
            HashMap<Long, Long> occurencies = new HashMap<>();
            int blinks = 75;
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(" ");
                for (String s: nums){
                    occurencies.put(Long.parseLong(s), 1L);
                }
            }
            for (int i = 0; i < blinks; i++){
                System.out.println(i);
                HashMap<Long, Long> newNumbersOcc = new HashMap<>();
                for (Long num: occurencies.keySet()){
                    Long o = occurencies.get(num);
                    ArrayList<Long> next = nextStep(num);
                    for (Long l: next){
                        if (newNumbersOcc.containsKey(l)) {
                            newNumbersOcc.put(l, newNumbersOcc.get(l) + o);
                        } else {
                            newNumbersOcc.put(l, o);
                        }
                    }
                }
                occurencies = newNumbersOcc;
            }
            for (Long l: occurencies.values()) total += l;
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Long> nextStep(long num){
        ArrayList<Long> newNumbers = new ArrayList<>();
        int digits = (int)(Math.log10(num)+1);
        if (num == 0) {
            newNumbers.add(1L);
        } else if (digits % 2 == 0) {
            newNumbers.add((long) (num / Math.pow(10, digits / 2)));
            newNumbers.add((long) (num % Math.pow(10, digits / 2)));
        } else {
            newNumbers.add(num * 2024);
        }
        return newNumbers;
    }
}