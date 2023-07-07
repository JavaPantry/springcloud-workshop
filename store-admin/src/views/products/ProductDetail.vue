<script setup>

import { useRoute } from 'vue-router'
import { storeToRefs } from "pinia";
import { useShopStore } from '@/store/shop'

import ProductDetails from '@/components/product/Details.vue'

const shop = useShopStore();
const { products } = storeToRefs(shop)

const { id } = useRoute().params
console.log('ProductDetail param id = ', id)
console.log('ProductDetail products[id] = ', products.value[id-1])

const product = products.value[id-1]
console.log('ProductDetail product = ', product)

</script>

<template>
				<ProductDetails :product="product" />
</template>


<style scoped>

</style>
