package tools

fun String.findAllIndexed(substring: String) = substring.toRegex().findAll(this).map { it.range.first }.toList()