
## Report Template (Critical Analysis Guide)

Here's a structure for your report:

### 1. Introduction
- Brief overview of pathfinding algorithms
- Problem context: Wall-E navigation
- Objectives: Compare DFS, BFS, and A* in a concave trap environment

### 2. Experimental Setup
- **Grid**: 100×100 with U-shaped obstacle
- **Start**: (20,50), **Goal**: (80,50)
- **Noise**: 15% random obstacles (seeded for reproducibility)
- **Heuristic**: Manhattan Distance for A*
- **Metrics**: Path cost, nodes visited, execution time, optimality

### 3. Results
Present your table with actual measured values from multiple seed runs.

### 4. Critical Analysis (Key Points)

**Search Space**: 
- State space is 10,000 possible positions
- Effective search space reduced by obstacles (~15-20% blocked)

**Optimality**:
- **BFS & A***: Guaranteed optimal on unweighted grids
- **DFS**: Not optimal - may find longer paths

**Efficiency**:
- DFS: Fewer nodes if lucky, but unpredictable
- BFS: Expands all nodes at each depth level (breadth-heavy)
- A*: Uses heuristic to guide search, typically fewest nodes

**Path Suboptimality in DFS**:
- DFS explores depth-first, may find long path around trap before short path
- No consideration of distance to goal

**Impact of Heuristic**:
- Manhattan Distance is admissible (never overestimates)
- Guides A* toward goal, reducing unnecessary expansions
- Particularly effective in open areas, less impact in narrow corridors

**Seed Impact**:
- Random noise can block optimal paths
- Some seeds may create "bottlenecks" forcing detours
- A* may perform worse if heuristic becomes less informative due to maze-like noise

### 5. Conclusion
- Summarize findings
- Recommend A* for optimal pathfinding
- Note trade-offs between optimality and speed

## Compilation to JAR

To create the executable JAR as required:

```bash
# Compile
javac -d out src/*.java

# Create manifest
echo "Main-Class: Main" > MANIFEST.MF

# Package JAR
jar cvfm WallEPathfinding.jar MANIFEST.MF -C out .

# Run
java -jar WallEPathfinding.jar
