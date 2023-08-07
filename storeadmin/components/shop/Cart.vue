<script setup lang="ts">
import { ref, computed } from 'vue'
// import ShoppingCart from '@/models/ShoppingCart'

const { cart: ShoppingCart } = defineProps(['cart'])

/* const total = computed(() => {
    cart.products.reduce((total, product) => total + product.quantity, 0)
}) */

/* function computedTotal(cart: ShoppingCart) {
    let total = 0;
    console.log('computedTotal from')
    // cart.products.forEach(product => {
    //     total += product.quantity;
    // });
    return total;
} */
</script>

<template>

    <v-card class="mx-auto">
        <v-card-title class="text-h5">Shopping Cart</v-card-title>
        <v-card-subtitle>Store: {{ cart.storeId }}</v-card-subtitle>
        <v-card-text>
                <v-list-item v-for="product in cart.products" :key="product.productId">
                    {{ product.productId }} - {{ product.quantity }}
                </v-list-item>
                <hr>
                Total: 
        </v-card-text>
    </v-card>

</template>