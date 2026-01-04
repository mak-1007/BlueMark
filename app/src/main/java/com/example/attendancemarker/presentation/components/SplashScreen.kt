package com.example.attendancemarker.presentation.components


import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.attendancemarker.R
import com.example.attendancemarker.ui.theme.SplashBgColor
import kotlinx.coroutines.delay


@Composable
fun FancySplashScreen() {
    var alpha by remember { mutableFloatStateOf(0.0f) }
    val scale by animateFloatAsState(
        targetValue = if (alpha == 1f) 1f else 0.8f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        delay(1500) // Show splash for 1.5s
        alpha = 1f
        delay(1000) // Extra delay for smooth transition
        //onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Attendance Marker",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.scale(scale)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContent() {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 800, easing = EaseOutQuad),
        label = "scale"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashBgColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.iconn), // Replace with your icon
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(250.dp)
                    .scale(scale)
            )
            Image(
                painter = rememberAsyncImagePainter(R.drawable.icon_gif),
                contentDescription = "Splash GIF",
                modifier = Modifier
                    .size(250.dp)
                    .scale(scale)
                    )
        }
    }
}
