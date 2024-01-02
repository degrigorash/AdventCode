package tools

fun readFile(filePath: String): List<String> =
     ClassLoader.getSystemResourceAsStream(filePath)!!.bufferedReader().readLines()

fun readFileText(filePath: String): String =
     ClassLoader.getSystemResourceAsStream(filePath)!!.bufferedReader().readText()