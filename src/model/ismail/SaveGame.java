import java.io.*;
import java.util.Scanner;

public class SaveGame {

    public void Save(gameBoard board) {

        FileWriter fw;
        try {
            fw = new FileWriter("/Users/ismailmarwan/IdeaProjects/Scrabble/src/saveGame.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 15; i++) {

                for (int j = 0; j < 15; j++) {
                    String a = Integer.toString(i);
                    String b = Integer.toString(j);
                    bw.write(board.getNode(i, j).getLetter());
                    bw.write(" ");
                    bw.write(a);
                    bw.write(" ");
                    bw.write(b);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load(gameBoard board) {

        Scanner sc = null;
        try {
            sc = new Scanner(new File("/Users/ismailmarwan/IdeaProjects/Scrabble/src/saveGame.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            Scanner s2 = new Scanner(sc.nextLine());
            while (s2.hasNext()) {
                char s = s2.next().charAt(0);
                int i = Integer.parseInt(s2.next());
                int j = Integer.parseInt(s2.next());
                board.setLetter(i,j,s);
            }

        }

    }
}

