import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.model.InventoryDisplayItem

class InventoryAdapter(private var items: List<InventoryDisplayItem>) :
    RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    class InventoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.text_inventory_item_name)
        val quantityText: TextView = itemView.findViewById(R.id.text_inventory_item_quantity)
        val expiryText: TextView = itemView.findViewById(R.id.text_inventory_item_expiry)
        val image: ImageView = itemView.findViewById(R.id.image_inventory_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory, parent, false)
        return InventoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.quantityText.text = "Quantity: ${item.quantity ?: 0}"
        holder.expiryText.text = "Expires: ${item.expirationDate ?: "-"}"
        holder.image.setImageResource(R.drawable.cook)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<InventoryDisplayItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}

