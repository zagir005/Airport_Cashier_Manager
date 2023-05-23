package model

import kotlin.random.Random

data class Plane (
    val name: String,
    val codeName: String,
    val seats: List<PlaneSeat>
)

data class PlaneSeat(
    val category: SeatCategory,
    val row: Int,
    val column: Int,
)

sealed class SeatCategory(val name: String, val baggageWeight: Int){
    object Economy: SeatCategory("Эконом", 10)
    object Business: SeatCategory("Бизнес",20)
    object FirstGrade: SeatCategory("Первый класс", 50)
}
val seatCategories = listOf(
    SeatCategory.Business,
    SeatCategory.Economy,
    SeatCategory.FirstGrade,
)

fun generateSeat(): PlaneSeat {
    val category = seatCategories.random()
    val row = Random.nextInt(1, 30)
    val column = Random.nextInt(1, 10)
    return PlaneSeat(category, row, column)
}

val planeNames = listOf(
    "Boeing 747", "Airbus A380", "Boeing 787 Dreamliner", "Airbus A350",
    "Boeing 777", "Embraer E-Jet", "Bombardier CRJ", "McDonnell Douglas MD-80",
    "Sukhoi Superjet 100", "Cessna Citation Longitude",
)

val planeCodeNames = listOf(
    "B747", "A388", "B788", "A359", "B777",
    "E190", "CRJ9", "MD80", "SU95", "C700",
)

fun generatePlane(name: String = ""): Plane {
    val seats = List(200) { generateSeat() }
    return Plane(planeNames.random(), planeCodeNames.random(), seats)
}

val airportList = listOf(
    Airport(
        "Витязево", "AAQ", "Анапа",
    ),
    Airport(
        "Абакан","ABA","Абакан"
    ),
    Airport(
        "Быково","BKA","Москва"
    ),
    Airport(
        "Внуково","VKO","Москва"
    ),
    Airport(
        "Шереметьево","SVO","Москва"
    ),
    Airport(
        "Домодедово","DME","Москва"
    ),
    Airport(
        "Новый","KHV","Хабаровск"
    ),
    Airport(
        "Пашковский","KRR","Краснодар"
    ),
    Airport(
        "Ржевка","RVH","Санкт-Петербург"
    ),
    Airport(
        "Пулково","LED","Санкт-Петербург"
    ),
)

fun main(){
    repeat(5){
        println(generatePlane())
    }
}