package com.timmitof.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.timmitof.retrofit.dictionaries.Constants.Companion.API_ID
import com.timmitof.retrofit.dictionaries.Constants.Companion.region
import com.timmitof.retrofit.dictionaries.Constants.Companion.regionNames
import com.timmitof.retrofit.models.ResponseWeather
import com.timmitof.retrofit.models.Weather
import com.timmitof.retrofit.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.get_weather_btn)
        val tvRegion: TextView = findViewById(R.id.tv_region)
        val tvTemp: TextView = findViewById(R.id.tv_temp)
        val spinner: Spinner = findViewById(R.id.spinner_choose_region)
        val image: ImageView = findViewById(R.id.image_weather)

        spinnerChooseRegion(spinner, regionNames)
        spinner.onItemSelectedListener = this

        button.setOnClickListener {
            App.services?.getWeather(latitude = lat, longitude = lon, appid = API_ID)?.enqueue(object: Callback<ResponseWeather>{
                override fun onResponse(
                    call: Call<ResponseWeather>,
                    response: Response<ResponseWeather>
                ) {
                    setImageView(response.body()!!.weather[0].icon, image)
                    tvRegion.text = response.body()?.name
                    tvTemp.text = response.body()?.main?.temp.toString()
                    Log.e("Success", response.body().toString())
                }

                override fun onFailure(call: Call<ResponseWeather>, t: Throwable) {
                    Log.e("Error", t.localizedMessage)
                }
            })
        }
    }

    fun setImageView(image: String, view: ImageView){
        val url = "https://openweatheramp.org/img/wn/"
        Picasso.Builder(this).build().load("$url$image@2x.png").into(view)
    }

    fun spinnerChooseRegion(spinner: Spinner, list: List<String>){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adapter
    }

    override fun onItemSelected(item: AdapterView<*>?, view: View?, position: Int, id: Long) {
        for (i in region){
            if (item?.selectedItem == i.name){
                lat = i.lat
                lon = i.lon
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}