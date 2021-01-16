package cl.alejandroperez.anchorbooks.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.alejandroperez.anchorbooks.R
import cl.alejandroperez.anchorbooks.model.db.EntityBookDetail
import cl.alejandroperez.anchorbooks.viewmodel.BookViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_list.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DetailFragment : Fragment() {

    private val bookViewModel : BookViewModel by activityViewModels()

    lateinit var book : EntityBookDetail

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookViewModel.result.observe(viewLifecycleOwner,{

            if (it != null) {
                txtTitleBook.text = it.title
                txtDetailLasPrice.text = it.lastPrice.toString()
                txtDetailPrice.text = it.price.toString()
                txtDetailYear.text = it.year.toString()
                txtDetailPage.text = it.pages.toString()
                txtDetailLanguage.text = it.language
                txtDetailLink.text = it.link
                txtDetailAutor.text = it.author
                Picasso.get().load(it.imageLink).into(imageDetailImage)
            }
            fun email() {

                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ventas@anchoBook.cl"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta por Libro ${it.title} , ID : ${it.id} ")
                intent.putExtra(
                    Intent.EXTRA_TEXT, " “Hola\n" +
                        "Vi el Libro ${it.title} y me gustaría que me contactaran a este correo o al\n" +
                        "siguiente número _________")
                intent.type = "message/rfc822"
                startActivity(Intent.createChooser(intent, "Choose an email client"))
            }

            fab.setOnClickListener { view ->
                Snackbar.make(view, "Email", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
                email()
            }







        })


    }

}