package edu.ntnu.tobiasth.backend.entity

import javax.persistence.*

@Entity
class User(var username: String, var password: String, @OneToMany var equations: List<Equation> = arrayListOf()) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}