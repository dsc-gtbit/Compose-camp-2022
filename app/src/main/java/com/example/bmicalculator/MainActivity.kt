package com.example.bmicalculator

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import com.example.bmicalculator.ui.theme.BMICalculatorTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    mainScreen()
                }
            }
        }
    }
}


@Composable
fun PressIconButton(

    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
    text: @Composable () -> Unit,
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        onClick = onClick, modifier = modifier,
        interactionSource = interactionSource
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
text()
    }
}

@Composable
fun mainScreen() {
    var height by remember {
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(text = "BMI CALCULATOR", color = Color.Blue, fontWeight = FontWeight.Bold ,
            fontSize = 30.sp)

        TextField(
            modifier = Modifier.padding(15.dp).background(color = Color.Cyan),

            value = height,
            onValueChange = { height = it },
            label = {
                Text(text = "Height (M)")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        TextField(
            modifier = Modifier.padding(15.dp).background(color = Color.Cyan),
            value = weight,
            onValueChange = { weight = it },
            label = {
                Text(text = "Weight (KG)")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                    onDone = { result = calculateBmi(height.toDouble(), weight.toDouble())}),
        )
        PressIconButton(
            onClick = {
                result = calculateBmi(height.toDouble(), weight.toDouble())
            },
            icon = { Icon(Icons.Rounded.PlayArrow, contentDescription = null) }
        ) {
            Text("Calculate")


        }

            if (result.isNotBlank()) {
                Text(
                    text = result,
                    color = Color.Magenta
                )
            }

        }

    }

    private fun calculateBmi(height: Double, weight: Double): String {
        val bmiIndex = weight / (height * height)
        return "Result: $bmiIndex"
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BMICalculatorTheme {
            mainScreen()
        }
    }

