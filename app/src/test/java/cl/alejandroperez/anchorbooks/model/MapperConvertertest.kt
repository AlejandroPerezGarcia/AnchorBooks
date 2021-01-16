package cl.alejandroperez.anchorbooks.model

import cl.alejandroperez.anchorbooks.model.api.BookDetail
import cl.alejandroperez.anchorbooks.model.api.Books
import cl.alejandroperez.anchorbooks.model.db.EntityBookDetail
import cl.alejandroperez.anchorbooks.model.db.EntityBooks
import org.junit.Assert
import org.junit.Test

class MapperConvertertest {

    @Test
    fun productConvert() {
        //Given
        val products = listOf(Books(1, "name", "Chile", "image", "español", "camino al cielo"))
        val entityProduct =
            listOf(EntityBooks(1, "name", "Chile", "image", "español", "camino al cielo"))

        //when
        val result = productConvert(products)

        //then

        Assert.assertNotNull(result)
        Assert.assertEquals(entityProduct, result)


    }

    @Test
    fun detailConvert() {
        //Given
        val details = BookDetail(
            1,
            "name",
            "chile",
            "image",
            "Japones",
            "hola mundo",
            122,
            "La vuelta al mundo",
            1234,
            12000,
            5000,
            true
        )
        val entityDetail = EntityBookDetail(
            1,
            "name",
            "chile",
            "image",
            "Japones",
            "hola mundo",
            122,
            "La vuelta al mundo",
            1234,
            12000,
            5000,
            true
        )
        //when
        val result = detailConvert(details)

        //then
        Assert.assertNotNull(result)
        Assert.assertEquals(entityDetail, result)


    }
}