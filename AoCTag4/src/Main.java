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
            int total = 0;
            ArrayList<String> field = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                field.add(line);
            }
            for (int i = 0; i < field.size() - 2; i++){
                for (int j = 0; j < field.get(0).length() - 2; j++){
                    if(field.get(i + 1).charAt(j + 1) == 'A'){
                        if ((field.get(i).charAt(j) == 'M' && field.get(i + 2).charAt(j + 2) == 'S') || (field.get(i).charAt(j) == 'S' && field.get(i + 2).charAt(j + 2) == 'M')){
                            if ((field.get(i + 2).charAt(j) == 'M' && field.get(i).charAt(j + 2) == 'S') || (field.get(i + 2).charAt(j) == 'S' && field.get(i).charAt(j + 2) == 'M')) {
                                total++;
                            }
                        }
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}