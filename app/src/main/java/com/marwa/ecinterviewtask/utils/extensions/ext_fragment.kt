package com.marwa.ecinterviewtask.utils.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marwa.ecinterviewtask.R


fun Fragment.hideKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.showToast(message: String) {
    activity?.let {
        Toast.makeText(it, message ?: "", Toast.LENGTH_LONG).show()
    }
}



