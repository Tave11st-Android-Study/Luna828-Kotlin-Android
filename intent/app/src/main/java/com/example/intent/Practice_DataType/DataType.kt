package com.example.practice_intent.Practice_DataType

fun main() {
    practiceVal()
    practiceVar()
    practiceDataType1() //숫자형
    practiceDataType2() //문자형
    practiceLateinit() //늦은 초기화 , 지연 초기화
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
    //Char은 외따옴표로 감싸서 저장
    var charValue = 'A'

    // String 여러개의 문자 저장
    var stringValue = "안녕하세요"

    // Boolean true , false 두가지 값
    var booleanValue = true
}

fun practiceLateinit() {
    // 참조 초기화를 늦추기 위해서는 var를 사용할 수 밖에 없지만, 일단 참조를 초기화해 값이 정해진 다음부터는 변경을 막고 싶을 때가 있습니다.
    // 그 중 가장 흔한 경우는 초기화에 비용이 많이 들어서 참조를 실제로 사용하기 전까지 절대 초기화하고 싶지 않은 경우 입니다.

    //늦은 초기화
    var name: String? = null

//    val name1 : String by lazy {
//
//    }

    //가변 참조의 늦은 초기화
    //lateinit 은 최초 사용 시 초기화가 되지 않지만 어떤 면에서는 by lazy 와 같은 효과를 냅니다.

    lateinit var name2: String

    /* - 코틀린은 `널이 될 수 있는 타입과 없는 타입을 구분하기 때문에 널 값을 사용하여 초기화하는 방법보다 늦은 초기화 방법을 사용하는 것이 좋습니다`.
    - `늦은 초기화 방식을 사용하는 경우 NullPointerException이 발생할 위협을 제거할 수 있어 안전합니다.`
    - `var` 를 늦은 초기화하기 위해서는 `lateinit` 이라는 키워드를 사용하고
    - `val` 를 늦은 초기화하기 위해서는 `by lazy` 라는 키워드를 사용해야 합니다.
     */
}

