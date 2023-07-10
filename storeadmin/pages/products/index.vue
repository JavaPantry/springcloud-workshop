<script setup>
import { storeToRefs}   from "pinia";
import { useShopStore } from '@/stores/shop'

const shop = useShopStore();
shop.fetchProducts();

const {products} = storeToRefs(shop)

const hasProducts = computed(() => products.value.length > 0);

</script>

<template>
    <NuxtLayout>
        <div class="container">
            <h2>Products</h2>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>

            <v-container fluid v-if="hasProducts">
                <!-- <div class="row row-cols-1 gap-3"> -->
                    <ProductCard :product="product" v-for="product in products" :key="product.id"/>
                <!-- </div> -->
            </v-container>
            <v-container fluid v-else>
                <h3>Products NOT loaded</h3><br>
            </v-container>
        </div>
    </NuxtLayout>
</template>


<style scoped>
</style>