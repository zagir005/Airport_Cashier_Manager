package screens.planes.model

import org.mongodb.kbson.ObjectId

data class PlaneSeatCategory(
    val planeId: ObjectId,
    var seatId: ObjectId = ObjectId(),
    var categoryName: String = "",
    var count: Int = 0,
    var baggageWeight: Int = 0
){
    override fun toString(): String {
        return "categoryName: $categoryName || count: $count || baggageWeight: $baggageWeight"
    }
}
