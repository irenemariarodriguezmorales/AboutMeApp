package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Variable para la vinculación
    //Variable que hace uso de la clase de datos
    private val myName: com.example.aboutme.MyName = com.example.aboutme.MyName("Irene")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Establecemos el layout a la variable de vinculación
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Establecemos la clase de datos a la variable de vinculación
        binding.myName = myName
        //Establecemos los Listener para el botón Done y el texto del nickname
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
        binding.nicknameText.setOnClickListener {
            updateNickname(it)
        }
    }

    //Función que añade el Nickname introducido por el usuario
    private fun addNickname(view: View) {
        /*Este bloque aplica las sentencias dentro del ámbito de la vinculación
          Las sentencias recogen el texto introducido y lo establecen, esconden el botón
          y el texto de edición y muestran el texto establecido */
        binding.apply {
            nicknameText.text = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
        //Oculta el teclado
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //Función que actualiza el Nickname introducido por el usuario
    private fun updateNickname (view: View) {
        /*
          Las sentencias muestran el botón y el texto de edición estableciéndole el foco
          y esconden el texto introducido anteriormente por el usuario */
        binding.apply {
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nicknameText.visibility = View.GONE
            nicknameEdit.requestFocus()
        }

        //Muestra el teclado
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)
    }
}