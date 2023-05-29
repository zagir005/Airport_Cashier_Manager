package data.users.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserLocal: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var userLogin: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var patronymic: String = ""
    var password: String = ""
    var isAdmin: Boolean = false
}