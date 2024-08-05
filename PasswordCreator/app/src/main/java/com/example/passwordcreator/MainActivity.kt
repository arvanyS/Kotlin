package com.example.passwordcreator

import android.app.AlertDialog
import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.text.ClipboardManager
import android.text.InputType
import android.view.View
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.passwordcreator.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var copyText:TextView
    private var upperCase=false
    private var lowerCase=false
    private var numbers=false
    private var symbols=false
    private var length=0
    private var password=""
    private lateinit var uppercaseList: Array<String>
    private lateinit var lowercaseList: Array<String>
    private lateinit var numbersList: Array<String>
    private lateinit var symbolsList: Array<String>
    private lateinit var unitedArray: Array<String>
    private lateinit var mixArray: Array<String>
    private var mixArrayRandom=0
    private var controlValue:Boolean=false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.editTextText2.inputType = InputType.TYPE_NULL

        uppercaseList = arrayOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
        lowercaseList = arrayOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
        numbersList = arrayOf("0","1","2","3","4","5","6","7","8","9")
        symbolsList = arrayOf("!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","]","{","}",";",":","'",",","<",">",".","/","?","|","~")

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                uppercaseOpen()
            } else {
                uppercaseClose()
            }
        }
        binding.switch2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                lowercaseOpen()
            } else {
                lowercaseClose()
            }
        }
        binding.switch3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                numbersOpen()
            } else {
                numbersClose()
            }
        }

        binding.switch4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                symbolsOpen()
            } else {
                symbolsClose()
            }
        }

    }
    private fun uppercaseOpen() {
        upperCase=true
    }

    private fun uppercaseClose() {
        upperCase=false
    }
    private fun lowercaseOpen() {
        lowerCase=true
    }

    private fun lowercaseClose() {
        lowerCase=false
    }
    private fun numbersOpen() {
        numbers=true
    }

    private fun numbersClose() {
        numbers=false
    }
    private fun symbolsOpen() {
        symbols=true
    }

    private fun symbolsClose() {
        symbols=false
    }
    private fun lengthControlMin():Boolean{
        return length<8
    }
    private fun lengthControlMax():Boolean{
        return length>20
    }

    fun buttonClick(view: View){


        length = binding.editTextNumberPassword.text.toString().toInt()


        if(lengthControlMin()){
            alertDialog("The password must be at least 8 characters long!")
        }
        else if(lengthControlMax()){
            alertDialog("The password can be at most 20 characters long!")
        }
        else if(!upperCase&&!lowerCase&&!numbers&&!symbols) {
            alertDialog("Please select at least one password character")
        }
        else{
            copyText=binding.textView
            copyText.visibility=View.VISIBLE

            createMixArray()


            while (!controlValue){
                password=""
                createPassword()
                controlPassword()
            }
            controlValue=false
            printPassword()
        }
    }
    private fun alertDialog(a:String){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("ERROR!")
        alertDialog.setMessage(a)
        alertDialog.show()
    }
    private  fun printPassword(){
        binding.editTextText2.setText(password)
    }
    private fun createMixArray(){


            unitedArray = when {
                upperCase && !lowerCase && !numbers && !symbols -> uppercaseList
                !upperCase && lowerCase && !numbers && !symbols -> lowercaseList
                !upperCase && !lowerCase && numbers && !symbols -> numbersList
                !upperCase && !lowerCase && !numbers && symbols -> symbolsList
                upperCase && lowerCase && !numbers && !symbols -> uppercaseList + lowercaseList
                upperCase && !lowerCase && numbers && !symbols -> uppercaseList + numbersList
                upperCase && !lowerCase && !numbers && symbols -> uppercaseList + symbolsList
                !upperCase && lowerCase && numbers && !symbols -> lowercaseList + numbersList
                !upperCase && lowerCase && !numbers && symbols -> lowercaseList + symbolsList
                !upperCase && !lowerCase && numbers && symbols -> numbersList + symbolsList
                upperCase && lowerCase && numbers && !symbols -> uppercaseList + lowercaseList + numbersList
                upperCase && lowerCase && !numbers && symbols -> uppercaseList + lowercaseList + symbolsList
                upperCase && !lowerCase && numbers && symbols -> uppercaseList + numbersList + symbolsList
                !upperCase && lowerCase && numbers && symbols -> lowercaseList + numbersList + symbolsList
                else -> uppercaseList + lowercaseList + numbersList + symbolsList
                }




            mixArray = unitedArray.toList().shuffled().toTypedArray()



    }
    private fun createPassword(){
        for (i in 0 until length) {
            mixArrayRandom = Random.nextInt(0,mixArray.size)
            password+=mixArray[mixArrayRandom]

        }
    }
    private fun controlPassword() {
        controlValue = when {
            upperCase && !lowerCase && !numbers && !symbols -> true
            !upperCase && lowerCase && !numbers && !symbols -> true
            !upperCase && !lowerCase && numbers && !symbols -> true
            !upperCase && !lowerCase && !numbers && symbols -> true
            upperCase && lowerCase && !numbers && !symbols -> passwordControl(uppercaseList,lowercaseList)
            upperCase && !lowerCase && numbers && !symbols -> passwordControl(uppercaseList,numbersList)
            upperCase && !lowerCase && !numbers && symbols -> passwordControl(uppercaseList,symbolsList)
            !upperCase && lowerCase && numbers && !symbols -> passwordControl(lowercaseList,numbersList)
            !upperCase && lowerCase && !numbers && symbols -> passwordControl(lowercaseList,symbolsList)
            !upperCase && !lowerCase && numbers && symbols -> passwordControl(numbersList,symbolsList)
            upperCase && lowerCase && numbers && !symbols -> passwordControl(uppercaseList,lowercaseList,numbersList)
            upperCase && lowerCase && !numbers && symbols -> passwordControl(uppercaseList,lowercaseList,symbolsList)
            upperCase && !lowerCase && numbers && symbols -> passwordControl(uppercaseList,symbolsList,numbersList)
            !upperCase && lowerCase && numbers && symbols -> passwordControl(lowercaseList,symbolsList,numbersList)
            else ->  passwordControl(lowercaseList,symbolsList,numbersList,uppercaseList)
            }
    }

    private fun passwordControl(a:Array<String>,b:Array<String>):Boolean{
        val containsFromFirstArray = a.any { char -> password.contains(char) }
        val containsFromSecondArray = b.any { char -> password.contains(char) }

        return containsFromFirstArray && containsFromSecondArray
    }
    private fun passwordControl(a:Array<String>,b:Array<String>,c:Array<String>):Boolean{
        val containsFromFirstArray = a.any { char -> password.contains(char) }
        val containsFromSecondArray = b.any { char -> password.contains(char) }
        val containsFromThirdArray =c.any(){char -> password.contains(char)}

        return containsFromFirstArray && containsFromSecondArray && containsFromThirdArray
    }
    private fun passwordControl(a:Array<String>,b:Array<String>,c:Array<String>,d:Array<String>):Boolean{
        val containsFromFirstArray = a.any { char -> password.contains(char) }
        val containsFromSecondArray = b.any { char -> password.contains(char) }
        val containsFromThirdArray =c.any(){char -> password.contains(char)}
        val containsFromFourthArray =d.any(){char -> password.contains(char)}

        return containsFromFirstArray && containsFromSecondArray && containsFromThirdArray && containsFromFourthArray
    }

    fun copyToClipboard(view: View) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText( "copied",password)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(this, "The password copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}