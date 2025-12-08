package com.navarrorncf.secretSanta.system

interface IUserInterfaceHandler {
    fun read(): String
    fun write(input: String)
}

class UserInterfaceHandler: IUserInterfaceHandler {
    override fun read(): String {
        return readln()
    }

    override fun write(input: String) {
        println(input)
    }
}