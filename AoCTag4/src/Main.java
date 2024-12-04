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
                //forwards
                total += line.split("XMAS", -1).length - 1;
                //backwards
                total += (new StringBuilder(line).reverse().toString()).split("XMAS", - 1).length - 1;
            }
            //downwards and upwards
            for (int i = 0; i < field.size() - 3; i++){
                for (int j = 0; j < field.get(0).length(); j++){
                    if(field.get(i).charAt(j) == 'X' && field.get(i + 1).charAt(j) == 'M' && field.get(i + 2).charAt(j) == 'A' && field.get(i + 3).charAt(j) == 'S') total++;
                    if(field.get(i).charAt(j) == 'S' && field.get(i + 1).charAt(j) == 'A' && field.get(i + 2).charAt(j) == 'M' && field.get(i + 3).charAt(j) == 'X') total++;
                }
            }
            //diagonal array
            ArrayList<String> diagonal = new ArrayList<>();
            for (int n = -field.size(); n <= field.size(); n++) {
                ArrayList<Character> row = new ArrayList<>();
                for(int i = 0; i < field.size(); i++){
                    if((i-n>=0)&&(i-n< field.size())){
                        row.add(field.get(i).charAt(i-n));
                    }
                }
                diagonal.add(getStringRepresentation(row));
            }
            //forwards and backwards
            for (String s: diagonal){
                total += s.split("XMAS", -1).length - 1;
                total += (new StringBuilder(s).reverse().toString()).split("XMAS", - 1).length - 1;
            }
            ArrayList<String> diagonalb = new ArrayList<>();
            for (int n = 0; n <= 2 * (field.size() - 1); n++) {
                ArrayList<Character> row = new ArrayList<>();
                for(int i = 0; i <= n; i++){
                    try {
                        row.add(field.get(n - i).charAt(i));
                    }catch (Exception ignored){}
                }
                diagonalb.add(getStringRepresentation(row));
            }
            //forwards and backwards
            for (String s: diagonalb){
                total += s.split("XMAS", -1).length - 1;
                total += (new StringBuilder(s).reverse().toString()).split("XMAS", - 1).length - 1;
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStringRepresentation(ArrayList<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
}