package org.kb10uy.shipley.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * Shipley のアカウント
 */
data class Account(
    val displayName: String,
    val screenName: String,
    val avatar: String,
    val accessToken: String
)
