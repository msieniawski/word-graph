package pl.agh.edu

import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph


fun main() {
    val data: List<WordInfo> = DataProvider.get()
    val graph: SimpleWeightedGraph<String, DefaultWeightedEdge> = GraphBuilder.build(data)
    ShortestPathFinder.findAndSave(graph, data.map { it.word })
    GraphDrawer.draw(graph)
}