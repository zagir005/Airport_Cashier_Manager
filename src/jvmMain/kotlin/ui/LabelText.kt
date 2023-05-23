package ui

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle

@Composable
fun LabelText(
    label: String,
    text: String,
    labelSpanStyle: SpanStyle = SpanStyle(Color.Gray),
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current){

    Text(label.annotatedString(SpanStyle(Color.Gray)) + text, style = style)
}