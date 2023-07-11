<script setup>
import { storeToRefs } from "pinia";
import { useShopStore } from '@/stores/shop'

const shopStore = useShopStore();
shopStore.fetchShops(); // fetch shops from api

const { count, shops} = storeToRefs(shopStore) // decompose count and shops from shop store

</script>
<template>
    <NuxtLayout>
        <div>
            <h2>Home</h2>
            <h5>Counter: {{count}}</h5>
            <ul>
                <li v-for="shop in shops" :key="shop.id">
                    {{shop.name}}
                </li>
            </ul>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>
        </div>
        <v-container fluid>
            <v-layout column>
                <v-flex xs12>
                <v-layout align-center justify-center row wrap fill-height class='menu-card-container'>
                <!-- <img src="~/assets/reports.png" alt="logo" class="logo"> -->
                    <MenuCard section-img="./assets/orders.png"
                                section-description="Select Project"
                                link="/selectProject"
                                class="menu-card-gap"
                                key="selectProjectMenu"/>
                    <MenuCard section-img="./assets/reports.png"
                                section-description="Reports"
                                link="/selectProject"
                                class="menu-card-gap"
                                key="selectProjectMenu1"/>
                    <MenuCard section-img="./assets/inventory.png"
                                section-description="Inventory"
                                link="/createProject"
                                class="menu-card-gap"
                                key="ServiceSuppliesCall0"/>
                    <MenuCard section-img="./assets/status.png"
                                section-description="Status"
                                link="/createProject"
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