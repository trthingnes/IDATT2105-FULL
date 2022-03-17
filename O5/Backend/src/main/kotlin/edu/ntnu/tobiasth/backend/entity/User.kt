package edu.ntnu.tobiasth.backend.entity

import javax.persistence.*

@Entity
class User(var username: String, var salt: String, var hash: String, @OneToMany var equations: MutableList<Equation> = arrayListOf()) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}