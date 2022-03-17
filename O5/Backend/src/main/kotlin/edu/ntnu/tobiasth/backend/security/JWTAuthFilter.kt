package edu.ntnu.tobiasth.backend.security

import edu.ntnu.tobiasth.backend.controller.TokenController
import edu.ntnu.tobiasth.backend.util.TokenParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthFilter: OncePerRequestFilter() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val claims = TokenParser(request).claims
            if (claims != null) {
                if (claims.body["authorities"] != null) {
                    val authorities = claims.body["authorities"] as List<String>
                    val auth = UsernamePasswordAuthenticationToken(
                        claims.body.subject,
                        null,
                        authorities.stream().map(::SimpleGrantedAuthority).toList()
                    )
                    SecurityContextHolder.getContext().authentication = auth
                } else {
                    SecurityContextHolder.clearContext()
                }
            }

            filterChain.doFilter(request, response)
        }
        catch (e: Exception) {
            logger.warn(e.message)
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
        }
    }
}