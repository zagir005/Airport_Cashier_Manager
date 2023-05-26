package screens.planes.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.AppDatabase
import data.plane.datasourceimpl.PlaneLocalCrudDataSourceImpl
import org.mongodb.kbson.ObjectId
import screens.planes.details.ui.PlaneDetails
import ui.*

class PlaneDetailScreen(
    private val planeId: ObjectId
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val planeDetailsModel = PlaneDetailsModel(
            PlaneLocalCrudDataSourceImpl(AppDatabase()),
            planeId
        )

        val screenState by planeDetailsModel.state.collectAsState()

        when (val state = screenState) {
            PlaneDetailsState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            is PlaneDetailsState.PlaneIsLoaded -> {
                TopPanel(
                    navBackBtnClick = {
                        navigator.pop()
                    },
                    title = {
                        Text(
                            "Самолет - ${state.planeLocal.codeName}"
                        )
                    }
                ) {
                    PlaneDetails(state.planeLocal, planeDetailsModel)
                }
            }
        }
    }
}




