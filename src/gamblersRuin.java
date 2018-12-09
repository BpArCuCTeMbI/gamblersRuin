import java.util.Scanner;

public class gamblersRuin {
    public static void main(String[] args) {
        double probOfWinning;
        double bid;
        double moneyFirstPlayer;
        double moneySecondPlayer;
        Scanner scn = new Scanner(System.in);

        System.out.printf("Input first player's winning probability [0,64]: ");
        probOfWinning = scn.nextDouble();
        System.out.printf("Input bid: ");
        bid = scn.nextDouble();
        System.out.printf("Input 1st player bankroll: ");
        moneyFirstPlayer = scn.nextDouble();
        System.out.printf("Input 2nd player bankroll: ");
        moneySecondPlayer = scn.nextDouble();

        //GameModel model = new GameModel(0.4, 1, 1000, 90);

        GameModel model = new GameModel(probOfWinning, bid, moneyFirstPlayer, moneySecondPlayer);
        model.calcProbOfCompleteWinning();
        System.out.println("Probability of complete winning of the 1st player is: " + model.getProbOfCompleteWinning());
        GameLog log = model.play(100000000);
        log.plotScoreHistories();

    }
}

