package org.shikimori.koshiki.utils

/**
 * Temp class
 */
class ValueParser {

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







    fun getOrder(order: Int?): String {
        when(order) {
            0 -> return "popularity" // actual
            1 -> return "animeNameEn" // actual
            2 -> return "aired_on" // actual
            3 -> return "status" // actual
            4 -> return "random" // actual
            else -> return "popularity"
        }
    }

    fun getOrderInt(order: String?): Int {
        when(order) {
            "popularity" -> return 0
            "animeNameEn" -> return 1
            "aired_on" -> return 2
            "status" -> return 3
            "random" -> return 4
            else -> return 0
        }
    }

    fun getKind(label: Boolean, kind: Int?): String? {
        if (!label) {
            when(kind) {
                0 -> return "tv"
                1 -> return "movie"
                2 -> return "ova"
                3 -> return "ona"
                4 -> return "special"
                5 -> return "music"
                else -> return null
            }
        } else {
            when (kind) {
                0 -> return "ТВ сериал"
                1 -> return "Фильм"
                2 -> return "ОVA"
                3 -> return "ONA"
                4 -> return "Спешл"
                5 -> return "Клип"
                else -> return "-"
            }
        }
    }

    fun getKindInt(kind: String?): Int {
        when(kind) {
            "tv" -> return 0
            "movie" -> return 1
            "ova" -> return 2
            "ona" -> return 3
            "special" -> return 4
            "music" -> return 5
            else -> return -1
        }
    }



    fun getStatusInt(status: String?): Int {
        when(status) {
            "anons" -> return 0
            "ongoing" -> return 1
            "released" -> return 2
            else -> return -1
        }
    }

    fun getRating(label: Boolean, rating: Int?): String? {
        if (!label) {
            when(rating) {
                0 -> return "g"
                1 -> return "pg"
                2 -> return "pg_13"
                3 -> return "r"
                4 -> return "r_plus"
                5 -> return "rx"
                else -> return null
            }
        } else {
            when(rating) {
                0 -> return "G"
                1 -> return "PG"
                2 -> return "PG-13"
                3 -> return "R-17"
                4 -> return "R+"
                5 -> return "Rx"
                else -> return "-"
            }
        }
    }

    fun getRatingInt(rating: String?): Int {
        when(rating) {
            "g" -> return 0
            "pg" -> return 1
            "pg_13" -> return 2
            "r" -> return 3
            "r_plus" -> return 4
            "rx" -> return 5
            else -> return -1
        }
    }
}