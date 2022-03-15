package edu.ntnu.tobiasth.backend.repo

import edu.ntnu.tobiasth.backend.entity.Equation
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface EquationRepository: PagingAndSortingRepository<Equation, Long>