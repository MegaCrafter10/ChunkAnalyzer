# ChunkAnalyzer
A Spigot plugin for analyzing the number of entities in loaded chunks

Tested on: 1.12.2

### Commands
**/analyze** - Analyzes loaded chunks in the current world and displays the top 10 chunks which have a \<entity-threshold\> number of entities or more. The plugin displays the X and Z coordinates of that chunk's center block.

### Permissions
**chunkanalyzer.analyze** - Allows the use of /analyze, defaults to op.

### Config
```
# the number of entities required for the plugin to log the chunk.
# logged chunks are displayed to the user once they run /analyze.
entity-threshold: 20
```
