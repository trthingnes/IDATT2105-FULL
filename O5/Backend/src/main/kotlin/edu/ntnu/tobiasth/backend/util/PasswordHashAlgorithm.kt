package edu.ntnu.tobiasth.backend.util

import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

const val DEFAULT_SALT_SIZE = 32

class PasswordHashAlgorithm(private val algorithm: String, private val keyLength: Int, private val iterations: Int) {
    fun getSaltedHash(password: String, salt: String): String {
        val chars = password.toCharArray()
        val bytes = salt.toByteArray(StandardCharsets.UTF_8)
        val spec = PBEKeySpec(chars, bytes, iterations, keyLength)

        Arrays.fill(chars, Character.MIN_VALUE)

        return try {
            val factory = SecretKeyFactory.getInstance(algorithm)
            val passwordHash = factory.generateSecret(spec).encoded
            Base64.getEncoder().encodeToString(passwordHash)
        } catch (e: Exception) {
            throw IllegalStateException(
                String.format(
                    "Hashing failed because of a %s with the message %s.",
                    e.javaClass,
                    e.message
                )
            )
        } finally {
            spec.clearPassword()
        }
    }

    fun getSaltedHash(password: String): Array<String> {
        val salt = generateSalt()
        return arrayOf(salt, getSaltedHash(password, salt))
    }

    fun verifyHash(password: String, salt: String, hash: String): Boolean {
        val passwordHash: String = try {
            getSaltedHash(password, salt)
        } catch (e: IllegalStateException) {
            return false
        }

        return passwordHash == hash
    }

    fun generateSalt(): String {
        return generateSalt(DEFAULT_SALT_SIZE)
    }

    fun generateSalt(length: Int): String {
        val sr = SecureRandom()
        val salt = ByteArray(length)
        sr.nextBytes(salt)

        return Base64.getEncoder().encodeToString(salt)
    }

    companion object {
        // Hash algorithm presets
        val PBKDF2 = PasswordHashAlgorithm("PBKDF2WithHmacSHA256", 256, 65536)
    }
}