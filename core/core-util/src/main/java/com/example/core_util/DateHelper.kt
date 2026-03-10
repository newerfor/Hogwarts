package com.example.core_util

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DateHelper {
    fun getAgeFromDate(dateString: String): String? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val birthDate = LocalDate.parse(dateString, formatter)
            val currentDate = LocalDate.now()
            Period.between(birthDate, currentDate).years.toString()
        } catch (e: Exception) {
            "Неизвестна дата рождения"
        }
    }
}