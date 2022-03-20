package com.example.smssherlar.model

import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {
    private const val NAME = "POEMS"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreference: SharedPreferences

    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    var poem:String?
        get() = sharedPreference.getString("Poem",null)
        set(value) = sharedPreference.edit{
            it.putString("Poem",value)
        }
    var LovePoem:String?
    get() = sharedPreference.getString("LovePoem",null)
    set(value) = sharedPreference.edit{
        it.putString("LovePoem",value)
    }
    var MissPoem:String?
        get() = sharedPreference.getString("MissPoem",null)
        set(value) = sharedPreference.edit{
            it.putString("MissPoem",value)
        }
    var CongratulationPoem:String?
        get() = sharedPreference.getString("CongratulationPoem",null)
        set(value) = sharedPreference.edit{
            it.putString("CongratulationPoem",value)
        }
    var ParentPoem:String?
        get() = sharedPreference.getString("ParentPoem",null)
        set(value) = sharedPreference.edit{
            it.putString("ParentPoem",value)
        }
    var FriendlyPoem:String?
        get() = sharedPreference.getString("FriendlyPoem",null)
        set(value) = sharedPreference.edit{
            it.putString("FriendlyPoem",value)
        }
    var JokePoem:String?
        get() = sharedPreference.getString("JokePoem",null)
        set(value) = sharedPreference.edit{
            it.putString("JokePoem",value)
        }

}