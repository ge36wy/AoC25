import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] first = line.split(": ");
                long left = Long.parseLong(first[0]);
                String[] second = first[1].split(" ");
                ArrayList<Long> right = new ArrayList<>();
                for (String s: second){
                    right.add(Long.parseLong(s));
                }
                if (canBeCombined(left, right)){
                    total += left;
                    //System.out.println(left);
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean canBeCombined(Long value, ArrayList<Long> factors){
        if (factors.size() == 1){
            return Objects.equals(value, factors.get(0));
        }
        //plus
        ArrayList<Long> newFactors = new ArrayList<>();
        newFactors.add(factors.get(0) + factors.get(1));
        for (int i = 2; i < factors.size(); i++) newFactors.add(factors.get(i));
        boolean plus = canBeCombined(value, newFactors);

        //mult
        ArrayList<Long> newFactorsMult = new ArrayList<>();
        newFactorsMult.add(factors.get(0) * factors.get(1));
        for (int i = 2; i < factors.size(); i++) newFactorsMult.add(factors.get(i));
        boolean mult = canBeCombined(value, newFactorsMult);

        //concat
        ArrayList<Long> newFactorsConc = new ArrayList<>();
        newFactorsConc.add(Long.parseLong(factors.get(0) + String.valueOf(factors.get(1))));
        for (int i = 2; i < factors.size(); i++) newFactorsConc.add(factors.get(i));
        boolean concat = canBeCombined(value, newFactorsConc);
        return plus || mult || concat;
    }
}