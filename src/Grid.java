import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Grid {
    private int width;
    private int height;
    private int[][] grid;
    private Random random;

    public Grid(int width, int height, long seed, double noiseProbability) {
        this.width = width;
        this.height = height;
        this.grid = new int[width][height];
        this.random = new Random(seed);

        initializeGrid();
        createUTrap();
        addNoise(noiseProbability);
    }

    private void initializeGrid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = 0; // 0 = traversable
            }
        }
    }

    private void createUTrap() {
        // Back Wall: Vertical line at x = 50, from y = 30 to y = 70
        for (int y = 30; y <= 70; y++) {
            grid[50][y] = 1;
        }

        // Bottom Arm: Horizontal from (50, 30) to (30, 30)
        for (int x = 30; x <= 50; x++) {
            grid[x][30] = 1;
        }

        // Top Arm: Horizontal from (50, 70) to (30, 70)
        for (int x = 30; x <= 50; x++) {
            grid[x][70] = 1;
        }
    }

    private void addNoise(double probability) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Don't add noise to U-trap or start/goal positions
                if (grid[i][j] == 0 && !isStartOrGoal(i, j)) {
                    if (random.nextDouble() < probability) {
                        grid[i][j] = 1; // 1 = obstacle
                    }
                }
            }
        }
    }

    private boolean isStartOrGoal(int x, int y) {
        return (x == 20 && y == 50) || (x == 80 && y == 50);
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && grid[x][y] == 0;
    }

    public boolean isObstacle(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return true;
        return grid[x][y] == 1;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Grid Size: " + width + "x" + height);
            writer.println("0 = traversable, 1 = obstacle, S = start, G = goal");

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (x == 20 && y == 50) {
                        writer.print("S ");
                    } else if (x == 80 && y == 50) {
                        writer.print("G ");
                    } else {
                        writer.print(grid[x][y] + " ");
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            System.err.println("Error saving grid: " + e.getMessage());
        }
    }

    public void printGridWithPath(java.util.List<Node> path) {
        char[][] display = new char[width][height];

        // Initialize
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == 1) display[i][j] = '#';
                else display[i][j] = '.';
            }
        }

        // Mark path
        if (path != null) {
            for (Node n : path) {
                display[n.x][n.y] = '*';
            }
        }

        display[20][50] = 'S';
        display[80][50] = 'G';

        // Print (only print relevant section for visibility)
        System.out.println("Grid visualization (30-90 x, 20-80 y):");
        for (int y = 20; y <= 80; y++) {
            for (int x = 30; x <= 90; x++) {
                System.out.print(display[x][y]);
            }
            System.out.println();
        }
    }
}