package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType


fun main(){
}

sealed class DialogProperty<T>(obj: T){
    class TextFieldProperty<T>(
        obj: T,
        propertyName: String,
        textFieldLabel: String
    ): DialogProperty<T>(obj)
}

@Composable
fun <T> ObjRedactDialog(
    obj: T? = null,
    onApply: (T)-> Unit = {},
    onCloseWindow: () -> Unit = {}
){
    Column {
        repeat(10){
            var text by remember { mutableStateOf("") }
            TextField(
                text,
                {
                    text = it
                }
            )
        }
    }
}


data class Shit(
    val fdsa: String,
    val dfs: Int
)
