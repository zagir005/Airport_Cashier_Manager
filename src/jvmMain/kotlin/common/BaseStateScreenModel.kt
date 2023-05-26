package common

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseStateScreenModel<S>(initialState: S): StateScreenModel<S>(initialState) {

    internal fun updateState(state: S){
        mutableState.value = state
    }
    internal fun launchIOCoroutine(block: suspend CoroutineScope.() -> Unit){
        coroutineScope.launch(Dispatchers.IO) {
            block(this)
        }
    }
}