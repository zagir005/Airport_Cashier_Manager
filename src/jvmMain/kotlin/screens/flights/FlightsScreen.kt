package screens.flights

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
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
        FlightScreen()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightScreen(){
    Column {
        ToolPanel()
        Divider(
            modifier = Modifier.padding(2.dp),
            thickness = 2.dp
        )
        LazyColumn{
            items(10) {
                Card(
                    modifier = Modifier.padding(2.dp),
                    onClick = {

                    }
                ){
                    FlightCard()
                }
            }
        }
    }
}

@Composable
fun FlightCard(){
    Row(
        modifier = Modifier.padding(8.dp).height(IntrinsicSize.Min)
    ){
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text("NORDWIND")
            Text("N4 848")
            Text("В767-300")
        }
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text("Кол-во мест: 200")
            Text("Бронь: 30")
        }
        Divider(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp).fillMaxHeight().width(1.dp),
            color = Color.Black
        )
        Row(
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(4.dp)
            ) {
                Text("МХЧ")
                Text("Махачкала")
                Text("Шереметьево")
                Text("08.09.2023\n12:00",textAlign = TextAlign.Center)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(4.dp)
            ) {
                Image(painterResource("icon _airplane_.svg"),"", modifier = Modifier.width(100.dp).height(120.dp))
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(4.dp)
            ) {
                Text("МХЧ")
                Text("Махачкала")
                Text("Шереметьево")
                Text("08.09.2023\n12:00", textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview
@Composable
fun PreviewFlightCard(){
    MaterialTheme{
        FlightCard()
    }
}
@Preview
@Composable
fun PreviewFlightsScreen(){
    MaterialTheme{
        FlightScreen()
    }
}