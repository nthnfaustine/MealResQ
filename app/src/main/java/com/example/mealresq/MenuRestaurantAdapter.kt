// ini adapter buat tampilin recyclerview yang ada di HOME

package com.example.mealresq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuRestaurantAdapter(private val listMenu: ArrayList<Menu>) : RecyclerView.Adapter<MenuRestaurantAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onHapusClickCallback: OnHapusClicked

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnHapusClickCallBack(onHapusClickCallback: OnHapusClicked){
        this.onHapusClickCallback = onHapusClickCallback
    }


    // ini fungsi buat holder yang nge inflate layout row_player.xml (ada di folder res/layout)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_menu, viewGroup, false)
        return ListViewHolder(view)
    }

    // ini fungsi yang buat ngedit textview dll yang ada didalem row_player.xml
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.namaMenu.text = menu.name
        holder.fotoMenu.setBackgroundResource(menu.photo)
        holder.deskripsiMenu.text = menu.description
        holder.hargaMenu.text = menu.price
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMenu[holder.adapterPosition])
        }
    }

    interface OnHapusClicked {
        fun onHapusClicked(data: Menu)
    }

    // ini biar row nya bisa di klik
    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }

    // ini ga penting
    override fun getItemCount(): Int {
        return listMenu.size
    }

    // ini kelas buat daftarin komponen yang ada di row_player.xml ke holder
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namaMenu: TextView = itemView.findViewById(R.id.namaMakanan)
        var fotoMenu: ImageView = itemView.findViewById(R.id.fotoMakanan)
        var deskripsiMenu: TextView = itemView.findViewById(R.id.deskripsiMenu)
        var hargaMenu: TextView = itemView.findViewById(R.id.hargaMenu)
    }
}