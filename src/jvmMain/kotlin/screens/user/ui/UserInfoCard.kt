package screens.user.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.users.model.UserLocal
import ui.LabelText
import ui.card.CardTitleText
import ui.card.HeadCardPanel

@Composable
fun UserInfoCard(
    userLocal: UserLocal,
    editClick: (UserLocal) -> Unit,
    exitClick: () -> Unit
) {
    Column {
        Card(
            modifier = Modifier.padding(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                HeadCardPanel(
                    title ={
                        CardTitleText(
                            text = "Пользователь: ${userLocal.userLogin}",
                            rowScope = this
                        )
                    },
                    firstBtn = {
                        IconButton(
                            onClick = {
                                editClick(userLocal)
                            }
                        ){
                            Icon(
                                Icons.Default.Edit, ""
                            )
                        }
                    },
                    secondBtn = {
                        IconButton(
                            onClick = {
                                exitClick()
                            }
                        ){
                            Icon(
                                Icons.Default.ExitToApp, ""
                            )
                        }
                    }
                )

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
        Spacer(
            modifier = Modifier.padding(4.dp)
        )
    }
}