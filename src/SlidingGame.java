
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * A template implementation of a sliding game implementing the
 * Graph interface
 */
public class SlidingGame implements Configuration {

    public static final int N = 3, SIZE = N * N, HOLE = SIZE;
    /**
     * The board is represented by a 2-dimensional array; the position of the
     * hole is kept in 2 variables holeX and holeY
     */
    private int[][] board;
    private Configuration parent;
    private int holeX, holeY;
    private ArrayList<Configuration> boards = new ArrayList<>();

    /**
     * A constructor that initializes the board with the specified array
     *
     * @param start: a one dimensional array containing the initial board. The
     * elements of start are stored row-wise.
     */
    public SlidingGame(int[] start) {
        board = new int[N][N];
        assert start.length == N * N : "Length of specified board incorrect";
        for (int p = 0; p < start.length; p++) {
            board[p % N][p / N] = start[p];
            if (start[p] == HOLE) {
                holeX = p % N;
                holeY = p / N;
            }
        }

    }

    public SlidingGame(int[][] board, Configuration parent) {
        this.board = board;
        this.parent = parent;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == SIZE) {
                    this.holeX = i;
                    this.holeY = j;
                }
            }
        }
    }

    /**
     * Converts a board into a printable representation. The hole is displayed
     * as a space
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("\n");
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int puzzel = board[col][row];
                buf.append(puzzel == HOLE ? "  " : puzzel + " ");
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int x = N - 1; x >= 0; x--) {
            for (int y = N - 1; y >= 0; y--) {
                hash = 31 * hash + board[x][y];
            }
        }
        return hash;
    }

    @Override
    public boolean isSolution() {
        boolean A = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[j][i] != i * N + (j + 1)) {
                    A = false;
                }
            }
        }
        return A;
    }

    @Override
    public Collection<Configuration> successors() {
        if (holeX + Direction.NORTH.GetDX() < N && holeY + Direction.NORTH.GetDY() < N
                && holeX + Direction.NORTH.GetDX() >= 0 && holeY + Direction.NORTH.GetDY() >= 0) {
            boards.add(new SlidingGame(newBoard(Direction.NORTH), this));
        }
        if (holeX + Direction.EAST.GetDX() < N && holeY + Direction.EAST.GetDY() < N
                && holeX + Direction.EAST.GetDX() >= 0 && holeY + Direction.EAST.GetDY() >= 0) {
            boards.add(new SlidingGame(newBoard(Direction.EAST), this));
        }
        if (holeX + Direction.SOUTH.GetDX() < N && holeY + Direction.SOUTH.GetDY() < N
                && holeX + Direction.SOUTH.GetDX() >= 0 && holeY + Direction.SOUTH.GetDY() >= 0) {
            boards.add(new SlidingGame(newBoard(Direction.SOUTH), this));
        }
        if (holeX + Direction.WEST.GetDX() < N && holeY + Direction.WEST.GetDY() < N
                && holeX + Direction.WEST.GetDX() >= 0 && holeY + Direction.WEST.GetDY() >= 0) {
            boards.add(new SlidingGame(newBoard(Direction.WEST), this));
        }
        return boards;
    }

    public int[][] newBoard(Direction direction) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        int newHoleX = holeX + direction.GetDX();
        int newHoleY = holeY + direction.GetDY();
        int number = board[holeX][holeY];
        newBoard[holeX][holeY] = board[newHoleX][newHoleY];
        newBoard[newHoleX][newHoleY] = number;
        return newBoard;

    }

    @Override
    public int compareTo(Configuration g) {
        int x = this.manhattanDistance();
        int y = g.manhattanDistance();
        return x-y;
        
    }

    @Override
    public Configuration parent() {
        return parent;
    }
    
    public int manhattanDistance(){
        int number;
        int distance = 0;
        int k;
        int l;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                number = board[i][j];
                k = (number-1)/N;
                l = (number-1)%N;
                distance += Math.abs(i-k) + Math.abs(j-l);
            }
        }
        return distance;
}

}
