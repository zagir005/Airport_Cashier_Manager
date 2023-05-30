package ui

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import common.annotatedString
import common.plus

@Composable
fun LabelText(
    label: String,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
){
    Text(label.annotatedString(SpanStyle(Color.Gray)) + text, style = style, modifier = modifier)
}