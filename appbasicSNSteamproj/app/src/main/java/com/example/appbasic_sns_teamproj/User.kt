package com.example.appbasic_sns_teamproj

class User {
    var id = ""
    var pw = ""
    var name = ""
    var track = ""
    var isKorean = true

    constructor(id: String, pw: String, name: String, track: String) {
        this.id = id
        this.pw = pw
        this.name = name
        this.track = track
    }
}