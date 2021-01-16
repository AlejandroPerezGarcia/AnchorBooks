package cl.alejandroperez.anchorbooks.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.alejandroperez.anchorbooks.R
import cl.alejandroperez.anchorbooks.model.api.Books
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterBook(private var bookData: MutableList<Books>) :
    RecyclerView.Adapter<AdapterBook.BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return BookViewHolder(view)
    }

    val bookSelect = MutableLiveData<Books>()

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.id.text = bookData[position].id.toString()
        holder.author.text = bookData[position].author.toString()
        holder.country.text = bookData[position].country.toString()
        holder.language.text = bookData[position].language.toString()
        holder.title.text = bookData[position].title.toString()
        Picasso.get().load(bookData[position].imageLink).into(holder.imagelink.imageViewList)

        holder.itemView.setOnClickListener {
            bookSelect.value = bookData[position]
        }
    }

    override fun getItemCount(): Int {
        return bookData.size
    }


    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id: TextView = itemView.txtId
        var author: TextView = itemView.txtAuthor
        var country: TextView = itemView.txtCountry
        var language: TextView = itemView.txtLanguage
        var title: TextView = itemView.txtTitle
        var imagelink: ImageView = itemView.imageViewList

    }

    fun updateBook(it: List<Books>) {

        bookData.clear()
        bookData.addAll(it)
        notifyDataSetChanged()
    }
}