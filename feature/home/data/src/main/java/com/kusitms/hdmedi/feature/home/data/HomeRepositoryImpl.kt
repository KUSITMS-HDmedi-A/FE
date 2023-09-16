package com.kusitms.hdmedi.feature.home.data

import com.core.network.datasource.NetworkDataSource
import com.kusitms.hdmedi.feature.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: NetworkDataSource,
): HomeRepository {
}