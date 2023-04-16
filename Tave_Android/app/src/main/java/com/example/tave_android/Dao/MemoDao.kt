package com.example.tave_android.Dao

import androidx.room.*

// @Dao 어노테이션과 함께 데이터베이스에 접근하여 수행할 작업들을 메서드 형태 지정
// 조회, 추가, 수정, 삭제 기능 구현

@Dao
interface MemoDao {

    //조회
    @Query("SELECT * FROM room_memo")
    fun getMemo() : List<RoomMemo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //OnConflictStrategy.REPLACE = 중복된 투플값이 존재해도 데이터를 추가하면 그 값 위에 덮어씀 = Update 의미
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)

}