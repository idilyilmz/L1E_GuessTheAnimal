package com.example.leveloneexample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leveloneexample.ui.theme.LevelOneExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display.
        setContent {
            LevelOneExampleTheme {
                // Root container applying the app's theme.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessAnimalScreen() // Load the main screen content.
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessAnimalScreen() {
    // Scaffold provides a structure with a top app bar and main content.
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        // Main screen content with applied padding from Scaffold.
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    // Holds the user's answer input, ensuring it persists across recompositions.
    var answerText by remember { mutableStateOf("") }

    Column(
        modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Displays a question asking the user to guess the animal.
        Text(
            text = stringResource(R.string.animal_question),
            style = MaterialTheme.typography.headlineSmall
        )

        // Displays an image of the animal the user needs to guess.
        Image(
            painter = painterResource(id = R.drawable.giraffe),
            // decorative element
            contentDescription = "giraffe",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )

        // Input field and button for user interaction.
        InputSegment(answerText) { answerText = it }
    }
}

@Composable
fun InputSegment(answerText: String, onAnswerChange: (String) -> Unit) {
    val context = LocalContext.current // Accesses the Android context to display a Toast.

    Row(verticalAlignment = Alignment.CenterVertically) {
        // User input field with a placeholder and label.
        OutlinedTextField(
            value = answerText,
            onValueChange = onAnswerChange, // Updates the parent-managed state.
            label = { Text(stringResource(R.string.answer_label)) },
            placeholder = { Text(stringResource(R.string.animal_question)) }
        )

        Spacer(modifier = Modifier.width(8.dp)) // Adds spacing between input and button.

        // Submit button with an icon to process the answer.
        Button(onClick = { verifyAnswer(context, answerText) }) {
            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Submit answer")
        }
    }
}

/**
 * Verifies the user's answer and displays a toast message indicating correctness.
 *
 * @param context The Android application context to show a Toast.
 * @param answerText The user's inputted answer.
 */
fun verifyAnswer(context: Context, answerText: String) {
    // Compare user's answer (case-insensitive) to the correct value from resources.
    val resultMessage = if (answerText.trim().equals(
            context.getString(R.string.giraffe_upper), ignoreCase = true
        )
    ) {
        context.getString(R.string.correct) // Correct answer message.
    } else {
        context.getString(R.string.incorrect) // Incorrect answer message.
    }

    // Display feedback to the user via a toast message.
    Toast.makeText(context, "\"$answerText\" $resultMessage", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun GuessAnimalScreenPreview() {
    LevelOneExampleTheme {
        GuessAnimalScreen() // Preview version of the main screen.
    }
}