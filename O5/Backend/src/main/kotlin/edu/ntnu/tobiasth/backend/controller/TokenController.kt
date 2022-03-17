package edu.ntnu.tobiasth.backend.controller

import edu.ntnu.tobiasth.backend.entity.Credential
import edu.ntnu.tobiasth.backend.repo.UserRepository
import edu.ntnu.tobiasth.backend.util.PasswordHashAlgorithm
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.bouncycastle.cms.RecipientId.password
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/token")
class TokenController {
    companion object {
        const val secret = "f677838d-f011-45e9-9eb8-f5d9e0d4d866"
    }

    @Autowired
    private lateinit var userRepository: UserRepository
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun generateToken(@RequestBody credential: Credential): ResponseEntity<String> {
        try {
            val user = userRepository.findByUsername(credential.username)

            if (user.isPresent && PasswordHashAlgorithm.PBKDF2.verifyHash(credential.password, user.get().salt, user.get().hash)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(generateToken(user.get().username))
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied.")
        }
        catch (e: Exception) {
            logger.error(e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while generating token.")
        }
    }

    fun generateToken(username: String): String {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        val claims = Jwts.claims().setSubject(username)
        val auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")

        claims["username"] = username
        claims["authorities"] =  auth.stream().map(GrantedAuthority::getAuthority).toList()

        return Jwts.builder()
            .setId(UUID.randomUUID().toString())
            .setSubject(username)
            .setClaims(claims)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(600000)))
            .signWith(key)
            .compact()
    }
}