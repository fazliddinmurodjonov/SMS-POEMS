package com.example.smssherlar

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.smssherlar.model.MySharedPreference
import com.example.smssherlar.model.Poem
import com.example.smssherlar.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var poemLoveList: ArrayList<Poem>
    lateinit var poemMissList: ArrayList<Poem>
    lateinit var poemCongratulationList: ArrayList<Poem>
    lateinit var poemParentList: ArrayList<Poem>
    lateinit var poemFriendlyList: ArrayList<Poem>
    lateinit var poemJokeList: ArrayList<Poem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MySharedPreference.init(requireContext())
        var gson = Gson()

        if (MySharedPreference.LovePoem == null) {
            poemLoveList = ArrayList()
            poemLoveList.add(
                Poem(
                    "ALDANISHNI YAXSHI KO‘RAMAN",
                    resources.getString(R.string.love_1),
                    "love",
                    false
                )
            )
            poemLoveList.add(
                Poem(
                    "Umrimga oq libos kiyib boraman!",
                    resources.getString(R.string.love_2),
                    "love",
                    false
                )
            )
            poemLoveList.add(
                Poem(
                    "Qo‘limdan kelmas!",
                    resources.getString(R.string.love_3),
                    "love",
                    false
                )
            )
            poemLoveList.add(
                Poem(
                    "Chindan sevgan insoning!",
                    resources.getString(R.string.love_4),
                    "love",
                    false
                )
            )
            poemLoveList.add(
                Poem(
                    "O‘ZINGIZSIZ",
                    resources.getString(R.string.love_5),
                    "love",
                    false
                )
            )
            val toJson = gson.toJson(poemLoveList)
            MySharedPreference.LovePoem = toJson
        }
        if (MySharedPreference.MissPoem == null) {
            poemMissList = ArrayList()
            poemMissList.add(
                Poem(
                    "Sog'inib charchadim",
                    resources.getString(R.string.miss_1),
                    "miss",
                    false
                )
            )
            poemMissList.add(
                Poem(
                    "Nega sevaveraman",
                    resources.getString(R.string.miss_2),
                    "miss",
                    false
                )
            )
            poemMissList.add(
                Poem(
                    "Sog'inib eslab qolaman",
                    resources.getString(R.string.miss_3),
                    "miss",
                    false
                )
            )
            poemMissList.add(
                Poem(
                    "Yoningizga qaytgim keladi",
                    resources.getString(R.string.miss_4),
                    "miss",
                    false
                )
            )
            poemMissList.add(
                Poem(
                    "Sen ham meni eslaysanmi?",
                    resources.getString(R.string.miss_5),
                    "miss",
                    false
                )
            )
            val toJson = gson.toJson(poemMissList)
            MySharedPreference.MissPoem = toJson
        }
        if (MySharedPreference.CongratulationPoem == null) {
            poemCongratulationList = ArrayList()
            for (poem in poemLoveList) {
                poem.poemType = "congratulation"
                poemCongratulationList.add(poem)
            }
            val toJson = gson.toJson(poemCongratulationList)
            MySharedPreference.CongratulationPoem = toJson
        }
        if (MySharedPreference.ParentPoem == null) {
            poemParentList = ArrayList()
            for (poem in poemLoveList) {
                poem.poemType = "parent"
                poemParentList.add(poem)
            }
            val toJson = gson.toJson(poemParentList)
            MySharedPreference.ParentPoem = toJson
        }
        if (MySharedPreference.FriendlyPoem == null) {
            poemFriendlyList = ArrayList()
            for (poem in poemLoveList) {
                poem.poemType = "friendly"
                poemFriendlyList.add(poem)
            }
            val toJson = gson.toJson(poemFriendlyList)
            MySharedPreference.FriendlyPoem = toJson
        }
        if (MySharedPreference.JokePoem == null) {
            poemJokeList = ArrayList()
            for (poem in poemLoveList) {
                poem.poemType = "joke"
                poemJokeList.add(poem)
            }
            val toJson = gson.toJson(poemJokeList)
            MySharedPreference.JokePoem = toJson
        }
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.NewsPoemsCount.text = "30"
        binding.SavedPoemsCount.text = "${loadCount()}"

        binding.SavedCardView.setOnClickListener {
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.savedPoemsFragment, null, navOption.build())
        }

        binding.NewsCardView.setOnClickListener {
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.newPoemsFragment, null, navOption.build())
        }
        binding.poemLoveFragment.setOnClickListener {
            val bundle = bundleOf("key" to "love")
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.poemFragment, bundle, navOption.build())
        }
        binding.poemMissFragment.setOnClickListener {
            val bundle = bundleOf("key" to "miss")
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.poemFragment, bundle, navOption.build())
        }
        binding.poemCongratulationFragment.setOnClickListener {
            val bundle = bundleOf("key" to "congratulation")
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.poemFragment, bundle, navOption.build())
        }
        binding.poemParentFragment.setOnClickListener {
            val bundle = bundleOf("key" to "parent")
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.poemFragment, bundle, navOption.build())
        }
        binding.poemFriendlyFragment.setOnClickListener {
            val bundle = bundleOf("key" to "friendly")
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.poemFragment, bundle, navOption.build())
        }
        binding.poemJokeFragment.setOnClickListener {
            val bundle = bundleOf("key" to "joke")
            val navOption = NavOptions.Builder()
            navOption.setEnterAnim(R.anim.exit_anim)
            navOption.setExitAnim(R.anim.enter_pop_anim)
            navOption.setPopEnterAnim(R.anim.enter_anim)
            navOption.setPopExitAnim(R.anim.exit_pop_anim)
            findNavController().navigate(R.id.poemFragment, bundle, navOption.build())
        }



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val loadCount = loadCount()
        val bind = FragmentHomeBinding.inflate(layoutInflater)
        bind.SavedPoemsCount.text = "$loadCount"
    }

    fun loadCount(): Int {
        var gson = Gson()
        var count = 0
        val type = object : TypeToken<ArrayList<Poem>>() {}.type
        val loveList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.LovePoem, type)
        val missList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.MissPoem, type)
        val congratulationList =
            gson.fromJson<ArrayList<Poem>>(MySharedPreference.CongratulationPoem, type)
        val parentList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.ParentPoem, type)
        val friendlyList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.FriendlyPoem, type)
        val jokeList = gson.fromJson<ArrayList<Poem>>(MySharedPreference.JokePoem, type)

        for (poem in loveList) {
            if (poem.poemLiked == true) {
                count++
            }
        }
        for (poem in missList) {
            if (poem.poemLiked == true) {
                count++
            }
        }
        for (poem in congratulationList) {
            if (poem.poemLiked == true) {
                count++
            }
        }
        for (poem in parentList) {
            if (poem.poemLiked == true) {
                count++
            }
        }
        for (poem in friendlyList) {
            if (poem.poemLiked == true) {
                count++
            }
        }
        for (poem in jokeList) {
            if (poem.poemLiked == true) {
                count++
            }
        }


        return count
    }


}