<script setup lang="ts">
import { ref, computed } from 'vue'
import ShoppingCart from "~/models/ShoppingCart";

// 1st: const { cart: shoppingCart } = defineProps(['cart'])
// 2nd: - more readable and typescript friendly
const { cart: shoppingCart } = defineProps<{ cart: ShoppingCart }>();

const total = computed(() => {
   return shoppingCart.products.reduce((total, product) => total + product.quantity, 0)
}) 

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
                Total: {{ total }}
        </v-card-text>
    </v-card>

</template>