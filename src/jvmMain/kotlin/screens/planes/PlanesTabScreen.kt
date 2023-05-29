package screens.planes

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import data.AppDatabase
import data.plane.datasourceimpl.PlaneLocalCrudDataSourceImpl
import screens.planes.list.PlaneScreenModel
import screens.planes.list.PlanesListScreen

object PlanesTabScreen: Tab{
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Самолеты",
            icon = painterResource("plane.svg")
        )
    @Composable
    override fun Content() {
        val planeScreenModel = rememberScreenModel {
            PlaneScreenModel(
                PlaneLocalCrudDataSourceImpl(
                    AppDatabase()
                )
            )
        }

        Navigator(PlanesListScreen(planeScreenModel)){
            CurrentScreen()
        }
    }
}