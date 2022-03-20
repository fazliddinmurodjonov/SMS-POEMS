package com.example.smssherlar

import android.app.Dialog
import android.app.Fragment
import android.content.*
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.example.smssherlar.databinding.CustomDialogBinding
import com.example.smssherlar.databinding.FragmentPoemBinding
import com.example.smssherlar.model.MySharedPreference
import com.example.smssherlar.model.Poem
import com.example.smssherlar.model.PoemAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PoemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoemFragment : android.app.Fragment() {

    private lateinit var binding: FragmentPoemBinding
    var poemAdapter: PoemAdapter? = null
    var key: String? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MySharedPreference.init(requireContext())
        val gson = Gson()
//        var poemList: ArrayList<Poem>
//        poemList = ArrayList()
        binding = FragmentPoemBinding.inflate(inflater, container, false)
        key = arguments?.getString("key")
        val loadDate = loadData(key!!)
        poemAdapter = PoemAdapter(loadDate)

        binding.PoemTypesRV.adapter = poemAdapter

        poemAdapter!!.setOnMyItemClickListener(object : PoemAdapter.OnMyItemClickListener {
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
                    //poemAdapter!!.notifyItemChanged(position)
                    when (key) {
                        "love" -> {
                            val toJson = gson.toJson(loadDate)
                            MySharedPreference.LovePoem = toJson
                            poemAdapter!!.notifyDataSetChanged()

                        }
                        "miss" -> {
                            val toJson = gson.toJson(loadDate)
                            MySharedPreference.MissPoem = toJson
                            poemAdapter!!.notifyDataSetChanged()

                        }
                        "congratulation" -> {
                            val toJson = gson.toJson(loadDate)
                            MySharedPreference.CongratulationPoem = toJson
                            poemAdapter!!.notifyDataSetChanged()

                        }
                        "parent" -> {
                            val toJson = gson.toJson(loadDate)
                            MySharedPreference.ParentPoem = toJson
                            poemAdapter!!.notifyDataSetChanged()

                        }
                        "friendly" -> {
                            val toJson = gson.toJson(loadDate)
                            MySharedPreference.FriendlyPoem = toJson
                            poemAdapter!!.notifyDataSetChanged()

                        }
                        "joke" -> {
                            val toJson = gson.toJson(loadDate)
                            MySharedPreference.JokePoem = toJson
                            poemAdapter!!.notifyDataSetChanged()

                        }
                    }


                }
                view.poemSending.setOnClickListener {
                    val sendIntent = Intent(Intent.ACTION_VIEW)
                    sendIntent.putExtra("sms_body", "${poem.poem}")
                    sendIntent.type = "vnd.android-dir/mms-sms"
                    startActivity(sendIntent)
                }

                view.poemCopy.setOnClickListener {
                    val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
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
        binding.backButton.setOnClickListener {

            when (key) {
                "love" -> {
                    findNavController().popBackStack()
                }
                "miss" -> {
                    findNavController().popBackStack()
                }
                "congratulation" -> {
                    findNavController().popBackStack()
                }
                "parent" -> {
                    findNavController().popBackStack()
                }
                "friendly" -> {
                    findNavController().popBackStack()
                }
                "joke" -> {
                    findNavController().popBackStack()

                }
            }

        }
        return binding.root
    }

    fun loadData(key: String): ArrayList<Poem> {
        var gson = Gson()
        val type = object : TypeToken<ArrayList<Poem>>() {}.type
        var poemList: ArrayList<Poem>
        poemList = ArrayList()
        when (key) {
            "love" -> {
                val fromJson = gson.fromJson<ArrayList<Poem>>(MySharedPreference.LovePoem, type)
                poemList = fromJson
            }
            "miss" -> {
                val fromJson = gson.fromJson<ArrayList<Poem>>(MySharedPreference.MissPoem, type)
                poemList = fromJson
            }
            "congratulation" -> {
                val fromJson =
                    gson.fromJson<ArrayList<Poem>>(MySharedPreference.CongratulationPoem, type)
                poemList = fromJson
            }
            "parent" -> {
                val fromJson = gson.fromJson<ArrayList<Poem>>(MySharedPreference.ParentPoem, type)
                poemList = fromJson
            }
            "friendly" -> {
                val fromJson = gson.fromJson<ArrayList<Poem>>(MySharedPreference.FriendlyPoem, type)
                poemList = fromJson
            }
            "joke" -> {
                val fromJson = gson.fromJson<ArrayList<Poem>>(MySharedPreference.JokePoem, type)
                poemList = fromJson
            }
        }
        return poemList
    }


}