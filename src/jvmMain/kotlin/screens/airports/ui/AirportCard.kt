package screens.airports.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.airport.model.AirportLocal
import ui.LabelText
import ui.card.CardTitleText
import ui.card.head.EditDeleteHeadCard

@Composable
fun AirportCard(
    airport: AirportLocal,
    editAirport: (AirportLocal) -> Unit = {},
    deleteAirport: (AirportLocal) -> Unit = {},
){

    EditDeleteHeadCard(
        title = {
            CardTitleText(
                text = airport.name,
                rowScope = this,
                textModifier = {
                    it.padding(start = 4.dp)
                }
            )
        },
        editBtnClick = {
            editAirport(airport)
        },
        deleteBtnClick = {
            deleteAirport(airport)
        },
        cardModifier = Modifier.heightIn(120.dp,500.dp).padding(8.dp)
    ){
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                LabelText("Код города: ", airport.cityCode, style = MaterialTheme.typography.bodyLarge)
                LabelText("Город: ", airport.city, style = MaterialTheme.typography.bodyLarge)

            }
        }
    }
}