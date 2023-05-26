import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import screens.account.AccountTabScreen
import screens.airports.AirportsTabScreen
import screens.clients.ClientsTabScreen
import screens.flights.FlightsTabScreen
import screens.planes.PlanesTab
import screens.reports.ReportsTabScreen
import screens.tickets.TicketsTabScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication
    ) {
        val tabItems = listOf(
            AccountTabScreen,
            AirportsTabScreen,
            ClientsTabScreen,
            FlightsTabScreen,
            PlanesTab,
            TicketsTabScreen,
            ReportsTabScreen
        )

        TabNavigator(AirportsTabScreen) { tabNavigator ->
            Row {
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