package screens.airports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import domain.model.Airport
import ui.LabelText
import ui.StandardContextMenu
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
        AirportScreen()
    }

}
@Composable
fun AirportScreen(){
    Column {
        ToolPanel()
        Divider(
            modifier = Modifier.padding(2.dp),
            thickness = 2.dp
        )
        LazyVerticalGrid(
            GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(4.dp)
        ){
            items(emptyList<Airport>()){
                AirportCard(it)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirportCard(
    airport: Airport,
    redactAirport: (Airport) -> Unit = {},
    deleteAirport: (Airport) -> Unit = {},
    planeClick: (Airport) -> Unit = {}
){
    StandardContextMenu(
        deleteClick = {
            deleteAirport(airport)
        },
        editClick = {
            redactAirport(airport)
        }
    ){
        Card(
            onClick = {
//                planeClick()
            },
            modifier = Modifier.heightIn(120.dp,500.dp).padding(4.dp)
        ) {
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    LabelText("Код аэропорта: ", airport.codeName, style = MaterialTheme.typography.bodyLarge)
                    LabelText("Название аэропорта: ", airport.name, style = MaterialTheme.typography.bodyLarge)
                    LabelText("Город: ", airport.city, style = MaterialTheme.typography.bodyLarge)

                }
            }
        }
    }
}