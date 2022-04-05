package edu.ntnu.tobiasth.backend.controller

import edu.ntnu.tobiasth.backend.entity.Equation
import edu.ntnu.tobiasth.backend.repo.EquationRepository
import edu.ntnu.tobiasth.backend.repo.UserRepository
import edu.ntnu.tobiasth.backend.util.TokenParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import kotlin.IllegalStateException

@RestController
@CrossOrigin
class CalculateController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var equationRepository: EquationRepository

    @PostMapping("/calculation")
    fun calculate(@RequestBody equation: Equation, request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            try {
                val optional = userRepository.findByUsername(TokenParser(request).username)
                if (optional.isPresent) {

                    val user = optional.get()
                    user.equations.add(equation)
                    equationRepository.save(equation)
                    userRepository.save(user)

                    ResponseEntity.status(HttpStatus.OK).body(equation)
                }
                else {
                    throw IllegalStateException("User does not exist")
                }
            } catch (e: NumberFormatException) {
                throw IllegalStateException("Invalid number")
            }
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(Pair("error", e.message)))
        }
    }

    @GetMapping("/calculation")
    fun getAllCalculations(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            val optional = userRepository.findByUsername(TokenParser(request).username)
            if (optional.isPresent) {

                val user = optional.get()

                ResponseEntity.status(HttpStatus.OK).body(user.equations.reversed())
            }
            else {
                throw IllegalStateException("User does not exist")
            }
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(Pair("error", e.message)))
        }
    }
}