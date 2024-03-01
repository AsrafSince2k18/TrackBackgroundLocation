package com.example.traker.root.data.user

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserLocation : RealmObject{
    @PrimaryKey var _id : ObjectId = ObjectId()
    var userLocationList : UserData?=null
}

class UserData : RealmObject{
    var dateTime : String=""
    var latitude : String = ""
    var longitude : String=""
}