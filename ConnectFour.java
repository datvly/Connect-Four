import java.util.Scanner;


//Play a game of connect 4
public class ConnectFour {

    public final static int ROW_OF_GAMEBOARD = 6;
    public final static int COLUMN_OF_GAMEBOARD = 7;
    public final static int PLAYER_ONE = 1;
    public final static int PLAYER_TWO = 2;

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        playTheGame (key);
    }

    //Plays the entire game of connect 4
    public static void playTheGame (Scanner key) {
        boolean gameContinues = true;
        boolean playerOneTurn = true;
        char [][] gameBoard = new char [ROW_OF_GAMEBOARD][COLUMN_OF_GAMEBOARD];
        intro();
        String player1 = gettingPlayers(key, PLAYER_ONE);
        String player2 = gettingPlayers(key, PLAYER_TWO);
        createGameBoard (gameBoard);
        while (gameContinues) {
            printGameBoard(gameBoard);
            if (playerOneTurn) {    //If is player 1 turns, player 1 would go then set turn false so player 2 go
                playerColumnToDrop (key, player1, gameBoard, 'r');
                playerOneTurn = false;
            } else {
                playerColumnToDrop (key, player2, gameBoard, 'b');
                playerOneTurn = true;
            }
        }
    }



    public static void playTurn (char [][] gameBoard, Scanner key) {
        System.out.print(  "enter the column to drop your checker: ");
    }


    //Calculate where to drop the color depending on the input from player
    public static void playerColumnToDrop (Scanner key, String player, char [][] gameBoard, char color) {
        int rowStart = gameBoard.length - 1;
        System.out.println();
        System.out.println(player + " it is your turn.");
        System.out.println("Your pieces are the " + color + "'s.");
        int columnToDrop = getLegalColumn(key, player, gameBoard);
        while (gameBoard[rowStart][columnToDrop] != '.') {  //While there is no ., we will keep checking upwards
            rowStart--;
        }
        gameBoard[rowStart][columnToDrop] = color;
    }

    public static void determiningWinner () {

    }



    public static boolean checkingDirectionWinner (int initialRow, int initialColumn, int rowChange, int columnChange,
                                                   char [][] gameBoard) {
        int row = initialRow;
        int column = initialColumn;

        char initialCharacter = gameBoard[initialRow][initialColumn];
        for (int i = 0; i < 4; i++) {
            if (initialCharacter != gameBoard[row][column]) {
                return false;
            }
            row += rowChange;
            column += columnChange; //would bbe out of bound
        }

        return true; //we found 4 in a row!
    }



    //Asking for the player name
    public static String gettingPlayers (Scanner key, int player) {
        System.out.println();
        System.out.print("Player " + player + " enter your name: ");
        String enterPlayer = key.nextLine();
        return enterPlayer;
    }


    //Create the connect 4 game board
    public static void createGameBoard (char [][] gameBoard) {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard[0].length; column++ ) {
                    gameBoard[row][column] = '.';   // the dot is for the unoccupied space
            }
        }
    }

    public static void printGameBoard (char [][] gameBoard) {
        System.out.println();
        System.out.println("Current Board");
        for (int i = 1; i <= COLUMN_OF_GAMEBOARD; i++) {
            System.out.print(i + " " );
        }
        System.out.print(" column numbers");
        System.out.println();
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard[0].length; column++ ) {
                System.out.print(gameBoard[row][column] + " ");
            }
            System.out.println();
        }
    }


    // show the intro
    public static void intro() {
        System.out.println("This program allows two people to play the");
        System.out.println("game of Connect four. Each player takes turns");
        System.out.println("dropping a checker in one of the open columns");
        System.out.println("on the board. The columns are numbered 1 to 7.");
        System.out.println("The first player to get four checkers in a row");
        System.out.println("horizontally, vertically, or diagonally wins");
        System.out.println("the game. If no player gets fours in a row and");
        System.out.println("and all spots are taken the game is a draw.");
        System.out.println("Player one's checkers will appear as r's and");
        System.out.println("player two's checkers will appear as b's.");
        System.out.println("Open spaces on the board will appear as .'s.");
    }


    // Prompt the user for an int. The String prompt will
    // be printed out. I expect key is connected to System.in.
    public static int getInt(Scanner key, String player) {
        System.out.print(player + ", enter the column to drop your checker: ");
        while(!key.hasNextInt()) {
            String notAnInt = key.nextLine();
            System.out.println();
            System.out.println(notAnInt + " is not an integer.");
            System.out.print(player + ", enter the column to drop your checker: ");
        }
        int result = key.nextInt();
        key.nextLine();
        return result;
    }

    public static int getValidColumn (Scanner key, String player) {
        boolean validColumn = false;
        int validInt = getInt(key, player);
        while (!validColumn) {
            if (validInt > 0  && validInt < COLUMN_OF_GAMEBOARD + 1) {
                validColumn = true;   //we would set it true bc we know our value is within the range of column
            } else {
                System.out.println();
                System.out.println(validInt + " is not a valid column.");
                validInt = getInt(key, player);
            }
        }
        return validInt;
    }

    public static int getLegalColumn (Scanner key, String player, char [][] gameBoard) {
        boolean legalColumn = false;
        int validColumn = getValidColumn(key, player) - 1;
        while (!legalColumn) {
            if (gameBoard[0][validColumn] == '.') {  //if our row is not equal to . if, it is full
                legalColumn = true;
            } else {
                System.out.println();
                System.out.println((validColumn + 1 )+ " is not a legal column. That column is full");
                validColumn = getValidColumn(key, player) - 1;
            }
        }
        return validColumn;
    }


}
