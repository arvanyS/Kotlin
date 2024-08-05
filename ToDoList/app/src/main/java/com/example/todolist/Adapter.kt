package com.example.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.RecycleBinding

class Adapter(val List:ArrayList<Todos>):RecyclerView.Adapter<Adapter.viewHolder>() {

    class viewHolder(val binding:RecycleBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding=RecycleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  List.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.textView8.text = List[position].title
        holder.binding.textView2.text = "#${List[position].no.toString()}"

        holder.binding.imageButton.setOnClickListener() {
            List.removeAt(position)
            reIndexing(position)

            if (holder.itemView.context is MainActivity) {
                (holder.itemView.context as MainActivity).emptyControl()
            }
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, List.size)
            Toast.makeText(holder.itemView.context,"#${position+1} Deleted",Toast.LENGTH_SHORT).show()


        }

        holder.itemView.setOnClickListener() {
            val intent = Intent(holder.itemView.context, Det::class.java)
            intent.putExtra("selectedIndex", position)
            holder.itemView.context.startActivity(intent)

        }
    }



    }
