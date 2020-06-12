
import java.util.*;

/**
 * A class that implements a breadth-first search algorithm for finding the
 * Configurations for which the isSolution predicate holds
 */
public class Solver {
    // A queue for maintaining graphs that are not visited yet.

    PriorityQueue<Configuration> toExamine;
    HashSet<Configuration> observedStates;

    public Solver(Configuration g) {
        toExamine = new PriorityQueue<>();
        observedStates = new HashSet<>();
        toExamine.add(g);
    }

    /**
     * A skeleton implementation of the solver
     *
     * @return a string representation of the solution
     */
    @SuppressWarnings("empty-statement")
    public String solve() {
        while (!toExamine.isEmpty()) {
            Configuration next = toExamine.remove();
            if (!observedStates.contains(next)) {
                observedStates.add(next);    
                if (next.isSolution()) {
                    System.out.println(next.pathFromRoot());
                    return "Success!";
                } else {
                    for (Configuration succ : next.successors()) {
                        toExamine.add(succ);
                    }
                }
            }
        }
        return "Failure!";
    }

}
