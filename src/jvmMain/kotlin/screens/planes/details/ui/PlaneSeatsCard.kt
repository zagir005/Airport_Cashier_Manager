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
import androidx.compose.material.icons.filled.Clear
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
import ui.CardTitleText
import ui.CardWithOptionBtn
import ui.ElevatedCardWithOptionBtn
import ui.LabelText


@Composable
fun PlaneSeatsCard(
    planeLocal: PlaneLocal,
    seatsCategoriesList: List<PlaneSeatCategory>,
    addSeatsCategory: () -> Unit = {},
    editSeatsCategory: (PlaneSeatCategory) -> Unit = {},
    removeSeatsCategory: (PlaneSeatCategory) -> Unit = {}
) {
    CardWithOptionBtn(
        title = {
            CardTitleText("Места самолета", this)
        },
        optionBtnClick = {
            addSeatsCategory()
        },
        icon = Icons.Default.Add
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            userScrollEnabled = false
        ) {
            items(seatsCategoriesList) { seatCategory ->
                ElevatedCardWithOptionBtn(
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
                    optionBtnClick = {
                        editSeatsCategory(seatCategory)
                    },
                    secondOptionBtnClick = {
                        removeSeatsCategory(seatCategory)
                    },
                    cardModifier = Modifier.wrapContentSize().padding(4.dp),
                    secondIcon = Icons.Default.Clear
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