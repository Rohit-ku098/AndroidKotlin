package com.example.mylearningapp

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mylearningapp.databinding.ActivityDialogboxBinding

class Dialogbox : AppCompatActivity() {
    lateinit var binding: ActivityDialogboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myname.text = "Alert Dialog Boxes"

        binding.button1.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Close App")
            builder1.setMessage("Do you want to close the app?")
            builder1.setIcon(R.drawable.baseline_exit_to_app_24)
            builder1.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
                finish()
            })
            builder1.setNegativeButton("No", DialogInterface.OnClickListener { dialog, i ->
                dialog.dismiss()
            })
            builder1.show()
        }

        binding.button2.setOnClickListener {
            val builder2 = AlertDialog.Builder(this)
            val colorOptions = arrayOf("Red", "Green", "Blue")
            builder2.setTitle("Choose Your favorite Color")
            builder2.setSingleChoiceItems(colorOptions, 0, DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "You selected ${colorOptions[which]}", Toast.LENGTH_SHORT).show()
            })
            builder2.setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            builder2.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            builder2.show()
        }

        binding.button3.setOnClickListener {
            val builder3 = AlertDialog.Builder(this)
            val techOption = arrayOf("Java", "Kotlin", "Python", "C++")
            builder3.setTitle("Select Language you know")
            builder3.setMultiChoiceItems(techOption, null, DialogInterface.OnMultiChoiceClickListener {
                    dialog, which, isChecked ->
                Toast.makeText(this, "You selected ${techOption[which]}", Toast.LENGTH_SHORT).show()
            })
            builder3.setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            builder3.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            builder3.show()
        }

        binding.button4.setOnClickListener {
            val builder = Dialog(this)
            builder.setContentView(R.layout.custom_dialog_box)
            builder.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_dialog))
            builder.show()

            val okbtn = builder.findViewById<Button>(R.id.okbtn)
            okbtn?.setOnClickListener {
                builder.dismiss()
            }
        }


    }

}