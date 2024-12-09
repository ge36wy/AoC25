import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<Pair> files = new ArrayList<>();
            ArrayList<Pair> filesWOGaps = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                boolean file = true;
                int index = 0;
                for (int i = 0; i < line.length(); i++){
                    int curr = Integer.parseInt(String.valueOf(line.charAt(i)));
                    if (file){
                        files.add(new Pair(true, index, curr));
                        filesWOGaps.add(new Pair(true, index, curr));
                        index++;
                    } else {
                        files.add(new Pair(false, -1, curr));
                    }
                    file = !file;
                }
            }
            Collections.reverse(filesWOGaps);
            ArrayList<Pair> newPairs = new ArrayList<>();
            for (int i = 0; i < files.size(); i++){
                if (i % 1000 == 0) System.out.println(i);
                Pair current = files.get(i);
                if (current.file){
                    newPairs.add(current);
                } else {
                    int sizeRem = current.size;
                    for (int j = 0; j < filesWOGaps.size(); j++){
                        Pair toFill = filesWOGaps.get(j);
                        int index = files.indexOf(toFill);
                        if (toFill.size <= sizeRem){
                            newPairs.add(toFill);
                            sizeRem -= toFill.size;
                            filesWOGaps.remove(j);
                            files.set(index, new Pair(false, -1, toFill.size));
                            j--;
                        }
                        if (sizeRem == 0) break;
                    }
                    if (sizeRem > 0){
                        newPairs.add(new Pair(false, -1, sizeRem));
                    }
                }
            }
            int x = 0;
            HashSet<Integer> seen = new HashSet<>();
            for (Pair p : newPairs) {
                if (p.file && !seen.contains(p.index)) {
                    seen.add(p.index);
                    for (int j = 0; j < p.size; j++) {
                        total += (long) p.index * (x + j);
                    }
                }
                x += p.size;
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}