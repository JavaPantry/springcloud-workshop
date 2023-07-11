<script setup lang="ts">
import { storeToRefs } from "pinia";
import { useShopStore } from '@/stores/shop'
// import Shop from "models/Shop";
const shopStore = useShopStore();
shopStore.fetchShops(); // fetch shops from api

let { count, shops, currentShop } = storeToRefs(shopStore) // decompose count and shops from shop store

let selectedStoreId = ref(null); // selected store

const selectedStore = computed(() => shops.value.find(shop => shop.id === selectedStoreId.value));

watch(selectedStore, (newData) => {
    currentShop = newData
     // same currentShop.value = newData
})

</script>
<template>
    <NuxtLayout>
        <v-container fluid>
            <p v-if="currentShop"> currentShop Not empty</p>
            <p v-else> currentShop empty</p>
            <p>Current Shop from Pinia store: {{ currentShop }}
                
            </p>
            <v-select label="Select Store" :items="shops" item-title="name" item-value="id" v-model="selectedStoreId"></v-select>
            <div v-if="selectedStore">
                <p>
                    Selected Store: {{ selectedStoreId }} - {{ selectedStore.name }} - {{ selectedStore.address }}
                </p>
                <p>
                {{ selectedStore.description }}
                </p>
                <p>Current Shop from Pinia store: {{ currentShop }}</p>
                <v-img :src="currentShop.image" width="200" height="200"></v-img>
            </div>
            <div v-else>
                Please select a store
            </div>
            <v-layout column  v-if="selectedStore">
                <v-flex xs12>
                <v-layout align-center justify-center row wrap fill-height class='menu-card-container'>
                <!-- <img src="~/assets/reports.png" alt="logo" class="logo"> -->
                    <MenuCard section-img="./assets/orders.png"
                                section-description="Select Project"
                                link="/dummy/dummy"
                                class="menu-card-gap"
                                key="selectProjectMenu"/>
                    <MenuCard section-img="./assets/reports.png"
                                section-description="Reports"
                                link="/dummy/dummy"
                                class="menu-card-gap"
                                key="selectProjectMenu1"/>
                    <MenuCard section-img="./assets/inventory.png"
                                section-description="Inventory"
                                link="/dummy/dummy"
                                class="menu-card-gap"
                                key="ServiceSuppliesCall0"/>
                    <MenuCard section-img="./assets/status.png"
                                section-description="Status"
                                link="/dummy/dummy"
                                class="menu-card-gap"
                                key="ServiceSuppliesCall1"/>
                </v-layout>
                </v-flex>
            </v-layout>
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