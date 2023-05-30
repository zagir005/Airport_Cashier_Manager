package ui.card.head

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ComposableEmptyLambda
import common.EmptyLambda

@Composable
fun EditHeadCard(
    title: @Composable RowScope.() -> Unit,
    editBtnClick: () -> Unit,
    cardModifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CustomHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        cardModifier = cardModifier,
        content = content
    )
}
@Composable
fun EditHeadCard(
    title: @Composable RowScope.() -> Unit,
    onCardClick: EmptyLambda = {},
    editBtnClick: EmptyLambda = {},
    cardModifier: Modifier = Modifier,
    content: ComposableEmptyLambda
) {
    CustomHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        cardModifier = cardModifier,
        cardClick = onCardClick,
        content = content
    )
}
@Composable
fun EditDeleteHeadCard(
    title: @Composable RowScope.() -> Unit,
    editBtnClick: () -> Unit,
    deleteBtnClick: () -> Unit,
    cardModifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CustomHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        secondBtn = {
            IconButton(
                onClick = deleteBtnClick
            ){
                Icon(Icons.Default.Clear,"")
            }
        },
        cardModifier = cardModifier,
        content = content
    )
}
@Composable
fun EditDeleteHeadCard(
    title: @Composable RowScope.() -> Unit,
    onCardClick: EmptyLambda = {},
    editBtnClick: EmptyLambda = {},
    deleteBtnClick: EmptyLambda = {},
    cardModifier: Modifier = Modifier,
    content: ComposableEmptyLambda
) {
    CustomHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick,
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        secondBtn = {
            IconButton(
                onClick = deleteBtnClick
            ){
                Icon(Icons.Default.Clear,"")
            }
        },
        cardClick = onCardClick,
        cardModifier = cardModifier,
        content = content
    )
}

@Composable
fun ElevatedEditHeadCard(
    title: @Composable RowScope.() -> Unit,
    editBtnClick: () -> Unit,
    cardModifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ElevatedHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        cardModifier = cardModifier,
        content = content
    )
}
@Composable
fun ElevatedEditHeadCard(
    title: @Composable RowScope.() -> Unit,
    onCardClick: EmptyLambda = {},
    editBtnClick: EmptyLambda = {},
    cardModifier: Modifier = Modifier,
    content: ComposableEmptyLambda
) {
    ElevatedHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        cardClick = onCardClick,
        cardModifier = cardModifier,
        content = content
    )
}
@Composable
fun ElevatedEditDeleteHeadCard(
    title: @Composable RowScope.() -> Unit,
    editBtnClick: () -> Unit,
    deleteBtnClick: () -> Unit,
    cardModifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ElevatedHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        secondBtn = {
            IconButton(
                onClick = deleteBtnClick
            ){
                Icon(Icons.Default.Clear,"")
            }
        },
        cardModifier = cardModifier,
        content = content
    )
}
@Composable
fun ElevatedEditDeleteHeadCard(
    title: @Composable RowScope.() -> Unit,
    onCardClick: EmptyLambda = {},
    editBtnClick: EmptyLambda = {},
    deleteBtnClick: EmptyLambda = {},
    cardModifier: Modifier = Modifier,
    content: ComposableEmptyLambda
) {
    ElevatedHeadCard(
        title = title,
        firstBtn = {
            IconButton(
                onClick = editBtnClick
            ){
                Icon(Icons.Default.Edit,"")
            }
        },
        secondBtn = {
            IconButton(
                onClick = deleteBtnClick
            ){
                Icon(Icons.Default.Clear,"")
            }
        },
        cardClick = onCardClick,
        cardModifier = cardModifier,
        content = content
    )
}