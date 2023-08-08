<script setup lang="ts">
import { ref, computed } from 'vue'
import ShoppingCart from "~/models/ShoppingCart";

// 1st: const { cart: shoppingCart } = defineProps(['cart'])
// 2nd: - more readable and typescript friendly
const { cart: shoppingCart } = defineProps<{ cart: ShoppingCart }>();

const total = computed(() => {
   return shoppingCart.products.reduce((total, product) => total + product.quantity, 0)
}) 

const openCart = () => {
    // Implement logic to open the cart or navigate to the cart page
    console.log('openCart action')
};
</script>

<template>
    <div class="shop-cart" @click="openCart">
        <span class="cart-icon">
            <i class="mdi mdi-cart"></i>
        </span>
        <span class="cart-count">{{ total }}</span>
    </div>
</template>

<style scoped>
.shop-cart {
    display: flex;
    align-items: center;
    cursor: pointer;
}

.cart-icon {
    font-size: 1.5rem;
    margin-right: 0.5rem;
}

.cart-count {
    background-color: #f44336;
    color: white;
    border-radius: 50%;
    padding: 0.25rem 0.5rem;
    font-size: 0.8rem;
}
</style>