package org.kb10uy.shipley.mastodon

data class Account(
    val id: String,
    val username: String,
    val displayName: String,
    val locked: Boolean,
    val bot: Boolean
)
