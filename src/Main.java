
import java.util.List;


/**
 *
 * @author Joeri Janssen
 */
public class Main
{
    public static void main(String[] args) {
        int [] game = {3,8,7, 5,9,6, 2,1,4};

        SlidingGame s = new SlidingGame (game);
        System.out.println("The following sliding game needs to be solved");
        System.out.println(s);
        Solver solver = new Solver(s);
        System.out.println("It has the following solution read from bottom to top");
        System.out.println(solver.solve());
    }
}
