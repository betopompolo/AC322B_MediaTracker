package com.betopompolo.mediatracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betopompolo.mediatracker.ui.theme.MediaTrackerTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class MovieDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTrackerTheme {
                MovieDetailPage()
            }
        }
    }
}

@Composable
fun MovieDetailPage() {
    Scaffold(
        topBar = {},
        floatingActionButton = {},
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(R.drawable.lotr_book_cover),
                    contentDescription = "Movie banner image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surface
                                )
                            )
                        )
                ) {}

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Blade Runner 2049", style = MaterialTheme.typography.displayMedium)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "Movie",
                            "Sci-fi",
                            "2017",
                            movieDuration.formatted
                        ).forEach { text ->
                            Badge(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(text, modifier = Modifier.padding(4.dp))
                            }
                        }
                    }

                }
            }

            MovieCard("Synopsis") {
                Text("""Thirty years after the events of the first film, a new blade runner, LAPDOfficer K, unearths a long-buriedsecret that has the potential toplunge what's left of society intochaos. K's discovery leads him on aquest to find Rick Deckard, a formerLAPD blade runner who has beenmissing for 30 years. The journeytakes him deep into the irradiatedwastelands outside Los Angeles andinto the heart of the Wallace Corporation's secretive operations.""")
            }

            MovieCard("Watch progress") {
                var sliderProgress by remember { mutableFloatStateOf(0.25f) }
                val movieProgress = movieDuration * sliderProgress.toDouble()

                Text(movieProgress.formatted)
                Slider(
                    value = sliderProgress,
                    onValueChange = { sliderProgress = it }
                )
            }

            MovieCard("Details") {
                TitleAndValueRow("Director", "Denis Villeneuve")
                TitleAndValueRow(
                    "Studios", "Warner Bros.\n" +
                            "Alcon Entertainment"
                )
                TitleAndValueRow("Original release", "October 6, 2017")
            }
        }
    }
}

val movieDuration = 2.hours + 30.minutes

val Duration.formatted
    get() = toComponents { hours, minutes, _, _ ->
        "$hours h $minutes m"
    }

@Composable
fun MovieCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(Modifier.padding(28.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            content()
        }
    }
}

@Composable
fun TitleAndValueRow(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(title)
        Text(value, textAlign = TextAlign.End)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaTrackerTheme {
        MovieDetailPage()
    }
}