package com.example.attendancemarker.presentation.components


import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancemarker.Preferences.PreferenceManager
import com.example.attendancemarker.R
import com.example.attendancemarker.Utils.getFileNameFromUri
import com.example.attendancemarker.Utils.parseStudentFile
import com.example.attendancemarker.data.entity.StudentEntity
import com.example.attendancemarker.presentation.ViewModel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(onBackClick = { navController.popBackStack() })
        ProfileHeader(
            name = "Zeeshan Ahmed",
            id = "TCHR-001",
            tagline = "Let's mark smart attendance!"
        )
        AttendanceStats( students = 45)
        OptionsList(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                "Profile",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp) // Material default compact height
            .shadow(1.dp)
    )
}


@Composable
fun ProfileHeader(
    name: String,
    id: String,
    tagline: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color(0xFFBBDEFB)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_profile_placeholder),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("ID: $id", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(6.dp))
        Text(tagline, fontSize = 14.sp, color = Color.DarkGray)
    }
}

@Composable
fun AttendanceStats( students: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem("Class Name / Batch", "BTech 6th Sem")
        StatItem("Students", students.toString())
    }
}

@Composable
fun StatItem(label: String, count: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(count, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(label, fontSize = 14.sp, color = Color.Gray)
    }
}


// Options List (Edit Profile, Settings, Help, Logout)
@Composable
fun OptionsList(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)
    val viewModel: ProfileViewModel = hiltViewModel()


    // launcher for file picker
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val fileName = getFileNameFromUri(context, it)
                if (fileName.endsWith(".csv", ignoreCase = true)) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val students = parseStudentFile(context, it)
                        viewModel.saveStudents(students)
                    }
                } else {
                    Toast.makeText(context, "Please select a .csv file", Toast.LENGTH_SHORT).show()
                }
            }
        }


    Column(modifier = Modifier.padding(16.dp)) {
        OptionItem(
            icon = Icons.Filled.Edit,
            text = "Edit Profile"
        ) { /* Navigate to Edit Profile */ }
        OptionItem(
            icon = Icons.Default.UploadFile,
            text = "Upload Class Details"
        ) {
            launcher.launch("*/*")
        }
        OptionItem(
            icon = Icons.Filled.Settings,
            text = "Settings"
        ) { navController.navigate("settings") }
        OptionItem(icon = Icons.Filled.Help, text = "Help & Support") { /* Open Help */ }
        OptionItem(icon = Icons.Filled.ExitToApp, text = "Logout") {
            navController.navigate("login")
            preferenceManager.setUserLoggedIn(false)
        }
    }
}


suspend fun parseAndSaveStudentFile(context: Context, uri: Uri) {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri) ?: return
    val students = mutableListOf<StudentEntity>()

    inputStream.bufferedReader().useLines { lines ->
        lines.drop(1).forEach { line ->
            val tokens = line.split(",")
            if (tokens.size >= 4) {
                val student = StudentEntity(
                    rollNo = tokens[1].trim(),
                    name = tokens[0].trim(),
                    bluetoothName = tokens[2].trim(),
                    macAddress = tokens[3].trim()
                )
                students.add(student)
            }
        }
    }

    /* val db = AppDatabase.getDatabase(context)
     db.StudentDao().insertAll(students)*/

    withContext(Dispatchers.Main) {
        Toast.makeText(context, "Uploaded ${students.size} students", Toast.LENGTH_SHORT).show()
    }
}


@Composable
fun OptionItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp)
    }
}
