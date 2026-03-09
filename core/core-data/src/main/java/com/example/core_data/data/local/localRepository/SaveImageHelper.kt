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

object  SaveImageHelper {
    // Кэш путей к уже сохраненным файлам
    private val imageCache = mutableMapOf<String, String>()

    suspend fun saveImageFromUrl(context: Context, imageUrl: String): String? {
        // Проверяем кэш
        if (imageCache.containsKey(imageUrl)) {
            Log.d("SaveImageHelper", "Нашли в кэше: $imageUrl")
            return imageCache[imageUrl]
        }

        // Проверяем существование файла
        val existingPath = checkIfFileExists(context, imageUrl)
        if (existingPath != null) {
            Log.d("SaveImageHelper", "Файл уже существует: $existingPath")
            Log.d("aegljkneasdkhjg","NONSAVEIMAGE - файл уже есть")
            imageCache[imageUrl] = existingPath
            return existingPath
        }

        // Загружаем и сохраняем
        return withContext(Dispatchers.IO) {
            try {
                val bitmap = downloadBitmap(imageUrl) ?: return@withContext null
                val fileName = generateFileNameFromUrl(imageUrl)
                val path = saveBitmapToFile(context, bitmap, fileName)
                Log.d("SaveImageHelper", "Сохранен новый файл: $path")
                imageCache[imageUrl] = path
                path
            } catch (e: Exception) {
                Log.e("SaveImageHelper", "Ошибка сохранения: ${e.message}")
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
        Log.d("SaveImageHelper", "Проверка файла: ${imageFile.absolutePath}, существует: $exists")

        return if (exists) imageFile.absolutePath else null
    }

    private fun generateFileNameFromUrl(url: String): String {
        // Генерируем стабильное имя файла из URL
        val md = MessageDigest.getInstance("MD5")
        val hash = BigInteger(1, md.digest(url.toByteArray())).toString(16)
        return hash.padStart(32, '0')
    }

    private suspend fun downloadBitmap(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("SaveImageHelper", "Загрузка: $imageUrl")
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                    Log.d("SaveImageHelper", "Загружено успешно")
                    bitmap
                } else {
                    Log.e("SaveImageHelper", "Ошибка HTTP: ${connection.responseCode}")
                    null
                }
            } catch (e: Exception) {
                Log.e("SaveImageHelper", "Ошибка загрузки: ${e.message}")
                null
            }
        }
    }

    private fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String): String {
        // Формируем путь к папке в памяти приложения
        val imagesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "hogwarts/images"
        )

        // Создаем папки, если они не существуют
        if (!imagesDir.exists()) {
            imagesDir.mkdirs()
        }

        // Используем переданное имя (MD5 хеш) вместо UUID
        val imageFile = File(imagesDir, "$fileName.png")

        Log.d("SaveImageHelper", "Сохранение в: ${imageFile.absolutePath}")

        // Сохраняем Bitmap в файл
        FileOutputStream(imageFile).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream) // JPEG для меньшего размера
            outputStream.flush()
        }

        Log.d("SaveImageHelper", "Файл сохранен: ${imageFile.length()} байт")

        // Возвращаем путь к сохраненному файлу
        return imageFile.absolutePath
    }

    // Метод для очистки кэша (опционально)
    fun clearCache() {
        imageCache.clear()
    }

    // Метод для просмотра содержимого папки (для отладки)
    fun listSavedImages(context: Context) {
        val imagesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "hogwarts/images"
        )

        if (imagesDir.exists() && imagesDir.isDirectory) {
            val files = imagesDir.listFiles()
            Log.d("SaveImageHelper", "Файлов в папке: ${files?.size ?: 0}")
            files?.forEach { file ->
                Log.d("SaveImageHelper", "Файл: ${file.name}, размер: ${file.length()} байт")
            }
        }
    }
}