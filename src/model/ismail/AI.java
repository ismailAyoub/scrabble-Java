package model.ismail;
import model.claire.*;
import model.Junaid.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 The AI is used to add new words to the board when the player selects single payer.
 @author Ismail Ayoub
 */

public class AI extends Player{

    /**
     The AI node does the same thing as the Node class but it is used here to create a dummy board.
     */
    public class AINode{
            public class Position{
            private int i;
            private int j;

            public Position(){
                i = 0;
                j = 0;
            }

            public void setI(int i) {
                this.i = i;
            }

            public void setJ(int j) {
                this.j = j;
            }

            public int getI() {
                return i;
            }

            public int getJ() {
                return j;
            }
        }
            private boolean empty;
            private String bonus;
            private char letter;
            private Tile tile;
            private Position p;
            private boolean up;
            private boolean down;
            private boolean left;
            private boolean right;
            public PossibleWordPlacments pwp;


           AINode(){
               pwp = new PossibleWordPlacments();
               empty = true;
               letter = '_';
                bonus = "none";
                tile = null;
                p = new Position();
                up = true;
                down = true;
                left = true;
                right = true;
            }
            public void setEmpty(boolean a){
                this.empty = a;
            }

            public void setBonus(String f){
                this.bonus= f;
            }
            public void setLetter(char g){
                this.letter = g;
                this.empty = false;
            }
            public void setTile(Tile t)
            {
                this.tile = t;
                empty = false;
            }
            public void setPosition(int i, int j){
               p.setI(i);
               p.setJ(j);
            }

            public boolean isEmpty(){
                return empty;
            }

            public String getBounce(){
                return bonus;
            }
            public char getLetter(){
                return letter;
            }
            public Tile getTile()
            {
                return tile;
            }
            public Position getPosition(){
                return p;
            }

            public void setDown(boolean down) {
            this.down = down;
            }

            public void setLeft(boolean left) {
                this.left = left;
            }

            public void setRight(boolean right) {
                this.right = right;
            }

            public void setUp(boolean up) {
                this.up = up;
            }

            public boolean isDown() {
                return down;
            }

            public boolean isLeft() {
                return left;
            }

            public boolean isRight() {
                return right;
            }

            public boolean isUp() {
                return up;
            }
    }

   /**
    The PossibleWordPlacements is used to store the values for possible max values in each direction on the board.
    @author Ismail Ayoub
 */
    public class PossibleWordPlacments{
        private char letter;
        private int maxUp;
        private int maxDown;
        private int maxLeft;
        private int maxRight;
        private int maxUpLeft;
        private int maxUpRight;
        private int maxDownLeft;
        private int maxDownRight;

        public PossibleWordPlacments(){
            letter = '-';
            maxDown = 0;
            maxDownLeft = 0;
            maxDownRight = 0;
            maxLeft = 0;
            maxRight = 0;
            maxUp = 0;
            maxUpLeft = 0;
            maxUpRight = 0;
        }

        public void setLetter(char letter) {
            this.letter = letter;
        }

        public void setMaxDown(int maxDown) {
            this.maxDown = maxDown;
        }

        public void setMaxDownLeft(int maxDownLeft) {
            this.maxDownLeft = maxDownLeft;
        }

        public void setMaxDownRight(int maxDownRight) {
            this.maxDownRight = maxDownRight;
        }

        public void setMaxLeft(int maxLeft) {
            this.maxLeft = maxLeft;
        }

        public void setMaxRight(int maxRight) {
            this.maxRight = maxRight;
        }

        public void setMaxUp(int maxUp) {
            this.maxUp = maxUp;
        }

        public void setMaxUpLeft(int maxUpLeft) {
            this.maxUpLeft = maxUpLeft;
        }

        public void setMaxUpRight(int maxUpRight) {
            this.maxUpRight = maxUpRight;
        }

        public int getMaxUp() {
            return maxUp;
        }

        public int getMaxDown() {
            return maxDown;
        }

        public char getLetter() {
            return letter;
        }

        public int getMaxDownLeft() {
            return maxDownLeft;
        }

        public int getMaxDownRight() {
            return maxDownRight;
        }

        public int getMaxLeft() {
            return maxLeft;
        }

        public int getMaxRight() {
            return maxRight;
        }

        public int getMaxUpLeft() {
            return maxUpLeft;
        }

        public int getMaxUpRight() {
            return maxUpRight;
        }
    }
    /**
     The occupiedNodes is used to store the location of all the nodes that contain something in them and are not empty.
     @author Ismail Ayoub
     */
    public class occupiedNodes{
        private int i;
        private int j;

        public occupiedNodes(int a, int b){
            i = a;
            j = b;
        }

        public int getJ() {
            return j;
        }

        public int getI() {
            return i;
        }
    }
    private List<String> wordList = new ArrayList<String>();
    private List<String> wordList2 = new ArrayList<String>();
    private List<String> wordList3 = new ArrayList<String>();
    private List<String> wordList4 = new ArrayList<String>();
    private List<String> wordList5 = new ArrayList<String>();
    private List<String> wordList6 = new ArrayList<String>();
    private List<String> wordList7 = new ArrayList<String>();
    private List<String> wordList8 = new ArrayList<String>();
    private List<String> wordList9 = new ArrayList<String>();
    private List<String> wordList10 = new ArrayList<String>();
    private List<String> wordList11 = new ArrayList<String>();
    private List<String> wordList12 = new ArrayList<String>();
    private List<String> wordList13 = new ArrayList<String>();
    private List<String> wordList14 = new ArrayList<String>();
    private List<String> wordList15 = new ArrayList<String>();
    private AINode[][] AIBoard = new AINode[15][15];
    private void setBonus(AINode n[][]){

        n[0][0].setBonus("tw");
        n[0][3].setBonus("dl");
        n[0][7].setBonus("tw");
        n[0][11].setBonus("dl");
        n[0][14].setBonus("tw");

        n[1][1].setBonus("dw");
        n[1][5].setBonus("tl");
        n[1][9].setBonus("tl");
        n[1][13].setBonus("dw");

        n[2][2].setBonus("dw");
        n[2][6].setBonus("dl");
        n[2][8].setBonus("dl");
        n[2][12].setBonus("dw");

        n[3][0].setBonus("dl");
        n[3][3].setBonus("dw");
        n[3][7].setBonus("dl");
        n[3][11].setBonus("dw");
        n[3][14].setBonus("dl");

        n[4][4].setBonus("dw");
        n[4][10].setBonus("dw");

        n[5][1].setBonus("tl");
        n[5][5].setBonus("tl");
        n[5][9].setBonus("tl");
        n[5][13].setBonus("tl");

        n[6][2].setBonus("dl");
        n[6][6].setBonus("dl");
        n[6][8].setBonus("dl");
        n[6][12].setBonus("dl");

        n[7][0].setBonus("tw");
        n[7][3].setBonus("dl");
        n[7][7].setBonus("star");
        n[7][11].setBonus("dl");
        n[7][14].setBonus("tw");

        n[8][2].setBonus("dl");
        n[8][6].setBonus("dl");
        n[8][8].setBonus("dl");
        n[8][12].setBonus("dl");

        n[9][1].setBonus("tl");
        n[9][5].setBonus("tl");
        n[9][9].setBonus("tl");
        n[9][13].setBonus("tl");

        n[10][4].setBonus("dw");
        n[10][10].setBonus("dw");

        n[11][0].setBonus("dl");
        n[11][3].setBonus("dw");
        n[11][7].setBonus("dl");
        n[11][11].setBonus("dw");
        n[11][14].setBonus("dl");

        n[12][2].setBonus("dw");
        n[12][6].setBonus("dl");
        n[12][8].setBonus("dl");
        n[12][12].setBonus("dw");

        n[13][1].setBonus("dw");
        n[13][5].setBonus("tl");
        n[13][9].setBonus("tl");
        n[13][13].setBonus("dw");

        n[14][0].setBonus("tw");
        n[14][3].setBonus("dl");
        n[14][7].setBonus("tw");
        n[14][11].setBonus("dl");
        n[14][14].setBonus("tw");

    }
    public List<occupiedNodes> o_nodes;

    /**
     This is the AI constructor and it initializes all the valuables on the class and creates the dummy board..
     @param board The is a GameBoard object that is going to be copied into the dummy board.
     @param  d the d is the difficulty of the game that the player chooses.
     */
    public AI(GameBoard board, Difficulty d){
        super("AI", 0);

        Player p = new Player("Computer",0);

        o_nodes = new ArrayList<occupiedNodes>();

        for(int i=0; i < 15;i++){
            for(int j=0; j<15;j++){
                AIBoard[i][j] = new AINode();
                AIBoard[i][j].setEmpty(board.getNode(i,j).isEmpty());
                AIBoard[i][j].setTile(board.getTile(i,j));
                if(board.getNode(i,j).getTile() == null){
                    AIBoard[i][j].setEmpty(true);
                }
                AIBoard[i][j].setPosition(i,j);
                if(i == 0){
                    AIBoard[i][j].setUp(false);
                }
                if(i == 14){
                    AIBoard[i][j].setDown(false);
                }
                if(j == 0){
                    AIBoard[i][j].setLeft(false);
                }
                if(j == 14){
                    AIBoard[i][j].setRight(false);
                }
                if(AIBoard[i][j].isEmpty() == false){
                    o_nodes.add(new occupiedNodes(i,j));
                }
            }
        }

        setBonus(AIBoard);
        AIBoardScan();
        AIBoardScan2();
        if(d == Difficulty.HARD) {
            createHardWords();
        }
        else {
            createEasyWords();
        }
        //addWord(board);

    }


    /**
     AIBoardScan scans the ordinal board and copies it into the dummy board.
     */

    public void AIBoardScan(){

        int dummy_i =0, dummy_j=0;
        for(int i=0; i<15; i++){
            for(int j=0; j<15; j++){

                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isLeft() ){
                    if(AIBoard[i][dummy_j-1].isEmpty() == false){
                        AIBoard[i][j].setLeft(false);
                    }
                }
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isRight()){
                    if(AIBoard[i][ dummy_j+1].isEmpty() == false){
                        AIBoard[i][j].setRight(false);
                    }
                }
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isUp()){
                    if(AIBoard[ dummy_i-1][j].isEmpty() == false){
                        AIBoard[i][j].setUp(false);
                    }
                }
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isDown()){
                    if(AIBoard[ dummy_i+1][j].isEmpty() == false){
                        AIBoard[i][j].setDown(false);
                    }
                }

            }
        }


    }
    /**
     AIBoardScan2 scans the dummy board and calculates the max letter it can install in each direction..
     */

    public void AIBoardScan2(){
        int maxUp= 0, maxDown = 0, maxLeft = 0,maxRight = 0;
        int dummy_i = 0; int dummy_j = 0;
        for(int i=0; i<15; i++){
            for(int j=0; j<15; j++){
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isEmpty() == false){
                    if(AIBoard[i][j].isUp()){
                        while(AIBoard[dummy_i][j].isUp()){
                            dummy_i--;
                            maxUp++;
                        }
                    }
                }
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isEmpty() == false){
                    if(AIBoard[i][j].isDown()){
                        while(AIBoard[dummy_i][j].isDown()){
                            dummy_i++;
                            maxDown++;
                        }
                    }
                }
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isEmpty() == false){
                    if(AIBoard[i][j].isLeft()){
                        while(AIBoard[i][dummy_j].isLeft()){
                            dummy_j--;
                            maxLeft++;
                        }
                    }
                }
                dummy_i = i; dummy_j = j;
                if(AIBoard[i][j].isEmpty() == false){
                    if(AIBoard[i][j].isRight()){
                        while(AIBoard[i][dummy_j].isRight()){
                            dummy_j++;
                            maxRight++;
                        }
                    }
                }
                AIBoard[i][j].pwp.setMaxUp(maxUp);
                AIBoard[i][j].pwp.setMaxDown(maxDown);
                AIBoard[i][j].pwp.setMaxLeft(maxLeft);
                AIBoard[i][j].pwp.setMaxRight(maxRight);
                maxUp= 0;maxDown=0;maxLeft=0;maxRight=0;
            }
        }

    }

    public AINode getNode(int i,int j){
        return AIBoard[i][j];
    }

    /**
     printBoard prints the dummy board.
     */
    public void printBoard(){
        for(int i=0; i<15; i++){

            for(int j=0; j<15; j++){
                if(AIBoard[i][j].getTile() == null){
                    System.out.print('_' + " ");
                }
                else {
                    System.out.print(AIBoard[i][j].getTile().getLetter() + " ");
                }

            }
            System.out.println();
        }
    }

    /**
     createHardWords reads the hard word text file and sorts it in a ArrayList by size to use later in the addWord function.
     */
    public void createHardWords(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("/Users/ismailmarwan/Documents/Game/Scrabble_HARD.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            int wordLength = word.length();
            switch (wordLength) {
                case 2:
                    wordList2.add(word);
                    break;
                case 3:
                    wordList3.add(word);
                    break;
                case 4:
                    wordList4.add(word);
                    break;
                case 5:
                    wordList5.add(word);
                    break;
                case 6:
                    wordList6.add(word);
                    break;
                case 7:
                    wordList7.add(word);
                    break;
                case 8:
                    wordList8.add(word);
                    break;
                case 9:
                    wordList9.add(word);
                    break;
                case 10:
                    wordList10.add(word);
                    break;
                case 11:
                    wordList11.add(word);
                    break;
                case 12:
                    wordList12.add(word);
                    break;
                case 13:
                    wordList13.add(word);
                    break;
                case 14:
                    wordList14.add(word);
                    break;
                case 15:
                    wordList15.add(word);
                    break;

            }
        }
    }
    /**
     createEasyWords reads the easy word text file and sorts it in a ArrayList by size to use later in the addWord function.
     */
    public void createEasyWords(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("/Users/ismailmarwan/Documents/Game/Scrabble_EASY.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            int wordLength = word.length();
            switch (wordLength) {
                case 2:
                    wordList2.add(word);
                    break;
                case 3:
                    wordList3.add(word);
                    break;
                case 4:
                    wordList4.add(word);
                    break;
                case 5:
                    wordList5.add(word);
                    break;
                case 6:
                    wordList6.add(word);
                    break;
                case 7:
                    wordList7.add(word);
                    break;
                case 8:
                    wordList8.add(word);
                    break;
                case 9:
                    wordList9.add(word);
                    break;
                case 10:
                    wordList10.add(word);
                    break;
                case 11:
                    wordList11.add(word);
                    break;
                case 12:
                    wordList12.add(word);
                    break;
                case 13:
                    wordList13.add(word);
                    break;
                case 14:
                    wordList14.add(word);
                    break;
                case 15:
                    wordList15.add(word);
                    break;

            }
        }
    }

    /**
     The addWord uses the calculations made to add a new word to the board in the correct place.
     @param Board is the board that the addWord function is going to place the letters on.
     */
    public void addWord(GameBoard Board){


        Random random = new Random();
        int index = random.nextInt(o_nodes.size());
        //index = 0;

        int i =o_nodes.get(index).getI();
        int j =o_nodes.get(index).getJ();
        int dummy_i, dummy_j, dummy_i2, dummy_j2;

        int maxRight = AIBoard[i][j].pwp.getMaxRight();
        int maxLeft = AIBoard[i][j].pwp.getMaxLeft();
        int maxUp = AIBoard[i][j].pwp.getMaxUp();
        int maxDown = AIBoard[i][j].pwp.getMaxDown();



        int direction = random.nextInt(4);
        //direction = 3;

        int wordList_loop=0;

        if(direction == 0) {
            Random random1 = new Random();
            int x = random.nextInt(maxRight)+1;
            x=4;
            switch (x) {
                case 1:
                    wordList = wordList2;
                    break;
                case 2:
                    wordList = wordList3;
                    break;
                case 3:
                    wordList = wordList4;
                    break;
                case 4:
                    wordList = wordList5;
                    break;
                case 5:
                    wordList = wordList6;
                    break;
                case 6:
                    wordList = wordList7;
                    break;
                case 7:
                    wordList = wordList8;
                    break;
                case 8:
                    wordList = wordList9;
                    break;
                case 9:
                    wordList = wordList10;
                    break;
                case 10:
                    wordList = wordList11;
                    break;
                case 11:
                    wordList = wordList12;
                    break;
                case 12:
                    wordList = wordList13;
                    break;
                case 13:
                    wordList = wordList14;
                    break;
                case 14:
                    wordList = wordList15;
                    break;

            }


            wordList_loop=0;
            Random random2 = new Random();

            while (wordList_loop < wordList.size()) {
                dummy_i = i;
                dummy_j = j;
                dummy_i2 = i;
                dummy_j2 = j;

                int x2 = random.nextInt(wordList.size());

                if (wordList.get(x2).length() <= maxRight+1) {
                    if(Character.toUpperCase(wordList.get(x2).charAt(0)) == AIBoard[i][j].getTile().getLetter()){
                        for (int k = 1; k < wordList.get(x2).length(); k++) {
                            Tile tile = new Tile(0,Character.toUpperCase(wordList.get(x2).charAt(k)));
                            AIBoard[i][++dummy_j].setTile(tile);


                            boolean found = false;
                            char c = Character.toUpperCase(wordList.get(x2).charAt(k));
                            Scanner sc = null;
                            try {
                                sc = new Scanner(new File("/Users/ismailmarwan/Documents/Game/AI_tile_distribution.txt"));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            while (sc.hasNextLine()) {
                                Scanner s2 = new Scanner(sc.nextLine());
                                char l = '-';
                                int p = 0;
                                while (s2.hasNext()) {
                                    if(c == s2.next().charAt(0)) {
                                        l = c;
                                        p = Integer.parseInt(s2.next());
                                        found = true;
                                    }
                                }

                                if(found){
                                    Tile t = new Tile(p,l);
                                    Board.setTile(i,++dummy_j2,t);
                                    break;
                                }

                            }
                            sc.close();


                        }
                        break;
                    }
                }
                wordList_loop++;

            }
        }

        if(direction == 1) {
                Random random1 = new Random();
                int x = random.nextInt(maxLeft)+1;

                switch (x) {
                    case 1:
                        wordList = wordList2;
                        break;
                    case 2:
                        wordList = wordList3;
                        break;
                    case 3:
                        wordList = wordList4;
                        break;
                    case 4:
                        wordList = wordList5;
                        break;
                    case 5:
                        wordList = wordList6;
                        break;
                    case 6:
                        wordList = wordList7;
                        break;
                    case 7:
                        wordList = wordList8;
                        break;
                    case 8:
                        wordList = wordList9;
                        break;
                    case 9:
                        wordList = wordList10;
                        break;
                    case 10:
                        wordList = wordList11;
                        break;
                    case 11:
                        wordList = wordList12;
                        break;
                    case 12:
                        wordList = wordList13;
                        break;
                    case 13:
                        wordList = wordList14;
                        break;
                    case 14:
                        wordList = wordList15;
                        break;

                }

            wordList_loop=0;
            Random random2 = new Random();
                /*while (wordList_loop < wordList.size()) {
                dummy_i = i;
                dummy_j = j;

                int x2 = random.nextInt(wordList.size());
                if (wordList.get(x2).length() <= maxLeft+1) {
                    int wordLength = wordList.get(x2).length();
                    if(wordList.get(x2).charAt(wordLength-1) == AIBoard[i][j].getLetter()){
                    dummy_j -= wordList.get(x2).length()-1;
                    for (int k = 1; k < wordList.get(x2).length()-1; k++) {
                        AIBoard[i][dummy_j++].setLetter(wordList.get(x2).charAt(k));
                    }
                    break;
                }
                    }
                wordList_loop++;

            }*/
            while (wordList_loop < wordList.size()) {
                dummy_i = i;
                dummy_j = j;
                dummy_i2 = i;
                dummy_j2 = j;

                int x2 = random.nextInt(wordList.size());

                if (wordList.get(x2).length() <= maxLeft+1) {
                    int wordLength = wordList.get(x2).length();
                    if(Character.toUpperCase(wordList.get(x2).charAt(wordLength-1)) == AIBoard[i][j].getTile().getLetter()){
                        dummy_j -= wordList.get(x2).length()-1;
                        dummy_j2 -= wordList.get(x2).length()-1;
                        for (int k = 0; k < wordList.get(x2).length(); k++) {
                            Tile tile = new Tile(0,Character.toUpperCase(wordList.get(x2).charAt(k)));
                            AIBoard[i][dummy_j++].setTile(tile);


                            boolean found = false;
                            char c = Character.toUpperCase(wordList.get(x2).charAt(k));
                            Scanner sc = null;
                            try {
                                sc = new Scanner(new File("/Users/ismailmarwan/Documents/Game/AI_tile_distribution.txt"));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            while (sc.hasNextLine()) {

                                Scanner s2 = new Scanner(sc.nextLine());
                                char l = '-';
                                int p = 0;
                                while (s2.hasNext()) {
                                    if(c == s2.next().charAt(0)) {
                                        l = c;
                                        p = Integer.parseInt(s2.next());
                                        found = true;
                                    }
                                }

                                if(found){
                                    Tile t = new Tile(p,l);
                                    Board.setTile(i,dummy_j2++,t);
                                    break;
                                }

                            }
                            sc.close();


                        }
                        break;
                    }
                }
                wordList_loop++;

            }
        }

        if(direction == 2) {

            Random random1 = new Random();
            int x = random.nextInt(maxUp)+1;
            switch (x) {
                case 1:
                    wordList = wordList2;
                    break;
                case 2:
                    wordList = wordList3;
                    break;
                case 3:
                    wordList = wordList4;
                    break;
                case 4:
                    wordList = wordList5;
                    break;
                case 5:
                    wordList = wordList6;
                    break;
                case 6:
                    wordList = wordList7;
                    break;
                case 7:
                    wordList = wordList8;
                    break;
                case 8:
                    wordList = wordList9;
                    break;
                case 9:
                    wordList = wordList10;
                    break;
                case 10:
                    wordList = wordList11;
                    break;
                case 11:
                    wordList = wordList12;
                    break;
                case 12:
                    wordList = wordList13;
                    break;
                case 13:
                    wordList = wordList14;
                    break;
                case 14:
                    wordList = wordList15;
                    break;

            }


            wordList_loop=0;
            Random random2 = new Random();

           /* while (wordList_loop < wordList.size()) {

                int x2 = random.nextInt(wordList.size());
                dummy_i = i;
                dummy_j = j;
                if (wordList.get(x2).length() <= maxUp+1) {
                    int wordLength = wordList.get(x2).length();
                    if(wordList.get(x2).charAt(wordLength-1) == AIBoard[i][j].getLetter()){
                        dummy_i -= wordList.get(x2).length()-1;
                        for (int k = 0; k < wordList.get(x2).length()-1; k++) {
                            AIBoard[dummy_i++][j].setLetter(wordList.get(x2).charAt(k));

                        }
                        break;
                    }
                }
                wordList_loop++;

            }*/
            while (wordList_loop < wordList.size()) {
                dummy_i = i;
                dummy_j = j;
                dummy_i2 = i;
                dummy_j2 = j;

                int x2 = random.nextInt(wordList.size());

                if (wordList.get(x2).length() <= maxUp+1) {
                    int wordLength = wordList.get(x2).length();
                    if(Character.toUpperCase(wordList.get(x2).charAt(wordLength-1)) == AIBoard[i][j].getTile().getLetter()){
                        dummy_i -= wordList.get(x2).length()-1;
                        dummy_i2 -= wordList.get(x2).length()-1;
                        for (int k = 0; k < wordList.get(x2).length(); k++) {
                            Tile tile = new Tile(0,Character.toUpperCase(wordList.get(x2).charAt(k)));
                            AIBoard[dummy_i++][j].setTile(tile);


                            boolean found = false;
                            char c = Character.toUpperCase(wordList.get(x2).charAt(k));
                            Scanner sc = null;
                            try {
                                sc = new Scanner(new File("/Users/ismailmarwan/Documents/Game/AI_tile_distribution.txt"));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            while (sc.hasNextLine()) {

                                Scanner s2 = new Scanner(sc.nextLine());
                                char l = '-';
                                int p = 0;
                                while (s2.hasNext()) {
                                    if(c == s2.next().charAt(0)) {
                                        l = c;
                                        p = Integer.parseInt(s2.next());
                                        found = true;
                                    }
                                }

                                if(found){
                                    Tile t = new Tile(p,l);
                                    Board.setTile(dummy_i2++,j,t);
                                    break;
                                }

                            }
                            sc.close();


                        }
                        break;
                    }
                }
                wordList_loop++;

            }


        }

        if(direction == 3) {

            Random random1 = new Random();
            int x = random.nextInt(maxDown)+1;
            switch (x) {
                case 1:
                    wordList = wordList2;
                    break;
                case 2:
                    wordList = wordList3;
                    break;
                case 3:
                    wordList = wordList4;
                    break;
                case 4:
                    wordList = wordList5;
                    break;
                case 5:
                    wordList = wordList6;
                    break;
                case 6:
                    wordList = wordList7;
                    break;
                case 7:
                    wordList = wordList8;
                    break;
                case 8:
                    wordList = wordList9;
                    break;
                case 9:
                    wordList = wordList10;
                    break;
                case 10:
                    wordList = wordList11;
                    break;
                case 11:
                    wordList = wordList12;
                    break;
                case 12:
                    wordList = wordList13;
                    break;
                case 13:
                    wordList = wordList14;
                    break;
                case 14:
                    wordList = wordList15;
                    break;

            }


            wordList_loop=0;
            Random random2 = new Random();
           /* while (wordList_loop < wordList.size()) {

                int x2 = random.nextInt(wordList.size());
                dummy_i = i;
                dummy_j = j;
                if (wordList.get(x2).length() <= maxDown+1) {
                    int wordLength = wordList.get(x2).length();
                    if(wordList.get(x2).charAt(0) == AIBoard[i][j].getLetter()){
                        for (int k = 1; k < wordList.get(x2).length(); k++) {
                            AIBoard[++dummy_i][j].setLetter(wordList.get(x2).charAt(k));
                        }
                        break;
                    }
                }
                wordList_loop++;

            }*/
            while (wordList_loop < wordList.size()) {
                dummy_i = i;
                dummy_j = j;
                dummy_i2 = i;
                dummy_j2 = j;

                int x2 = random.nextInt(wordList.size());

                if (wordList.get(x2).length() <= maxDown+1) {
                    if(Character.toUpperCase(wordList.get(x2).charAt(0)) == AIBoard[i][j].getTile().getLetter()){
                        for (int k = 0; k < wordList.get(x2).length(); k++) {
                            Tile tile = new Tile(0,Character.toUpperCase(wordList.get(x2).charAt(k)));
                            AIBoard[dummy_i++][j].setTile(tile);


                            boolean found = false;
                            char c = Character.toUpperCase(wordList.get(x2).charAt(k));
                            Scanner sc = null;
                            try {
                                sc = new Scanner(new File("/Users/ismailmarwan/Documents/Game/AI_tile_distribution.txt"));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            while (sc.hasNextLine()) {
                                Scanner s2 = new Scanner(sc.nextLine());
                                char l = '-';
                                int p = 0;
                                while (s2.hasNext()) {
                                    if(c == s2.next().charAt(0)) {
                                        l = c;
                                        p = Integer.parseInt(s2.next());
                                        found = true;
                                    }
                                }

                                if(found){
                                    Tile t = new Tile(p,l);
                                    Board.setTile(dummy_i2++,j,t);
                                    break;
                                }

                            }
                            sc.close();


                        }
                        break;
                    }
                }
                wordList_loop++;

            }
        }
    }


}
