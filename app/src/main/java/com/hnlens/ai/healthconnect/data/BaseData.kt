package com.hnlens.ai.healthconnect.data

import java.time.Instant

data class BaseData<T>(
    val uid: String,
    var value: T,
    var atTime: Instant? = null,
    var startTime: Instant? = null,
    var endTime: Instant? = null
)