package com.example.movieapp.core.data.remote.extensions

import android.accounts.NetworkErrorException
import retrofit2.HttpException
import java.util.concurrent.CancellationException

inline fun <T,R> T.resultOf(block: T.() -> R): Result<R>{

    return try {
        Result.success(block())
    } catch (e: CancellationException){
        throw e
    }
    catch (e:Exception){
        Result.failure(e)
    }
}