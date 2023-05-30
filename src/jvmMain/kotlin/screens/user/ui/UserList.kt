package screens.user.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.users.model.UserLocal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersList(
    usersList: List<UserLocal>,
    editUser: (UserLocal) -> Unit = {},
    deleteUser: (UserLocal) -> Unit = {}
){
    LazyVerticalStaggeredGrid(
        StaggeredGridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(2.dp)
    ){
        items(usersList){
            UserCard(
                userLocal = it,
                editClick = editUser,
                deleteClick = deleteUser
            )
        }
    }
}

