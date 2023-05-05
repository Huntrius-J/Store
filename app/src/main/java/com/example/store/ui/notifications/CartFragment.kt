package com.example.store.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Adapters.CartRecyclerAdapter
import com.example.store.Common.Common
import com.example.store.Interface.CartCellClickListener
import com.example.store.Interface.RetrofitServices
import com.example.store.Models.Cart
import com.example.store.R
import com.example.store.databinding.FragmentCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment(), CartCellClickListener {

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CartRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cartViewModel =
            ViewModelProvider(this).get(CartViewModel::class.java)

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var recyclerCarts = root.findViewById<RecyclerView>(R.id.recyclerCarts)
        mService = Common.retrofitServices
        recyclerCarts.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(root.context)
        recyclerCarts.layoutManager = layoutManager

        getAllCarts(root)

        return root
    }

    fun getAllCarts(root:View)
    {
        mService.getAllCarts().enqueue(object : Callback<MutableList<Cart>> {

            override fun onFailure(call: Call<MutableList<Cart>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<Cart>>,
                response: Response<MutableList<Cart>>
            ) {
                adapter =
                    CartRecyclerAdapter(root.context, response.body() as MutableList<Cart>, this@CartFragment)
                adapter.notifyDataSetChanged()
                var recyclerCarts = root.findViewById<RecyclerView>(R.id.recyclerCarts)
                recyclerCarts.adapter = adapter
                var progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
                progressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun cellClickListener(data: Cart) {

    }
}