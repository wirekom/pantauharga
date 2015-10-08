package com.pantau.util

/**
 * Created by widodo on 10/8/15.
 */
class CustomObjectMarshallers {
    List marshallers = []

    def register() {
        marshallers.each { it.register() }
    }
}
