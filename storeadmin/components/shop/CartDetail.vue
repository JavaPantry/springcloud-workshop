<script setup lang="ts">

import { ref, computed } from 'vue'
import ShoppingCart from "~/models/ShoppingCart";

// 1st: const { cart: shoppingCart } = defineProps(['cart'])
// 2nd: - more readable and typescript friendly
// within template html, use cart.products instead of shoppingCart.products
const { cart: shoppingCart } = defineProps<{ cart: ShoppingCart }>();

const total = computed(() => {
   return shoppingCart.products.reduce((total, product) => total + product.quantity, 0)
})

const dialog = ref(false)

const openCart = () => {
    console.log('openCart action', shoppingCart)
    dialog.value = true;
};

defineExpose({ openCart })

const closeCart = () => {
    dialog.value = false;
};

const onSubmit = () => {
    // make an order
    console.log('onSubmit action')
    // clear shopping cart
    shoppingCart.products = [];
    dialog.value = false;
};

</script>

<template>
    <v-dialog v-model="dialog" activator="parent" width="auto">
        <v-card class="elevation-12">
          <v-toolbar dark color="primary">
            <v-toolbar-title><v-icon left>mdi-cart</v-icon>Shopping Cart</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-row v-for="(product, idx) in cart.products">
                <v-col cols="12" sm="4">
                    {{ product.productId }}
                </v-col>
                <v-col cols="12" sm="4">
                    {{ product.quantity }}
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" sm="4">
                    Total:
                </v-col>
                <v-col cols="12" sm="4">
                    {{ total }}
                </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-btn text small @click="closeCart">Close</v-btn>
            <v-spacer/>
            <v-btn color="primary" @click="onSubmit"> <v-icon left>mdi-content-save-outline</v-icon>Make an Order</v-btn>
          </v-card-actions>
        </v-card>
    </v-dialog>
</template>