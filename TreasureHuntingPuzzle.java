public class TreasureHuntingPuzzle{
    public static void main(String [] args){
        System.out.println(minSteps(5, 4, 5, 1, 1, 4));
    }

    public static int minSteps(int row, int col, int ai, int aj, int gi, int gj){
        boolean [][] prev = new boolean [row][col];
        int [][] memo = new int [row][col];
        ai--;
        aj--;
        gi--;
        gj--;
        prev[ai][aj] = true;
        return helper(row, col, ai, aj, gi, gj, prev, memo);
    }

    public static int helper(int row, int col, int ai, int aj, int gi, int gj, boolean [][] prev, int [][] memo){
        if(ai == gi && aj == gj)    return 0;
        if(memo[ai][aj] == 0){
            prev[ai][aj] = true;
            int left = (aj > 0 && !prev[ai][aj - 1]) ? 1 + helper(row, col, ai, aj - 1, gi, gj, prev, memo) : 100000;
            int right = (aj < col - 1 && !prev[ai][aj + 1]) ? 1 + helper(row, col, ai, aj + 1, gi, gj, prev, memo) : 100000;
            int up = (ai > 0 && !prev[ai - 1][aj]) ? 1 + helper(row, col, ai - 1, aj, gi, gj, prev, memo) : 100000;
            int down = (ai < row - 1 && !prev[ai + 1][aj]) ? 1 + helper(row, col, ai + 1, aj, gi, gj, prev, memo) : 100000;
            prev[ai][aj] = false;
            memo[ai][aj] = Math.min(Math.min(left, right), Math.min(up, down));
        }
        return memo[ai][aj];
    }
}