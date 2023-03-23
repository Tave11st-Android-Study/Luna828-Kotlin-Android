package com.example.tave_android.kotlinPrac

import java.time.Instant

fun main() {
    //Instance
    val eunKyung = Person(name = "김은경", birth = 1998)
    val dad = Person(name = "김YH", birth = 1969)

    val kyungJin = Person(name = "이경진")
    val heeWon = Person(name = "경희원")

    val luna = Person("Luna Kim", 1998, 26)

    eunKyung.introduce()
    dad.introduce()
    kyungJin.introduce()
    heeWon.introduce()
    luna.introduce()
}

open class Person(val name: String, val birth: Int) {

    constructor(name: String): this(name, 1998){ println("보조 생성자가 사용되었습니다") }

    constructor(name: String, birth: Int, age: Int) : this(name, birth) {
        println("$name + $birth + $age")
    }

    fun introduce() { println("안녕하세요, ${birth}인 ${name}입니다") }
}

class Member(name: String, registered: Instant) : Person(name)
