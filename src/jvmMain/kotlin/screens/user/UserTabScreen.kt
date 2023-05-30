package screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import data.AppDatabase
import data.users.datasourceimpl.UsersLocalCrudDataSourceImpl
import data.users.model.UserLocal
import screens.user.ui.UserEditDialog
import screens.user.ui.UserInfoCard
import screens.user.ui.UsersList
import ui.card.CardTitleText
import ui.card.HeadCardPanel

class UserTabScreen(
    private val user: UserLocal,
    private val exit: () -> Unit
): Tab{
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Профиль",
            icon = painterResource("accountsettings.svg")
        )
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { UserScreenModel(UsersLocalCrudDataSourceImpl(AppDatabase())) }
        val screenState by screenModel.state.collectAsState()

        LaunchedEffect(Unit){
            screenModel.loadUsers()
        }

        var isUserEditDialogIsOpen by remember { mutableStateOf(false) }
        var editableUserLocal by remember { mutableStateOf<UserLocal?>(null) }

        if (isUserEditDialogIsOpen && screenState is UserScreenState.UsersListLoaded){
            val usersList = (screenState as UserScreenState.UsersListLoaded).usersList
            UserEditDialog(
                isOpen = isUserEditDialogIsOpen,
                userLocal = editableUserLocal,
                usersList = usersList,
                update = {
                    screenModel.updateUser(it)
                },
                add = {
                    screenModel.addUser(it)
                },
                onClose = {
                    isUserEditDialogIsOpen = false
                    editableUserLocal = null
                }
            )
        }

        Column {
            when(val state = screenState){
                UserScreenState.Loading -> {

                }
                is UserScreenState.UsersListLoaded -> {
                    UserInfoCard(
                        user,
                        editClick = {
                            editableUserLocal = it
                            isUserEditDialogIsOpen = true
                        },
                        exitClick = {
                            exit()
                        }
                    )

                    Card(
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            HeadCardPanel(
                                title = {
                                    CardTitleText(
                                        text = "Список пользователей",
                                        rowScope = this
                                    )
                                },
                                firstBtn = {
                                    IconButton(
                                        onClick = {
                                            editableUserLocal = null
                                            isUserEditDialogIsOpen = true
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.Add, ""
                                        )
                                    }
                                }
                            )
                            UsersList(
                                usersList = state.usersList.filter {
                                    it.id != user.id
                                },
                                editUser = {
                                    editableUserLocal = it
                                    isUserEditDialogIsOpen = true
                                },
                                deleteUser = {
                                    screenModel.deleteUser(it)
                                }
                            )
                        }
                    }
                }
            }

        }

    }
}