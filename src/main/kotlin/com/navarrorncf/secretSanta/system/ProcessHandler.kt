package com.navarrorncf.secretSanta.system

import kotlin.system.exitProcess

interface ISystemProcessHandler {
    fun exit(code: Int)
}

class SystemProcessHandler: ISystemProcessHandler {
    override fun exit(code: Int) {
        exitProcess(code)
    }
}