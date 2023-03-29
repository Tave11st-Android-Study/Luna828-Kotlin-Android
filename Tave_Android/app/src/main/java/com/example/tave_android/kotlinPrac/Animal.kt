package com.example.tave_android.kotlinPrac

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

/*
/* CASE 1 - name 프로퍼티 선언에 합치는 경우 */
class Animal constructor(name: String) {
    val name: String = name
}
/* CASE 2 - 생성자 선언, 프로퍼티 선언, 프로퍼티 초기화를 하나로 합치는 경우 */
class Animal constructor(val name: String) {

}
/*
*	CASE 3 - CASE 2의 빈 블록 제거,
*	그리고 블럭이 비어있는지 관계없이 constructor라는 키워드는 없어도 된다.
*/
class Animal(val name: String)
/* CASE 4 - 추가로, 한 클래스 안에 여러 프로퍼티를 정의해도 된다 */
class Animal(val name: String, val registered: Instant)
 */

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val animal = Animal("Lion", Instant.now())
    println(animal.registered.toString())
}


open class Animal (
    val name: String,
    val registered: Instant
) : java.io.Serializable, Comparable<Animal> {
    override fun compareTo(other: Animal): Int {
        TODO("Not yet implemented")
    }
}

class Member1(name: String, registered: Instant) : Animal(name, registered)