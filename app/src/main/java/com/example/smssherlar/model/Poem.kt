package com.example.smssherlar.model

class Poem {
    var poemName: String? = null
    var poem: String? = null
    var poemType: String? = null
    var poemLiked: Boolean? = true

    constructor(poemName: String?, poem: String?, poemType: String?, poemLiked: Boolean) {
        this.poemName = poemName
        this.poem = poem
        this.poemType = poemType
        this.poemLiked = poemLiked
    }

    constructor()
}