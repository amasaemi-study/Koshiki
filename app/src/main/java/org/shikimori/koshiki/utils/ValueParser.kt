package org.shikimori.koshiki.utils

import org.shikimori.koshiki.R.array.*

/**
 * Temp class
 */
object ValueParser {

    /**
     * Метод возвращает тип аниме
     */
    fun getKind(kind: String?): String {
        when (kind) {
            "ТВ сериал" -> return "tv"
            "Фильм" -> return "movie"
            "OVA" -> return "ova"
            "ONA" -> return "ona"
            "Спешл" -> return "special"
            "Клип" -> return "music"

            "tv" -> return "ТВ сериал"
            "movie" -> return "Фильм"
            "ova" -> return "OVA"
            "ona" -> return "ONA"
            "special" -> return "Спешл"
            "music" -> return "Клип"

            else -> return "-"
        }
    }

    /**
     * Метод возвращает сезон аниме
     */
    fun getSeason(season: String?): String {
        val parseDate = season?.split("-") // 0 - год, 1 - месяц, 2 - день

        when (parseDate?.get(1)) {
            "12", "01", "02" -> return "Зима " + parseDate[0]

            "03", "04", "05" -> return "Весна " + parseDate[0]

            "06", "07", "08" -> return "Лето " + parseDate[0]

            "09", "10", "11" -> return "Осень " + parseDate[0]

            else -> return "-"
        }

        // возвращает в случае, если блок when не был вызван (parseDate == null)
        return "-"
    }

    /**
     * Метод возвращает статус аниме
     */
    fun getStatus(status: String?): String {
        when(status) {
            "Анонс" -> return "anons"
            "Онгоинг" -> return "ongoing"
            "Вышло" -> return "released"

            "anons" -> return "Анонс"
            "ongoing" -> return "Онгоинг"
            "released" -> return "Вышло"

            else -> return "-"
        }
    }

    fun getStatusSelected(requestParam: Boolean, status: IntArray?): String? {
        if (status == null)
            return null
        else {
            var values: String = ""

            if (requestParam) {
                for (i in status) {
                    when (i) {
                        0 -> values += "anons,"
                        1 -> values += "ongoing,"
                        2 -> values += "released,"
                    }
                }
            } else {
                for (i in status) {
                    when (i) {
                        0 -> values += " Анонс,"
                        1 -> values += " Онгоинг,"
                        2 -> values += " Вышло,"
                    }
                }
            }

            return values.substring(0, values.length - 1).trim()
        }
    }

    fun getKindSelected(requestParam: Boolean, kind: IntArray?): String? {
        if (kind == null)
            return null
        else {
            var values: String = ""

            if (requestParam) {
                for (i in kind) {
                    when (i) {
                        0 -> values += "tv,"
                        1 -> values += "movie,"
                        2 -> values += "ova,"
                        3 -> values += "ona,"
                        4 -> values += "special,"
                        5 -> values += "music,"
                    }
                }
            } else {
                for (i in kind) {
                    when (i) {
                        0 -> values += " ТВ сериал,"
                        1 -> values += " Фильм,"
                        2 -> values += " ОVA,"
                        3 -> values += " ONA,"
                        4 -> values += " Спешл,"
                        5 -> values += " Клип,"
                    }
                }
            }

            return values.substring(0, values.length - 1).trim()
        }
    }

    fun getRatingSelected(requestParam: Boolean, rating: IntArray?): String? {
        if (rating == null)
            return null
        else {
            var values: String = ""

            if (requestParam) {
                for (i in rating) {
                    when (i) {
                        0 -> values += "g,"
                        1 -> values += "pg,"
                        2 -> values += "pg_13,"
                        3 -> values += "r,"
                        4 -> values += "r_plus,"
                        5 -> values += "rx,"
                    }
                }
            } else {
                for (i in rating) {
                    when (i) {
                        0 -> values += " G,"
                        1 -> values += " PG,"
                        2 -> values += " PG-13,"
                        3 -> values += " R-17,"
                        4 -> values += " R+,"
                        5 -> values += " Rx,"
                    }
                }
            }

            return values.substring(0, values.length - 1).trim()
        }
    }

    fun getSeasonSelected(requestParam: Boolean, season: IntArray?): String? {
        if (season == null)
            return null
        else {
            var values: String = ""

            if (requestParam) {
                for (i in season) {
                    when (i) {
                        0 -> values += "2018_2020,"
                        1 -> values += "winter_2018,"
                        2 -> values += "fall_2017,"
                        3 -> values += "summer_2017,"
                        4 -> values += "spring_2017,"
                        5 -> values += "winter_2017,"
                        6 -> values += "fall_2016,"
                        7 -> values += "2010_2016,"
                        8 -> values += "200x,"
                        9 -> values += "199x,"
                        10 -> values += "198x,"
                        11 -> values += "ancient,"
                    }
                }
            } else {
                for (i in season) {
                    when (i) {
                        0 -> values += " В будущем,"
                        1 -> values += " Зима 2018,"
                        2 -> values += " Осень 2017,"
                        3 -> values += " Лето 2017,"
                        4 -> values += " Весна 2017,"
                        5 -> values += " Зима 2017,"
                        6 -> values += " Осень 2016,"
                        7 -> values += " 2010-2016,"
                        8 -> values += " 2000е,"
                        9 -> values += " 1990е,"
                        10 -> values += " 1980е,"
                        11 -> values += " Ранее 1980,"
                    }
                }
            }

            return values.substring(0, values.length - 1).trim()
        }
    }

    fun getMyListSelected(requestParam: Boolean, myList: IntArray?): String? {
        if (myList == null)
            return null
        else {
            var values: String = ""

            if (requestParam) {
                for (i in myList) {
                    when (i) {
                        0 -> values += "planned,"
                        1 -> values += "watching,"
                        2 -> values += "rewatching,"
                        3 -> values += "completed,"
                        4 -> values += "on_hold,"
                        5 -> values += "dropped,"
                    }
                }
            } else {
                for (i in myList) {
                    when (i) {
                        0 -> values += " Запланировано,"
                        1 -> values += " Смотрю,"
                        2 -> values += " Пересматриваю,"
                        3 -> values += " Просмотрено,"
                        4 -> values += " Отложено,"
                        5 -> values += " Брошено,"
                    }
                }
            }

            return values.substring(0, values.length - 1).trim()
        }
    }

    fun getGenresSelected(requestParam: Boolean, genres: IntArray?): String? {
        if (genres == null)
            return null
        else {
            var values: String = ""

            if (requestParam) {
                for (i in genres) {
                    values += "${i.toString()},"
                }
            } else {
                for (i in genres) {
                    when (i) {
                        1 -> values += " Экшен,"
                        2 -> values += " Приключения,"
                        3 -> values += " Машины,"
                        4 -> values += " Комедия,"
                        5 -> values += " Безумие,"
                        6 -> values += " Демоны,"
                        7 -> values += " Детектив,"
                        8 -> values += " Драма,"
                        9 -> values += " Этти,"
                        10 -> values += " Фэнтези,"
                        11 -> values += " Игры,"
                        12 -> values += " Хентай,"
                        13 -> values += " Исторический,"
                        14 -> values += " Ужасы,"
                        15 -> values += " Детское,"
                        16 -> values += " Магия,"
                        17 -> values += " Боевые искусства,"
                        18 -> values += " Меха,"
                        19 -> values += " Музыка,"
                        20 -> values += " Пародия,"
                        21 -> values += " Самураи,"
                        22 -> values += " Романтика,"
                        23 -> values += " Школа,"
                        24 -> values += " Фантастика,"
                        25 -> values += " Сёдзе,"
                        26 -> values += " Сёдзе-ай,"
                        27 -> values += " Сёнен,"
                        28 -> values += " Сёнен-ай,"
                        29 -> values += " Космос,"
                        30 -> values += " Спорт,"
                        31 -> values += " Супер сила,"
                        32 -> values += " Вампиры,"
                        33 -> values += " Яой,"
                        34 -> values += " Юри,"
                        35 -> values += " Гарем,"
                        36 -> values += " Повседневность,"
                        37 -> values += " Сверхъестественное,"
                        38 -> values += " Военное,"
                        39 -> values += " Полиция,"
                        40 -> values += " Психологическое,"
                        41 -> values += " Триллер,"
                        42 -> values += " Сейнен,"
                        43 -> values += " Дзёсей,"
                    }
                }
            }

            return values.substring(0, values.length - 1).trim()
        }
    }

    fun getOrderSelected(requestParam: Boolean, order: Int): String {
        if (requestParam) {
            when(order) {
                0 -> return "random"
                1 -> return "ranked"
                2 -> return "popularity"
                3 -> return "name"
                4 -> return "aired_on"
                5 -> return "status"
            }
        } else {
            when(order) {
                0 -> return "Случайно"
                1 -> return "По рейтингу"
                2 -> return "По популярности"
                3 -> return "По алфавиту"
                4 -> return "По дате выхода"
                5 -> return "По статуса"
            }
        }
        return "popularity"
    }
}