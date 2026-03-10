
## COS314 Assignment 1

Here's a structure for your report:
## Project Overview
This Java application implements and compares three fundamental pathfinding algorithms—Depth-First Search (DFS), Breadth-First Search (BFS), and A* Search—in a constrained 2D grid environment. The simulation evaluates algorithm performance in navigating a "Wall-E" robot through a 100×100 grid containing a U-shaped trap obstacle and 15% random environmental noise.

| Requirement        | Specification                          |
|--------------------|----------------------------------------|
| Java Version       | JDK 8 or higher                        |
| Memory             | Minimum 512MB RAM                      |
| Operating System   | Windows, macOS, or Linux               |
| Dependencies       | None (standard Java libraries only)    |

```text
u26594499_assignment/
├── src/
│   ├── Main.java              # Application entry point
│   ├── Grid.java              # Environment generation and management
│   ├── Node.java              # Node representation for search states
│   ├── SearchResult.java      # Result container for metrics
│   ├── SearchAlgorithm.java   # Algorithm interface
│   ├── DFS.java               # Depth-First Search implementation
│   ├── BFS.java               # Breadth-First Search implementation
│   └── AStar.java             # A* Search implementation
├── README.md                  # This file
├── University               # This houses the grid
```
### Compilation Instructions

Option 1: Using IntelliJ IDEA/Ultimate (Recommended)
1. Open IntelliJ IDEA and select File → Open
2. Navigate to the project directory and open it
3. Ensure all .java files are in the src folder
4. Select Build → Build Project (Ctrl+F9)
5. To create JAR: File → Project Structure → Artifacts → + → JAR → From modules with dependencies
6. Select Main as the main class
7. Click Build → Build Artifacts → Build
8. The JAR file will be generated in out/artifacts/

Option 2: Using Eclipse
1. Open Eclipse and select File → Import → Existing Projects into Workspace
2. Select the project directory
3. Right-click the project → Export → Java → Runnable JAR file
4. Select launch configuration and specify export destination
5. Click Finish

Option 3: Command Line
```bash
# Navigate to project directory
cd /path/to/u26594499_assignment

# Compile all Java files
javac -d out src/*.java

# Create manifest file
echo "Main-Class: Main" > MANIFEST.MF

# Package into JAR
jar cvfm WallEPathfinding.jar MANIFEST.MF -C out .

# Execute the JAR
java -jar WallEPathfinding.jar
```

### Execution Instructions
Running the JAR File

#### Program Operation

Upon execution, the program will prompt for the following inputs:
1. Seed Value (long integer or -1)
   - Enter a specific number (e.g., 12345) for reproducible results
   - Enter -1 for random seed based on system time
   - Note: For experimental consistency, use fixed seed values
2. Noise Percentage (double, 0.0 to 1.0)
   - Recommended: 0.15 (15% as per assignment specification)
   - Range: 0.0 (no noise) to 1.0 (fully obstructed)

### Algorithm Specifications
| Algorithm | Data Structure | Strategy | Optimality | Completeness |
|-----------|---------------|----------|------------|--------------|
| DFS | Stack (LIFO) | Deep exploration, backtracking | Not guaranteed | Complete in finite spaces |
| BFS | Queue (FIFO) | Level-order expansion | Guaranteed (uniform cost) | Complete |
| A* | Priority Queue | Best-first with heuristic f(n)=g(n)+h(n) | Guaranteed (admissible heuristic) | Complete |
Heuristic Function (A):* Manhattan Distance
``` text
h(n) = |x₁ - x₂| + |y₁ - y₂|
```

### Environment Configuration
| Parameter | Value |
|-----------|-------|
| Grid Dimensions | 100 × 100 cells |
| Start Position | (20, 50) |
| Goal Position | (80, 50) |
| Obstacle | U-shaped concave trap |
| Trap Structure | Back wall at x=50 (y=30 to 70), arms extending to x=30 |
| Movement | 4-connected (North, South, East, West) |
| Cost Model | Uniform (1 unit per step) |

### Output Metrics
The program generates the following performance metrics for each algorithm:
- **Path Cost:** Total distance from start to goal (in grid units)
- **Nodes Visited:** Number of nodes expanded during search
- **Execution Time:** Wall-clock time in milliseconds
- **Path Found:** Boolean indicating success/failure
- **Path Length:** Number of nodes in solution path

### Recommended Seed Values for Testing
For reproducible experimental results, use the following seeds:

| Seed | Characteristic |
|------|---------------|
| 12345 | Baseline case, moderate complexity |
| 55555 | High DFS path cost (demonstrates suboptimality) |
| 77777 | Dense noise, narrow corridors |
| 99999 | Open areas, best A* performance |
| 11111 | Constrained pathways, worst A* performance |

### Troubleshooting
| Issue | Solution |
|------|----------|
| `java.lang.UnsupportedClassVersionError` | Upgrade to JDK 8 or higher |
| `Could not find or load main class` | Ensure `Main.java` contains `public static void main(String[] args)` |
| `OutOfMemoryError` | Increase heap size: `java -Xmx1024m -jar WallEPathfinding.jar` |
| `No path found` | Verify start/goal positions are not inside obstacles; try different seed |
| `Compilation errors` | Ensure all 8 Java files are present in `src` directory |
