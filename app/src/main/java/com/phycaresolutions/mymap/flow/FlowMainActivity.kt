package com.phycaresolutions.mymap.flow

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.phycaresolutions.mymap.R
import com.phycaresolutions.mymap.databinding.ActivityFlowMainBinding
import kotlinx.coroutines.launch

class FlowMainActivity : AppCompatActivity() {
    private lateinit var viewModel: CommentsViewModel

    // create a view binding variable
    private lateinit var binding: ActivityFlowMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFlowMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)

        // Listen for the button click event to search
        binding.button.setOnClickListener {

            // check to prevent api call with no parameters
            if (binding.searchEditText.text.isNullOrEmpty()) {
                Toast.makeText(this, "Query Can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                // if Query isn't empty, make the api call
                viewModel.getNewComments(binding.searchEditText.text.toString().toInt())
            }
            // Since flow run asynchronously,
            // start listening on background thread
            lifecycleScope.launch {
                viewModel.commentState.collect {

                    // When state to check the
                    // state of received data
                    when (it.status) {

                        // If its loading state then
                        // show the progress bar
                        CommentApiState.Status.LOADING -> {
                            binding.progressBar.isVisible = true
                        }
                        // If api call was a success , Update the Ui with
                        // data and make progress bar invisible
                        CommentApiState.Status.SUCCESS -> {
                            binding.progressBar.isVisible = false

                            // Received data can be null, put a check to prevent
                            // null pointer exception
                            it.data?.let { comment ->
                                binding.commentIdTextview.text = comment.id.toString()
                                binding.nameTextview.text = comment.name
                                binding.emailTextview.text = comment.email
                                binding.commentTextview.text = comment.comment
                            }
                        }
                        // In case of error, show some data to user
                        else -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@FlowMainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                            Log.e("TAG","Error: "+it.message);
                        }
                    }
                }
            }
        }
    }
}

/*
Flow we can handle streams of data asynchronously which is being executed sequentially.*/
