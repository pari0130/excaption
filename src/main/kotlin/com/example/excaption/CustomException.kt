package com.example.excaption

class CustomException : Exception {
    var errorCode = 0

    companion object {
        fun invalidParameter(fieldname: String) = CustomException("$fieldname is required", 1000002)
        fun noValuesToUpdate() = CustomException("no values to update", 1000002)
    }

    constructor(message: String?) : super(message) {}

    constructor(errorCode: Int) : super() {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: Int) : super(message) {
        this.errorCode = errorCode
    }
}