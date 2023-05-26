package data

import data.plane.model.PlaneLocal
import data.plane.model.PlaneSeatLocal
import data.plane.model.SeatInfoLocal
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.realmListOf

class AppDatabase {
    private val config = RealmConfiguration.Builder(
        schema = setOf(
            PlaneLocal::class,
            PlaneSeatLocal::class,
            SeatInfoLocal::class
        )
    )
        .deleteRealmIfMigrationNeeded()
        .build()

    val db = Realm.open(config)
}