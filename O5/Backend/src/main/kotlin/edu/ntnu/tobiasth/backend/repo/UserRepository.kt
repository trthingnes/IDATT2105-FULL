package edu.ntnu.tobiasth.backend.repo

import edu.ntnu.tobiasth.backend.entity.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface UserRepository: PagingAndSortingRepository<User, Long> {
    fun findByUsername(@Param("username") username: String): User
}