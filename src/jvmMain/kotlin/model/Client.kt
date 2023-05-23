package model

import java.util.*

data class Client(
    var firstName: String,
    var lastName: String,
    var patronymic: String,
    var passportNumber: String,
    var birthday: Date,
    var gender: Boolean,
    val id: Int = Random().nextInt()
){
    companion object{
        fun getEmptyInstance(): Client{
            return Client(
                "","","","",Date(1),true
            )
        }
    }
}

