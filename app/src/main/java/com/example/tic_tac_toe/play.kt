package com.example.tic_tac_toe

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_play.*
import java.io.File

class play : AppCompatActivity() {
    private var player1Shape:String=""
    private var player2Shape:String=""
    private var player1Name:String=""
    private var player2Name:String=""
    private var turn:Int=0
    private val  message:String=""
    val board: Array<IntArray> = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        txtMessage.text= intent.getStringExtra("KEY")
        //rtaBar.visibility=View.INVISIBLE
        txtNav.setOnTouchListener(object : OnSwipeListener(this) {
            init {
                setDragHorizontal(true)
                setExitScreenOnSwipe(true)
                setAnimationDelay(500)
            }

            override fun onSwipeLeft(distance: Float) {
                Toast.makeText(applicationContext, "swiped left!", Toast.LENGTH_SHORT).show()
                goBackActivity()
            }

            override fun onSwipeRight(distance: Float) {
                Toast.makeText(applicationContext, "swiped right!", Toast.LENGTH_SHORT).show()
                txtNav.visibility=View.INVISIBLE
                finish()
                startActivity(getIntent())
            }
        })
        readFileInternal("data.txt")
    }
    private fun readFileInternal(filename: String) {
        val file = File(this.filesDir, filename)
        val contents = file.readLines() // Read file by lines
        //val contentsAll = file.readText()  // Read all text

        //val first_line = contents[0]
        //to read line i:
        //              text_to_show = contents[i]
        player1Shape=contents[1]
        player2Shape=contents[3]
        player1Name=contents[0]
        player2Name=contents[2]
        turn=1
        txtP1Name.text = "${contents[0]} \n${contents[1]}"
        txtP2Name.text = "${contents[2]} \n${contents[3]}"
        imgTurnP1.visibility = View.VISIBLE
        imgTurnP2.visibility = View.INVISIBLE

    }
    fun goBackActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("key2", "Welcome Back!!")
        startActivity(intent)
    }
    private fun setShape(imgView:ImageView, shape:String) {
        if (shape == "X") {
            imgView.setImageResource(R.drawable.x)
        }
        if (shape == "O") {
            imgView.setImageResource(R.drawable.o)
        }
        if (shape == "Star") {
            imgView.setImageResource(R.drawable.star)
        }
        if (shape == "StarD") {
            imgView.setImageResource(R.drawable.stard)
        }
        if (shape == "Pentagon") {
            imgView.setImageResource(R.drawable.pentagon)
        }
        if (shape == "Hexagon") {
            imgView.setImageResource(R.drawable.hexagon)
        }
        if (shape == "Heart") {
            imgView.setImageResource(R.drawable.heart)
        }
        if (shape == "Diamond") {
            imgView.setImageResource(R.drawable.diamond)
        }
        if (shape == "Zeus") {
            imgView.setImageResource(R.drawable.zeus)
        }
    }
    private fun checkGameStatus():Int {
             if((board[0][0]==1&&board[0][1]==1&&board[0][2]==1)||(board[1][0]==1&&board[1][1]==1&&board[1][2]==1)||(board[2][0]==1&&board[2][1]==1&&board[2][2]==1)||
                (board[0][0]==1&&board[1][0]==1&&board[2][0]==1)||(board[0][1]==1&&board[1][1]==1&&board[2][1]==1)||(board[0][2]==1&&board[1][2]==1&&board[2][2]==1)||
                (board[0][0]==1&&board[1][1]==1&&board[2][2]==1)||(board[0][2]==1&&board[1][1]==1&&board[2][0]==1)){txtResult.text="$player1Name has won!!!"; imgTurnP1.visibility = View.INVISIBLE; imgTurnP2.visibility = View.INVISIBLE; return 1}
        else if((board[0][0]==2&&board[0][1]==2&&board[0][2]==2)||(board[1][0]==2&&board[1][1]==2&&board[1][2]==2)||(board[2][0]==2&&board[2][1]==2&&board[2][2]==2)||
                (board[0][0]==2&&board[1][0]==2&&board[2][0]==2)||(board[0][1]==2&&board[1][1]==2&&board[2][1]==2)||(board[0][2]==2&&board[1][2]==2&&board[2][2]==2)||
                (board[0][0]==2&&board[1][1]==2&&board[2][2]==2)||(board[0][2]==2&&board[1][1]==2&&board[2][0]==2)) {txtResult.text="$player2Name has won!!!"; imgTurnP1.visibility = View.INVISIBLE; imgTurnP2.visibility = View.INVISIBLE; return 2}
        else if(board[0][0]!=0&&board[0][1]!=0&&board[0][2]!=0&&board[1][0]!=0&&board[1][1]!=0&&board[1][2]!=0&&board[2][0]!=0&&board[2][1]!=0&&board[2][2]!=0) {txtResult.text="It's tied Swipe Reset";imgTurnP1.visibility = View.INVISIBLE; imgTurnP2.visibility = View.INVISIBLE; return 0;}
        else {return 0}
    }
    fun img00Click(view:View) {
        if(board[0][0]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img00,player1Shape)
                board[0][0]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img00,player2Shape)
                board[0][0]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    fun img01Click(view:View) {
        if(board[0][1]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img01,player1Shape)
                board[0][1]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img01,player2Shape)
                board[0][1]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    fun img02Click(view:View) {
        if(board[0][2]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img02,player1Shape)
                board[0][2]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img02,player2Shape)
                board[0][2]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }

    fun img10Click(view:View) {
        if(board[1][0]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img10,player1Shape)
                board[1][0]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img10,player2Shape)
                board[1][0]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    fun img11Click(view:View) {
        if(board[1][1]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img11,player1Shape)
                board[1][1]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img11,player2Shape)
                board[1][1]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    fun img12Click(view:View) {
        if(board[1][2]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img12,player1Shape)
                board[1][2]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img12,player2Shape)
                board[1][2]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }

    fun img20Click(view:View) {
        if(board[2][0]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img20,player1Shape)
                board[2][0]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img20,player2Shape)
                board[2][0]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    fun img21Click(view:View) {
        if(board[2][1]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img21,player1Shape)
                board[2][1]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img21,player2Shape)
                board[2][1]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    fun img22Click(view:View) {
        if(board[2][2]==0&&checkGameStatus()==0) {
            if (turn == 1) {
                setShape(img22,player1Shape)
                board[2][2]=1
                turn=2
                imgTurnP1.visibility = View.INVISIBLE
                imgTurnP2.visibility = View.VISIBLE
            }
            else {
                setShape(img22,player2Shape)
                board[2][2]=2
                turn=1
                imgTurnP1.visibility = View.VISIBLE
                imgTurnP2.visibility = View.INVISIBLE
            }
        }
        checkGameStatus()
    }
    private fun resetSpot(imgView:ImageView, player:Int, posX:Int, posY:Int){
        if(player==1)
        {
            setShape(imgView,player1Shape)
            board[posY][posX]=1
        }
        else if(player==2)
        {
            setShape(imgView,player2Shape)
            board[posY][posX]=2
        }
    }
    override fun onSaveInstanceState(outState: Bundle? ) {
        super.onSaveInstanceState(outState)
        outState!!.putIntArray("RowZero",board[0] )
        outState.putIntArray("RowOne",board[1] )
        outState.putIntArray("RowTwo",board[2] )
        outState.putInt("TheTurn",turn )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val rowZero = savedInstanceState!!.getIntArray("RowZero")
        resetSpot(img00,rowZero[0],0,0)
        resetSpot(img01,rowZero[1],1,0)
        resetSpot(img02,rowZero[2],2,0)
        val rowOne = savedInstanceState.getIntArray("RowOne")
        resetSpot(img10,rowOne[0],0,1)
        resetSpot(img11,rowOne[1],1,1)
        resetSpot(img12,rowOne[2],2,1)
        val rowTwo = savedInstanceState.getIntArray("RowTwo")
        resetSpot(img20,rowTwo[0],0,2)
        resetSpot(img21,rowTwo[1],1,2)
        resetSpot(img22,rowTwo[2],2,2)
        val turnR = savedInstanceState.getInt("TheTurn")
        turn =turnR
        if(turn==1){
            imgTurnP1.visibility = View.VISIBLE
            imgTurnP2.visibility = View.INVISIBLE
        }
        if(turn==2){
            imgTurnP1.visibility = View.INVISIBLE
            imgTurnP2.visibility = View.VISIBLE
        }
        checkGameStatus()

    }
}
