package screens.planes.list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.plane.model.PlaneLocal
import ui.SaveOrCancelButtons
import ui.errorIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaneEditDialog(
    plane: PlaneLocal?,
    planeDialogIsOpen: Boolean,
    update: (PlaneLocal) -> Unit = {},
    add: (PlaneLocal) -> Unit = {},
    onClose: () -> Unit
){
    val isNewPlane = plane == null

    Dialog(
        visible = planeDialogIsOpen,
        onCloseRequest = {
            onClose()
        },
        title = if (isNewPlane) "Добавить новый самолет" else "Редактировать самолет ${plane!!.codeName}"
    ){
        val editablePlane = if (isNewPlane) PlaneLocal() else PlaneLocal().apply {
            this.id = plane!!.id
            this.name = plane.name
            this.codeName = plane.codeName
        }

        var codeName by remember{ mutableStateOf(editablePlane.codeName) }
        var name by remember { mutableStateOf(editablePlane.name) }

        var codeNameFieldError by remember{ mutableStateOf(false) }
        var nameFieldError by remember{ mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
        ) {
            TextField(
                value = codeName,
                onValueChange = {
                    codeName = it
                },
                label = {
                    Text("Код самолета")
                },
                supportingText = {
                    if (codeNameFieldError){
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (codeNameFieldError){
                        errorIcon()
                    }
                },
                isError = codeNameFieldError,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Название самолета")
                },
                supportingText = {
                    if (nameFieldError){
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (nameFieldError){
                        errorIcon()
                    }
                },
                isError = nameFieldError,
                modifier = Modifier.fillMaxWidth()
            )
            SaveOrCancelButtons(
                cancelBtnClick = {
                    onClose()
                },
                saveBtnClick = {
                    nameFieldError = name.isEmpty()
                    codeNameFieldError = codeName.isEmpty()

                    if (!nameFieldError && !codeNameFieldError){
                        editablePlane.apply {
                            this.name = name
                            this.codeName = codeName
                        }

                        if (isNewPlane)
                            add(editablePlane)
                        else
                            update(editablePlane)

                        onClose()
                    }

                }
            )
        }

    }
}