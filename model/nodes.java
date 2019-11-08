public class nodes {
    private boolean empty = true;
    private boolean left = false,right = false,top = false,bottom = false;
    private String bonus;
    private char letter;

    nodes(){
        letter = '_';
        bonus = "none";
    }
    nodes(char i){
        letter = i;
    }

    public void setEmpty(boolean a){
        this.empty = a;
    }
    public void setLeft(boolean b){
        this.left = b;
    }
    public void setRight(boolean c){
        this.right = c;
    }
    public void setTop(boolean d){
        this.top = d;
    }
    public void setBottom(boolean e){
        this.bottom = e;
    }
    public void setBounce(String f){
        this.bonus= f;
    }
    public void setLetter(char g){
        this.letter = g;
    }

    public boolean isEmpty(){
        return empty;
    }
    public boolean isLeft(){
        return left;
    }
    public boolean isRight(){
        return right;
    }
    public boolean isTop(){
        return top;
    }
    public boolean isBottom(){
        return bottom;
    }
    public String getBounce(){
        return bonus;
    }
    public char getLetter(){
        return letter;
    }

}
