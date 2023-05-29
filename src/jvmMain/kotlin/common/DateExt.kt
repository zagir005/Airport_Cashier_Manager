package common
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

fun Date.toLocalDateTime(): LocalDateTime =
    this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

fun LocalDateTime.toDate() =
    Date.from(
        this
            .atZone(ZoneId.systemDefault())
            .toInstant()

    )
fun LocalDate.toDate() =
    Date.from(
        this.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()

    )

fun LocalDate.copy(year: Int = this.year, month: Int = this.month.value, day: Int = this.dayOfMonth): LocalDate =
    LocalDate.of(year,month,day)
fun LocalDate.copyOrExc(
    year: Int = this.year,
    month: Int = this.month.value,
    day: Int = this.dayOfMonth,
    ifExc: () -> Unit
): LocalDate {
    return try {
        LocalDate.of(year,month,day)
    }catch (e: DateTimeException){
        ifExc()
        this
    }
}
fun LocalTime.copyOrExc(
    hour: Int = this.hour,
    minute: Int = this.minute,
    ifExc: () -> Unit
): LocalTime {
    return try {
        LocalTime.of(hour,minute)
    }catch (e: DateTimeException){
        ifExc()
        this
    }
}
fun LocalDateTime.copyOrExc(
    date: LocalDate = this.toLocalDate(),
    time: LocalTime = this.toLocalTime(),
    ifExc: () -> Unit = {}
): LocalDateTime {
    return try {
        LocalDateTime.of(date,time)
    }catch (e: DateTimeException){
        ifExc()
        this
    }
}

fun Long.getDate() = Date(this)

fun Date.getFullDateString():String = SimpleDateFormat("dd MMMM yyyy").format(this)

fun Date.getFullTimeString():String = SimpleDateFormat("HH:mm").format(this)