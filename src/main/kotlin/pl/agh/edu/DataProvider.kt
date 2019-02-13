package pl.agh.edu

import java.io.File
import java.nio.file.Files

object DataProvider {
    private const val DIR = "data/alkohol"

    fun get(): List<WordInfo> =
            File(DIR).walk()
                    .filter { it.isFile && it.extension == "csv" }
                    .map { getFromFile(it) }
                    .toList()

    private fun getFromFile(file: File): WordInfo {
        val word: String = file.nameWithoutExtension
        val entries: List<Entry> = Files.readAllLines(file.toPath())
                .map { it.split(",") }
                .map { Entry(it[0].toDouble(), it[1].replace("\"", "")) }
        return WordInfo(word, entries)
    }

}