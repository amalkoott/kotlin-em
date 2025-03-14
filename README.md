## Практика RxJava

Все походы на "сервер" (задания 1,3,5) - [Request.kt](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/utils/Request.kt) в пакете utils (модуль rx_example).

Адаптер для ресайклера - [тут](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/presentation/SimpleStringsAdapter.kt)

1) [Сетевой запрос](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/presentation/RecyclerFragment.kt) - объединен с третьим заданием
2) [Таймер](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/presentation/TimerFragment.kt)
3) [Ресайклер с выводом позиции элемента в toast (publish subject)](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/presentation/RecyclerFragment.kt)
4) [вывод из EditText не ранее трех секунд](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/presentation/EditTextFragment.kt)
5) [Zip списков кредитных кард с ошибкой и без](https://github.com/amalkoott/kotlin-em/blob/master/rx_example/src/main/java/ru/amalkoott/rx_example/presentation/CreditCardFragment.kt)