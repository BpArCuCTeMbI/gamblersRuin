import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class GameModel {

    private double probOfWinning;

    private double bid;

    private double currentMoneyFirst;

    private double currentMoneySecond;

    private double moneyFirst;

    private double moneySecond;

    private double probOfCompleteWinning;

    public double getProbOfCompleteWinning() {
        return probOfCompleteWinning;
    }

    public void setProbOfCompleteWinning(double probOfCompleteWinning) {
        this.probOfCompleteWinning = probOfCompleteWinning;
    }

    public double getCurrentMoneySecond() {
        return currentMoneySecond;
    }

    public void setCurrentMoneySecond(double currentMoneySecond) {
        this.currentMoneySecond = currentMoneySecond;
    }

    public GameModel(double probOfWinning, double bid, double moneyFirst, double moneySecond){
        this.probOfWinning = probOfWinning;
        this.bid = bid;
        this.moneyFirst = moneyFirst;
        this.moneySecond = moneySecond;
        this.currentMoneyFirst = moneyFirst;
        this.currentMoneySecond = moneySecond;
        this.probOfCompleteWinning = 0;

    }

    public double getMoneySecond() {
        return moneySecond;
    }

    public void setMoneySecond(double moneySecond) {
        this.moneySecond = moneySecond;
    }

    public double getMoneyFirst() {
        return moneyFirst;
    }

    public void setMoneyFirst(double moneyFirst) {
        this.moneyFirst = moneyFirst;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getProbOfWinning() {
        return probOfWinning;
    }

    public void setProbOfWinning(double probOfWinning) {
        this.probOfWinning = probOfWinning;
    }

    public void setCurrentMoneyFirst(double currentMoneyFirst) {
        this.currentMoneyFirst = currentMoneyFirst;
    }

    public double getCurrentMoneyFirst() {
        return currentMoneyFirst;
    }

    private static double[] convertDoubles(ArrayList<Double> doubles) {
        double[] ret = new double[doubles.size()];
        Iterator<Double> iterator = doubles.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().doubleValue();
        }
        return ret;
    }

    public GameLog play(int maxAmountOfGames){
        int i = 0;
        ArrayList<Double> sHistFirst = new ArrayList<>();
        ArrayList<Double> sHistSecond = new ArrayList<>();

        while(i < maxAmountOfGames && currentMoneyFirst > 0 && currentMoneySecond > 0){
            sHistFirst.add(currentMoneyFirst);
            sHistSecond.add(currentMoneySecond);
            this.playOnce();
            i++;
        }
        if(currentMoneyFirst <= 0){
            System.out.println("Second player won in " + i + " rounds.");
        }
        if(currentMoneySecond <= 0){
            System.out.println("First player won in " + i + " rounds.");
        }
        GameLog log = new GameLog(convertDoubles(sHistFirst), convertDoubles(sHistSecond));
        return log;
    }

    public void playOnce(){
        Random rnd = new Random();
        if(rnd.nextDouble() < probOfWinning){
            currentMoneyFirst += bid;
            currentMoneySecond -= bid;
            //System.out.println("WIN! Current score: " + currentMoneyFirst);
        }else{
            currentMoneyFirst -= bid;
            currentMoneySecond += bid;
            //System.out.println("LOSE! Current score: " + currentMoneyFirst);
    }
    }

    public double calcProbOfCompleteWinning(){
        if(probOfWinning != 0 && probOfWinning != 1 && probOfWinning != 0.5) {
            return this.probOfCompleteWinning = (Math.pow((1 - probOfWinning) / probOfWinning, moneyFirst) - 1) / (Math.pow((1 - probOfWinning) / probOfWinning, moneyFirst + moneySecond) - 1);
        }
        else if(probOfWinning == 1){
            return this.probOfCompleteWinning = 1;
        }
        else if(probOfWinning == 0.5){
            return this.probOfCompleteWinning = moneyFirst / (moneyFirst + moneySecond);
        } else {
            return this.probOfCompleteWinning = 0;
        }
    }

}
