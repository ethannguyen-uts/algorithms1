/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sites[][];
    private int N;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    // TODO: add opened site

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.N = n;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        sites = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            final int i = row - 1;
            final int j = col - 1;
            sites[i][j] = 1;
            this.connectToOpenedAdjacents(i, j);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return this.sites[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if (this.sites[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public void visualize() {
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                System.out.print(this.sites[i][j]);
                System.out.print("     ");
                if (j % this.N == N - 1) {
                    System.out.println();
                }
            }
        }
    }

    private boolean isValidPosition(int i, int j) {
        if (i < 0 || j < 0 || i > this.N - 1 || j > this.N - 1) {
            return false;
        }
        return true;
    }

    private void connectToOpenedAdjacents(int i, int j) {
        int[][] adjacents = {
                { i, j - 1 },
                { i, j + 1 },
                { i - 1, j },
                { i + 1, j }
        };

        for (int k = 0; k < adjacents.length; k++) {
            int adjacent_i = adjacents[k][0];
            int adjacent_j = adjacents[k][1];
            if (!isValidPosition(adjacent_i, adjacent_j)) {
                continue;
            }
            if (this.isOpen(adjacent_i + 1, adjacent_j + 1)) {
                this.weightedQuickUnionUF.union(i * this.N + j, adjacent_i * this.N + adjacent_j);
            }
        }
    }

    public static void main(String[] args) {
        // initialize a sites n
        Percolation a = new Percolation(5);

        a.open(2, 4);
        a.open(2, 3);
        a.visualize();
        System.out.println(a.weightedQuickUnionUF.find(8));
        System.out.println(a.weightedQuickUnionUF.find(7));
    }
}
