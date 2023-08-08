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
            <v-table density="compact">
                            <thead>
                                <tr>
                                    <th class="text-left">Product Id</th>
                                    <!-- <th class="text-left">Name</th> -->
                                    <!-- <th class="text-left">Description</th> -->
                                    <th class="text-left">Quantity</th>
                                    <!-- <th class="text-left">Price</th> -->
                                    <th class="text-left">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(product, idx) in cart.products" :key="product.productId">
                                    <td>{{ product.productId }}</td>
                                    <td>{{ product.quantity }}</td>
                                    <!-- <td>{{ item.price }}</td> -->
                                    <td>
                                        <v-icon aria-hidden="false" color="red-darken-4" @click.stop="deleteProduct(item)">mdi-trash-can-outline</v-icon>
                                        <v-icon aria-hidden="false" @click.stop="addProductToCart(item)">mdi-cart-arrow-down</v-icon>
                                        <v-icon aria-hidden="false" @click.stop="removeProductFromCart(item)">mdi-cart-arrow-up</v-icon>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Total</td>
                                    <td>{{ total }}</td>
                                </tr>
                            </tbody>
                        </v-table>
          </v-card-text>
          <v-card-actions>
            <v-btn text small @click="closeCart">Close</v-btn>
            <v-spacer/>
            <v-btn color="primary" @click="onSubmit"> <v-icon left>mdi-content-save-outline</v-icon>Make an Order</v-btn>
          </v-card-actions>
        </v-card>
    </v-dialog>
</template>