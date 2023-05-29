package screens.planes.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.plane.model.PlaneLocal
import screens.planes.details.PlaneDetailsModel
import screens.planes.model.PlaneSeatCategory

@Composable
fun PlaneDetails(planeLocal: PlaneLocal, planeDetailsModel: PlaneDetailsModel) {
    var editSeatCategoryDialogIsOpen by remember { mutableStateOf(false) }
    var editableSeatCategory by remember { mutableStateOf(PlaneSeatCategory(planeLocal.id)) }

    if (editSeatCategoryDialogIsOpen){
        EditSeatsCategoryDialog(
            seatCategory = editableSeatCategory,
            isOpen = editSeatCategoryDialogIsOpen,
            plane = planeLocal,
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
        modifier = Modifier.padding(4.dp)
    ){
        PlaneInfoCard(planeLocal)
        Spacer(modifier = Modifier.height(4.dp))
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