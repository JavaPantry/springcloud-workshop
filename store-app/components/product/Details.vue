<script setup>
const { product } = defineProps(['product'])
</script>

<template>
	<div class="container">
		<div class="mybox">
			<h2>{{ product.title }}</h2>
		</div>
	</div>
	<div class="container">
		<div class="mybox">
			<img :src="`${product.image}`" class="thumb"/>
			<div>
				<h5>stars</h5>
			</div>
		</div>
		<div class="mybox">
			<p>{{ product.description }}</p>
			<p>Price : {{ product.price }}</p>
			<p>Category: {{ product.category }}</p>
			<NuxtLink :to="`/products/${product.id}`" style="text-align: center">
				<button class="btn btn-primary">Add to Cart</button>
			</NuxtLink>
		</div>
	</div>
</template>

<style scoped>

.container {
	display: flex;
	flex-direction: row;
}

.mybox {
	display: flex;
	flex-direction: column;
	border: blue 1px solid;
	margin: 20px;
}

.thumb {
	max-height: 200px;
	max-width: 200px;
	margin: 0 auto;
}

</style>