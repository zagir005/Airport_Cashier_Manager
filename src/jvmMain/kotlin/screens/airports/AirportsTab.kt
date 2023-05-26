package screens.airports

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import data.AppDatabase
import data.airport.datasoureimpl.AirportLocalCrudDataSourceImpl
import data.airport.model.AirportLocal
import screens.airports.ui.AirportEditDialog
import screens.airports.ui.AirportsList
import ui.ToolPanel

object AirportsTabScreen: Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            "Аэропорты",
            icon = painterResource("airport.svg")
        )

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { AirportScreenModel(AirportLocalCrudDataSourceImpl(AppDatabase())) }
        val screenState by screenModel.state.collectAsState()

        var isAirportDialogIsOpen by remember { mutableStateOf(false) }
        var editableAirportLocal by remember { mutableStateOf<AirportLocal?>(null) }

        if (isAirportDialogIsOpen){
            AirportEditDialog(
                isOpen = isAirportDialogIsOpen,
                airportLocal = editableAirportLocal,
                update = {
                    screenModel.updateAirport(it)
                },
                add = {
                    screenModel.addAirport(it)
                },
                onClose = {
                    isAirportDialogIsOpen = false
                    editableAirportLocal = null
                }
            )
        }

        Column {
            ToolPanel(
                searchCall = {
                    screenModel.airportSearch(it)
                },
                filterBtnClick = {

                },
                addBtnClick = {
                    isAirportDialogIsOpen = true
                }
            )

            when(val state = screenState){
                AirportScreenState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                is AirportScreenState.AirportsListLoaded -> {
                    AirportsList(
                        state.list,
                        deleteAirport = {
                            println("del $it")
                            screenModel.deleteAirport(it)
                        },
                        editAirport = {
                            editableAirportLocal = it
                            isAirportDialogIsOpen = true
                        }
                    )
                }
            }
        }
    }
}