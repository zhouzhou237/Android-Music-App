package com.example.app.core.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.example.app.core.datastore.UserDataPreferences
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * DataStore protobuf sequencer 序列器
 */
class UserDataPreferencesSerializer @Inject constructor() : Serializer<UserDataPreferences> {
    override var defaultValue: UserDataPreferences = UserDataPreferences.getDefaultInstance()

    /**
     * Reads data from the input stream
     */
    override suspend fun readFrom(input: InputStream): UserDataPreferences =
        try {
            // readFrom is already called on the data store background thread
            UserDataPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    /**
     * write data in the output stream
     */
    override suspend fun writeTo(t: UserDataPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}