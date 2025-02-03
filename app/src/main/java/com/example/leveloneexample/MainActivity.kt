package com.example.leveloneexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leveloneexample.ui.theme.LevelOneExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LevelOneExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessAnimalScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api:: class)
@Composable
fun GuessAnimalScreen() {
   Scaffold(
       topBar = {
           TopAppBar(
               title = {
                   Text (
                       text = stringResource(id = R.string.app_name)
                   )
               },
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = MaterialTheme.colorScheme.primary,
                   titleContentColor = MaterialTheme.colorScheme.onPrimary
               )
           )
       },
       content = { padding -> ScreenContent(Modifier.padding(padding))},
   )
}

@Composable
fun ScreenContent(modifier: Modifier) {
    //TODO: we add more code here later
    Text(
        "It works! - remove this text later",
        modifier = modifier.padding()
    )
}

@Preview(showBackground = true)
@Composable
fun GuessAnimalScreenPreview() {
    LevelOneExampleTheme {
        GuessAnimalScreen()
    }
}