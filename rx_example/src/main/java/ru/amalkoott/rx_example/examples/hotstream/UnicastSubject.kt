package ru.amalkoott.rx_example.examples.hotstream

import io.reactivex.rxjava3.subjects.UnicastSubject

fun unicastSubject() : UnicastSubject<String>{
    return UnicastSubject.create<String>()
}