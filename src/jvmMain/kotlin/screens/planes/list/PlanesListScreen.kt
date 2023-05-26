package screens.planes.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        Column {
            ToolPanel(
                searchCall = {
                    screenModel.planeSearch(it)
                },
                addBtnClick = {
                    editablePlane = null
                    showPlaneEditDialog = true
                }
            )
            Divider(
                modifier = Modifier.padding(2.dp),
                thickness = 2.dp
            )
            when (val state = screenState.value) {
                PlaneScreenState.Loading -> {
                    CircularProgressIndicator()
                }

                is PlaneScreenState.PlanesListLoaded -> {
                    PlanesList(
                        planeList = state.planeList,
                        seatCategoriesList = PlaneScreenModel.getCategoriesOfPlanes(state.planeList),
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
