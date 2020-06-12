
/**
 * An interface for representing nodes in a state space.
 *
 * 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Configuration extends Comparable<Configuration> {

    @Override
    public default int compareTo(Configuration t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * To obtain the parent of the current configuration, i.e. the configuration
     * which had this as one of its successors
     *
     * @return a reference to the parent
     */
    public abstract Configuration parent();

    /**
     * To obtain the successors for a specific configuration
     *
     * @return a collection of configurations containing the successors
     */
    public abstract Collection<Configuration> successors();

    /**
     * For marking final / solution configurations.
     *
     * @return true if a this is a solution, false otherwise
     */
    public abstract boolean isSolution();

    /**
     * To build a path from the root configuration to the current one.
     *
     * @return a list of successive configurations from the root to 'this'
     */
    public default List<Configuration> pathFromRoot() {
        ArrayList<Configuration> solution = new ArrayList<>();
        solution.add(this);
        Configuration parent = this;
        while (parent != null) {
            parent = solution.get(solution.size() - 1).parent();
            solution.add(parent);
        }
        solution.remove(solution.size()-1);
        return solution;
    }

    public abstract int manhattanDistance();
}
