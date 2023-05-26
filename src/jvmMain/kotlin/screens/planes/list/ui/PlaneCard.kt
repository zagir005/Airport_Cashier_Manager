package screens.planes.list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.plane.model.PlaneLocal
import ui.card.CardTitleText
import ui.LabelText
import ui.card.head.EditDeleteHeadCard

@Composable
fun PlaneCard(
    plane: PlaneLocal,
    seatCategories: Map<String, Int>,
    editPlane: (PlaneLocal) -> Unit = {},
    deletePlane: (PlaneLocal) -> Unit = {},
    planeClick: (PlaneLocal) -> Unit = {},
){
    EditDeleteHeadCard(
        title = {
            CardTitleText(
                text = plane.codeName,
                rowScope = this,
                textModifier = {
                    Modifier.padding(start = 4.dp).align(Alignment.CenterVertically)
                }
            )
        },
        editBtnClick = {
            editPlane(plane)
        },
        deleteBtnClick = {
            deletePlane(plane)
        },
        onCardClick = {
            planeClick(plane)
        },
        cardModifier = Modifier.heightIn(120.dp,500.dp).padding(8.dp)
    ){
        Column {
            LabelText("Номер самолета: ", plane.codeName, style = MaterialTheme.typography.bodyLarge)
            LabelText("Название самолета: ", plane.name, style = MaterialTheme.typography.bodyLarge)
            for (i in seatCategories){
                LabelText(
                    label = "Количество мест ",
                    text = "\"${i.key}\": ${i.value}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

}