package network.repository

import data.model.Resource
import data.model.project.ProjectData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import network.HttpUtil
import network.ktor.ProjectApiService

class ProjectRepositoryImpl(
    private val httpUtil: HttpUtil,
    private val projectApiService: ProjectApiService
): ProjectRepository {

    override suspend fun getProjects(): Flow<Resource<List<ProjectData>>> = flow {
        emit(Resource.Loading)
        emit(httpUtil.checkResponse(projectApiService.getProjects()))
    }
}