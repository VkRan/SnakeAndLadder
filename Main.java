import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to SNAKE & LADDER game!\n");
        SnakeLadderGame s = new SnakeLadderGame();
        s.beginGame();
    }
}

class SnakeLadderGame{
    int destinationPos = 100;
    Map<Integer,Integer> snakePos = new HashMap<Integer,Integer>();
    Map<Integer,Integer> ladderPos = new HashMap<Integer,Integer>();

    public void beginGame(){
        setSnakePos();
        setLadderPos();
        int player1Pos=0, player2Pos=0;
        int currentPlayer = 1;
        Scanner s = new Scanner(System.in);
        String str;
        int diceValue;
        do{
            System.out.println(currentPlayer==1?"\n\nFIRST PLAYER TURN":"\n\nSECOND PLAYER TURN");
            System.out.println("Press r/R to roll dice");
            str=s.next();

            diceValue=diceRoll();

            if(currentPlayer==1){
                player1Pos = calculatePlayerPos(player1Pos,diceValue);
                displayCurrentPos(player1Pos,player2Pos);
                if(hasWon(player1Pos)){
                    System.out.println("\n\nFirst Player wins");
                    return;
                }
            } else {
                player2Pos = calculatePlayerPos(player2Pos,diceValue);
                displayCurrentPos(player1Pos,player2Pos);
                if(hasWon(player2Pos)){
                    System.out.println("\n\nSecond Player wins");
                    return;
                }
            }
            currentPlayer=3-currentPlayer;
        }while("r".equals(str)||"R".equals(str));
    }

    public void setSnakePos(){
        snakePos.put(97,42);
        snakePos.put(94,71);
        snakePos.put(75,32);
        snakePos.put(47,16);
        snakePos.put(37,3);
        snakePos.put(28,10);
    }

    public void setLadderPos(){
        ladderPos.put(4,56);
        ladderPos.put(12,50);
        ladderPos.put(14,55);
        ladderPos.put(22,58);
        ladderPos.put(41,79);
        ladderPos.put(54,88);
    }

    //simulating a dice roll by generating a random no. from 1-6
    private int diceRoll(){
        int number;
        Random randomValue = new Random();
        number = randomValue.nextInt(7);
        return (number==0?1:number);
    }

    //calculating the new position of the player after rolling dice
    private int calculatePlayerPos(int playerCurrentPos, int diceValue){
        playerCurrentPos = playerCurrentPos + diceValue;

        if(playerCurrentPos>destinationPos){
            playerCurrentPos=playerCurrentPos-diceValue;
            return playerCurrentPos;
        }
        if(snakePos.get(playerCurrentPos)!=null){
            System.out.println("Oops... Eaten by snake");
            playerCurrentPos=snakePos.get(playerCurrentPos);
        }
        else if(ladderPos.get(playerCurrentPos)!=null){
            System.out.println("Wohoo... Climbing the ladder");
            playerCurrentPos=ladderPos.get(playerCurrentPos);
        }
        return playerCurrentPos;
    }

    private void displayCurrentPos(int player1Pos, int player2Pos){
        System.out.println("First Player Position : "+player1Pos);
        System.out.println("Second Player Position : "+player2Pos);
        System.out.println("=================================");
    }

    //check if the player has won or not
    private boolean hasWon(int playerPos){
        return destinationPos == playerPos;
    }

}