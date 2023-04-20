package com.example.noteapp.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteCreateBinding
import com.example.noteapp.viewmodel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class NoteCreateFragment : Fragment() {
   private lateinit var binding: FragmentNoteCreateBinding
    var priority :String="1"
    var bgcolor="1"
    val viewModel:NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding= FragmentNoteCreateBinding.inflate(inflater,container,false)

        binding.noteCreate.setOnClickListener {
             createNote(it)
        }

        binding.blue.setOnClickListener {
            priority="1"
            binding.blue.setImageResource(R.drawable.done2)
            binding.red.setImageResource(0)
            binding.green.setImageResource(0)
        }
        binding.green.setOnClickListener {
            priority="2"
            binding.green.setImageResource(R.drawable.done2)
            binding.red.setImageResource(0)
            binding.blue.setImageResource(0)
        }
        binding.red.setOnClickListener {
            priority="3"
            binding.red.setImageResource(R.drawable.done2)
            binding.blue.setImageResource(0)
            binding.green.setImageResource(0)
        }


        when(bgcolor){
            "1"->{
                bgcolor ="1"
            }
            "2"->{
                bgcolor ="2"
            }
            "3"->{
                bgcolor ="3"
            }
        }


        return binding.root
    }




    @RequiresApi(Build.VERSION_CODES.O)
    fun createNote(it:View?){
        val title=binding.title.text.toString()
        val subtitle=binding.subtitle.text.toString()
        val note=binding.notes.text.toString()

        val formatter = DateTimeFormatter.ofPattern("MMMM d,yyyy")
        val currentime = LocalDateTime.now().format(formatter)
        ///  println(currentime)


        val data= com.example.noteapp.model.Note(
            null, title = title, subtitle = subtitle, notes = note,currentime.toString(),priority,bgcolor
        )
        viewModel.addNote(data)
        Toast.makeText(requireContext(),"Note Created",Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_noteCreateFragment2_to_noteListFragment)

    }
}