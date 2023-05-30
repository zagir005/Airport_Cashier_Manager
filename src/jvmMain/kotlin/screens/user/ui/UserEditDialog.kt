package screens.user.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.users.model.UserLocal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.LabelText
import ui.SaveOrCancelButtons
import ui.icon.errorIcon
import java.awt.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEditDialog(
    userLocal: UserLocal?,
    usersList: List<UserLocal>,
    isOpen: Boolean,
    update: (UserLocal) -> Unit = {},
    add: (UserLocal) -> Unit = {},
    onClose: () -> Unit,
) {
    val isNewUser = userLocal == null

    Dialog(
        visible = isOpen,
        onCloseRequest = { onClose() },
        title = if (isNewUser) "Добавить нового пользователя" else "Редактировать пользователя ${userLocal?.userLogin}"
    ) {
        this.window.minimumSize = Dimension(400,530)
        val editableUser = if (isNewUser) {
            UserLocal()
        } else {
            UserLocal().apply{
                this.id = userLocal!!.id
                this.userLogin = userLocal.userLogin
                this.firstName = userLocal.firstName
                this.lastName = userLocal.lastName
                this.patronymic = userLocal.patronymic
                this.password = userLocal.password
                this.isAdmin = userLocal.isAdmin
            }
        }

        var userLogin by remember { mutableStateOf(editableUser.userLogin) }
        var firstName by remember { mutableStateOf(editableUser.firstName) }
        var lastName by remember { mutableStateOf(editableUser.lastName) }
        var patronymic by remember { mutableStateOf(editableUser.patronymic) }
        var password by remember { mutableStateOf(editableUser.password) }
        var isAdmin by remember { mutableStateOf(editableUser.isAdmin) }

        var userLoginIsEmptyError by remember { mutableStateOf(false) }
        var firstNameIsEmptyError by remember { mutableStateOf(false) }
        var lastNameIsEmptyError by remember { mutableStateOf(false) }
        var passwordIsEmptyError by remember { mutableStateOf(false) }
        var duplicateUserError by remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = userLogin,
                onValueChange = { userLogin = it },
                label = { Text("Логин") },
                supportingText = {
                    if (userLoginIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (userLoginIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = userLoginIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Имя") },
                supportingText = {
                    if (firstNameIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (firstNameIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = firstNameIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Фамилия") },
                supportingText = {
                    if (lastNameIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (lastNameIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = lastNameIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = patronymic,
                onValueChange = { patronymic = it },
                label = { Text("Отчество") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = {Text("Пароль") },
                supportingText = {
                    if (passwordIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (passwordIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = passwordIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.Center
            ){
                Checkbox(
                    checked = isAdmin,
                    onCheckedChange = { isAdmin = it },
                    modifier = Modifier.padding(2.dp)
                )
                LabelText(
                    "",
                    "Администратор",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            var snackbarShowState by remember { mutableStateOf(false) }
            val coroutineScope = rememberCoroutineScope()

            if (duplicateUserError){
                AnimatedVisibility(
                    snackbarShowState
                ){
                    Snackbar{
                        Text("Пользователь с таким логином уже существует")
                        coroutineScope.launch {
                            delay(2000)
                            snackbarShowState = false
                        }
                    }
                }
            }

            SaveOrCancelButtons(
                cancelBtnClick = { onClose() },
                saveBtnClick = {
                    userLoginIsEmptyError = userLogin.isEmpty()
                    firstNameIsEmptyError = firstName.isEmpty()
                    lastNameIsEmptyError = lastName.isEmpty()
                    passwordIsEmptyError = password.isEmpty()
                    duplicateUserError = usersList.find {
                        it.userLogin == userLogin
                    } != null
                    snackbarShowState = duplicateUserError

                    if (!userLoginIsEmptyError && !firstNameIsEmptyError && !lastNameIsEmptyError && !passwordIsEmptyError && !duplicateUserError) {
                        editableUser.apply {
                            this.userLogin = userLogin
                            this.firstName = firstName
                            this.lastName = lastName
                            this.patronymic = patronymic
                            this.password = password
                            this.isAdmin = isAdmin
                        }

                        if (isNewUser) {
                            add(editableUser)
                        } else {
                            update(editableUser)
                        }

                        onClose()
                    }
                }
            )
        }
    }
}