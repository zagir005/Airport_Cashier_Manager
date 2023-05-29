package screens.clients.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.gender
import data.client.model.ClientLocal
import ui.LabelText
import ui.card.CardTitleText
import ui.card.head.EditDeleteHeadCard
import java.text.SimpleDateFormat

@Composable
fun ClientCard(
    client: ClientLocal,
    editClient: (ClientLocal) -> Unit = {},
    deleteClient: (ClientLocal) -> Unit = {},
    clientClick: (ClientLocal) -> Unit = {}
){
    val dateFormat = SimpleDateFormat("dd MMMM yyyy")

    EditDeleteHeadCard(
        title = {
            CardTitleText(
                text = "${client.lastName} ${client.firstName.first()}.${client.patronymic.first()}.",
                rowScope = this,
                textModifier = {
                    it.padding(start = 4.dp)
                }
            )
        },
        editBtnClick = {
            editClient(client)
        },
        deleteBtnClick = {
            deleteClient(client)
        },
        cardModifier = Modifier.padding(4.dp)
    ){
        Column(
            modifier = Modifier.padding(8.dp).wrapContentWidth()
        ) {
            LabelText(
                label = "Пол: ",
                text = client.gender.gender(),
                style = MaterialTheme.typography.bodyLarge
            )
            LabelText(
                label = "Дата рождения: ",
                text = dateFormat.format(client.birthday),
                style = MaterialTheme.typography.bodyLarge
            )
            LabelText(
                label = "Тип документа: ",
                text = "Паспорт",
                style = MaterialTheme.typography.bodyLarge
            )
            LabelText(
                label = "Номер паспорта: ",
                text = client.passportNumber,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}