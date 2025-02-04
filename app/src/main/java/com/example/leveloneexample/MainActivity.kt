package com.example.leveloneexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment

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
    Column(
        modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.animal_question),
            style = MaterialTheme.typography.headlineSmall,
        )
        Image(
            painter = painterResource(id = R.drawable.giraffe),
            contentDescription = "giraffe",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = String(),
                placeholder = { Text(text = stringResource(id = R.string.animal_question)) },
                onValueChange = {
                    // TODO to be completed
                },
                label = { Text(stringResource(R.string.answer_label)) }
            )
            Spacer(modifier = modifier.width(8.dp))
            Button(
                onClick = {
                    // TODO to be completed
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Process user input")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuessAnimalScreenPreview() {
    LevelOneExampleTheme {
        GuessAnimalScreen()
    }
}