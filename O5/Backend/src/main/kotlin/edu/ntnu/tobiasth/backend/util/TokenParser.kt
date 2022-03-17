package edu.ntnu.tobiasth.backend.util

import edu.ntnu.tobiasth.backend.controller.TokenController
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.http.HttpServletRequest

class TokenParser(private val request: HttpServletRequest) {
    val claims: Jws<Claims>?
        get() {
            val key = Keys.hmacShaKeyFor(TokenController.secret.toByteArray())
            val header = request.getHeader("Authorization")

            if (header == null || !header.startsWith("Bearer")) {
                SecurityContextHolder.clearContext()
                return null
            }

            val token = header.replace("Bearer ", "")

            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        }

    val username: String
        get() {
            return claims?.body?.get("username") as String
        }
}