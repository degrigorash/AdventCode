package tools

fun readFile(filePath: String): List<String> =
     ClassLoader.getSystemResourceAsStream(filePath)!!.bufferedReader().readLines()