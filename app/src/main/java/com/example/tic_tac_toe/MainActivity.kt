package com.example.tic_tac_toe

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.*
import android.view.View
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.graphics.BitmapFactory
import android.widget.Toast
import java.io.File
import java.io.IOException
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_main. *
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var Shapes = arrayOf("X", "O", "Star", "StarD", "Pentagon", "Hexagon", "Heart", "Diamond", "Zeus")
    private var sprShapeP1: Spinner? = null
    private var sprShapeP2: Spinner? = null
    private var player1IMGFileName: String = ""
    private var player2IMGFileName: String = ""
    private val REQUEST_TAKE_PHOTO = 1
    var currentPlayer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sprShapeP1 = this.sprShpP1
        sprShapeP1!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, Shapes)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item)
        sprShapeP1!!.setAdapter(aa)

        sprShapeP2 = this.sprShpP2
        sprShapeP2!!.setOnItemSelectedListener(this)
        val aaa = ArrayAdapter(this, android.R.layout.simple_spinner_item, Shapes)
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_item)
        sprShapeP2!!.setAdapter(aaa)
        txtPlay.setOnTouchListener(object : OnSwipeListener(this) {
            init {
                setDragHorizontal(true)
                setExitScreenOnSwipe(true)
                setAnimationDelay(500)
            }

            override fun onSwipeLeft(distance: Float) {
                Toast.makeText(applicationContext, "swiped left!", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeRight(distance: Float) {
                Toast.makeText(applicationContext, "swiped right!", Toast.LENGTH_SHORT).show()
                goToPlayActivity()
            }
        })

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        if (sprShapeP1!!.selectedItem.toString() == "X") {
            imgP1Shape.setImageResource(R.drawable.x)
        }
        if (sprShapeP1!!.selectedItem.toString() == "O") {
            imgP1Shape.setImageResource(R.drawable.o)
        }
        if (sprShapeP1!!.selectedItem.toString() == "Star") {
            imgP1Shape.setImageResource(R.drawable.star)
        }
        if (sprShapeP1!!.selectedItem.toString() == "StarD") {
            imgP1Shape.setImageResource(R.drawable.stard)
        }
        if (sprShapeP1!!.selectedItem.toString() == "Pentagon") {
            imgP1Shape.setImageResource(R.drawable.pentagon)
        }
        if (sprShapeP1!!.selectedItem.toString() == "Hexagon") {
            imgP1Shape.setImageResource(R.drawable.hexagon)
        }
        if (sprShapeP1!!.selectedItem.toString() == "Heart") {
            imgP1Shape.setImageResource(R.drawable.heart)
        }
        if (sprShapeP1!!.selectedItem.toString() == "Diamond") {
            imgP1Shape.setImageResource(R.drawable.diamond)
        }
        if (sprShapeP1!!.selectedItem.toString() == "Zeus") {
            imgP1Shape.setImageResource(R.drawable.zeus)
        }

        if (sprShapeP2!!.selectedItem.toString() == "X") {
            imgP2Shape.setImageResource(R.drawable.x)
        }
        if (sprShapeP2!!.selectedItem.toString() == "O") {
            imgP2Shape.setImageResource(R.drawable.o)
        }
        if (sprShapeP2!!.selectedItem.toString() == "Star") {
            imgP2Shape.setImageResource(R.drawable.star)
        }
        if (sprShapeP2!!.selectedItem.toString() == "StarD") {
            imgP2Shape.setImageResource(R.drawable.stard)
        }
        if (sprShapeP2!!.selectedItem.toString() == "Pentagon") {
            imgP2Shape.setImageResource(R.drawable.pentagon)
        }
        if (sprShapeP2!!.selectedItem.toString() == "Hexagon") {
            imgP2Shape.setImageResource(R.drawable.hexagon)
        }
        if (sprShapeP2!!.selectedItem.toString() == "Heart") {
            imgP2Shape.setImageResource(R.drawable.heart)
        }
        if (sprShapeP2!!.selectedItem.toString() == "Diamond") {
            imgP2Shape.setImageResource(R.drawable.diamond)
        }
        if (sprShapeP2!!.selectedItem.toString() == "Zeus") {
            imgP2Shape.setImageResource(R.drawable.zeus)
        }
        if (sprShapeP1!!.selectedItem.toString() == sprShapeP2!!.selectedItem.toString()) {
            if (sprShapeP1!!.selectedItemId < 8) {
                sprShapeP1!!.setSelection(sprShapeP1!!.selectedItemId.toInt())
                sprShapeP2!!.setSelection(sprShapeP1!!.selectedItemId.toInt() + 1)
            } else {
                sprShapeP1!!.setSelection(sprShapeP1!!.selectedItemId.toInt())
                sprShapeP2!!.setSelection(0)
            }
        }
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
        imgP1Shape.setImageResource(R.drawable.x)
        imgP2Shape.setImageResource(R.drawable.o)
    }

    fun goToPlayActivity() {
        writeFileInternal("data.txt")
        val intent = Intent(this, play::class.java)
        intent.putExtra("KEY", "Good Luck!!")
        startActivity(intent)
    }

    fun btnTakePP1Click(view:View) {
        //view.txtNameP1.toString()
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            currentPlayer = 1
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
    }

    fun btnTakePP2Click(view:View) {
       // view.txtNameP1.toString()
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            currentPlayer = 2
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            if (currentPlayer == 1) {
                //imgP1.setImageBitmap(imageBitmap)
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                player1IMGFileName=timeStamp
                val file = File(Environment.getExternalStorageDirectory().toString(), "${timeStamp}.jpg")
                try {
                    val stream: OutputStream = FileOutputStream(file)
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                    stream.close()
                    Toast.makeText(applicationContext, "Image saved successful.", Toast.LENGTH_SHORT).show()
                } catch (e: IOException){ // Catch the exception
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Error to save image.", Toast.LENGTH_SHORT).show()
                }
                imgP1.setImageURI(Uri.parse(file.absolutePath))
                galleryAddPic(file)
            }
            if (currentPlayer == 2) {
                //imgP1.setImageBitmap(imageBitmap)
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                player2IMGFileName=timeStamp
                val file = File(Environment.getExternalStorageDirectory().toString(), "${timeStamp}.jpg")
                try {
                    val stream: OutputStream = FileOutputStream(file)
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                    stream.close()
                    Toast.makeText(applicationContext, "Image saved successful.", Toast.LENGTH_SHORT).show()
                } catch (e: IOException){ // Catch the exception
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Error to save image.", Toast.LENGTH_SHORT).show()
                }
                imgP2.setImageURI(Uri.parse(file.absolutePath))
                galleryAddPic(file)
            }
        }
    }

    private fun galleryAddPic(f : File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        this.sendBroadcast(mediaScanIntent)
    }

    private fun writeFileInternal(filename:String) {
        if(txtNameP1.text.toString()=="")
        {
            txtNameP1.text.insert(0,"Player 1")
        }
        if(txtNameP2.text.toString()=="")
        {
            txtNameP2.text.insert(0,"Player 2")
        }
        val fileContents = "${txtNameP1.text}\n" +
                "${sprShapeP1!!.selectedItem}\n"+
                "${txtNameP2.text}\n"+
                "${sprShapeP2!!.selectedItem}"
        //This is how you write text in the file "manually"
        /*val fileContents = "First Line! \n" +
                            "Second line here!"*/
        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    override fun onSaveInstanceState(outState: Bundle? ) {
        super.onSaveInstanceState(outState)
        outState!!.putString("P1IMGPATH",player1IMGFileName )
        outState.putString("P2IMGPATH",player2IMGFileName )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val path1 = savedInstanceState!!.getString("P1IMGPATH")
        if(path1!=""){
            val file = File(Environment.getExternalStorageDirectory().toString(), "$path1.jpg")
            player1IMGFileName=path1
            imgP1.setImageURI(Uri.parse(file.absolutePath))
        }

        val path2 = savedInstanceState.getString("P2IMGPATH")
        if(path2!=""){
            val file = File(Environment.getExternalStorageDirectory().toString(), "$path2.jpg")
            player2IMGFileName=path2
            imgP2.setImageURI(Uri.parse(file.absolutePath))
        }
    }
}
