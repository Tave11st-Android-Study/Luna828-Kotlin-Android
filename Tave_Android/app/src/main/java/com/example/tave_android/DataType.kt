package com.example.tave_android

fun main() {
    practiceVal()
    practiceVar()
    practiceDataType1() //숫자형
    practiceDataType2() //문자형
}

fun practiceVal() {
    // val 이라는 키워드가 앞에 나오는 경우 불변이라는 것을 의미
    val name: String = "Luna Kim"

    // 타입 추론을 통하여 코틀린이 변수 타입을 추측할 수 있음
    // 일반적으로 타입을 지정하는 편이 좋다. (코틀린이 추론한 타입과 우리가 예상한 타입이 다른지 검사가 가능하기에)
    val name2 = "김은경"

    println(name)
    println(name2)
}

fun practiceVar() {
    // 1. 변수 선언과 동시에 값을 넣어 초기화 하는 법
    var name = "luna Kim"
    name = "김은경"

    // 2. 값으로 초기화 하지 않고 선언만 하고 사용하는 법
    var name2 : String
    name2 = "luna Kim"

    println(name)
    println(name2)
}

fun practiceDataType1() {

    // Double : 64비트 실수
    // 소수점이 있는 값 저장
    var doubleValue: Double
    doubleValue = 3.143245

    // Float 32비트 실수
    // Double과 동일한 용도이지만 더 작은 범위의 숫자를 저장할 때 사용
    var floatValue: Float
    floatValue = 3.15342F //맨 뒤 F 붙임

    // Int 32비트 정수
    //언더바로 자릿수를 구분 가능
    var intValue : Int
    intValue = 2_153_434

    // Long 64비트 정수
    var longValue : Long
    longValue = 2121213939372L

    //Short 와 Byte 16비트, 8비트 정수 (Int 값 크기보다 작음
    var shortValue : Short = 32_767
    var byteValue : Byte = 125

    println(doubleValue)
    println(floatValue)
    println(doubleValue)
    println(intValue)
    println(longValue)
    println(shortValue)
    println(byteValue)
}

fun practiceDataType2() {

}

