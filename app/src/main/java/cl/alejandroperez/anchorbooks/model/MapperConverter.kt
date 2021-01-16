package cl.alejandroperez.anchorbooks.model

import cl.alejandroperez.anchorbooks.model.api.BookDetail
import cl.alejandroperez.anchorbooks.model.api.Books
import cl.alejandroperez.anchorbooks.model.db.EntityBookDetail
import cl.alejandroperez.anchorbooks.model.db.EntityBooks


fun productConvert(listProducts: List<Books>): List<EntityBooks> {
    return listProducts.map { books ->
        EntityBooks(
            books.id,
            books.author,
            books.country,
            books.imageLink,
            books.language,
            books.title
        )
    }

}

fun detailConvert(details: BookDetail): EntityBookDetail {
    return EntityBookDetail(
        details.id,
        details.author,
        details.country,
        details.imageLink,
        details.language,
        details.link,
        details.pages,
        details.title,
        details.year,
        details.price,
        details.lastPrice,
        details.delivery
    )

}