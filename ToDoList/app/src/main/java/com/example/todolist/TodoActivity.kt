package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ActivityTodo2Binding

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodo2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTodo2Binding.inflate(layoutInflater)
        val view=binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar=binding.toolbar2
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
    }
    fun addtoList(view: View){

        if(!textControl()){
            Toast.makeText(this,"Title or content cannot be empty!",Toast.LENGTH_SHORT).show()
        }
        else{
            index++
            val a:Todos = Todos(index,binding.titleText.text.toString(),binding.contenText.text.toString())
            List.add(a)

            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
    private fun textControl():Boolean{
        return !(binding.titleText.text.toString().trim().isEmpty() || binding.contenText.text.toString().trim().isEmpty())
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
}