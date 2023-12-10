package mx.com.test.android.data.mappers

import mx.com.test.android.data.ApiSegment
import mx.com.test.android.domain.common.mapper.Mapper
import mx.com.test.android.domain.models.Segment

class ApiSegmentToSegmentMapper : Mapper<ApiSegment, Segment>() {
    override fun mapFrom(from: ApiSegment) = Segment(
        segmentCode = from.segmentCode,
        departureAirport = from.departureAirport,
        arrivalAirport = from.arrivalAirport,
        departureDateTime = from.departureDateTime,
        arrivalDateTime = from.arrivalDateTime,
        flightStatus = from.flightStatus,
        operatingCarrier = from.operatingCarrier,
        marketingCarrier = from.marketingCarrier,
        operatingFlightCode = from.operatingFlightCode,
        marketingFlightCode = from.marketingFlightCode,
        flightDurationInMinutes = from.flightDurationInMinutes,
        aircraftType = from.aircraftType,
        stops = from.stops
    )
}