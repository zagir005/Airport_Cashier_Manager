package screens.tickets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object TicketsTabScreen: Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Билеты",
            icon = painterResource("ticket.svg")
        )

    @Composable
    override fun Content() {
        TicketsScreen()
    }

}

@Composable
fun TicketsScreen(){
    Text("Ticket")
}