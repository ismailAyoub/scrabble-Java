import java.util.Scanner;

public class Scrabble {



    public static void main(String[] args){

        gameBoard board1 = new gameBoard();
        SaveGame save_game1 = new SaveGame();

        Scanner input = new Scanner(System.in);
        String loop = "yes";
        System.out.print("Do you want to load the last game?");
        String doLoad;
        doLoad = input.next();
        if(doLoad.equals("yes")){
            save_game1.Load(board1);
        }
        board1.printBoard();
        while (loop.equals("yes")){
            System.out.print("Enter the letter you want to place: ");
            char l = input.next().charAt(0);
            System.out.print("Enter position 'i' in array:");
            int i = input.nextInt();
            System.out.print("Enter position 'j' in array:");
            int j = input.nextInt();
            board1.setLetter(i,j,l);
            board1.printBoard();
            System.out.print("Do you want to input another letter? 'yes' or 'no' ");
            loop = input.next();

        }
        String doSave;
        System.out.print("Do you want to save game? 'yes' or 'no'");
        doSave = input.next();
        if(doSave.equals("yes")){
            save_game1.Save(board1);
        }


    }

}
