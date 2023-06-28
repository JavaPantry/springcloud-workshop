<script setup>
const { id } = useRoute().params
const uri = 'https://fakestoreapi.com/products/' + id

//  fetch the products
const { data: product } = await useFetch(uri, { key: id })

if (!product.value) {
	throw createError({ statusCode: 404, statusMessage: 'Product not found' })
}
</script>

<template>
	<NuxtLayout>
			<div><ProductDetails :product="product" /></div>
	</NuxtLayout>
</template>


<style scoped>

</style>