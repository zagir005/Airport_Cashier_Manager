package screens.planes.list

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.plane.model.PlaneLocal
import screens.planes.details.PlaneDetailScreen
import screens.planes.list.ui.PlaneEditDialog
import screens.planes.list.ui.PlanesList
import ui.ToolPanel

class PlanesListScreen(
    private val screenModel: PlaneScreenModel
): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenState = screenModel.state.collectAsState()

        LaunchedEffect(Unit){
            screenModel.loadPlanes()
        }

        var showPlaneEditDialog by remember { mutableStateOf(false) }
        var editablePlane by remember { mutableStateOf<PlaneLocal?>(null) }

        if (showPlaneEditDialog) {
            PlaneEditDialog(
                editablePlane,
                showPlaneEditDialog,
                update = {
                    screenModel.updatePlane(it)
                },
                add = {
                    screenModel.addPlane(it)
                },
                onClose = {
                    showPlaneEditDialog = false
                }
            )
        }

        val scrollStateRemember = rememberScrollState()
        Column(
            modifier = Modifier.scrollable(scrollStateRemember,Orientation.Vertical)
        ) {
            ToolPanel(
                searchCall = {
                    screenModel.planeSearch(it)
                },
                addBtnClick = {
                    editablePlane = null
                    showPlaneEditDialog = true
                }
            )

            when (val state = screenState.value) {
                PlaneScreenState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is PlaneScreenState.PlanesListLoaded -> {
                    PlanesList(
                        planeList = state.planeList,
                        editPlane = {
                            editablePlane = it
                            showPlaneEditDialog = true
                        },
                        deletePlane = {
                            screenModel.deletePlane(it)
                        },
                        planeClick = {
                            navigator.push(PlaneDetailScreen(it.id))
                        }
                    )
                }
            }
        }
    }
}
