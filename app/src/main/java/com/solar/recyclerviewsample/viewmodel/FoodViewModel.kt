package com.solar.recyclerviewsample.viewmodel

import android.view.View
import androidx.lifecycle.*
import com.solar.recyclerview.ViewModelList
import com.solar.recyclerviewsample.model.food.Food
import com.solar.recyclerviewsample.model.food.FoodFactory
import kotlinx.coroutines.Dispatchers

class FoodViewModel : ViewModel(), ViewModelList<Food> {
    override val list: LiveData<ArrayList<Food>> = MutableLiveData()

    val toastEvent = MutableLiveData<String>()

    private val _foodList: MutableLiveData<Int> = MutableLiveData()

    private val totalList = arrayListOf<Food>()

    val foodList: LiveData<List<Food>> = _foodList.switchMap {
        liveData(Dispatchers.IO) {
            val foods = FoodFactory.getFoodList(5)
            totalList.addAll(foods)
            //emit(totalList.toList())
            emit(foods)
        }
    }

    val foodGridList: LiveData<List<Food>> = liveData(Dispatchers.IO) {
        emit(FoodFactory.getFoodSample())
    }

    fun getMoreFoodList() {

    }

    fun onClick(item: Food) {
        toastEvent.postValue(item.title)
    }

    fun fetchList() {
        _foodList.value = 1
    }
}