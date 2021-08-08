/**
 * Main.java
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR       Lauren Himbeault (A01)
 * ASSIGNMENT       Assignment #4
 * @author          Matthew Wallis, 7613913
 * @version         2021-08-01
 *
 *
 * PURPOSE: Utilize the StdDraw class to draw fractals using a recursive method.
 */

public class Main {
    public static void main(String[] args) {
        final int branches = 5;
        final int subBranches = 5;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);
        StdDraw.line(0.35, 0.5, 0.65, 0.5);
        StdDraw.line(0.5, 0.35, 0.5, 0.65);
        recursiveBranch(subBranches, subBranches, branches, 0.5, 0.5, 0.5, 0.65);
        recursiveBranch(subBranches, subBranches, branches, 0.5, 0.5, 0.5, 0.35);
        recursiveBranch(subBranches, subBranches, branches, 0.5, 0.5, 0.65, 0.5);
        recursiveBranch(subBranches, subBranches, branches, 0.5, 0.5, 0.35, 0.5);
        }


    /**
     * PURPOSE: Passed coordinates for the last vector drawn, as well as the number of branches and "subbranches"
     * within a branch to be drawn. Generates and draws new vectors extending from previous StdDraw line.
     * Subbranches is passed to both "subbranches" and "currentSub" as a way to compare the current subBranch against
     * the total required for generating subbranches at consistent angle offsets. If this method were intended to be
     * interacted with by a user, an interface would be used to mask the duplicate parameters, and the recursiveBranch
     * calls in main would be replaced by a single simplified call. In this case, that was not necessary as the process is automated.
     */

        public static void recursiveBranch(int subBranches, int currentSub, int branches, double prevX, double prevY, double x, double y) {
            if (branches > 0 && currentSub > 0) {
                final double sizeDown = .75;
                final double thicknessDown = Math.pow(0.9, 25.0/branches); //reduces thickness as a function of the branch number
                double length = Math.sqrt(Math.pow(x - prevX, 2) + Math.pow(y - prevY, 2)); //length of line

                //calculates the angle between two vectors (in this case, the previous line drawn and a line of angle 0 and any length n)
                //via cos(theta) = (a dot b)/(length a dot length b)
                double theta0 = Math.acos((x - prevX) / Math.sqrt(Math.pow(x - prevX, 2) + Math.pow(y - prevY, 2))); //using length double instead here causes loss of precision

                //corrects angle based on quadrant of vector
                if (y - prevY < 0 && x - prevX != 0) {theta0 = (2) * Math.PI - theta0;}
                else if (y - prevY < 0) {theta0 = Math.PI + theta0;}

                //creates coordinates for a subbranch based on a primary branch that continues the direction of the previous larger branch.
                double theta = theta0 + ((currentSub - ((subBranches + 1.0) / 2)) * (180.0 / (subBranches-1)) * Math.PI / 180); //all branches distributed over 270 degrees
                double x2 = x + length * sizeDown * (Math.cos(theta));
                double y2 = y + length * sizeDown * (Math.sin(theta));
                StdDraw.setPenRadius(0.002 * thicknessDown);
                StdDraw.line(x, y, x2, y2);
                recursiveBranch(subBranches, currentSub - 1, branches, prevX, prevY, x, y); //recursive call for each subbranch of similar size / hierarchy level
                recursiveBranch(subBranches, subBranches, branches - 1, x, y, x2, y2); //recursive call for each smaller branch off main branch

            }
    }
}
           





