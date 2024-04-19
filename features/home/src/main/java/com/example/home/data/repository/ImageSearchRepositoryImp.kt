package com.example.home.data.repository

import com.example.database.dbManager.HitDao
import com.example.home.data.mapper.ImageEntityMapper
import com.example.home.data.mapper.ImageEntityToDomainMapper
import com.example.home.data.remote.service.imageSearch.ImageSearchService
import com.example.utils.model.Hit
import com.example.home.domain.repository.ImageSearchRepository
import com.example.utils.utils.cachingHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ImageSearchRepositoryImp @Inject constructor(
    private val service: ImageSearchService,
    private val hitDao: HitDao,
    private val entityMapper: ImageEntityMapper,
    private val entityDomainMapper: ImageEntityToDomainMapper,

    ) : ImageSearchRepository {

//    override suspend fun getAllFootballMatches(): MutableList<Match>? {
//        val allMatches = service.callFootballMatches()
//        val favorites = hitDao.getAllFavorites()
//        return allMatches.mapToDomainMatch(favorites)
//    }

//    override suspend fun callImageSearch(searchKey: String): Flow<List<Hit>?> =
//        flow {
//            try {
//               val result = service.callImageSearch(searchKey)
//
//                emit(result)
//            }catch (e: Exception){
//                emit(getCached())
//            }
//        }.onEach {
//            entityMapper.map(it)?.let {
//                hitDao.save(it)
//            }
//        }.map {
//            domainMapper.map(it)
//        }.catch {
//           getCached()
//        }


    override suspend fun callImageSearch(searchKey: String): Flow<List<Hit>?> =
        cachingHandler(
            localSource = {
                getCached(searchKey).map {
                    it?.let(entityDomainMapper::map)
                }
            },
            remoteSource = {
                service.callImageSearch(searchKey)
            },
            saveResult = {
                entityMapper.map(it)?.let {
                    hitDao.save(it)
                }
            }
        )


    fun getCached(tagKey: String) =  hitDao.getAllContainingTag(tagKey).onEach {
        it?.let(entityDomainMapper::map)
    }

}