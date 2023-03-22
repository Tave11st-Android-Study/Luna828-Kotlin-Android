package com.example.tave_android.kotlinPrac

import java.time.Instant

fun main() {
    //인스턴스 만들기
    val 은경 = Person(name = "김은경", birth = 1998)
    val 아빠 = Person(name = "김YH", birth = 1969)

    val 경진 = Person(name = "이경진")
    val 희원 = Person(name = "경희원")

    val luna = Person("Luna Kim", 1998, 26)

    은경.introduce()
    아빠.introduce()
    경진.introduce()
    희원.introduce()
    luna.introduce()
}

open class Person(val name: String, val birth: Int) {

    constructor(name: String): this(name, 1998){
        println("보조 생성자가 사용되었습니다")
    }

    constructor(name: String, birth: Int, age: Int) : this(name, birth) {
        println("${name} + ${birth} + ${age}")
    }

    fun introduce() {
        println("안녕하세요, ${birth}인 ${name}입니다")
    }

}

class Member(name: String, registered: Instant) : Person(name)
