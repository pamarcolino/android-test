package com.pamarcolino.sofie.network

import com.pamarcolino.sofie.network.dto.TaskDTO
import com.pamarcolino.sofie.network.response.GetTaskResponse
import com.pamarcolino.sofie.network.response.SaveTaskResponse
import io.reactivex.Observable
import retrofit2.http.*
import java.util.*

interface ServiceData {

    @GET("task")
    fun getTasks(): Observable<GetTaskResponse>

    @POST("task")
    fun saveTask(@Body task: TaskDTO): Observable<SaveTaskResponse>

    @PUT("task/{id}")
    fun updateTask(@Path("id") id: UUID, @Body task: TaskDTO) : Observable<SaveTaskResponse>

    @DELETE("task/{id}")
    fun putSolicitacaoDetalhe(@Path("id") id: UUID, @Body task: TaskDTO) : Observable<SaveTaskResponse>
}