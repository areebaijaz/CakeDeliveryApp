package com.example.cakedeliveryapp.data.repository

import com.example.cakedeliveryapp.data.local.CakeDao
import com.example.cakedeliveryapp.data.remote.ApiService
import com.example.cakedeliveryapp.data.toDomain
import com.example.cakedeliveryapp.data.toEntity
import com.example.cakedeliveryapp.domain.model.Cake
import com.example.cakedeliveryapp.domain.repository.CakeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.IOException

class CakeRepositoryImpl(
    private val apiService: ApiService,
    private val dao: CakeDao
) : CakeRepository {

    override suspend fun getCakes(): Result<List<Cake>> {
        return try {
            val response = apiService.getCakes()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val domainCake = body.map { it.toDomain() }
                    val entities = domainCake.map { it.toEntity() }
                    dao.insert(entities)
                    Result.success(domainCake)

                } else {
                    val cached = dao.getAllCakes().first().map { it.toDomain() }
                    Result.success(cached)
                }
            } else {
                val cached = dao.getAllCakes().first().map { it.toDomain() }
                Result.success(cached)
            }
        } catch (e:IOException) {
            val cached = dao.getAllCakes().first().map { it.toDomain() }
            Result.success(cached)
        } catch (e:Exception){
            Result.failure(e)
        }
    }
    override fun getAllCake(): Flow<List<Cake>> {
        return dao.getAllCakes().map { list->
            list.map { it.toDomain() }
        }

    }

    override suspend fun updateCakeId(cakeId : Int,qty: Int): Cake? {
         dao.updateCakeQuantity(cakeId,qty)
        return dao.getCakeById(cakeId)?.toDomain()
    }

    override suspend fun getCakeById(cakeId: Int): Cake? {
        return dao.getCakeById(cakeId)?.toDomain()
    }
}