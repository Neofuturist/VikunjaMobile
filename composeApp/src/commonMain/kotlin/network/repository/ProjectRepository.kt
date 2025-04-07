package network.repository

import data.model.Resource
import data.model.project.ProjectData
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun getProjects(): Flow<Resource<List<ProjectData>>>
}