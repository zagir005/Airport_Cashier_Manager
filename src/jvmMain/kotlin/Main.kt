
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import data.users.model.UserLocal
import screens.airports.AirportsTabScreen
import screens.auth.UserAuthScreen
import screens.clients.ClientsTabScreen
import screens.flights.FlightsTabScreen
import screens.planes.PlanesTabScreen
import screens.tickets.TicketsTabScreen
import screens.user.UserTabScreen
import java.awt.Dimension

fun main() = application {
    var windowState = rememberWindowState(width = Dp.Unspecified, height = Dp.Unspecified)
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState
    ) {

        var user by remember { mutableStateOf<UserLocal?>(null) }

        if (user != null) {
            MainScreen(user!!)
            window.size = Dimension(1000, 700)
        } else {
            UserAuthScreen {
                user = it
            }
        }
    }
}

@Composable
fun MainScreen(user: UserLocal) {
    val tabItems = listOf(
        UserTabScreen(user),
        TicketsTabScreen,
        FlightsTabScreen,
        ClientsTabScreen,
        AirportsTabScreen,
        PlanesTabScreen,
    )

    TabNavigator(TicketsTabScreen) { tabNavigator ->
        Row{
            NavigationRail {
                tabItems.map {
                    TabNavigationRailItem(it)
                }
            }
            Surface(modifier = Modifier.weight(1f)) {
                tabNavigator.current.Content()
            }
        }
    }
}
@Composable
fun TabNavigationRailItem(tabScreen: Tab){
    val tabNavigator = LocalTabNavigator.current
    NavigationRailItem(
        selected = tabNavigator.current.key == tabScreen.key,
        icon = {
            Icon(
                tabScreen.options.icon!!,
                "",
                modifier = Modifier
                    .height(Icons.Default.Search.defaultHeight)
                    .width(Icons.Default.Search.defaultWidth)
            )
        },
        onClick = {
            tabNavigator.current = tabScreen
        }
    )
}