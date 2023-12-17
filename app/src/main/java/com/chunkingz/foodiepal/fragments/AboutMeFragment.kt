package com.chunkingz.foodiepal.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.chunkingz.foodiepal.R
import com.chunkingz.foodiepal.adapter.AboutMeAdapter

class AboutMeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val aboutMeDetails: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)
        recyclerView = view.findViewById(R.id.aboutMeRecyclerView)
        addButton = view.findViewById(R.id.addDetailButton)
        val adapter = AboutMeAdapter(aboutMeDetails)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        addButton.setOnClickListener {
            showAddDetailDialog(adapter)
        }
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddDetailDialog(adapter: AboutMeAdapter) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_about_me_detail, null)
        val detailEditText: EditText = dialogView.findViewById(R.id.detailEditText)

        builder.setView(dialogView)
            .setTitle("Add Detail")
            .setPositiveButton("Add") { _, _ ->
                val detail = detailEditText.text.toString()
                aboutMeDetails.add(detail)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
        builder.create().show()
    }

}