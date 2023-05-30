package screens.user.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.users.model.UserLocal
import ui.LabelText
import ui.card.CardTitleText
import ui.card.head.ElevatedEditDeleteHeadCard

@Composable
fun UserCard(
    userLocal: UserLocal,
    editClick: (UserLocal) -> Unit,
    deleteClick: (UserLocal) -> Unit,
) {
    ElevatedEditDeleteHeadCard(
        title = {
            CardTitleText("Пользователь: ${userLocal.userLogin}",this)
        },
        editBtnClick = {
            editClick(userLocal)
        },
        deleteBtnClick = {
            deleteClick(userLocal)
        },
        cardModifier = Modifier.padding(6.dp)
    ){
        LabelText(
            label = "ФИО: ",
            text = "${userLocal.firstName} ${userLocal.firstName} ${userLocal.patronymic}"
        )
        Spacer(modifier = Modifier.height(4.dp))
        LabelText(
            label = "Тип пользователя: ",
            text = if(userLocal.isAdmin) "Администратор" else "Кассир"
        )
    }
}