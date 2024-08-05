package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivityEditTodoBinding

class EditTodo : AppCompatActivity() {
    var intentfromDetails:Int=0
    private lateinit var binding: ActivityEditTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityEditTodoBinding.inflate(layoutInflater)
        val view=binding.root
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar=binding.toolbar4
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)

        intentfromDetails=intent.getIntExtra("IndextoEditPage",0)

        binding.titleText.setText(List[intentfromDetails].title)
        binding.contenText.setText(List[intentfromDetails].content)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Geri butonuna basıldığında ne yapılacağını buraya yaz
                onBackPressed() // Varsayılan geri basma davranışı
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun saveTodo(view: View){
        List[intentfromDetails].title=binding.titleText.text.toString()
        List[intentfromDetails].content=binding.contenText.text.toString()

        Toast.makeText(this,"#${intentfromDetails+1} Saved",Toast.LENGTH_SHORT).show()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

}