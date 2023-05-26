package screens.planes.list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.plane.model.PlaneLocal
import ui.LabelText
import ui.StandardContextMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaneCard(
    plane: PlaneLocal,
    seatCategories: Map<String, Int>,
    editPlane: (PlaneLocal) -> Unit = {},
    deletePlane: (PlaneLocal) -> Unit = {},
    planeClick: (PlaneLocal) -> Unit = {},
){
    StandardContextMenu(
        deleteClick = {
            deletePlane(plane)
        },
        editClick = {
            editPlane(plane)
        }
    ){
        Card(
            onClick = {
                planeClick(plane)
            },
            modifier = Modifier.heightIn(120.dp,500.dp).padding(4.dp)
        ) {
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    LabelText("Номер самолета: ", plane.codeName, style = MaterialTheme.typography.bodyLarge)
                    LabelText("Название самолета: ", plane.name, style = MaterialTheme.typography.bodyLarge)
                    for (i in seatCategories){
                        Column {
                            LabelText(
                                label = "Количество мест ",
                                text = "\"${i.key}\": ${i.value}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}