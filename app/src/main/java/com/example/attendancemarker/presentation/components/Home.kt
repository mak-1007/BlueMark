package com.example.attendancemarker.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attendancemarker.data.entity.StudentEntity

@Composable
fun TeacherHomeScreen(
    teacherName: String,
    presentStudents: Int,
    presentStudentList: List<StudentEntity>,
    onNavigateToPresent: () -> Unit
) {
    var isScanning by remember { mutableStateOf(false) }
    var totalStudents by remember { mutableStateOf(50) }

    val absentStudents = totalStudents - presentStudents

    val animatedProgress = rememberInfiniteTransition().animateFloat(
        initialValue = 0.2f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hi, $teacherName!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Attendance Summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Today's Attendance", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                CustomPieChart(presentPercentage = (presentStudents.toFloat() / totalStudents) * 100)
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AttendanceStatBox("Total", totalStudents, Color.Blue)
                    AttendanceStatBox("Present", presentStudents, Color.Green)
                    AttendanceStatBox("Absent", absentStudents, Color.Red)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

/*        // Navigate to Present Students List Screen
        *//*  Button(onClick = onNavigateToPresentList) {
              Text("View Present Students")
          }*//*
        Spacer(modifier = Modifier.height(16.dp))

        // Scanning Status
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isScanning) Color(0xFFD1C4E9) else Color(
                    0xFFF5F5F5
                )
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isScanning) "Scanning for Students..." else "Start Attendance",
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                if (isScanning) LinearProgressIndicator(
                    progress = animatedProgress.value,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { isScanning = !isScanning }) {
                Text(if (isScanning) "Stop Attendance" else "Start Attendance")
            }
            Button(onClick = { *//* Navigate to Logs *//* }) {
                Text("View Logs")
            }
        }*/

        Spacer(modifier = Modifier.height(24.dp))

// Weekly Attendance Summary (Dummy)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Weekly Attendance Summary",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
                val attendance = listOf(42, 44, 40, 46, 43) // Dummy values

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    days.zip(attendance).forEach { (day, value) ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .height((value * 1.5).dp.coerceAtMost(100.dp))
                                    .width(14.dp)
                                    .background(Color(0xFF4CAF50), RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(day, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

// Motivational Quote
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "“A teacher affects eternity; they can never tell where their influence stops.”",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF6D4C41)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "- Henry Adams",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

    }
}

@Composable
fun CustomPieChart(presentPercentage: Float) {
    Canvas(modifier = Modifier.size(100.dp)) {
        val absentPercentage = 100f - presentPercentage
        drawArc(
            color = Color.Green,
            startAngle = -90f,
            sweepAngle = (presentPercentage / 100) * 360,
            useCenter = true,
            size = Size(size.width, size.height)
        )
        drawArc(
            color = Color.Red,
            startAngle = -90f + (presentPercentage / 100) * 360,
            sweepAngle = (absentPercentage / 100) * 360,
            useCenter = true,
            size = Size(size.width, size.height)
        )
    }
}

@Composable
fun AttendanceStatBox(label: String, count: Int, color: Color) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.2f)),
        modifier = Modifier.size(90.dp, 70.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(text = "$count", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}


