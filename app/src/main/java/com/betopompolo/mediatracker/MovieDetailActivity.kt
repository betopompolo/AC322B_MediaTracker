package com.betopompolo.mediatracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betopompolo.mediatracker.ui.theme.MediaTrackerTheme

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
            modifier = Modifier.padding(innerPadding)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(28.dp)) {
                    Text(
                        "Synopsis",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text("""Thirty years after the events of the first film, a new blade runner, LAPDOfficer K, unearths a long-buriedsecret that has the potential toplunge what's left of society intochaos. K's discovery leads him on aquest to find Rick Deckard, a formerLAPD blade runner who has beenmissing for 30 years. The journeytakes him deep into the irradiatedwastelands outside Los Angeles andinto the heart of the WallaceCorporation's secretive operations.""")
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(28.dp),
                ) {
                    TitleAndValueRow("Director", "Denis Villeneuve")
                    TitleAndValueRow("Studios", "Warner Bros.\n" +
                            "Alcon Entertainment")
                    TitleAndValueRow("Original release", "October 6, 2017")
                }
            }
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