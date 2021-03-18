package io.grooviter.memento.bank.projector.domain.events

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

import java.time.OffsetDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class Withdrawal {
    @JsonProperty('aggregateId')
    UUID accountId

    @JsonProperty('balance')
    BigDecimal newBalance

    @JsonProperty('createdAt')
    OffsetDateTime createdAt
}
