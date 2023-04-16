package com.example.tave_android.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomMemo::class], version = 1, exportSchema = false)
abstract class memoDataBase: RoomDatabase() {

    abstract fun memoDao(): MemoDao
    /*
    * @Database 어노테이션과 함께 데이터베이스의 전체적인 소유자 역할을 하고 있으며
    * 앞에서 생성한 Entity, DAO 클래스를 통합적으로 묶어 데이터베이스를 생성하거나 버전 관리를 진행합니다.
    * entities 값을 RoomMemo로 설정하여 이전에 설정한 Entity 클래스를 추가해주며
    * version 정보 또한 함께 설정해줍니다.
    * 아래의 getInstance 메서드는 추후 테스트를 진행할 때 사용될 것입니다.
    * https://juyeop.tistory.com/30
    * */

    companion object{

        private var INSTANCE : memoDataBase? = null

        fun getInstance(context: Context)  : memoDataBase?{
            if(INSTANCE == null){
                synchronized(memoDataBase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        memoDataBase::class.java,
                        "momo.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}