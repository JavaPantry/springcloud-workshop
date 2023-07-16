<script setup>
import { storeToRefs}   from "pinia";
import { useShopStore } from '@/stores/shop'

const shop = useShopStore();
shop.fetchInventory(shop.currentShop.id) // fetch inventory for current shop

const {inventory} = storeToRefs(shop)

const hasInventory = computed(() => inventory.value.length > 0);

</script>

<template>
    <NuxtLayout>
        <div class="container">
            <h2>Products in store {{ shop.currentShop.name }} id: {{ shop.currentShop.id }}</h2>
            <p> {{ shop.currentShop.description }}</p>

            <v-container fluid v-if="hasInventory">
                    <!-- <ProductCard :product="product" v-for="product in products" :key="product.id"/> -->
                    <div>
                        <span>product_id | </span>
                        <span>quantity | </span>
                        <span>price</span>
                    </div>
                    <div v-for="item in inventory" :key="item.id">
                        <span>{{item.product_id}} | </span>
                        <span>{{item.quantity}} | </span>
                        <span>{{item.price}}</span>
                        <hr>
                    </div>
            </v-container>
            <v-container fluid v-else>
                <h3>Products NOT loaded</h3><br>
            </v-container>
        </div>
    </NuxtLayout>
</template>


<style scoped>
</style>