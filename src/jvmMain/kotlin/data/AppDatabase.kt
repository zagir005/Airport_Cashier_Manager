package data

import data.airport.model.AirportLocal
import data.client.model.ClientLocal
import data.flight.model.FlightLocal
import data.flight.model.TicketPrice
import data.plane.model.PlaneLocal
import data.plane.model.PlaneSeatLocal
import data.plane.model.SeatInfoLocal
import data.ticket.model.TicketLocal
import data.users.model.UserLocal
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class AppDatabase {
    private val config = RealmConfiguration.Builder(
        schema = setOf(
            PlaneLocal::class,
            PlaneSeatLocal::class,
            SeatInfoLocal::class,
            AirportLocal::class,
            ClientLocal::class,
            FlightLocal::class,
            TicketPrice::class,
            TicketLocal::class,
            UserLocal::class
        )
    )
        .deleteRealmIfMigrationNeeded()
        .build()

    val db = Realm.open(config)

    init {
        GlobalScope.launch {
            db.write {
                if (query<UserLocal>().find().isEmpty()){
                    copyToRealm(
                        UserLocal().apply {
                            userLogin = "admin"
                            password = "admin"
                            firstName = "admin"
                            lastName = "admin"
                            patronymic = "admin"
                            isAdmin = true
                        }
                    )
                    copyToRealm(
                        UserLocal().apply {
                            userLogin = "user"
                            password = "user"
                            firstName = "user"
                            lastName = "user"
                            patronymic = "user"
                            isAdmin = false
                        }
                    )
                }
                if (query<ClientLocal>().find().isEmpty()){
                    ClientLocal.generateClients(10).map {
                        copyToRealm(it)
                    }

                }

                if (query<PlaneLocal>().find().isEmpty()) {
                    PlaneLocal.generatePlanes(10).map {
                        copyToRealm(it)
                    }
                }
            }
        }
    }
}