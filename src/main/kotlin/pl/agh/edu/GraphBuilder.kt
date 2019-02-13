package pl.agh.edu

import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph
import kotlin.math.sqrt

object GraphBuilder {

    fun build(wordInfos: List<WordInfo>) =
            SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
                    .also { graph ->
                        for (wordInfo in wordInfos) {
                            if (!graph.containsVertex(wordInfo.word)) {
                                graph.addVertex(wordInfo.word)
                            }
                            for (entry in wordInfo.entries) {
                                if (graph.containsEdge(wordInfo.word, entry.word)) {
                                    val edge = graph.getEdge(wordInfo.word, entry.word)
                                    val currentWeight = graph.getEdgeWeight(edge)
                                    val nextWeight = transformWeight(entry.weight)
                                    val newWeight = mergeWeights(currentWeight, nextWeight)
                                    graph.removeEdge(wordInfo.word, entry.word)
                                    val newEdge = graph.addEdge(wordInfo.word, entry.word)
                                    graph.setEdgeWeight(newEdge, newWeight)
                                } else {
                                    if (!graph.containsVertex(entry.word)) {
                                        graph.addVertex(entry.word)
                                    }
                                    if (wordInfo.word != entry.word) {
                                        val newEdge = graph.addEdge(wordInfo.word, entry.word)
                                        graph.setEdgeWeight(newEdge, transformWeight(entry.weight))
                                    }
                                }
                            }
                        }
                    }

    private fun transformWeight(weight: Double) = 1 / weight

    private fun mergeWeights(weight1: Double, weight2: Double) = sqrt(weight1 * weight2)
}