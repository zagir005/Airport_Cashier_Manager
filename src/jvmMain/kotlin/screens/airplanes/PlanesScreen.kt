package screens.airplanes

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Plane
import model.SeatCategory
import model.generatePlane
import ui.LabelText
import ui.StandardContextMenu
import ui.ToolBar

@Composable
fun PlanesScreen(){
    Column {
        ToolBar()
        Divider(
            modifier = Modifier.padding(2.dp),
            thickness = 2.dp
        )
        LazyVerticalGrid(
            GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(4.dp)
        ){
            items(List(10){
                generatePlane()
            }){
                PlaneCard(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaneCard(
    plane: Plane,
    redactPlane: (Plane) -> Unit = {},
    deletePlane: (Plane) -> Unit = {},
    planeClick: (Plane) -> Unit = {}
){
    StandardContextMenu(
        deleteClick = {
            deletePlane(plane)
        },
        redactClick = {
            redactPlane(plane)
        }
    ){
        Card(
            onClick = {
//                planeClick()
            },
            modifier = Modifier.heightIn(190.dp,500.dp).padding(4.dp)
        ) {
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    LabelText("Номер самолета: ", plane.codeName, style = MaterialTheme.typography.bodyLarge)
                    LabelText("Название самолета: ", plane.name, style = MaterialTheme.typography.bodyLarge)
                    LabelText(
                        label = "Количество мест \"${SeatCategory.Economy.name}\": ",
                        text = plane.seats.count {
                            it.category is SeatCategory.Economy
                        }.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    LabelText(
                        label = "Количество мест \"${SeatCategory.FirstGrade.name}\": ",
                        text = plane.seats.count {
                            it.category is SeatCategory.FirstGrade
                        }.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    LabelText(
                        label = "Количество мест \"${SeatCategory.Business.name}\": ",
                        text = plane.seats.count {
                            it.category is SeatCategory.Business
                        }.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun PlaneCardPreview(){
    MaterialTheme{
        PlaneCard(generatePlane())
    }
}