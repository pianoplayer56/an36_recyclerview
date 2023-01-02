package com.example.firstandroidapp

import adapter.PostAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firstandroidapp.databinding.ActivityMainBinding
import ru.netology.viewmodel.PostViewModel
import ru.netology.viewmodel.getRightNumber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter({ viewModel.likeById(it.id) }, {
            viewModel.repostById(it.id)
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts -> adapter.submitList(posts) }
    }

}