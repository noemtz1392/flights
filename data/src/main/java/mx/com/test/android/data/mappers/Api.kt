package mx.com.test.android.data.mappers

import mx.com.test.android.data.ApiFlightStatus
import mx.com.test.android.domain.common.mapper.Mapper
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.models.FlightStatus

class Api(private val mapper: ApiSegmentToSegmentMapper) : Mapper<ApiFlightStatus, Flight>() {
    override fun mapFrom(from: ApiFlightStatus): Flight = Flight(
        id = from.segment.segmentCode,
        status = FlightStatus.valueOf(from.status),
        boardingTerminal = from.boardingTerminal,
        boardingGate = from.boardingGate,
        boardingTime = from.boardingTime,
        estimatedDepartureTime = from.estimatedDepartureTime,
        estimatedArrivalTime = from.estimatedArrivalTime,
        delayInMinutes = from.delayInMinutes,
        arrivalTerminal = from.arrivalTerminal,
        arrivalGate = from.arrivalGate,
        segment = mapper.mapFrom(from.segment)
    )
}