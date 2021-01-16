package cl.alejandroperez.anchorbooks.model.api

data class Books(
    val id: Int,
    val author: String,
    val country: String,
    val imageLink: String,
    val language: String,
    val title: String

)
