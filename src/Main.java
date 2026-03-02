import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Request seed value
        System.out.print("Enter seed value (or -1 for random): ");
        long seedInput = scanner.nextLong();
        long seed = (seedInput == -1) ? System.currentTimeMillis() : seedInput;

        System.out.println("Seed Value: " + seed);

        // Request noise percentage (default 15%)
        System.out.print("Enter noise percentage (0.0 to 1.0, default 0.15): ");
        double noise = scanner.nextDouble();

        // Create grid
        Grid grid = new Grid(100, 100, seed, noise);

        // Define start and goal
        Node start = new Node(20, 50);
        Node goal = new Node(80, 50);

        System.out.println("\nGrid Configuration:");
        System.out.println("Start: " + start);
        System.out.println("Goal: " + goal);
        System.out.println("Grid Size: 100x100");
        System.out.println("Noise Level: " + (noise * 100) + "%");

        // Run DFS
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Running DFS...");
        DFS dfs = new DFS();
        runAlgorithm(dfs, grid, start, goal);

        // Run BFS
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Running BFS...");
        BFS bfs = new BFS();
        runAlgorithm(bfs, grid, start, goal);

        // Run A*
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Running A*...");
        AStar astar = new AStar();
        runAlgorithm(astar, grid, start, goal);

        // Optional: Save grid to file
        System.out.print("\nSave grid to file? (y/n): ");
        String save = scanner.next();
        if (save.equalsIgnoreCase("y")) {
            System.out.print("Enter filepath: ");
            String filepath = scanner.next();
            grid.saveToFile(filepath);
            System.out.println("Grid saved to: " + filepath);
        }

        scanner.close();
    }

    private static void runAlgorithm(SearchAlgorithm algorithm, Grid grid, Node start, Node goal) {
        long startTime = System.nanoTime();
        SearchResult result = algorithm.search(grid, start, goal);
        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1_000_000.0; // Convert to ms

        System.out.println("Path Cost (Distance): " + result.pathCost);
        System.out.println("Nodes Visited: " + result.nodesVisited);
        System.out.println("Execution Time: " + String.format("%.3f", executionTime) + " ms");
        System.out.println("Path Found: " + (result.pathFound ? "Yes" : "No"));

        if (result.pathFound && result.path != null) {
            System.out.println("Path length: " + result.path.size());
            // Uncomment to print path coordinates
            // System.out.println("Path: " + result.path);
        }
    }
}