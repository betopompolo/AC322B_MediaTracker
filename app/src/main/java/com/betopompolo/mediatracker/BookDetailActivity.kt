package com.betopompolo.mediatracker

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.betopompolo.mediatracker.databinding.ActivityBookDetailBinding
import com.betopompolo.mediatracker.databinding.BadgeLayoutBinding

var bookDetail = BookDetail(
    title = "The Lord of the Rings: The Fellowship of the Ring",
    synopsis = "A young hobbit, Frodo, who has found the One Ring that belongs to the Dark Lord Sauron, begins his journey with eight companions to Mount Doom, the only place where it can be destroyed.",
    author = "J.R.R. Tolkien",
    publisher = "Allen & Unwin",
    firstPublished = "1954",
    totalPages = 423,
    genres = listOf("Fantasy", "Adventure")
)

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookTitle.text = bookDetail.title
        binding.synopsisTextView.text = bookDetail.synopsis
        binding.authorTextView.text = bookDetail.author
        binding.publisherTextView.text = bookDetail.publisher
        binding.publisherTextView.text = bookDetail.firstPublished
        binding.genres.text = bookDetail.genres.joinToString(", ")

        updateReadingProgressViews()

        binding.readingProgressBackButton.setOnClickListener {
            bookDetail.getPreviousPage()?.let {
                bookDetail = it
                updateReadingProgressViews()
            }
        }

        binding.readingProgressNextButton.setOnClickListener {
            bookDetail.getNextPage()?.let {
                bookDetail = it
                updateReadingProgressViews()
            }
        }

        val texts = listOf(
            "Book",
            bookDetail.genres.first(),
            "${bookDetail.totalPages} pages",
            "9.2",
        )

        texts.take(3).forEach { text ->
            addBadgeView(binding.badgesRow1, text)
        }

        texts.drop(3).take(3).forEach {
            addBadgeView(binding.badgesRow2, it)
        }
    }

    private fun addBadgeView(atRow: LinearLayout, text: String) {
        val badgeViewBinding = BadgeLayoutBinding.inflate(layoutInflater, atRow, false)
        badgeViewBinding.badgeText.text = text
        atRow.addView(badgeViewBinding.root)
    }

    private fun updateReadingProgressViews() {
        binding.readingProgressTextView.text =
            getString(R.string.page, bookDetail.currentPage, bookDetail.totalPages)
        binding.readingProgressPercentage.text = "${bookDetail.progressPercentage}%"
        binding.readingProgressIndicator.progress = bookDetail.progressPercentage
    }
}

data class BookDetail(
    val title: String,
    val synopsis: String,
    val author: String,
    val publisher: String,
    val firstPublished: String,
    val currentPage: Int = 0,
    val totalPages: Int,
    val genres: List<String>
) {
    init {
        require(totalPages > 0) { "totalPages must be greater than 0" }
        require(currentPage in 0..totalPages) { "currentPage must be between 0 and totalPages" }
    }

    val progressPercentage: Int
        get() = ((currentPage.toFloat() / totalPages) * 100).toInt()

    fun getPreviousPage(): BookDetail? {
        try {
            return copy(currentPage = currentPage - 1)
        } catch (_: IllegalArgumentException) {
            return null
        }
    }

    fun getNextPage(): BookDetail? {
        try {
            return copy(currentPage = currentPage + 1)
        } catch (_: IllegalArgumentException) {
            return null
        }
    }
}