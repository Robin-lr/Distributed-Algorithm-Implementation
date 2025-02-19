# Distributed Algorithm Simulation using JBotSim

## Description

This project implements a distributed algorithm using the [JBotSim](http://jbotsim.io/) library for network simulations. The algorithm models a distributed message-passing system where nodes communicate to explore their neighbors, acknowledge received messages, and compute a final aggregated value.

## Features

- **Node Initialization**: Each node initializes itself upon startup.
- **Message Passing**: Nodes exchange "exploration" and "acknowledgment" messages to determine their roles in the distributed process.
- **Initiator Node**: One node is selected as the initiator, which starts the algorithm.
- **Tree Formation**: The algorithm builds a spanning tree through message passing.
- **Value Aggregation**: Nodes sum up received values and propagate results back to the initiator.

## Project Structure

- **`Noeud.java`**: Defines the behavior of each node in the network.
- **`Content.java`**: Represents the messages exchanged between nodes.
- **`Main.java`**: Initializes the topology and starts the simulation.

## Requirements

- Java 8 or later
- [JBotSim](http://jbotsim.io/) library

## Installation

1. Download and install JBotSim from [JBotSim's official website](http://jbotsim.io/).
2. Clone this repository:
   git clone https://github.com/robin-lr/Distributed-Algorithm-Implementation.git
   cd Distributed-Algorithm-Implementation
3. Compile the Java files:
   ```sh
   javac -cp path/to/jbotsim.jar *.java
   ```
4. Run the simulation:
   ```sh
   java -cp .:path/to/jbotsim.jar Main
   ```

## How It Works

1. Nodes are initialized and display their unique ID.
2. One node is manually selected as the **initiator**, which starts the process.
3. The initiator sends an "exploration" message to its neighbors.
4. Nodes that receive the message respond with an acknowledgment (`ack`).
5. If all acknowledgments are received, the algorithm completes, and the final aggregated value is displayed.

## Example Output

```
----------------Implementation Algo Dist 2----------------
0 initialized.
1 initialized.
2 initialized.
0 selected as initiator.
0 sent exploration message to all neighbors.
1 received message: exp from 0
1 sent: ack to 0
2 received message: exp from 0
2 sent: ack to 0
0: nbr_ack = 0
Algo finished, value = 3
```

## Authors

- Robin

## License

This project is licensed under the MIT License.

Feel free to modify it to include your name, license type, or additional setup instructi
