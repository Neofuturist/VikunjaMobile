package com.example.room.ui.screen

import androidx.lifecycle.ViewModel
import data.model.MasterUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import room.entity.ItemEntity
import room.repository.ItemRepository
import shared.Dispatcher

class ItemsScreenViewModel(
    private val itemRepository: ItemRepository,
    private val dispatcher: Dispatcher,
    private val mainScope: CoroutineScope,
) : ViewModel() {
    private val _items: MutableStateFlow<MasterUI<List<ItemEntity>>> = MutableStateFlow(MasterUI.Init)
    val items: StateFlow<MasterUI<List<ItemEntity>>> = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        mainScope.launch(dispatcher.io) {
            itemRepository.getItems().collect { itemsData ->
                _items.value = itemsData.toMasterUI()
            }
        }
    }
}