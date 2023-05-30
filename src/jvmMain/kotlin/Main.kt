
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
import androidx.compose.ui.unit.dp
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
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 500.dp, height = 600.dp)
    ) {


        var auth by remember { mutableStateOf(true) }
        var user by remember { mutableStateOf<UserLocal?>(null) }

        if (auth){
            UserAuthScreen {
                user = it
                auth = false
            }
            window.size = Dimension(500, 600)
        } else {
            MainScreen(user!!) {
                user = null
                auth = true
            }
            window.size = Dimension(1000, 700)
        }
    }
}

@Composable
fun MainScreen(user: UserLocal, exit: () -> Unit) {
    val tabItems = if (user.isAdmin){
        listOf(
            UserTabScreen(user, exit = exit),
            TicketsTabScreen,
            FlightsTabScreen(true),
            ClientsTabScreen,
            AirportsTabScreen,
            PlanesTabScreen,
        )
    }else{
        listOf(
            UserTabScreen(user, exit = exit),
            TicketsTabScreen,
            FlightsTabScreen(false),
            ClientsTabScreen,
        )
    }


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