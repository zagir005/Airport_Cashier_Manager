package data.client.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.text.SimpleDateFormat
import java.util.*

class ClientLocal: RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var firstName: String = ""
    var lastName: String = ""
    var patronymic: String = ""
    var passportNumber: String = ""
    var birthday: Long = Date().time
    var gender: Boolean = false

    companion object {
        fun generateClients(count: Int): RealmList<ClientLocal> {
            val clients = realmListOf<ClientLocal>()
            val random = Random()
            val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            for (i in 1..count) {
                val client = ClientLocal()
                client.firstName = listOf("Алексей", "Мария", "Иван", "Любовь", "Михаил", "Елизавета","Милена","Ирина").random()
                client.lastName = listOf("Иванов", "Сидоров", "Петров", "Кузнецов", "Соколов", "Сергеева","Миронова").random()
                client.patronymic = listOf("Александрович", "Дмитриевна", "Иванович", "Михайловна", "Петрович","Алексеевич", "Михайлович").random()
                client.passportNumber = (100_000_000 + random.nextInt(900_000_000)).toString()
                client.gender = random.nextBoolean()

                val birthdate = Calendar.getInstance()
                birthdate.set(Calendar.YEAR, 1950 + random.nextInt(50))
                birthdate.set(Calendar.MONTH, random.nextInt(12))
                birthdate.set(Calendar.DAY_OF_MONTH, random.nextInt(28) + 1)
                client.birthday = birthdate.timeInMillis

                clients.add(client)
            }

            return clients
        }
    }
}