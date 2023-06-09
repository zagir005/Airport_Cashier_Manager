package screens.planes.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.plane.model.PlaneLocal
import ui.card.CardTitleText
import ui.LabelText
import ui.card.head.EditHeadCard


@Composable
fun PlaneInfoCard(
    planeLocal: PlaneLocal,
    editInfoOnClick: (PlaneLocal) -> Unit = {}
) {
    EditHeadCard(
        title = {
            CardTitleText("Информация", this)
        },
        editBtnClick = {
            editInfoOnClick(planeLocal)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            LabelText(
                label = "Название самолета: ",
                text = planeLocal.name,
                style = MaterialTheme.typography.titleMedium
            )
            LabelText(
                label = "Общее количество мест: ",
                text = planeLocal.seats.sumOf {it.seatInfo.count() }.toString(),
                style = MaterialTheme.typography.titleMedium
            )
            LabelText(
                label = "Количество ближайших рейсов: ",
                text = "5",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}