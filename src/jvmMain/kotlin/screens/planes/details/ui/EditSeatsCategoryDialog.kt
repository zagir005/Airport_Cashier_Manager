package screens.planes.details.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.plane.model.PlaneLocal
import screens.planes.model.PlaneSeatCategory
import ui.*
import ui.icon.errorIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSeatsCategoryDialog(
    seatCategory: PlaneSeatCategory,
    plane: PlaneLocal,
    isOpen: Boolean,
    addSeatCategory: (PlaneSeatCategory) -> Unit,
    updateSeatCategory: (PlaneSeatCategory) -> Unit,
    onClose: () -> Unit
) {
    val isNewCategory = seatCategory.categoryName.isEmpty()

    Dialog(
        visible = isOpen,
        onCloseRequest = {
            onClose()
        },
        title = if (isNewCategory) "Добавить новый тариф" else "Редактировать тариф ${seatCategory.categoryName}"
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(4.dp)
        ) {
            var seatCategoryName by remember{ mutableStateOf(seatCategory.categoryName) }
            var seatCategoryBaggageWeight by remember{ mutableStateOf(seatCategory.baggageWeight.toString()) }
            var seatCategoryCount by remember{ mutableStateOf(seatCategory.count.toString()) }

            var categoryNameIsEmptyError by remember{ mutableStateOf(false) }
            var categoryNameDuplicateError by remember{ mutableStateOf(false) }
            var categoryBaggageWeightEmptyError by remember{ mutableStateOf(false) }
            var categoryCountEmptyError by remember{ mutableStateOf(false) }

            var categoryBaggageWeightInputError by remember{ mutableStateOf(false) }
            var categoryCountInputError by remember{ mutableStateOf(false) }

            TextField(
                value = seatCategoryName,
                onValueChange = {
                    seatCategoryName = it
                },
                label = {
                    Text("Название категории")
                },
                supportingText = {
                    if (categoryNameIsEmptyError){
                        Text("Поле должно быть заполнено")
                    }else if (categoryNameDuplicateError){
                        Text("Дублирование категорий")
                    }
                },
                trailingIcon = {
                    if (categoryNameIsEmptyError || categoryNameDuplicateError){
                        errorIcon()
                    }
                },
                isError = categoryNameIsEmptyError || categoryNameDuplicateError,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = seatCategoryCount,
                onValueChange = {
                    seatCategoryCount = it
                },
                label = {
                    Text("Количество мест")
                },
                supportingText = {
                    if (categoryCountEmptyError){
                        Text("Поле должно быть заполнено")
                    }else{
                        if(categoryCountInputError){
                            Text("Некорректный ввод")
                        }
                    }
                },
                trailingIcon = {
                    if (categoryCountInputError){
                        errorIcon()
                    }
                },
                isError = categoryCountInputError || categoryCountEmptyError,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = seatCategoryBaggageWeight,
                onValueChange = {
                    seatCategoryBaggageWeight = it
                },
                label = {
                    Text("Допустимый вес багажа")
                },
                supportingText = {
                    if (categoryBaggageWeightEmptyError){
                        Text("Поле должно быть заполнено")
                    }else{
                        if(categoryBaggageWeightInputError){
                            Text("Некорректный ввод")
                        }
                    }
                },
                trailingIcon = {
                    if (categoryNameIsEmptyError){
                        errorIcon()
                    }
                },
                isError = categoryBaggageWeightEmptyError || categoryBaggageWeightInputError,
                modifier = Modifier.fillMaxWidth()
            )

            SaveOrCancelButtons(
                cancelBtnClick = {
                    onClose()
                },
                saveBtnClick = {
                    categoryNameIsEmptyError = seatCategoryName.isEmpty()
                    categoryBaggageWeightEmptyError = seatCategoryBaggageWeight.isEmpty()
                    categoryCountEmptyError = seatCategoryCount.isEmpty()
                    categoryBaggageWeightInputError = seatCategoryBaggageWeight.toIntOrNull() == null
                    categoryCountInputError = seatCategoryCount.toIntOrNull() == null ||
                            seatCategoryCount.toIntOrNull()?.equals(0) == true
                    categoryNameDuplicateError = plane.seats.find {
                        it.categoryName == seatCategoryName
                    } != null


                    if (
                        !categoryNameIsEmptyError && !categoryCountEmptyError && !categoryCountInputError &&
                        !categoryBaggageWeightEmptyError && !categoryBaggageWeightInputError && !categoryNameDuplicateError
                    ){
                        if(isNewCategory){
                            addSeatCategory(
                                PlaneSeatCategory(
                                    planeId =  seatCategory.planeId,
                                    categoryName = seatCategoryName,
                                    count = seatCategoryCount.toInt(),
                                    baggageWeight = seatCategoryBaggageWeight.toInt()
                                )
                            )
                        }else{
                            updateSeatCategory(
                                PlaneSeatCategory(
                                    planeId =  seatCategory.planeId,
                                    seatId = seatCategory.seatId,
                                    categoryName = seatCategoryName,
                                    count = seatCategoryCount.toInt(),
                                    baggageWeight = seatCategoryBaggageWeight.toInt()
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}