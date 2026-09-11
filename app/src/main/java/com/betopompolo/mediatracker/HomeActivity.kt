package com.betopompolo.mediatracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betopompolo.mediatracker.databinding.ActivityHomeBinding
import com.betopompolo.mediatracker.databinding.HomeListItemLayoutBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.listView.adapter = HomeListAdapter(homeItems)
        binding.listView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class HomeListAdapter(val list: List<HomeListItem>) :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    class ViewHolder(val binding: HomeListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val context: Context = binding.root.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            HomeListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.binding.title.text = item.title
        holder.binding.badge.badgeText.text = holder.context.getString(R.string.book)
        holder.binding.pageCount.text = holder.context.getString(R.string.page_count, item.pageCount)
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.context, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, item.id)
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}

data class HomeListItem(
    val id: String,
    val title: String,
    val pageCount: Int,
)

val homeItems: List<HomeListItem> = List(1000) {
    HomeListItem("id$it", title = "Book #${it + 1}", pageCount = 100)
}