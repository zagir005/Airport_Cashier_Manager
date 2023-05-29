package screens.flights

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
import data.flight.datasourceimpl.FlightLocalCrudDataSourceImpl
import data.flight.model.FlightLocal
import screens.flights.ui.FlightEditDialog
import screens.flights.ui.FlightsList
import ui.ToolPanel

object FlightsTabScreen: Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Рейсы",
            icon = painterResource("flight.svg")
        )

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { FlightScreenModel(FlightLocalCrudDataSourceImpl(AppDatabase())) }
        val screenState by screenModel.state.collectAsState()

        LaunchedEffect(Unit){
            screenModel.loadFlights()
        }

        var flightEditDialogIsOpen by remember { mutableStateOf(false) }
        var editableFlight by remember { mutableStateOf<FlightLocal?>(null) }

        if (flightEditDialogIsOpen && screenState is FlightScreenState.FlightListLoaded){
            val state = (screenState as FlightScreenState.FlightListLoaded)
            FlightEditDialog(
                flightLocal =  editableFlight,
                planeList =  state.planeList,
                airportList =  state.airportList,
                isOpen = flightEditDialogIsOpen,
                update = {
                    screenModel.updateFlight(it)
                },
                add = {
                    screenModel.addFlight(it)
                },
                onClose = {
                    flightEditDialogIsOpen = false
                    editableFlight = null
                    screenModel.loadFlights()
                }
            )
        }

        Column {
            ToolPanel(
                searchCall = {
                    screenModel.flightSearch(it)
                },
                filterBtnClick = {

                },
                addBtnClick = {
                    println("ADD CALL")
                    editableFlight = null
                    flightEditDialogIsOpen = true
                }
            )
            when(val state = screenState){
                FlightScreenState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                is FlightScreenState.FlightListLoaded -> {
                    FlightsList(
                        state.flightList,
                        editFlight = {
                            editableFlight = it
                            flightEditDialogIsOpen = true
                        },
                        deleteFlight = {
                            screenModel.removeFlights(it.id)
                        },
                        onClickFlight = {

                        }
                    )
                }
            }

        }
    }

}
