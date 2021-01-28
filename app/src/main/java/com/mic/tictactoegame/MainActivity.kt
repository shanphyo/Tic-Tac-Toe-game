package com.mic.tictactoegame

import android.app.Application
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var userOneTurn=true
    var userTwoTurn=false
    var data = Array(3) { arrayOfNulls<String>(3) }
    var count=0
    var useronescold=0;
    var usertwoscold=0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_btnOne.setOnClickListener {
            readButtonData(0,0,btn_btnOne)


        }
        btn_btnTwo.setOnClickListener {
            readButtonData(0,1,btn_btnTwo)
        }
        btn_btnThree.setOnClickListener {
            readButtonData(0,2,btn_btnThree)

        }
        btn_btnFour.setOnClickListener {
            readButtonData(1,0,btn_btnFour)

        }
        btn_btnFive.setOnClickListener {
            readButtonData(1,1,btn_btnFive)
        }
        btn_btnSix.setOnClickListener {
            readButtonData(1,2,btn_btnSix)

        }
        btn_btnSeven.setOnClickListener {
            readButtonData(2,0,btn_btnSeven)

        }
        btn_btnEight.setOnClickListener {
            readButtonData(2,1,btn_btnEight)
        }
        btn_btnNine.setOnClickListener {
            readButtonData(2,2,btn_btnNine)

        }
        btn_reset.setOnClickListener {
            userOneTurn=true
            userTwoTurn=false
            data= Array(3) { arrayOfNulls<String>(3) }
            count=0

            completeGame(true)
            resetGame()
        }

    }
   fun readButtonData(dataX:Int,dataY:Int,button:Button){
       if(userOneTurn){
          // button.setBackgroundColor(Color.GREEN)
              button.setText("O")
              button.setTextColor(Color.GREEN)

           button.isClickable=false
           userOneTurn=false
           userTwoTurn=true
           data[dataX][dataY]="green"
           count++
           if(count>4 && count<9){
               if(checkWinner()){
                   resultDialog("User One is Winner","Winner")
                   useronescold += 1
                   txt_useronecount.setText(useronescold.toString())
                   completeGame(false)
               }
           }else if(count==9 ){
               if(checkWinner()){
                   resultDialog("User One is Winner","Winner")
                   useronescold += 1
                   txt_useronecount.setText(useronescold.toString())
                   completeGame(false)
               }else{
                   System.out.println("Drawer")
               }

           }


       }else{
           button.setText("*")
           button.setTextColor(Color.RED)
           button.isClickable=false
           userOneTurn=true
           userTwoTurn=false
           data[dataX][dataY]="red"
           count++
           if(count>4 && count<9){
               if(checkWinner()){
                   resultDialog("User Two is Winner","Winner")
                   usertwoscold += 1
                   txt_usertwocount.setText(usertwoscold.toString())
                   completeGame(false)
               }
           }else if(count==9 ){
               if(checkWinner()){
                   resultDialog("User Two is Winner","Winner")
                   usertwoscold += 1
                   txt_usertwocount.setText(usertwoscold.toString())
                   completeGame(false)
               }else{
                   resultDialog("","Drawer")

               }
           }
       }



   }
    fun checkWinner():Boolean{

            for (i in 0..2) {
                if (data[i][0] == data[i][1] && data[i][0] == data[i][2] && data[i][0] != null) {
                   return true
                }
            }

            for (i in 0..2) {
                if (data[0][i] == data[1][i] && data[0][i] == data[2][i] && data[0][i] != null) {
                    return true
                }
            }
            if (data[0][0] == data[1][1] && data[0][0] == data[2][2] && data[0][0] != null) {
                return true
            }
            if (data[0][2] == data[1][1] && data[0][2] == data[2][0] && data[0][2] != null) {
                return true
            }else{
                return false
            }

    }

    fun completeGame( result:Boolean){
        btn_btnOne.isClickable=result
        btn_btnTwo.isClickable=result
        btn_btnThree.isClickable=result
        btn_btnFour.isClickable=result
        btn_btnFive.isClickable=result
        btn_btnSix.isClickable=result
        btn_btnSeven.isClickable=result
        btn_btnEight.isClickable=result
        btn_btnNine.isClickable=result
    }
    fun resetGame(){
        btn_btnOne.setText("")
        btn_btnTwo.setText("")
        btn_btnThree.setText("")
        btn_btnFour.setText("")
        btn_btnFive.setText("")
        btn_btnSix.setText("")
        btn_btnSeven.setText("")
        btn_btnEight.setText("")
        btn_btnNine.setText("")

    }

    fun resultDialog(data:String,title:String) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, android.R.style.Holo_SegmentedButton))

        with(builder)
        {
            setTitle(title)
            setMessage(data)
            //setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }
    }
}