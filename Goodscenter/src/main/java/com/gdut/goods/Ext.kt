package com.gdut.goods

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R

/**
 * @author  Li Xuyang
 * date  2019/8/27 15:04
 */
fun NumberButton.getEditText():EditText{
    return find(R.id.text_count)
}