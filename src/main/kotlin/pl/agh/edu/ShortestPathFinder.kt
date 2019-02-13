package pl.agh.edu

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph
import java.io.File

object ShortestPathFinder {

    fun findAndSave(graph: SimpleWeightedGraph<String, DefaultWeightedEdge>, tagrets: List<String>) {
        for (target in tagrets) {
            val paths = find(graph, target)
            val output = paths.joinToString("\n")
            File("data/output/$target.txt").writeText(output)
        }
    }

    fun find(graph: SimpleWeightedGraph<String, DefaultWeightedEdge>, target: String): List<Path> =
            graph.vertexSet()
                    .filterNot { it == target }
                    .map { Path(it, DijkstraShortestPath(graph).getPath(it, target).vertexList) }

}