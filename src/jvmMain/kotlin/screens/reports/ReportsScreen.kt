package screens.reports

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object ReportsTabScreen: Tab{
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Отчеты",
            icon = painterResource("report.svg")
        )
    @Composable
    override fun Content() {
        Text("Отчеты")
    }

}