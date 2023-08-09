<script setup lang="ts">
import { storeToRefs } from "pinia";
import { mapWritableState } from 'pinia'
import { useShopStore } from '@/stores/shop'
import { onMounted } from 'vue'
import Shop from "models/Shop";
const shopStore = useShopStore();

// let { currentShop } = mapWritableState(shopStore, ['currentShop'])

shopStore.fetchShops(); // fetch shops from api



let { shops, currentShop } = storeToRefs(shopStore) // decompose and shops from shop store

let selectedStoreId = ref(); // selected store

const selectedStore = computed(() => shops.value.find(shop => shop.id === selectedStoreId.value));

watch(selectedStoreId, (newStoreIndex) => {
    console.log('selectedStoreId was ', selectedStoreId.value)
    console.log('selectedStoreId changed to ', newStoreIndex)
    let  shop:Shop = shops.value.find(shop => shop.id === newStoreIndex) as Shop 
    console.log('shop is ', shop)
    shopStore.setCurrentShop(shop)

}),

// add on mount hook to set selected store in v-select component
onMounted(() => {
    if (currentShop.value) {
        selectedStoreId.value = currentShop.value.id
    }
})

</script>
<template>
    <NuxtLayout>
        <v-container fluid>
            <p v-if="currentShop"> currentShop {{ currentShop.name }}</p>
            <p v-else> Shop not selected</p>
                
            
            <v-select label="Select Store" :items="shops" item-title="name" item-value="id" v-model="selectedStoreId"></v-select>

            <div v-if="selectedStore">
                <v-container fluid>
                    <v-row align-center justify-center>
                    <v-img :src="currentShop.image" width="800" height="200"></v-img>
                    </v-row>
                </v-container>
                <v-layout column>
                    <v-layout align-center justify-center row wrap fill-height class='menu-card-container'>
                        <!-- <MenuCard section-img="./assets/orders.png"
                                    section-description="Store Inventory"
                                    link="/inventory"
                                    class="menu-card-gap"
                                    key="selectProjectMenu"/> -->
                        <MenuCard section-img="./assets/reports.png"
                                    section-description="Reports"
                                    link="/dummy/dummy"
                                    class="menu-card-gap"
                                    key="selectProjectMenu1"/>
                        <MenuCard section-img="./assets/inventory.png"
                                    section-description="Store Inventory"
                                    link="/inventory"
                                    class="menu-card-gap"
                                    key="ServiceSuppliesCall0"/>
                        <MenuCard section-img="./assets/status.png"
                                    section-description="Status"
                                    link="/dummy/dummy"
                                    class="menu-card-gap"
                                    key="ServiceSuppliesCall1"/>
                    </v-layout>
                </v-layout>
            </div>

        </v-container>
    </NuxtLayout>
</template>

<style scoped>
h2 {
    margin-bottom: 20px;
    font-size: 36px;
}
p {
    margin: 20px 0;
}

.menu-card-gap{
  margin: 10px;
}
</style>