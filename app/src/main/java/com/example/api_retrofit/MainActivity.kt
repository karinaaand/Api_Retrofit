package com.example.api_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_retrofit.databinding.ActivityMainBinding
import com.example.api_retrofit.model.ApiData
import com.example.api_retrofit.network.ApiClient
import com.example.api_retrofit.network.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Aktivitas utama yang bertanggung jawab untuk menampilkan data dari API
class MainActivity : AppCompatActivity() {

    // View Binding untuk mengakses komponen tampilan secara lebih efisien
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Inisialisasi client API dan adapter untuk RecyclerView
    private val client = ApiClient.getInstance()  // Client untuk mengakses API
    private lateinit var adapter: ApiAdapter  // Adapter untuk RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menetapkan layout tampilan menggunakan View Binding
        setContentView(binding.root)

        // Mengaktifkan edge-to-edge UI setelah setContentView
        enableEdgeToEdge()

        // Mengatur padding untuk status bar dan navigation bar agar UI tidak tertutup
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Mengambil jarak sistem bar
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)  // Menyesuaikan padding
            insets  // Mengembalikan insets untuk diterapkan
        }

        // Mengatur RecyclerView dengan LayoutManager dan adapter
        binding.rvRc.layoutManager = LinearLayoutManager(this)  // Menyusun item dalam daftar secara vertikal
        adapter = ApiAdapter(listOf())  // Membuat adapter dengan daftar kosong
        binding.rvRc.adapter = adapter  // Menetapkan adapter ke RecyclerView

        // Memanggil fungsi untuk mengambil data dari API
        fetchData()
    }

    // Fungsi untuk mengambil data dari API
    private fun fetchData() {
        // Memanggil client API untuk mendapatkan data karakter
        val response = client.getApiData()
        response.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                // Memeriksa apakah data berhasil diterima
                val characterList = response.body()?.results  // Mengambil daftar karakter dari response
                if (characterList != null && characterList.isNotEmpty()) {
                    // Jika data ada, memperbarui data pada adapter
                    adapter.updateApiData(characterList)
                } else {
                    // Menampilkan pesan jika data tidak tersedia
                    Toast.makeText(this@MainActivity, "Tidak ada data yang tersedia", Toast.LENGTH_SHORT).show()
                }
            }

            // Menangani kegagalan ketika pengambilan data gagal
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Menampilkan pesan error saat terjadi kegagalan
                Toast.makeText(this@MainActivity, "Koneksi Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
