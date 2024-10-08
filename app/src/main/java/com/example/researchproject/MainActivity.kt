package com.example.researchproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.researchproject.ui.theme.ResearchProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResearchProjectTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedFeature by remember { mutableStateOf(FeatureType.None) }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DemoButtons { selectedFeature = it }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .padding(20.dp)
            ) {
                DisplayselectedFeature(selectedFeature)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DemoButtons { selectedFeature = it }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                DisplayselectedFeature(selectedFeature)
            }
        }
    }
}

@Composable
fun DemoButtons(onSelect: (FeatureType) -> Unit) {
    Button(onClick = { onSelect(FeatureType.LazyColumn) }) { Text("Lazy Column") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.LazyRow) }) { Text("Lazy Row") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.ResponsiveArchitecture) }) { Text("Responsive Architecture") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.FillMaxWidth) }) { Text("Fill MaxWidth") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.PaddingAlignment) }) { Text("Padding & Alignment") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.DpUsage) }) { Text("DP Usage") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.FlowAlignment) }) { Text("Flow Alignment") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.Breakpoint) }) { Text("Breakpoint") }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = { onSelect(FeatureType.Weight) }) { Text("Weight") }
}

@Composable
fun DisplayselectedFeature(selectedFeature: FeatureType) {
    when (selectedFeature) {
        FeatureType.LazyColumn -> LazyColumnExample()
        FeatureType.LazyRow -> LazyRowExample()
        FeatureType.ResponsiveArchitecture -> ResponsiveArchitectureExample()
        FeatureType.FillMaxWidth -> FillMaxWidthExample()
        FeatureType.PaddingAlignment -> PaddingAlignmentExample()
        FeatureType.DpUsage -> DpUsageWithSliderExample()
        FeatureType.FlowAlignment -> FlowAlignmentExample()
        FeatureType.Breakpoint -> BreakpointDemoExample()
        FeatureType.Weight -> WeightExample()
        else -> {}
    }
}

enum class FeatureType {
    None, LazyColumn, LazyRow, ResponsiveArchitecture, FillMaxWidth, PaddingAlignment,
    DpUsage, FlowAlignment, Breakpoint, Weight
}

@Composable
fun LazyColumnExample() {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        items(10) { index ->
            Text("Item $index", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun LazyRowExample() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(20.dp)
    ) {
        items(10) { index ->
            Text("Item $index", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ResponsiveArchitectureExample() {
    val configuration = LocalConfiguration.current
    val orientationText = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        "Landscape"
    } else {
        "Portrait"
    }
    val aspectRatio = configuration.screenWidthDp.toFloat() / configuration.screenHeightDp.toFloat()

    BoxWithConstraints {
        val currentWidth = maxWidth

        if (currentWidth < 600.dp && aspectRatio < 1.5f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
                    .padding(20.dp)
            ) {
                Text(
                    "Compact form factor (Mobile) - $orientationText",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if (currentWidth < 800.dp || aspectRatio < 1.7f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
                    .padding(20.dp)
            ) {
                Text(
                    "Medium form factor (Tablet) - $orientationText",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
                    .padding(20.dp)
            ) {
                Text(
                    "Large form factor (Desktop/Foldable) - $orientationText",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun FillMaxWidthExample() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        Text("This Box fills the maximum width available.")
    }
}

@Composable
fun PaddingAlignmentExample() {
    var alignmentOption by remember { mutableStateOf(Alignment.Center) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(32.dp),
            contentAlignment = alignmentOption
        ) {
            Text("Padding and Alignment Example", modifier = Modifier.padding(8.dp))
        }

        Text("")

        Button(onClick = {
            alignmentOption = if (alignmentOption == Alignment.Center) {
                Alignment.TopStart
            } else if (alignmentOption == Alignment.TopStart) {
                Alignment.BottomEnd
            } else {
                Alignment.Center
            }
        }) {
            Text("Toggle Alignment (Center, TopStart, BottomEnd)")
        }
    }
}

@Composable
fun DpUsageWithSliderExample() {
    var dpValue by remember { mutableStateOf(16f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Adjust the padding (dp):", modifier = Modifier.padding(20.dp))
        Slider(
            value = dpValue,
            onValueChange = { dpValue = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dpValue.dp)
                .background(Color.LightGray)
                .height(150.dp)
        ) {
            Text("Padding: ${dpValue.dp}", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun FlowAlignmentExample() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Flow Alignment (Start)", modifier = Modifier.background(Color.Cyan).padding(20.dp))
        Text("")
        Text("Flow Alignment (End)", modifier = Modifier.background(Color.Magenta).padding(20.dp))
    }
}

@Composable
fun BreakpointDemoExample() {
    var sliderPosition by remember { mutableStateOf(300f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resize the screen with the slider", modifier = Modifier.padding(20.dp))
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 300f..600f,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .width(sliderPosition.dp)
                .height(200.dp)
                .background(if (sliderPosition < 400f) Color.Yellow else Color.Green)
        ) {
            Text(
                text = if (sliderPosition < 400f) "Small Screen" else "Large Screen",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun WeightExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.Yellow)
                .padding(20.dp)
        ) {
            Text("1/3 weight", modifier = Modifier.align(Alignment.Center))
        }
        Box(
            modifier = Modifier
                .weight(2f)
                .background(Color.Green)
                .padding(20.dp)
        ) {
            Text("2/3 weight", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ResearchProjectTheme {
        MainScreen()
    }
}
