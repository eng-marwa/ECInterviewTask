package com.marwa.ecinterviewtask.utils.glide

import android.content.Context
import android.net.Uri
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.caverock.androidsvg.SVG

class SvgInputStreamFetcher(private val context: Context, private val uri: Uri) : DataFetcher<SVG> {

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in SVG>) {
        try {
            val svg = SVG.getFromInputStream(context.contentResolver.openInputStream(uri))
            callback.onDataReady(svg)
        } catch (e: Exception) {
            callback.onLoadFailed(e)
        }
    }

    override fun cleanup() {
        // Intentionally empty.
    }

    override fun cancel() {
        // Intentionally empty.
    }

    override fun getDataClass(): Class<SVG> {
        return SVG::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }
}