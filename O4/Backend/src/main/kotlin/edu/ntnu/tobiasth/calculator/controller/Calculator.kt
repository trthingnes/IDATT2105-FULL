package edu.ntnu.tobiasth.calculator.controller

import edu.ntnu.tobiasth.calculator.model.Calculation
import edu.ntnu.tobiasth.calculator.model.Operator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalStateException

@RestController
@CrossOrigin
class Calculator {
    @PostMapping("/calculate")
    fun calculate(@RequestBody data: Map<String, Any>): ResponseEntity<Any> {
        val logger = LoggerFactory.getLogger(this.javaClass)
        logger.info("Received request to handle.")

        return try {
            for(key in listOf("firstNumber", "secondNumber", "operator")) {
                if (!data.containsKey(key)) {
                    throw IllegalStateException("Key '$key' is required")
                }
            }
            try {
                val first = (data["firstNumber"] as String).toDouble()
                val second = (data["secondNumber"] as String).toDouble()
                val operatorName = data["operator"] as String
                val calculation = Calculation(first, second, Operator.of(operatorName))

                logger.info("Successfully finished request for calculation '$calculation'.")
                ResponseEntity.status(HttpStatus.OK).body(calculation)
            } catch (e: NumberFormatException) {
                throw IllegalStateException("Invalid number")
            }
        }
        catch (e: Exception) {
            logger.warn("Request failed with message '${e.message}'.")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(Pair("error", e.message)))
        }
    }
}