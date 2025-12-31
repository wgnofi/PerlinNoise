package com.example.perlinnoise.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perlinnoise.R
import kotlin.random.Random

fun logicNoise(x: Int): Float {
    val random = Random(x)
    return (random.nextFloat() * 2 - 1)
}


fun fade(t: Float): Float {
    return t * t * t * (t * (t * 6 - 15) + 10)
}

// Linear Interpolation function
fun lerp(a: Float, b: Float, t: Float): Float {
    return a + t * (b - a)
}

fun getPerlin1D(x
                : Float, useFade: Boolean): Float {
    val x0 = x.toInt() // nearest lower integer
    val x1 = x0 + 1
    val t = x - x0.toFloat()

    val gradient0 = logicNoise(x0)
    val gradient1 = logicNoise(x1)

    val influence0 = gradient0 * t
    val influence1 = gradient1 * (t - 1f)

    return lerp(influence0, influence1, if (useFade) fade(t) else t)
}



@Composable
fun PerlinScreen() {
    var octaves by rememberSaveable {
        mutableIntStateOf(1)
    }
    var fade by rememberSaveable {
        mutableStateOf(true)
    }

    var amplitude by rememberSaveable {
        mutableFloatStateOf(100f)
    }
    val points = mutableListOf<Offset>()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Canvas(modifier = Modifier.fillMaxWidth().height(500.dp).padding(16.dp)) {
            for (x in 0 until size.width.toInt()) {
                var totalY = 0f
                var currentAmplitude = amplitude
                var currentFrequency = 0.005f
                for (i in 0 until octaves) {
                    val octaveOffset = i * 1000f
                    totalY += getPerlin1D((x * currentFrequency) + octaveOffset, fade) * currentAmplitude
                    currentAmplitude *= 0.5f
                    currentFrequency *= 2.0f
                }
                val y = (size.height / 2) + totalY
                val point = Offset(x.toFloat(), y)
                points.add(point)
            }

            drawPoints(
                points = points,
                pointMode = PointMode.Points,
                color = Color.Blue,
                strokeWidth = 7f,
                cap = StrokeCap.Round
            )


        }
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Octaves $octaves", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Row {
                Surface(
                    shape = CircleShape, color = Color.White,
                    modifier = Modifier.clickable(onClick = {
                        octaves = (octaves - 1).coerceAtLeast(1)
                    })
                ) {
                    Icon(
                        painter = painterResource(R.drawable.remove),
                        contentDescription = "Remove",
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Surface(
                    shape = CircleShape, color = Color.White,
                    modifier = Modifier.clickable(onClick = {
                        octaves++
                    })
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "Add",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

        }
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Fade Function", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Switch(
                checked = fade,
                onCheckedChange = {
                    fade = it
                }
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Amplitude", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.padding(8.dp))
            Slider(
                value = amplitude,
                onValueChange = {
                    amplitude = it
                },
                valueRange = 100f..1000f,
                steps = 100
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

