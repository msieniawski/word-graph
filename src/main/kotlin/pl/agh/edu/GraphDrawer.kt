package pl.agh.edu

import com.mxgraph.layout.mxCircleLayout
import com.mxgraph.swing.mxGraphComponent
import com.mxgraph.util.mxRectangle
import org.jgrapht.ext.JGraphXAdapter
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph
import javax.swing.JFrame

object GraphDrawer {

    fun draw(graph: SimpleWeightedGraph<String, DefaultWeightedEdge>) {
        val frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val graphAdapter = JGraphXAdapter<String, DefaultWeightedEdge>(graph)
        graphAdapter.view.scale = 0.008
        val layout = mxCircleLayout(graphAdapter)

        layout.execute(graphAdapter.defaultParent)

        frame.add(mxGraphComponent(graphAdapter))

        //frame.size = Dimension(2000, 2000)
        frame.pack()
        frame.isLocationByPlatform = true
        frame.isVisible = true
    }
}