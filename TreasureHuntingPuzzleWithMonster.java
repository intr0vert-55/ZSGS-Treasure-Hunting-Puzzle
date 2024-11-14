public class TreasureHuntingPuzzleWithMonster{
    public static void main(String [] args){
        System.out.println(minSteps(5, 4, 4, 1, 2, 3, 3, 1));   // -1 (No possible solutions)
        System.out.println(minSteps(5, 4, 5, 1, 4, 3, 3, 1));   // 3
    }

    public static int minSteps(int row, int col, int ai, int aj, int gi, int gj, int mi, int mj){
        // Adjusting positions for indexing....
        ai--;
        aj--;
        gi--;
        gj--;
        mi--;
        mj--;
        int adventurer = helper(row, col, ai, aj, gi, gj, new boolean [row][col], new int [row][col]);
        int monster = helper(row, col, mi, mj, gi, gj, new boolean [row][col], new int [row][col]);
        // System.out.println("Adventurer : " + adventurer);
        // System.out.println("Monster : " + monster);
        if(monster < adventurer)    return -1;
        else return adventurer;
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