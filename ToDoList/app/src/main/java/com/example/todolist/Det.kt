package com.example.todolist

import android.app.Notification.Action
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivityDetBinding

class Det : AppCompatActivity() {
    private lateinit var binding:ActivityDetBinding
    var position:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityDetBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar=binding.toolbar3
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)

        position=intent.getIntExtra("selectedIndex",0)

        binding.textView9.text="#${List[position].no.toString()}"
        binding.textView5.text=List[position].title
        binding.textView6.text=List[position].content


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
    fun editTodo(view: View){
        val intent= Intent(this,EditTodo::class.java)
        intent.putExtra("IndextoEditPage",position)
        startActivity(intent)
    }
    fun deleteTodo(view: View){
        List.removeAt(position)
        reIndexing(position)
        Toast.makeText(this,"#${position+1} Deleted", Toast.LENGTH_SHORT).show()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
}