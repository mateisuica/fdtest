package ro.emag.fashiondays.ui.itemlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.emag.fashiondays.ProductBinding
import ro.emag.models.Product
import ro.emag.models.Products

class ProductListAdapter: RecyclerView.Adapter<ProductItemViewHolder>() {
    private val productItems: MutableList<Product> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: Products) {
        productItems.clear()
        productItems.addAll(products.products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductBinding.inflate(layoutInflater, parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        productItems.getOrNull(position)?.let { productItem ->
            holder.binding.uiModel = productItem
            holder.binding.executePendingBindings()
            holder.binding.root.setOnLongClickListener {
                productItems.removeAt(position)
                notifyDataSetChanged()
                true
            }
        }
    }

    override fun getItemCount() = productItems.size
}