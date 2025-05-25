## Практика Android sdk

В :android_example находятся только фрагменты, необходимые для задания 1. Они наследуют NavNodeFragment - базовый фрагмент для узла навигации.
Основной код по заданиям находится в [:core](https://github.com/amalkoott/kotlin-em/tree/master/core/src/main/java/ru/amalkoott/core)

Задание 1
1) [NavNodeFragment](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/navigation/NavNodeFragment.kt) - базовый фрагмент для узла навигации
2) [Router](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/navigation/Router.kt) - класс с логикой переходов (вперед/назад) на основе backStack
3) [Navigator](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/navigation/Navigator.kt) - интерфейс с поведением для всего, что способно к навигации по фрагментам
2) [Пакет с фрагментами](https://github.com/amalkoott/kotlin-em/tree/master/android_example/src/main/java/ru/amalkoott/android_example/presentation) - просто фрагменты, которые переопределяют абстрактный метод базового класса

Задание 2
1) [ChargeNotifyWorker](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/worker/ChargeNotifyWorker.kt) - воркер, показывающий уведомление
2) [WMUtils](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/worker/WMUtils.kt) - файл с методами запуска работ
3) [NotificationUtils](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/utils/NotificationUtils.kt) - методы для работы с уведомлениями

Задание 3
1) [EventFillView](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/java/ru/amalkoott/core/view/EventFillView.kt) - кастомная view
2) [аттрибуты для кастомной view](https://github.com/amalkoott/kotlin-em/blob/master/core/src/main/res/values/event_fill_vew_attrs.xml) - вынесла, чтобы посмотреть, как все работает + логично иметь настройку шага заполнения 
