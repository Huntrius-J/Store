package com.example.store

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Adapters.CartTempRecyclerAdapter
import com.example.store.Models.AppDatabase
import com.example.store.Models.CartData
import com.example.store.databinding.FragmentCartTempBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class CartTemp : Fragment() {

    companion object {
        fun newInstance() = CartTemp()
    }

    private var _binding: FragmentCartTempBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: CartTempViewModel

    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartTempBinding.inflate(inflater, container, false)

        val root: View = binding.root


        var db = AppDatabase.getDatabase(root.context)

        getData(db,root)

        return root


    }

    private fun getData(db: AppDatabase, root:View)
    {
        var progressBar = root.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        var list: List<CartData>

        lifecycleScope.launch(Dispatchers.IO){

            list = db.cartDao().getAll()
            var totals: Double = 0.0;

            for( cart in list)
            {
                totals += cart.price!!.toDouble();
            }





            withContext(Dispatchers.Main)
            {
                var totalsText: TextView = root.findViewById(R.id.totalPrice)

                val df = DecimalFormat("#.##")
                totalsText.setText(df.format(totals)+"$")

                var orderButton: Button = root.findViewById(R.id.orderButton)

                orderButton.setOnClickListener()
                {
                    val intent = Intent(root.context, OrderActivity::class.java)
                    intent.putExtra("sum",totalsText.text)
                    startActivity(intent)
                }
                var adapter = CartTempRecyclerAdapter(requireActivity(),list)
                var cartRecycler = root.findViewById<RecyclerView>(R.id.recyclerProdCart)
                cartRecycler.adapter = adapter
                cartRecycler.layoutManager = LinearLayoutManager(requireActivity())
                progressBar.visibility = View.GONE
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartTempViewModel::class.java)
    }

}