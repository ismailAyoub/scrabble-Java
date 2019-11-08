package model.ismail;

import java.util.Scanner;

public class Scrabble {



    /*public static void setBounces(nodes n[][]){

        n[0][0].setBounce("tw");
        n[0][3].setBounce("dl");
        n[0][7].setBounce("tw");
        n[0][11].setBounce("dl");
        n[0][14].setBounce("tw");

        n[1][1].setBounce("dw");
        n[1][5].setBounce("tl");
        n[1][9].setBounce("tl");
        n[1][13].setBounce("dw");

        n[2][2].setBounce("dw");
        n[2][6].setBounce("dl");
        n[2][8].setBounce("dl");
        n[2][12].setBounce("dw");

        n[3][0].setBounce("dl");
        n[3][3].setBounce("dw");
        n[3][7].setBounce("dl");
        n[3][11].setBounce("dw");
        n[3][14].setBounce("dl");

        n[4][4].setBounce("dw");
        n[4][10].setBounce("dw");

    }*/


    public static void main(String[] args){

        GameBoard board1 = new GameBoard();
        board1.printBoard();
        System.out.println();
		board1.getNode(0,0).getBounce();
        String words[] = {"cat","dog","house","car","bike"};

        Scanner input = new Scanner(System.in);
        String loop = "yes";
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


    }

}
