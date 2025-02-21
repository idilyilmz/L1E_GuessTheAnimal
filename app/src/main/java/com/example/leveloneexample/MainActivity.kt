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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leveloneexample.ui.theme.LevelOneExampleTheme

/**
 * The main activity that initializes the app UI.
 *
 * This activity sets up the content view using Jetpack Compose,
 * enables edge-to-edge display, and applies the app's custom theme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display for immersive UI.
        enableEdgeToEdge()

        setContent {
            // Applies the app's custom theme.
            LevelOneExampleTheme {
                // Root container applying the app's theme.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Load the main screen content.
                    GuessAnimalScreen()
                }
            }
        }
    }
}

/**
 * Composable function that represents the main screen for guessing the animal.
 *
 * This function sets up the screen layout using a [Scaffold] with a top app bar and the main content area.
 * It displays the app's name in the top app bar and calls [ScreenContent] to display the main content.
 */
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

/**
 * Composable function that displays the main content of the screen.
 *
 * This function displays a question, an image of a giraffe, and an input field with a submit button.
 * It manages the user's input and updates the UI accordingly.
 *
 * @param modifier Modifier to apply styling and layout behavior.
 */
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
        // Adds spacing between input and button.
        Spacer(modifier = Modifier.height(24.dp))

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

/**
 * Composable function that displays the input segment for the user to enter their answer.
 *
 * This function includes an [OutlinedTextField] for user input and a [Button] to submit the answer.
 * It updates the parent-managed state with the user's input and triggers the answer verification process.
 *
 * @param answerText The current text in the input field.
 * @param onAnswerChange Callback function to update the answer text.
 */
@Composable
fun InputSegment(answerText: String, onAnswerChange: (String) -> Unit) {
    // Accesses the Android context to display a Toast.
    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically) {
        // User input field with a placeholder and label.
        OutlinedTextField(
            value = answerText,
            onValueChange = onAnswerChange, // Updates the parent-managed state.
            label = { Text(stringResource(R.string.answer_label)) },
            placeholder = { Text(stringResource(R.string.animal_question)) },
            modifier = Modifier.widthIn(max = 250.dp) // Max width of 300dp
        )

        // Adds spacing between input and button.
        Spacer(modifier = Modifier.width(8.dp))

        // Submit button with an icon to process the answer.
        Button(onClick = { verifyAnswer(context, answerText) }) {
            // Add the icon to the button
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Submit answer",
                modifier = Modifier.size(24.dp) // Adjust size as needed
            )
        }
    }
}

/**
 * Verifies the user's answer and displays a toast message indicating correctness.
 *
 * This function compares the user's answer to the correct answer (case-insensitive) and displays a Toast
 * message to inform the user whether their answer was correct or incorrect.
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

/**
 * Preview function for the [GuessAnimalScreen] composable.
 *
 * This function allows you to preview the [GuessAnimalScreen] in the Android Studio design view.
 */
@Preview(showBackground = true)
@Composable
fun GuessAnimalScreenPreview() {
    LevelOneExampleTheme {
        // Preview version of the main screen.
        GuessAnimalScreen()
    }
}