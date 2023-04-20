package com.example.noteapp.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteEditBinding
import com.example.noteapp.viewmodel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NoteEditFragment : Fragment() {
    lateinit var binding: FragmentNoteEditBinding
    val viewModel: NoteViewModel by viewModels()
    var priority="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         binding= FragmentNoteEditBinding.inflate(inflater,container,false)
         binding.title.setText(arguments?.getString("title"))
         binding.notes.setText(arguments?.getString("notes"))
         binding.subtitle.setText(arguments?.getString("subtitle"))
         var pri=arguments?.getString("priority")
          var id=arguments?.getInt("id")


        when(pri){
            "1"->{
                priority="1"
                binding.blue.setImageResource(R.drawable.done2)
                binding.red.setImageResource(0)
                binding.green.setImageResource(0)
            }
            "2"->{
                priority="2"
                binding.green.setImageResource(R.drawable.done2)
                binding.red.setImageResource(0)
                binding.blue.setImageResource(0)
            }
            "3"->{
                priority="3"
                binding.red.setImageResource(R.drawable.done2)
                binding.blue.setImageResource(0)
                binding.green.setImageResource(0)
            }
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



        binding.addNoteCreate.setOnClickListener {
            updateNote(it)
        }
        binding.deleteNote.setOnClickListener {
            val bottomSheetDialog:BottomSheetDialog=BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(R.layout.dialog_delete)
            bottomSheetDialog.show()

            val yes=bottomSheetDialog.findViewById<TextView>(R.id.yes)
            val no=bottomSheetDialog.findViewById<TextView>(R.id.no)
            yes?.setOnClickListener {
                if (id != null) {
                    viewModel.deleteNote(id)
                    findNavController().navigate(R.id.action_noteEditFragment_to_noteListFragment)
                    bottomSheetDialog.dismiss()
                }
            }
            no?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateNote(it:View?){
        val title=binding.title.text.toString()
        val subtitle=binding.subtitle.text.toString()
        val note=binding.notes.text.toString()

        val formatter = DateTimeFormatter.ofPattern("MMMM d,yyyy")
        val currentime = LocalDateTime.now().format(formatter)
        ///  println(currentime)

        var id=arguments?.getInt("id")
        val data= com.example.noteapp.model.Note(
            id, title = title, subtitle = subtitle, notes = note,currentime.toString(),priority
        )
        viewModel.updateNote(data)
        Toast.makeText(requireContext(),"Note Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_noteEditFragment_to_noteListFragment)
    }

}