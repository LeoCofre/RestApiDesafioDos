package cl.desafiolatam.restapidesafiodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.restapidesafiodos.pojo.Photo
import cl.desafiolatam.restapidesafiodos.remote.RetrofitClient

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private var postsList =  ArrayList<Photo>()
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = PhotoAdapter(postsList)
        postsRecyclerView.adapter = viewAdapter

        loadApiData()
    }

    private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()

        call.enqueue(object : Callback<ArrayList<Photo>> {
            override fun onResponse(
                    call: Call<ArrayList<Photo>>,
                    response: Response<ArrayList<Photo>>
            ) {
                response.body()?.map {
                    Log.d("MAIN", "${it.id} - ${it.title}")
                    postsList.add(it)
                }
                viewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
                Log.d("MAIN", "Error: " + t)
                Toast.makeText(
                        applicationContext,
                        "Error: no pudimos recuperar los posts desde el api",
                        Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}