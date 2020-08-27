package cl.desafiolatam.restapidesafiodos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.restapidesafiodos.pojo.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photos_list_item.view.*

class PhotoAdapter(private val myDataset: List<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photos_list_item, parent, false)

        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post = myDataset[position]
        holder.textId.text = post.id.toString()
        holder.title.text = post.title
        Picasso.get().load(post.url).into(holder.image)
    }

    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.title
        var textId: TextView = itemView.tvId
        var image: ImageView = itemView.image
    }
}