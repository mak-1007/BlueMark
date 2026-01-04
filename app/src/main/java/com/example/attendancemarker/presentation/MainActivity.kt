package com.example.attendancemarker.presentation


import SettingsScreen
import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.attendancemarker.Preferences.PreferenceManager
import com.example.attendancemarker.presentation.ViewModel.SplashViewModel
import com.example.attendancemarker.presentation.components.BtmNavigation.NavigationItem
import com.example.attendancemarker.presentation.components.DeviceScreen
import com.example.attendancemarker.presentation.components.Login
import com.example.attendancemarker.presentation.components.NotificationScreen
import com.example.attendancemarker.presentation.components.OptionsList
import com.example.attendancemarker.presentation.components.PresentStudentListScreen
import com.example.attendancemarker.presentation.components.ProfileScreen
import com.example.attendancemarker.presentation.components.SettingScreen
import com.example.attendancemarker.presentation.components.SplashScreenContent
import com.example.attendancemarker.presentation.components.TeacherHomeScreen
import com.example.attendancemarker.ui.theme.AttendanceMarkerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val bluetoothViewModel: BluetoothViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()

    private val bluetoothManager by lazy {
        application.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d(TAG, "onCreate: ")
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { splashViewModel.isSplashVisible.value }

        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { /* Not needed */ }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()

        ) { permissions ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                permissions[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
            )
        }

        setContent {
            AttendanceMarkerTheme {

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (splashViewModel.isSplashVisible.value) {
                        Log.d(TAG, "splash value: ${splashViewModel.isSplashVisible.value}")
                        SplashScreenContent()
                    } else {
                        Log.d(TAG, "Further App: ")
                        MyApp(bluetoothViewModel)
                    }
                }
            }
        }
    }


    @Composable
    fun MyApp(bluetoothViewModel: BluetoothViewModel) {
        val navController = rememberNavController()
        val context = LocalContext.current
        val preferenceManager = PreferenceManager(context)
        val startDestination = if (preferenceManager.isUserLoggedIn()) "home" else "login"

        val navItemList = listOf(
            NavigationItem("Home", Icons.Default.Home, "home"),
            NavigationItem("Dashboard", Icons.Default.Dashboard, "dashboard"),
            NavigationItem("Attendance", Icons.Default.AcUnit, "attendance"),
            NavigationItem("Notification", Icons.Default.Notifications, "notification"),
            NavigationItem("You", Icons.Default.Person, "profile")
        )
        // Get current destination to decide whether to show bottom navigation
        val currentDestination =
            navController.currentBackStackEntryAsState().value?.destination?.route

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (currentDestination !in listOf("login")) {
                    BottomNavigationBar(navController, navItemList)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable("login") {
                    Login(
                        onLoginSuccess = {
                            preferenceManager.setUserLoggedIn(true)
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    )
                }
                composable("home") {
                    val state = bluetoothViewModel.state.collectAsState().value
                    TeacherHomeScreen(
                        teacherName = "Zeeshan",
                        presentStudents = state.presentStudents.size,
                        presentStudentList = state.presentStudents,
                        onNavigateToPresent = { navController.navigate("presentList") }
                    )
                }

                composable("dashboard") {
                    val state = bluetoothViewModel.state.collectAsState().value
                    PresentStudentListScreen(
                        presentStudents = state.presentStudents,
                        onBack = { navController.popBackStack() }
                    )
                }
                composable("attendance") {
                    val state = bluetoothViewModel.state.collectAsState().value
                    DeviceScreen(
                        state = state,
                        onStartScan = { bluetoothViewModel.startScan() },
                        onStopScan = { bluetoothViewModel.stopScan() }
                    )
                }
                composable("notification") { NotificationScreen() }
                composable("profile") { ProfileScreen(navController) }
                composable("presentList") {
                    val state = bluetoothViewModel.state.collectAsState().value
                    PresentStudentListScreen(
                        presentStudents = state.presentStudents,
                        onBack = { navController.popBackStack() }
                    )
                }
                composable("settings") { SettingScreen(navController) }

            }
        }
    }


    @Composable
    fun BottomNavigationBar(navController: NavController, navItems: List<NavigationItem>) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        NavigationBar {
            navItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        BadgedBox(badge = {
                            item.badgeCount?.let { count ->
                                if (count > 0) {
                                    Badge { Text(count.toString()) }
                                }
                            }
                        }) {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        }
                    },
                    label = { Text(text = item.title, maxLines = 1, fontSize = 11.sp)}
                )
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }

}

