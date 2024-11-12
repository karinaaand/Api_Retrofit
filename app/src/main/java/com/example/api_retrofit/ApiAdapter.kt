package com.example.api_retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.api_retrofit.databinding.ItemapiBinding
import com.example.api_retrofit.model.ApiData
import com.squareup.picasso.Picasso

// Adapter untuk RecyclerView yang digunakan untuk menampilkan data API dalam bentuk daftar
class ApiAdapter(private var apiList: List<ApiData>) : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {

    // ViewHolder untuk mengikat tampilan ItemapiBinding dengan RecyclerView
    inner class ApiViewHolder(val binding: ItemapiBinding) : RecyclerView.ViewHolder(binding.root)

    // Digunakan untuk membuat dan mengembalikan ViewHolder dengan mengikat layout item API
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val binding = ItemapiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiViewHolder(binding)  // Mengembalikan objek ApiViewHolder
    }

    // Mengikat data ke tampilan untuk setiap item pada RecyclerView
    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val apiData = apiList[position]
        // Mengatur nilai-nilai data dari ApiData ke tampilan
        holder.binding.name.text = apiData.name
        holder.binding.status.text = apiData.status
        holder.binding.species.text = apiData.species
        holder.binding.gender.text = apiData.gender

        // Menggunakan Picasso untuk memuat gambar karakter dari URL ke ImageView
        Picasso.get()
            .load("https://rickandmortyapi.com/api/character/avatar/${apiData.id}.jpeg")
            .into(holder.binding.imgChar)

        // Menambahkan aksi ketika item di klik untuk menampilkan nama karakter di Toast
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${apiData.name}", Toast.LENGTH_SHORT).show()
        }
    }

    // Mengembalikan jumlah item dalam daftar apiList
    override fun getItemCount(): Int {
        return apiList.size
    }

    // Fungsi untuk memperbarui data dalam adapter dan memberitahukan perubahan pada RecyclerView
    fun updateApiData(newApiData: List<ApiData>) {
        apiList = newApiData  // Memperbarui data dengan data baru
        notifyDataSetChanged()  // Memberitahukan adapter untuk memperbarui tampilan
    }
}
