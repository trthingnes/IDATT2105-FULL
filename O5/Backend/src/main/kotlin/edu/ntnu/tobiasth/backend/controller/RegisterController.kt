package edu.ntnu.tobiasth.backend.controller

import edu.ntnu.tobiasth.backend.entity.Credential
import edu.ntnu.tobiasth.backend.entity.User
import edu.ntnu.tobiasth.backend.repo.UserRepository
import edu.ntnu.tobiasth.backend.util.PasswordHashAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/register")
class RegisterController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping
    fun register(@RequestBody credential: Credential): ResponseEntity<String> {
        if(credential.username.isBlank() || credential.password.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password is required.")
        }

        if (userRepository.findByUsername(credential.username).isPresent) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken.")
        }

        val salt = PasswordHashAlgorithm.PBKDF2.generateSalt()
        val hash = PasswordHashAlgorithm.PBKDF2.getSaltedHash(credential.password, salt)
        val user = User(credential.username, salt, hash)

        userRepository.save(user)

        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered.")
    }
}