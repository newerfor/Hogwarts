package com.example.core_data.data.local.localRepository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest

object SaveImageHelper {

    private val imageCache = mutableMapOf<String, String>()

    suspend fun saveImageFromUrl(context: Context, imageUrl: String): String? {
        if (imageCache.containsKey(imageUrl)) {
            return imageCache[imageUrl]
        }
        val existingPath = checkIfFileExists(context, imageUrl)
        if (existingPath != null) {
            imageCache[imageUrl] = existingPath
            return existingPath
        }
        return withContext(Dispatchers.IO) {
            try {
                val bitmap = downloadBitmap(imageUrl) ?: return@withContext null
                val fileName = generateFileNameFromUrl(imageUrl)
                val path = saveBitmapToFile(context, bitmap, fileName)
                imageCache[imageUrl] = path
                path
            } catch (e: Exception) {
                null
            }
        }
    }

    private fun checkIfFileExists(context: Context, imageUrl: String): String? {
        val fileName = generateFileNameFromUrl(imageUrl)
        val imagesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "hogwarts/images"
        )
        val imageFile = File(imagesDir, "$fileName.png")
        val exists = imageFile.exists()
        return if (exists) imageFile.absolutePath else null
    }

    private fun generateFileNameFromUrl(url: String): String {
        val md = MessageDigest.getInstance("MD5")
        val hash = BigInteger(1, md.digest(url.toByteArray())).toString(16)
        return hash.padStart(32, '0')
    }

    private suspend fun downloadBitmap(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                    bitmap
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    private fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String): String {
        val imagesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "hogwarts/images"
        )
        if (!imagesDir.exists()) {
            imagesDir.mkdirs()
        }
        val imageFile = File(imagesDir, "$fileName.png")
        FileOutputStream(imageFile).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream)
            outputStream.flush()
        }
        return imageFile.absolutePath
    }

    fun clearCache() {
        imageCache.clear()
    }

    fun listSavedImages(context: Context) {
        val imagesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "hogwarts/images"
        )
        if (imagesDir.exists() && imagesDir.isDirectory) {
            val files = imagesDir.listFiles()
            files?.forEach { file ->
                Log.d("SaveImageHelper", "Файл: ${file.name}, размер: ${file.length()} байт")
            }
        }
    }
}