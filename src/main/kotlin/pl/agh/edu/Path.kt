package pl.agh.edu

data class Path(val from: String, val path: List<String>) {

    override fun toString() =
            "Path from '$from' to '${path.last()}' : [${path.joinToString(", ") { "'$it'" }}]"

}