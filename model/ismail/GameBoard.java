package model.ismail;

public class GameBoard {

    private Node board[][] = new Node[15][15];

    GameBoard(){
        for(int i=0; i<15; i++){

            for(int j=0; j<15; j++) {
                board[i][j] = new Node();
                if(i == 0) board[i][j].setTop(true);
                if(i == 14) board[i][j].setBottom(true);
                if(j == 0) board[i][j].setLeft(true);
                if(j == 14) board[i][j].setRight(true);
            }

        }

        setBonus(board);

    }

    private void setBonus(Node n[][]){

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

    }
    public void printBoard(){
        for(int i=0; i<15; i++){

            for(int j=0; j<15; j++){
                System.out.print(board[i][j].getLetter() + " ");

            }
            System.out.println();
        }
    }

    public void setLetter(int i, int j, char l){
        board[i][j].setLetter(l);
    }
    public Node getNode(int i,int j){
        return board[i][j];
    }


}
