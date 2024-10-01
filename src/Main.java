import java.util.*;

public class Main {
    private static int ROW_COUNT= 3;
    private static int COL_COUNT= 3;

    private static  String CELL_STATE_EMPTY = " ";
    private static  String CELL_STATE_X = "X";
    private static  String CELL_STATE_O = "O";// o - буква

    private static  String CELL_STATE_X_WON = "X виграли";
    private static  String CELL_STATE_O_WON = "O виграли";
    private static  String CELL_STATE_DRAW = "нічия";
    private static  String CELL_STATE_NOT_FINISH = "гра не зауінченна";


    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Вітаємо у грі Хрестики-Нулики!");
        System.out.println("Інструкція:");
        System.out.println("1. Гра проходить на полі 3x3.");
        System.out.println("2. Гравець X грає проти комп'ютера (O).");
        System.out.println("3. Для того, щоб зробити хід, введіть координати комірки (рядок та стовпчик) через пробіл.");
        System.out.println("4. Координати рядків та стовпчиків знаходяться у діапазоні від 0 до 2.");
        System.out.println("5. Наприклад, щоб вибрати верхній лівий кут, введіть: 0 0");
        System.out.println("6. Гра закінчується, коли один із гравців перемагає або на полі не залишиться вільних клітин.");
        System.out.println("Удачі!\n");

        startGameRound();
    }


    public static void startGameRound(){
        String[][] board = createBoard();
        startGameLoop(board);


    }

    public static  String[][] createBoard(){
        String[][] board = new String[ROW_COUNT][COL_COUNT];

        for (int row = 0; row < ROW_COUNT; row++){
            for (int col = 0; col <COL_COUNT; col++){
                board[row][col]= CELL_STATE_EMPTY;
            }
        }
        return board;
    }

    public  static  void startGameLoop(String[][] board){
        do{
            makePlayTurn(board);
            printBoard(board);

            System.out.println();

            makeBotTurns(board);
            printBoard(board);

            String gameState = checkGameState(board);

            if (!Objects.equals(gameState, CELL_STATE_NOT_FINISH)){
                System.out.println(gameState);
                return;
            }

        }while (true);

    }

    public static void makePlayTurn(String[][] board){
        int [] coordinates = inputCellCoordinates(board);

        board[coordinates[0]][coordinates[1]] = CELL_STATE_X;

        //get input
        //validate input
        //place X on a board
    }

    public static int[] inputCellCoordinates(String[][] board){
        System.out.println("Ведіть кординати через пробел (0-2):");

        do{
            //Немає перевірок на пробіли та перевірки на цифр
            String[] input = scanner.nextLine().split(" ");

            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);

            if ((row < 0) || (row >= ROW_COUNT) || (col < 0)|| (col >= COL_COUNT)){
                System.out.println("Ведіть кординати через пробел від 0 до 2:");
            } else if (!Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                System.out.println("Ця клітинка вже занята");
            }else {
                return new int[] {row,col};
            }
        }while (true);
    }

    public static  void makeBotTurns(String[][] board){
        System.out.println("Ход ");

        int[] coordinates = getRandomEmptyCellCoordinates(board);
        board[coordinates[0]][coordinates[1]] = CELL_STATE_O;


    }
    public static int[] getRandomEmptyCellCoordinates(String[][] board){
        do {
            int row = random.nextInt(ROW_COUNT);
            int col = random.nextInt(ROW_COUNT);

            if (board[row][col] != CELL_STATE_EMPTY){

            }else {
                return new int[]{row,col};
            }
        }while (true);

    }

    public static String checkGameState(String[][] board) {
        ArrayList<Integer> sums = new ArrayList<>();

        for (int row = 0; row < ROW_COUNT;row++){
            int rowSum = 0;
            for (int col = 0; col < ROW_COUNT; col++){
                rowSum += calculaterNumVaue(board[row][col]);
            }
            sums.add(rowSum);
        }

        //iterate columns
        for (int col = 0; col < ROW_COUNT; col++){
            int colSum = 0;
            for (int row = 0; row < ROW_COUNT;row++){
                colSum += calculaterNumVaue(board[row][col]);
            }
            sums.add(colSum);
        }

        //diagonal left
        int leftDiagonal = 0;
        for (int i = 0; i <ROW_COUNT; i++){
            leftDiagonal += calculaterNumVaue(board[i][i]);
        }
        sums.add(leftDiagonal);

        //diagonal right
        int rightDiagonal = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            rightDiagonal += calculaterNumVaue(board[i][(ROW_COUNT - 1) - i]);
        }
        sums.add(rightDiagonal);

        if(sums.contains(3)){
            return CELL_STATE_X_WON;
        } else if (sums.contains(-3)){
            return CELL_STATE_O_WON;
        }else if (areAllCellTaken(board)){
            return CELL_STATE_DRAW;
        }else {
            return CELL_STATE_NOT_FINISH;
        }

    }

    private static int calculaterNumVaue(String cellState) {
        if (Objects.equals(cellState, CELL_STATE_X)){
            return 1;
        }else if (Objects.equals(cellState, CELL_STATE_O)) {
            return -1;
        } else{
            return 0;
        }
    }

    public static boolean areAllCellTaken(String[][] board){
        for (int row = 0; row < ROW_COUNT; row++){
            for (int col = 0; col < COL_COUNT; col++){
                if (board[row][col] == CELL_STATE_EMPTY){
                    return false; // Якщо є порожні клітинки, гра ще не завершена
                }
            }
        }
        return true;
    }


    public static void printBoard(String[][] board) {
        System.out.print("  ");
        for (int col = 0; col < COL_COUNT; col++) {
            System.out.print("--");
        }
        System.out.println();

        for (int row = 0; row < ROW_COUNT; row++) {
            String line = "| ";
            for (int col = 0; col < COL_COUNT; col++) {
                line += board[row][col] + " ";
            }
            line += "|";
            System.out.println(line);
        }

        System.out.print("  ");
        for (int col = 0; col < COL_COUNT; col++) {
            System.out.print("--");
        }
        System.out.println();
    }

}
