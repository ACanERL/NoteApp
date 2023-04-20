package com.example.noteapp.fragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteListAdapter
import com.example.noteapp.databinding.FragmentNoteListBinding
import com.example.noteapp.viewmodel.NoteViewModel
import org.w3c.dom.Text
import java.util.*

class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    val viewModel: NoteViewModel by viewModels()
    var oldNotes =arrayListOf<com.example.noteapp.model.Note>()
    lateinit var adapter: NoteListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentNoteListBinding.inflate(inflater,container,false)
       setHasOptionsMenu(true)
        binding.addNotefab.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_noteCreateFragment2)
        }
        binding.innerFab.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_noteCreateFragment2)
        }
        viewModel.getAllNote().observe(viewLifecycleOwner, Observer { notelist->
            oldNotes = notelist as ArrayList<com.example.noteapp.model.Note>
            binding.rvNote.layoutManager=GridLayoutManager(requireContext(),2)
            adapter= NoteListAdapter(requireContext(), noteList = notelist)
            binding.rvNote.adapter=adapter

        })

        binding.openNote.setOnClickListener {
            viewModel.getAllNote().observe(viewLifecycleOwner, Observer { notelist->
                oldNotes=notelist as ArrayList<com.example.noteapp.model.Note>
                binding.rvNote.layoutManager=GridLayoutManager(requireContext(),2)
                adapter= NoteListAdapter(requireContext(), noteList = notelist)
                binding.rvNote.adapter=adapter

            })
        }

        binding.high.setOnClickListener {
            viewModel.getHighNote().observe(viewLifecycleOwner, Observer { notelist->
                oldNotes=notelist as ArrayList<com.example.noteapp.model.Note>
                binding.rvNote.layoutManager=GridLayoutManager(requireContext(),2)
                adapter= NoteListAdapter(requireContext(), noteList = notelist)
                binding.rvNote.adapter=adapter

            })
        }

        binding.medium.setOnClickListener {
            viewModel.getMediumNote().observe(viewLifecycleOwner, Observer { notelist->
                oldNotes=notelist as ArrayList<com.example.noteapp.model.Note>
                binding.rvNote.layoutManager=GridLayoutManager(requireContext(),2)
                adapter= NoteListAdapter(requireContext(), noteList = notelist)
                binding.rvNote.adapter=adapter

            })
        }
        binding.low.setOnClickListener {
            viewModel.getLowNote().observe(viewLifecycleOwner, Observer { notelist->
                oldNotes=notelist as ArrayList<com.example.noteapp.model.Note>
                binding.rvNote.layoutManager=GridLayoutManager(requireContext(),2)
                adapter= NoteListAdapter(requireContext(), noteList = notelist)
                binding.rvNote.adapter=adapter

            })
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item =menu.findItem(R.id.app_bar_search)
        val searchview=item.actionView as SearchView
        searchview.queryHint="Note here..."
        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    filter(p0)
                }
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun filter(text: String) {
        val list=ArrayList<com.example.noteapp.model.Note>()
        for (model in oldNotes) {
            if (model.title.lowercase().contains(text) || model.subtitle.contains(text)) {
                list.add(model)
            }
          }
            if(list.isEmpty()){
                Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
            }
            else {
                adapter.setFilter(list)
            }

    }
}