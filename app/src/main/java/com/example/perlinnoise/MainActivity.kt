package com.example.perlinnoise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perlinnoise.ui.screen.PerlinScreen
import com.example.perlinnoise.ui.screen.SineScreen
import com.example.perlinnoise.ui.theme.PerlinNoiseTheme

enum class Screen {
    Perlin,
    Sine
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerlinNoiseTheme {
                Column(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                    var screen by remember { mutableStateOf(Screen.Perlin) }
                    Surface(modifier = Modifier.fillMaxWidth().padding(16.dp), color = Color.Black, shape = RoundedCornerShape(5.dp)) {
                        Box(modifier = Modifier.padding(8.dp).wrapContentHeight(align = Alignment.CenterVertically)) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Surface(modifier = Modifier.weight(1f)
                                    .clickable(onClick = {
                                        screen = Screen.Perlin
                                    }),
                                    color = if (screen == Screen.Perlin)
                                        MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface) {
                                    Text(text = "Perlin Noise", fontSize = 24.sp,
                                        modifier = Modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally))
                                }
                                Spacer(modifier = Modifier.padding(16.dp))
                                Surface(modifier = Modifier.weight(1f)
                                    .clickable(onClick = {
                                        screen = Screen.Sine
                                    }),
                                    color = if (screen == Screen.Sine)
                                        MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface) {
                                    Text(text = "Sine Noise", fontSize = 24.sp,
                                        modifier = Modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally))
                                }
                            }
                        }
                    }
                    when (screen) {
                        Screen.Perlin -> PerlinScreen()
                        Screen.Sine -> SineScreen()
                    }
                }
            }
        }
    }
}
