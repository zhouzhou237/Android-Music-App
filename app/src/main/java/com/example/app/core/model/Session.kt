package com.example.app.core.model

import kotlinx.serialization.Serializable

/**
 * Information returned after a successful login
 */
@Serializable
data class Session(
    /**
     * User Id
     */
    val userId: String,

    /**
     * Session token after login
     */
    val session: String,

    /**
     * Chat token
     */
    val chatToken: String,

    /**
     * User information returned directly after login to
     * avoid additional requests and improve efficiency
     */
    val user: User,)
{}
