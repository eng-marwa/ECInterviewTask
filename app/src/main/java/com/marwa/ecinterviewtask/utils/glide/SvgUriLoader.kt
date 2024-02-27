package com.marwa.ecinterviewtask.utils.glide


import android.content.Context
import android.net.Uri
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import com.caverock.androidsvg.SVG

class SvgUriLoader(private val context: Context) : ModelLoader<Uri, SVG> {

    override fun buildLoadData(
        uri: Uri,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<SVG> {
        return ModelLoader.LoadData(ObjectKey(uri), SvgInputStreamFetcher(context, uri))
    }

    override fun handles(uri: Uri): Boolean {
        return "file" == uri.scheme && uri.path?.endsWith(".svg") ?: false
    }

    class Factory(private val context: Context) : ModelLoaderFactory<Uri, SVG> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<Uri, SVG> {
            return SvgUriLoader(context)
        }

        override fun teardown() {
            // Do nothing, this instance doesn't own the Context.
        }
    }
}