package com.example.store.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Adapters.ProdsRecyclerAdapter
import com.example.store.Common.Common
import com.example.store.DetailsActivity
import com.example.store.Interface.ProdCellClickListener
import com.example.store.Interface.RetrofitServices
import com.example.store.Models.Product
import com.example.store.R
import com.example.store.databinding.FragmentProdsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsFragment : Fragment() , AdapterView.OnItemSelectedListener , ProdCellClickListener{

    private var _binding: FragmentProdsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: ProdsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productsViewModel =
            ViewModelProvider(this).get(ProductsViewModel::class.java)

        _binding = FragmentProdsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var recyclerProds = root.findViewById<RecyclerView>(R.id.recyclerProds)
        mService = Common.retrofitServices
        recyclerProds.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(root.context)
        recyclerProds.layoutManager = layoutManager

        val categorySpinner: Spinner = root.findViewById(R.id.category)
        ArrayAdapter.createFromResource(
            root.context,
            R.array.shop_categoryes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        categorySpinner.onItemSelectedListener = this@ProductsFragment

        getAllClothesList(root,"men's clothing")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        val categorySpinner: Spinner = requireView().findViewById(R.id.category)
        val selected: String = categorySpinner.getSelectedItem().toString()
        getAllClothesList(requireView(),selected)
        var progressBar = requireView().findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun getAllClothesList(root:View, category: String) {
        //dialog.show()
        if(category == "men's clothing") {
            mService.getMensClothesList().enqueue(object : Callback<MutableList<Product>> {

                override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MutableList<Product>>,
                    response: Response<MutableList<Product>>
                ) {
                    adapter =
                        ProdsRecyclerAdapter(root.context, response.body() as MutableList<Product>, this@ProductsFragment)
                    adapter.notifyDataSetChanged()
                    var recyclerProds = root.findViewById<RecyclerView>(R.id.recyclerProds)
                    recyclerProds.adapter = adapter
                    var progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
                    progressBar.visibility = View.GONE
                }
            })
        }
        else if(category == "women's clothing")
        {
            mService.getWomensClothesList().enqueue(object : Callback<MutableList<Product>> {

                override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MutableList<Product>>,
                    response: Response<MutableList<Product>>
                ) {
                    adapter =
                        ProdsRecyclerAdapter(root.context, response.body() as MutableList<Product>,this@ProductsFragment)
                    adapter.notifyDataSetChanged()
                    var recyclerProds = root.findViewById<RecyclerView>(R.id.recyclerProds)
                    recyclerProds.adapter = adapter
                    var progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
                    progressBar.visibility = View.GONE
                }
            })
        }
    }

    override fun cellClickListener(data: Product) {
        val intent = Intent(this.context, DetailsActivity::class.java)
        intent.putExtra("title", data.title)
        intent.putExtra("price", data.price)
        intent.putExtra("description", data.description)
        intent.putExtra("id", data.id)
        intent.putExtra("image", data.image)
        intent.putExtra("rating", data.rating?.rate)
        intent.putExtra("rated", data.rating?.count)
        startActivity(intent)
    }

}