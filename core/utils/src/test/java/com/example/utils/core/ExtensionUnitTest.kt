package com.example.utils.core

import org.junit.Assert
import org.junit.Test

class ExtensionUnitTest {

    val utcData = "2023-05-20T14:00:00Z"
    val wrongData = "2023-05-20"

    val correctResult = "02:00 PM"
    val errorResult = ""


    @Test
    fun `test_convert_date_return_correct_format`(){
        val result = utcData.convertDate()
        Assert.assertEquals(correctResult, result)
    }


    @Test
    fun `test_convert_date_return_error_format`(){
        val result = wrongData.convertDate()
        Assert.assertEquals(errorResult , result)
    }

}