package common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import data.client.model.ClientLocal
import java.util.*

fun String.annotatedString(spanStyle: SpanStyle = SpanStyle()): AnnotatedString{
    return AnnotatedString(this,spanStyle)

}

operator fun AnnotatedString.plus(string: String): AnnotatedString{
    return this + string.annotatedString()
}

fun String.toDate(): Date {
    return Date()
}

fun Boolean.gender(): String{
    return if (this){
        "Мужчина"
    }else{
        "Женщина"
    }
}

fun ClientLocal.getInitialsName() =
    "${this.lastName} ${this.firstName.first()}. ${this.patronymic.first()}. "