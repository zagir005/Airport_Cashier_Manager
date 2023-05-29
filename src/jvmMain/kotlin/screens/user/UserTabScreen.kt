package screens.user

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import data.users.model.UserLocal

class UserTabScreen(
    val user: UserLocal
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
        Text(user.firstName)
    }

}