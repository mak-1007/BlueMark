package com.example.attendancemarker.Utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.example.attendancemarker.data.entity.StudentEntity

fun getFileNameFromUri(context: Context, uri: Uri): String {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    return cursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst() && nameIndex >= 0) it.getString(nameIndex) ?: "unknown.csv" else "unknown.csv"
    } ?: "unknown.csv"
}

suspend fun parseStudentFile(context: Context, uri: Uri): List<StudentEntity> {
    val inputStream = context.contentResolver.openInputStream(uri) ?: return emptyList()
    val students = mutableListOf<StudentEntity>()

    inputStream.bufferedReader().useLines { lines ->
        lines.drop(1).forEach { line ->
            val tokens = parseCsvLine(line)
            if (tokens.size >= 4) {
                students.add(
                    StudentEntity(
                        rollNo = tokens[0].trim(),
                        name = tokens[1].trim(),
                        bluetoothName = tokens[2].trim(),
                        macAddress = tokens[3].trim()
                    )
                )
            }
        }
    }
    return students
}

fun parseCsvLine(line: String): List<String> {
    val regex = Regex("""("([^"]|"")*"|[^,]+)""")
    return regex.findAll(line).map { match ->
        match.value.trim().trim('"')
    }.toList()
}
