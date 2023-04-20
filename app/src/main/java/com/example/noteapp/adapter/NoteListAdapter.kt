package com.example.noteapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.fragment.NoteListFragmentDirections
import com.example.noteapp.model.Note
import com.squareup.picasso.Picasso
import java.util.ArrayList

class NoteListAdapter(val context:Context, var noteList:List<Note>):RecyclerView.Adapter<NoteListAdapter.ViewHolder>(){
    class ViewHolder(val binding: NoteItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListAdapter.ViewHolder {

        return ViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }



    fun setFilter(coin: List<Note>) {
        this.noteList= coin.toMutableList() as ArrayList<Note>
        notifyDataSetChanged()
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: NoteListAdapter.ViewHolder, position: Int) {
        val data=noteList[position]
        holder.binding.itemTitle.text=data.title
        holder.binding.itemNotes.text=data.notes
        holder.binding.itemDate.text=data.date.toString()
        holder.binding.subtitle.text=data.subtitle

        when(data.priority){
            "1"->{
              holder.binding.itemColor.setBackgroundResource(R.drawable.blue)
            }
            "2"->{
                holder.binding.itemColor.setBackgroundResource(R.drawable.green)
            }
            "3"->{
                holder.binding.itemColor.setBackgroundResource(R.drawable.red)

            }
        }


        holder.binding.root.setOnClickListener {
            val bundle = bundleOf(
             "title" to data.title,
              "notes" to data.notes,
                "date" to data.notes,
                "priority" to data.priority,
                "subtitle" to data.subtitle,
                "id" to data.id,
            )
            Navigation.findNavController(it).navigate(R.id.action_noteListFragment_to_noteEditFragment,bundle)

        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}



