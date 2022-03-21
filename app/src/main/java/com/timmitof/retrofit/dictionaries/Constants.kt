package com.timmitof.retrofit.dictionaries

import com.timmitof.retrofit.models.Regions

class Constants {
    companion object{
        const val BASE_URL: String = "https://api.openweathermap.org/data/2.5/"
        const val API_ID: String = "71848816e8551970c0cda9bdadef9d9b"
        val region = listOf(
            Regions(0, "Bishkek", 42.8768537, 74.521821),
            Regions(1, "Batken", 40.0575553, 70.788302),
            Regions(2, "Jalal-Abad", 40.9379146, 72.9453559),
            Regions(3, "Issyk-Kul", 42.4607256, 76.6950042),
            Regions(4, "Naryn", 41.4255713, 75.9111049),
            Regions(5, "Osh", 40.5201178, 72.7729773),
            Regions(6, "Talas", 42.5299875, 72.1879001)
        )
        val regionNames = listOf(
            "Bishkek",
            "Batken",
            "Jalal-Abad",
            "Issyk-Kul",
            "Naryn",
            "Osh",
            "Talas"
        )
    }
}