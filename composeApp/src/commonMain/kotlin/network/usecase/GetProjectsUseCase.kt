package network.usecase

import data.model.Resource
import data.model.project.ProjectData
import kotlinx.coroutines.flow.Flow
import network.repository.ProjectRepository

class GetProjectsUseCase(
    private val projectRepository: ProjectRepository
) {
    suspend fun execute(): Flow<Resource<List<ProjectData>>> {
        return projectRepository.getProjects()
    }
}