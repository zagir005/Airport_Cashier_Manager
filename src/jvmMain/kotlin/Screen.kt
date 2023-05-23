
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val name: String, val route: String?, val icon: ImageVector?, val iconPath: String? ) {
    object Planes: Screen(
        "Самолеты", null, null, "plane.svg"
    )
    object Flights: Screen(
        "Рейсы", null, null, "flight.svg"
    )
    object Clients: Screen(
        "Клиенты", null, Icons.Default.Person, null
    )
    object Report: Screen(
        "Отчеты", null, null, "report.svg"
    )
    object Ticket: Screen(
        "Билеты", null, null, "ticket.svg"
    )
    object Airport: Screen(
        "Аэропорты", null, null, "airport.svg"
    )
}