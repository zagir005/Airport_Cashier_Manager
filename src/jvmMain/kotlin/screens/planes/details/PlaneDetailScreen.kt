package screens.planes.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.AppDatabase
import data.plane.datasourceimpl.PlaneLocalCrudDataSourceImpl
import data.plane.model.PlaneLocal
import org.mongodb.kbson.ObjectId
import screens.planes.details.ui.EditSeatsCategoryDialog
import screens.planes.details.ui.PlaneInfoCard
import screens.planes.details.ui.PlaneSeatsCard
import screens.planes.model.PlaneSeatCategory
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
                CircularProgressIndicator()
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

@Composable
fun PlaneDetails(planeLocal: PlaneLocal, planeDetailsModel: PlaneDetailsModel) {
    var editSeatCategoryDialogIsOpen by remember { mutableStateOf(false) }
    var editableSeatCategory by remember { mutableStateOf(PlaneSeatCategory(planeLocal.id)) }

    if (editSeatCategoryDialogIsOpen){
        EditSeatsCategoryDialog(
            seatCategory = editableSeatCategory,
            isOpen = editSeatCategoryDialogIsOpen,
            addSeatCategory = {
                planeDetailsModel.addSeatCategory(it)
                editSeatCategoryDialogIsOpen = false
                editableSeatCategory = PlaneSeatCategory(planeLocal.id)
            },
            updateSeatCategory = {
                planeDetailsModel.updatePlaneSeat(it)
                editSeatCategoryDialogIsOpen = false
                editableSeatCategory = PlaneSeatCategory(planeLocal.id)
            },
            onClose = {
                editSeatCategoryDialogIsOpen = false
                editableSeatCategory = PlaneSeatCategory(planeLocal.id)
            }
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PlaneInfoCard(planeLocal)
        PlaneSeatsCard(
            planeLocal = planeLocal,
            seatsCategoriesList = PlaneDetailsModel.getPlaneSeatsCategories(planeLocal),
            addSeatsCategory = {
                editSeatCategoryDialogIsOpen = true
            },
            editSeatsCategory = {
                editableSeatCategory = it
                editSeatCategoryDialogIsOpen = true
            },
            removeSeatsCategory = {
                planeDetailsModel.removePlaneSeats(it)
            }
        )
    }
}



