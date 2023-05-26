package screens.planes.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.plane.model.PlaneLocal
import screens.planes.model.PlaneSeatCategory
import ui.card.CardTitleText
import ui.card.head.CustomHeadCard
import ui.LabelText
import ui.card.head.ElevatedEditDeleteHeadCard


@Composable
fun PlaneSeatsCard(
    planeLocal: PlaneLocal,
    seatsCategoriesList: List<PlaneSeatCategory>,
    addSeatsCategory: () -> Unit = {},
    editSeatsCategory: (PlaneSeatCategory) -> Unit = {},
    removeSeatsCategory: (PlaneSeatCategory) -> Unit = {}
) {
    CustomHeadCard(
        title = {
            CardTitleText("Места самолета", this)
        },
        firstBtn = {
            IconButton(
                onClick = {
                    addSeatsCategory()
                }
            ){
                Icon(Icons.Default.Add,"")
            }
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp)
        ) {
            items(seatsCategoriesList) { seatCategory ->
                ElevatedEditDeleteHeadCard(
                    title = {
                        Text(
                            text = seatCategory.categoryName,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraLight,
                                fontSize = 22.sp
                            )
                        )
                    },
                    editBtnClick = {
                        editSeatsCategory(seatCategory)
                    },
                    deleteBtnClick = {
                        removeSeatsCategory(seatCategory)
                    },
                    cardModifier = Modifier.wrapContentSize().padding(4.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(6.dp)
                    ) {
                        LabelText(
                            label = "Количество мест: ",
                            text = seatCategory.count.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        LabelText(
                            label = "Допустимый вес багажа: ",
                            text = seatCategory.baggageWeight.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}