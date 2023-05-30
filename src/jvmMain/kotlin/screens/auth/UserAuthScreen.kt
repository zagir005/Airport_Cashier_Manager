package screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import data.AppDatabase
import data.users.datasourceimpl.UsersLocalCrudDataSourceImpl
import data.users.model.UserAuth
import data.users.model.UserLocal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.icon.errorIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAuthScreen(
    onAuth: (UserLocal) -> Unit
) {
    val userDataSource = UsersLocalCrudDataSourceImpl(AppDatabase())
    val coroutineScope = rememberCoroutineScope()

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var loginIsEmptyError by remember { mutableStateOf(false) }
    var passwordIsEmptyError by remember { mutableStateOf(false) }

    var snackbarShowState by remember { mutableStateOf(false) }

    Box{
        Column(
            modifier = Modifier.width(500.dp).padding(18.dp).height(500.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Авторизация", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(60.dp))

            TextField(
                value = login,
                onValueChange = {
                    login = it
                },
                label = {
                    Text("Логин")
                },
                supportingText = {
                    if (loginIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (loginIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = loginIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text("Пароль")
                },
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
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                onClick = {
                    coroutineScope.launch {
                        loginIsEmptyError = login.isEmpty()
                        passwordIsEmptyError = password.isEmpty()
                        if (!loginIsEmptyError && !passwordIsEmptyError){
                            val authResult = userDataSource.authUser(
                                UserAuth().apply {
                                    this.login = login
                                    this.password = password
                                }
                            )
                            if (authResult != null){
                                onAuth(authResult)
                            }else{
                                snackbarShowState = true
                            }
                        }
                    }
                }
            ){
                Text("Войти")
            }
        }

        AnimatedVisibility(
            snackbarShowState,
            modifier = Modifier.align(Alignment.BottomCenter)
        ){
            Snackbar{
                Text("Такого пользователя не существует")
                coroutineScope.launch {
                    delay(2000)
                    snackbarShowState = false
                }
            }
        }
    }
}