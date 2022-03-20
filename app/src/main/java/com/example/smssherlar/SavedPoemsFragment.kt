package com.example.smssherlar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smssherlar.databinding.CustomDialogBinding
import com.example.smssherlar.databinding.FragmentSavedPoemsBinding
import com.example.smssherlar.model.MySharedPreference
import com.example.smssherlar.model.Poem
import com.example.smssherlar.model.PoemAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class SavedPoemsFragment : Fragment() {
    lateinit var gson: Gson
    lateinit var poemAdapter: PoemAdapter
    lateinit var binding: FragmentSavedPoemsBinding
    lateinit var savedPoemList: ArrayList<Poem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        MySharedPreference.init(requireContext())
        gson = Gson()

        savedPoemList = ArrayList()
        binding = FragmentSavedPoemsBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        savedPoemList = loadData()


        poemAdapter = PoemAdapter(savedPoemList)
        binding.SavedPoemRV.adapter = poemAdapter

        poemAdapter.setOnMyItemClickListener(object : PoemAdapter.OnMyItemClickListener {
            override fun onClick(poem: Poem, position: Int) {
                val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
                val view =
                    CustomDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false)
                if (poem.poemLiked == true) {
                    view.likedIcon.setImageResource(R.drawable.ic_love_solid)
                } else {
                    view.likedIcon.setImageResource(R.drawable.ic_love_red)
                }

                view.poemName.text = poem.poemName
                view.poem.text = poem.poem
                view.poemLiked.setOnClickListener {
                    if (poem.poemLiked == true) {
                        view.likedIcon.setImageResource(R.drawable.ic_love_red)
                        poem.poemLiked = false
                    } else {
                        view.likedIcon.setImageResource(R.drawable.ic_love_solid)
                        poem.poemLiked = true
                    }
                    poemAdapter.notifyDataSetChanged()
                    // poemAdapter!!.notifyItemChanged(position)
                    if (poem.poemLiked == false) {
                        var key = poem.poemType
                        unSave(key!!, poem)
                        savedPoemList.remove(poem)
                        poemAdapter.notifyItemRemoved(position)
                        poemAdapter.notifyItemRangeRemoved(position, savedPoemList.size)


                        dialog.dismiss()
                    }
                }
                view.poemSending.setOnClickListener {
                    val sendIntent = Intent(Intent.ACTION_VIEW)
                    sendIntent.putExtra("sms_body", "${poem.poem}")
                    sendIntent.type = "vnd.android-dir/mms-sms"
                    startActivity(sendIntent)
                }

                view.poemCopy.setOnClickListener {
                    val clipboard =
                        requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip: ClipData = ClipData.newPlainText("label", "${poem.poem}")
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(requireContext(), "Poem copied", Toast.LENGTH_SHORT).show()
                }
                view.poemShare.setOnClickListener {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "${poem.poem}")
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
                dialog.setContentView(view.root)
                dialog.show()


            }


        })


        return binding.root
    }


    fun unSave(key: String, poem: Poem) {
        val type = object : TypeToken<ArrayList<Poem>>() {}.type

        when (key) {
            "love" -> {
                val loveList = gson.fromJson<ArrayList<Poem>>(
                    MySharedPreference.LovePoem,
                    type
                )
                for (love in loveList) {
                    if (love.poemName == poem.poemName) {
                        love.poemLiked = false
                        break
                    }
                }
                val toJson = gson.toJson(loveList)
                MySharedPreference.LovePoem = toJson
            }
            "miss" -> {
                val missList = gson.fromJson<ArrayList<Poem>>(
                    MySharedPreference.MissPoem,
                    type
                )
                for (miss in missList) {
                    if (miss.poemName == poem.poemName) {
                        miss.poemLiked = false
                        break
                    }
                }
                val toJson = gson.toJson(missList)
                MySharedPreference.MissPoem = toJson

            }
            "congratulation" -> {
                val congratulationList = gson.fromJson<ArrayList<Poem>>(
                    MySharedPreference.CongratulationPoem,
                    type
                )
                for (miss in congratulationList) {
                    if (miss.poemName == poem.poemName) {
                        miss.poemLiked = false
                        break
                    }
                }
                val toJson = gson.toJson(congratulationList)
                MySharedPreference.CongratulationPoem = toJson

            }

            "parent" -> {
                val parentList = gson.fromJson<ArrayList<Poem>>(
                    MySharedPreference.ParentPoem,
                    type
                )
                for (miss in parentList) {
                    if (miss.poemName == poem.poemName) {
                        miss.poemLiked = false
                        break
                    }
                }
                val toJson = gson.toJson(parentList)
                MySharedPreference.ParentPoem = toJson

            }
            "friendly" -> {
                val friendlyList = gson.fromJson<ArrayList<Poem>>(
                    MySharedPreference.FriendlyPoem,
                    type
                )
                for (miss in friendlyList) {
                    if (miss.poemName == poem.poemName) {
                        miss.poemLiked = false
                        break
                    }
                }
                val toJson = gson.toJson(friendlyList)
                MySharedPreference.FriendlyPoem = toJson

            }
            "joke" -> {
                val jokeList = gson.fromJson<ArrayList<Poem>>(
                    MySharedPreference.JokePoem,
                    type
                )
                for (miss in jokeList) {
                    if (miss.poemName == poem.poemName) {
                        miss.poemLiked = false
                        break
                    }
                }
                val toJson = gson.toJson(jokeList)
                MySharedPreference.JokePoem = toJson

            }
        }
    }


    private fun loadData(): ArrayList<Poem> {
        var saveList = ArrayList<Poem>()
        gson = Gson()
        val type = object : TypeToken<ArrayList<Poem>>() {}.type
        val loveList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.LovePoem, type)
        val missList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.MissPoem, type)
        val congratulationList =   gson.fromJson<ArrayList<Poem>>(MySharedPreference.CongratulationPoem, type)
        val parentList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.ParentPoem, type)
        val friendlyList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.FriendlyPoem, type)
        val jokeList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.JokePoem, type)
        for (poem in loveList) {

            if (poem.poemLiked == true) {
                saveList.add(poem)
            }
        }
        for (poem in missList) {
            if (poem.poemLiked == true) {
                saveList.add(poem)
            }
        }
        for (poem in congratulationList) {
            if (poem.poemLiked == true) {
                saveList.add(poem)
            }
        }
        for (poem in parentList) {
            if (poem.poemLiked == true) {
                saveList.add(poem)
            }
        }
        for (poem in friendlyList) {
            if (poem.poemLiked == true) {
                saveList.add(poem)
            }
        }
        for (poem in jokeList) {
            if (poem.poemLiked == true) {
                saveList.add(poem)
            }
        }
        return saveList
    }


}